package org.example.recap1.Repo;

import org.example.recap1.Domain.Table;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableRepo {
    private final String url = "jdbc:postgresql://localhost:5432/recap1";
    private final String username = "postgres";
    private final String password = "noris2580";

    public List<Table> findAll() {
        List<Table> tables = new ArrayList<>();
        String query = "SELECT id FROM mese";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tables.add(parseTable(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public Table findOne(int id) {
        Table item = null;
        String query = "SELECT * FROM mese WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);  // Set the ID in the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a result is found, parse it into a MenuItem object
            if (resultSet.next()) {
                item = parseTable(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;  // Return the found MenuItem or null if not found
    }



    private Table parseTable(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        return new Table(id);
    }
}
