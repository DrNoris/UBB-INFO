package ubb.scs.map.service;

import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.domain.Utilizator;
import ubb.scs.map.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Service {
    private final Repository<Tuple<Long, Long>, Prietenie> prieteniRepo;
    private final Repository<Long, Utilizator> utilizatoriRepo;
    private void loadFriendships() {
        prieteniRepo.findAll().forEach(prietenie -> {
            Utilizator u1 = utilizatoriRepo.findOne(prietenie.getId().getLeft()).orElse(null);
            Utilizator u2 = utilizatoriRepo.findOne(prietenie.getId().getRight()).orElse(null);

            if (u1 != null && u2 != null) {
                u1.addFriend(u2);
                u2.addFriend(u1);
            }
        });
    }

    public Service(Repository<Tuple<Long, Long>, Prietenie> prieteniRepo,
                   Repository<Long, Utilizator> utilizatoriRepo) {
        this.prieteniRepo = prieteniRepo;
        this.utilizatoriRepo = utilizatoriRepo;

        loadFriendships();
    }

    public Optional<Utilizator> addUtilizator(Long id, String firstName, String lastName){
        Utilizator u = new Utilizator(firstName, lastName);
        u.setId(id);
        return utilizatoriRepo.save(u);
    }

    public Optional<Utilizator> deleteUtilizator(Long id) {
        Utilizator u = utilizatoriRepo.findOne(id).orElse(null);

        if (u == null) {
            return Optional.empty();
        }

        List<Utilizator> prieteni = u.getFrinds();
        prieteni.forEach(prieten -> {
            prieten.deleteFriend(u);
            prieteniRepo.delete(new Tuple<>(prieten.getId(), u.getId()));
            prieteniRepo.delete(new Tuple<>(u.getId(), prieten.getId()));
        });

        return utilizatoriRepo.delete(id);
    }


    public Optional<Prietenie> addPrietenie(Long id1, Long id2){
        Prietenie pr = new Prietenie();
        Utilizator u1 = utilizatoriRepo.findOne(id1).orElseThrow(IllegalArgumentException::new);
        Utilizator u2 = utilizatoriRepo.findOne(id2).orElseThrow(IllegalArgumentException::new);

//        if (u1 == null || u2 == null) {
//            throw new IllegalArgumentException("One or both users do not exist.");
//        }

        Prietenie existingFriendship = prieteniRepo.findOne(new Tuple<>(id1, id2)).orElse(null);
        if (existingFriendship != null) {
            throw new IllegalStateException("Friendship already exists between these users.");
        }

        existingFriendship = prieteniRepo.findOne(new Tuple<>(id2, id1)).orElse(null);
        if (existingFriendship != null) {
            throw new IllegalStateException("Friendship already exists between these users.");
        }

        pr.setDate(LocalDateTime.now());
        pr.setId(new Tuple<>(id1, id2));

        u1.addFriend(u2);
        u2.addFriend(u1);

        return prieteniRepo.save(pr);
    }

    public Optional<Prietenie> deletePrietenie(Long id1, Long id2){
        Utilizator u1 = utilizatoriRepo.findOne(id1).orElse(null);
        Utilizator u2 = utilizatoriRepo.findOne(id2).orElse(null);

        if (u1 == null || u2 == null) {
            throw new IllegalArgumentException("One or both users do not exist.");
        }

        Tuple<Long, Long> id = new Tuple<>(id1, id2);
        Prietenie prietenie = prieteniRepo.findOne(id).orElse(null);
        if (prietenie == null) {
            id.setLeft(id2);
            id.setRight(id1);
            prietenie = prieteniRepo.findOne(id).orElse(null);
            if (prietenie == null)
                throw new IllegalStateException("Friendship does not exists between these users.");
        }

        u1.deleteFriend(u2);
        u2.deleteFriend(u1);

        return prieteniRepo.delete(id);
    }

    public int getNrNetworks(){
        Graph graph = new Graph();
        return graph.countConnectedComponents(prieteniRepo.findAll());
    }

    public Map.Entry<List<Long>, Integer> longestNetwork() {
        Iterable<Prietenie> edges = prieteniRepo.findAll();
        Graph graph = new Graph();
        return graph.longestPathInNetwork(edges);
    }
}
