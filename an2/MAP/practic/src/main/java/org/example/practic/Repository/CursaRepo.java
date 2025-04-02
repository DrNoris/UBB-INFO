package org.example.practic.Repository;

import org.example.practic.Domain.Cursa;
import org.example.practic.Domain.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursaRepo {
    private final String url = "jdbc:postgresql://localhost:5432/practic";
    private final String username = "postgres";
    private final String password = "noris2580";

    public List<Cursa> findAllFor(Driver driver) {
        List<Cursa> curse = new ArrayList<>();
        String query = "SELECT id, driverid, status, startdate, pickupaddress, destinationaddress, clientname " +
                "FROM cursa WHERE driverid = ? and status like 'IN_PROGRESS'";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, driver.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                curse.add(parseCursa(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curse;
    }

    private Cursa parseCursa(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int driverId = resultSet.getInt("driverid");
        String status = resultSet.getString("status");
        Timestamp startDate = resultSet.getTimestamp("startdate");
        String pickupAddress = resultSet.getString("pickupaddress");
        String destinationAddress = resultSet.getString("destinationaddress");
        String clientName = resultSet.getString("clientname");

        return new Cursa(id, driverId, status, startDate, null, pickupAddress, destinationAddress, clientName);
    }

    public void update(Cursa cursa, String status) {
        String query;

        if (status.equals("FINISHED")) {
            query = "UPDATE cursa SET status = ?, enddate = NOW() WHERE id = ?";
        }
        else if (status.equals("IN_PROGRESS")) {
            query = "UPDATE cursa SET status = ?, driverid = ? WHERE id = ?";
        } else {
            throw new IllegalArgumentException("Unsupported status: " + status);
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (status.equals("FINISHED")) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, cursa.getId());
            } else if (status.equals("IN_PROGRESS")) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, cursa.getDriverId());
                preparedStatement.setInt(3, cursa.getId());
            }

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cursa ID " + cursa.getId() + " updated to status: " + status);
                if (status.equals("FINISHED")) {
                    System.out.println("End date set to: " + java.time.LocalDate.now());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int add(String startingAddress, String destinationAddress, String clientName) {
        String query = "INSERT INTO cursa (driverid, status, startdate, pickupaddress, destinationaddress, clientname) " +
                "VALUES (NULL, 'PENDING', NOW(), ?, ?, ?) RETURNING id";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, startingAddress);
            preparedStatement.setString(2, destinationAddress);
            preparedStatement.setString(3, clientName);

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    System.out.println("New Cursa added with ID: " + generatedId);
                    return generatedId;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Cursa findById(int id) {
        String query = "SELECT id, driverid, status, startdate, enddate, pickupaddress, destinationaddress, clientname " +
                "FROM cursa WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return parseCursa(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
