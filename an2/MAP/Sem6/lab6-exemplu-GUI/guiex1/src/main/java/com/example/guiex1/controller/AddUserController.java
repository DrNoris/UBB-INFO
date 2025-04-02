package com.example.guiex1.controller;

import com.example.guiex1.domain.Utilizator;
import com.example.guiex1.services.UtilizatorService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {
    @FXML
    private Button handleAddButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    private UtilizatorService service;

    public void setUtilizatorService(UtilizatorService service) {
        this.service = service;
    }

    public void handleAddButton() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        service.addUtilizator(new Utilizator(firstName, lastName));

        Stage stage = (Stage) handleAddButton.getScene().getWindow();
        stage.close();
    }
}
