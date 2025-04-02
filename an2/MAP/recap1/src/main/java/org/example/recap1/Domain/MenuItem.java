package org.example.recap1.Domain;

public class MenuItem {
    private int id;
    private String category;
    private String item;
    private float price;
    private String currency;

    // Constructor
    public MenuItem(int id, String category, String item, float price, String currency) {
        this.id = id;
        this.category = category;
        this.item = item;
        this.price = price;
        this.currency = currency;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
