package org.example.recap1.Service;

import org.example.recap1.Domain.Table;
import org.example.recap1.Repo.TableRepo;

import java.util.List;

public class TableService {
    private final TableRepo tableRepository;

    public TableService(TableRepo tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }
}
