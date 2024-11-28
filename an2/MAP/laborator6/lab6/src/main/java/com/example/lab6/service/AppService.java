package com.example.lab6.service;

import com.example.lab6.domain.FriendRequest;
import com.example.lab6.domain.Prietenie;
import com.example.lab6.domain.Tuple;
import com.example.lab6.domain.Utilizator;
import com.example.lab6.repository.Repository;
import com.example.lab6.repository.database.PrietenieDatabaseRepository;
import com.example.lab6.repository.database.RequestDatabaseRepository;
import com.example.lab6.repository.database.UtilizatorDatabaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppService extends Service{
    private final UtilizatorDatabaseRepository utilizatoriRepo;
    private final PrietenieDatabaseRepository prietenieRepo;
    private final RequestDatabaseRepository requestRepo;

    public AppService (UtilizatorDatabaseRepository utilizatoriRepo,
                       PrietenieDatabaseRepository prietenieRepo,
                       RequestDatabaseRepository requestRepo) {
        super(prietenieRepo, utilizatoriRepo, requestRepo);
        this.utilizatoriRepo = utilizatoriRepo;
        this.prietenieRepo = prietenieRepo;
        this.requestRepo = requestRepo;
    }

    public Optional<List<Utilizator>> findAllName(String username){
        Optional<List<Utilizator>> users = utilizatoriRepo.findManyName(username);

        if (users.isEmpty())
            return null;

        return users;
    }

    public List<Utilizator> getAllFriendships(Utilizator user) {
        List<Utilizator> friends = new ArrayList<>();

        prietenieRepo.findAllWith(user.getId()).forEach(friendId -> {
            Utilizator friend = utilizatoriRepo.findOne(friendId).orElse(null);
            if (friend != null) {
                friends.add(friend);
            }
        });

        return friends;
    }

    public void sendRequest(Long sender_id, Long receiver_id){
        FriendRequest request = new FriendRequest(sender_id, receiver_id, "PENDING");
        requestRepo.save(request);
    }

    public Optional<List<Utilizator>> getAllRequests(Utilizator currentUser) {
        List<Utilizator> requests = new ArrayList<>();

        requestRepo.findAllWith(currentUser.getId()).forEach(sender_id -> {
            Utilizator sender = utilizatoriRepo.findOne(sender_id).orElse(null);
            if (sender != null) {
                requests.add(sender);
            }
        });

        return requests.isEmpty() ? Optional.empty() : Optional.of(requests);
    }

    public void acceptPrietenie(Long receiver, Long sender) {
        try {
            addPrietenie(sender, receiver);
            requestRepo.modify(sender, receiver, 'Y');
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
}
