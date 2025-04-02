package org.example.recap1.Repo;

import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Domain.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MenuItemRepo {
    private final String url = "jdbc:postgresql://localhost:5432/recap1";
    private final String username = "postgres";
    private final String password = "noris2580";

    public Hashtable<String, List<MenuItem>> findAll() {
        Hashtable<String, List<MenuItem>> menu = new Hashtable<>();
        String query = "SELECT * FROM MenuItem";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                MenuItem item = parseMenuItem(resultSet);

                if (!menu.containsKey(item.getCategory())) {
                    menu.put(item.getCategory(), new ArrayList<>());
                }

                menu.get(item.getCategory()).add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    public MenuItem findOne(int id) {
        MenuItem item = null;
        String query = "SELECT * FROM MenuItem WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);  // Set the ID in the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a result is found, parse it into a MenuItem object
            if (resultSet.next()) {
                item = parseMenuItem(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;  // Return the found MenuItem or null if not found
    }


    private MenuItem parseMenuItem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String category = resultSet.getString("category");
        String item = resultSet.getString("item");
        float price = resultSet.getFloat("price");
        String currency = resultSet.getString("currency");

        return new MenuItem(id, category, item, price, currency);
    }
}
