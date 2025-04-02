package org.example.recap1.Domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int id;
    private Table table;  // This references the Table object
    private List<MenuItem> menuItems;  // List of MenuItem objects
    private LocalDateTime date;

    // Constructor
    public Order(int id, Table table, List<MenuItem> menuItems, LocalDateTime date) {
        this.id = id;
        this.table = table;
        this.menuItems = menuItems;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
