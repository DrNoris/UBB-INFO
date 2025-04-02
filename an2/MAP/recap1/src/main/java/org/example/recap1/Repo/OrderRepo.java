package org.example.recap1.Repo;

import org.example.recap1.Domain.Order;
import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Domain.Table;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    private final String url = "jdbc:postgresql://localhost:5432/recap1";
    private final String username = "postgres";
    private final String password = "noris2580";
    private MenuItemRepo menuItemRepo;
    private TableRepo tableRepo;


    public OrderRepo(MenuItemRepo menuItemRepo, TableRepo tableRepo){
        this.menuItemRepo = menuItemRepo;
        this.tableRepo = tableRepo;
    }

    public void createOrder(int table, List<MenuItem> items) {
        String query = "INSERT INTO orders (table_id) VALUES (?) RETURNING id";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, table);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int orderId = resultSet.getInt("id");

                addOrderItems(orderId, items);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addOrderItems(int orderId, List<MenuItem> menuItems) {
        String query = "INSERT INTO order_items (order_id, menu_item_id) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (MenuItem item : menuItems) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, item.getId());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders order by date asc";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tableId = resultSet.getInt("table_id");
                Timestamp timestamp = resultSet.getTimestamp("date");
                LocalDateTime date = timestamp.toLocalDateTime();

                Table table = getTableById(tableId);

                List<MenuItem> menuItems = getMenuItemsForOrder(id);

                orders.add(new Order(id, table, menuItems, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private List<MenuItem> getMenuItemsForOrder(int orderId) {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT menu_item_id FROM order_items WHERE order_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int menuItemId = resultSet.getInt("menu_item_id");
                // Assuming a method to get MenuItem by ID
                MenuItem menuItem = getMenuItemById(menuItemId);
                menuItems.add(menuItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    private Table getTableById(int tableId) {
        return tableRepo.findOne(tableId);
    }

    private MenuItem getMenuItemById(int menuItemId) {
        return menuItemRepo.findOne(menuItemId);
    }
}
