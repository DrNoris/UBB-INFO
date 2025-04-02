package org.example.practic.Service;

import org.example.practic.Domain.Cursa;
import org.example.practic.Domain.Driver;
import org.example.practic.Repository.CursaRepo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class CursaService extends Observable{
    private final CursaRepo cursaRepo;

    public CursaService(CursaRepo cursaRepo) {
        this.cursaRepo = cursaRepo;
    }

    public List<Cursa> getAllCursasForDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        return cursaRepo.findAllFor(driver);
    }

    public Cursa findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Cursa ID");
        }

        Cursa cursa = cursaRepo.findById(id);
        if (cursa == null) {
            throw new RuntimeException("Cursa with ID " + id + " not found");
        }

        return cursa;
    }

    public void updateCursa(Cursa cursa, String status) {
        cursaRepo.update(cursa, status);
    }

    public void createCursa(String startingAddress, String destinationAddress, String clientName) {
        if (startingAddress.isEmpty() || destinationAddress.isEmpty() || clientName.isEmpty()) {
            throw new RuntimeException("Fields must not me empty");
        }

        int id = cursaRepo.add(startingAddress, destinationAddress, clientName);

        notifyAboutNewOrder(id);
    }

    public boolean hasOrders(Driver driver) {
        return !cursaRepo.findAllFor(driver).isEmpty();
    }

    public Timestamp getLastFinishedOrderDate(Driver driver) {
            Optional<Cursa> lastFinishedOrder = getAllCursasForDriver(driver).stream()
                    .filter(cursa -> "FINISHED".equals(cursa.getStatus()))
                    .max((c1, c2) -> c1.getEndDate().compareTo(c2.getEndDate()));

            return lastFinishedOrder.map(Cursa::getEndDate).orElse(null);
    }
}
