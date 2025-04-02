package com.example.seminar14;

import com.example.seminar14.Domain.Angajat;
import com.example.seminar14.Domain.Senioritate;
import com.example.seminar14.Repository.RepositoryAngajatiDB;
import com.example.seminar14.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;

public class HelloController {
    @FXML
    public TableColumn numeColumn;
    @FXML
    public TableColumn venitOraColumn;
    @FXML
    public TableColumn senioritateColumn;
    @FXML
    public TableView angajatiTable;

    private Service service;
    
    public HelloController(){
        String dbUser = "postgres";
        String dbPassword = "noris2580";
        String dbUrl = "jdbc:postgresql://localhost:5432/angajati";

        RepositoryAngajatiDB repositoryAngajatiDB = new RepositoryAngajatiDB(dbUser, dbPassword, dbUrl);
        this.service = new Service(repositoryAngajatiDB);
    }
    
    @FXML
    private void initialize(){
        setAngajati();
    }

    private void setAngajati() {
        List<Angajat> senioritateList = service.groupAngajatiBySenioritateAndOrderByVenitPeOra();

        angajatiTable.setItems(FXCollections.observableList(senioritateList));
    }
}