package com.example.guiex1.controller;

import com.example.guiex1.domain.Utilizator;
import com.example.guiex1.domain.ValidationException;
import com.example.guiex1.services.UtilizatorService;
import com.example.guiex1.utils.events.UtilizatorEntityChangeEvent;
import com.example.guiex1.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UtilizatorController implements Observer<UtilizatorEntityChangeEvent> {
    UtilizatorService service;
    ObservableList<Utilizator> model = FXCollections.observableArrayList();

    @FXML
    TableView<Utilizator> tableView;
    @FXML
    TableColumn<Utilizator,String> tableColumnFirstName;
    @FXML
    TableColumn<Utilizator,String> tableColumnLastName;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;


    public void setUtilizatorService(UtilizatorService service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        // Set up columns
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Set the items in the TableView
        tableView.setItems(model);
    }


    private void initModel() {
        // Convert Iterable to List and add to the ObservableList
        List<Utilizator> utilizatori = StreamSupport.stream(service.getAll().spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(utilizatori);
    }


    @Override
    public void update(UtilizatorEntityChangeEvent utilizatorEntityChangeEvent) {
        //se poate mai eficient in functe de enum
        List<Utilizator> utilizatori = StreamSupport.stream(service.getAll().spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(utilizatori);
    }

    public void handleDeleteUtilizator(ActionEvent actionEvent) {
        Utilizator user=(Utilizator) tableView.getSelectionModel().getSelectedItem();

        if (user!=null) {
            Utilizator deleted = service.deleteUtilizator(user.getId());
        }
    }

    public void handleAddMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/guiex1/views/addUtilizatorView.fxml"));
            Parent root = fxmlLoader.load();

            AddUserController addUserController = fxmlLoader.getController();

            addUserController.setUtilizatorService(service);

            Stage stage = new Stage();
            stage.setTitle("Add New User");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void handleText(ActionEvent actionEvent) {
        Utilizator user=(Utilizator) tableView.getSelectionModel().getSelectedItem();
        if (user!=null) {
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
        }
    }
}
