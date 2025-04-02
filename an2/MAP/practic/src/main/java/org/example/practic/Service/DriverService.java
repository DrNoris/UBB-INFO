package org.example.practic.Service;


import org.example.practic.Domain.Driver;
import org.example.practic.Repository.DriverRepo;

import java.util.List;

public class DriverService {
    private final DriverRepo driverRepo;

    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public List<Driver> getAllDrivers() {
        return driverRepo.findAll();
    }
}
