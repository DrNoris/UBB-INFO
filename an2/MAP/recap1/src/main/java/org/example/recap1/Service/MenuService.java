package org.example.recap1.Service;

import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Domain.Table;
import org.example.recap1.Repo.MenuItemRepo;
import org.example.recap1.Repo.TableRepo;

import java.util.Hashtable;
import java.util.List;

public class MenuService {
    private final MenuItemRepo menuItemRepo;

    public MenuService(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    public Hashtable<String, List<MenuItem>> getAllItems() {
        return menuItemRepo.findAll();
    }
}
