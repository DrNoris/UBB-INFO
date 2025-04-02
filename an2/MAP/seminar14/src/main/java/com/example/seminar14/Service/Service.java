package com.example.seminar14.Service;

import com.example.seminar14.Domain.Angajat;
import com.example.seminar14.Domain.Senioritate;
import com.example.seminar14.Repository.RepositoryAngajatiDB;

import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private RepositoryAngajatiDB repository;

    public Service(RepositoryAngajatiDB repository) {
        this.repository = repository;
    }


    public List<Angajat> groupAngajatiBySenioritateAndOrderByVenitPeOra() {
        Optional<List<Angajat>> optionalAngajati = repository.findAll();
        if (optionalAngajati.isPresent()) {
            List<Angajat> angajati = optionalAngajati.get();

            return angajati.stream()
                    .collect(Collectors.groupingBy(
                            Angajat::getSenioritate,
                            TreeMap::new,             // Ensure Senioritate is sorted
                            Collectors.toList()       // Collect each group into a list
                    ))
                    .values()                    // Get all groups (List<Angajat>) from the map
                    .stream()                    // Flatten them into a single stream
                    .flatMap(List::stream)       // Flatten the nested lists into one stream
                    .sorted(Comparator.comparingDouble(Angajat::getVenitPeOra).reversed()) // Sort the final list by venitPeOra
                    .collect(Collectors.toList());  // Collect back into a single list
        } else {
            return Collections.emptyList();  // Return an empty list if no Angajati are found
        }
    }
}
