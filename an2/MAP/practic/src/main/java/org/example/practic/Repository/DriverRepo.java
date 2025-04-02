package org.example.practic.Repository;

import org.example.practic.Domain.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepo {
    private final String url = "jdbc:postgresql://localhost:5432/practic";
    private final String username = "postgres";  // Replace with your database username
    private final String password = "noris2580";  // Replace with your database password

    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT id, name FROM driver";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                drivers.add(parseDriver(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    public Driver findOne(int id) {
        Driver driver = null;
        String query = "SELECT id, name FROM driver WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                driver = parseDriver(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private Driver parseDriver(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Driver(id, name);
    }
}
