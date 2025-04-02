package com.example.seminar14.Repository;

import com.example.seminar14.Domain.Angajat;
import com.example.seminar14.Domain.Senioritate;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class RepositoryAngajatiDB {
    public String user;
    public String password;
    public String url;

    public RepositoryAngajatiDB(String user, String password, String url){
        this.user = user;
        this.url = url;
        this.password = password;
    }

    private Angajat createAngajatFromUserSet(ResultSet resultSet){
        try {
            String id = resultSet.getString("angajatId");
            String nume = resultSet.getString("nume");
            double venitOra = resultSet.getDouble("venitPeOra");
            Senioritate senioritate = Senioritate.valueOf(resultSet.getString("senioritate"));

            return new Angajat(id, nume, venitOra, senioritate);
        } catch (SQLException e){
            return null;
        }
    }

    public Optional<List<Angajat>> findAll() {
        List<Angajat> angajati = new ArrayList<>();
        String query = "SELECT * FROM Angajat";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Angajat angajat = createAngajatFromUserSet(resultSet);
                    angajati.add(angajat);  // Add each user to the list
                }
            }

            return angajati.isEmpty()? Optional.of(null) : Optional.of(angajati);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
