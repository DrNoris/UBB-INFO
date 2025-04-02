package org.example.practic.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.practic.Domain.Cursa;
import org.example.practic.Service.CursaService;

public class DispeceratView {

    @FXML
    private TextField startingAddressField;

    @FXML
    private TextField destinationAddressField;

    @FXML
    private TextField clientNameField;

    private CursaService cursaService;

    public DispeceratView(CursaService cursaService) {
        this.cursaService = cursaService;
    }

    @FXML
    public void createOrder() {
        String startingAddress = startingAddressField.getText().trim();
        String destinationAddress = destinationAddressField.getText().trim();
        String clientName = clientNameField.getText().trim();

        try {
            cursaService.createCursa(startingAddress, destinationAddress, clientName);
            clearFields();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void clearFields() {
        startingAddressField.clear();
        destinationAddressField.clear();
        clientNameField.clear();
    }
}
