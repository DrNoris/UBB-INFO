package org.example.practic.Controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.practic.Domain.Cursa;
import org.example.practic.Domain.Driver;
import org.example.practic.Service.CursaService;
import org.example.practic.Service.DriverService;
import org.example.practic.Service.Observer;

import java.sql.Timestamp;
import java.util.List;

public class DriverView extends Observer {
    @FXML
    private VBox tablesContainer;

    private DriverService driverService;
    private CursaService cursaService;
    private Driver driver;
    private boolean isAlertVisible = false;
    private Alert currentAlert;

    @FXML
    public void initialize() {
        cursaService.subscribe(this);
        if (driver == null) {
            System.out.println("Driver is not set.");
            return;
        }

        List<Cursa> cursas = cursaService.getAllCursasForDriver(driver);

        if (cursas.isEmpty()) {
            System.out.println("No orders found for this driver.");
            return;
        }

        System.out.println("Initialize method is called. Cursas populated for driver: " + driver.getName());

        VBox tableContainer = new VBox(10);
        tableContainer.setStyle("-fx-border-color: black; -fx-padding: 10;");

        Label categoryLabel = new Label("Orders for Driver: " + driver.getName());
        categoryLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        tableContainer.getChildren().add(categoryLabel);

        for (Cursa cursa : cursas) {
            HBox cursaItemContainer = new HBox(10);

            Label cursaDetailsLabel = new Label("ID: " + cursa.getId() +
                    " | Status: " + cursa.getStatus() +
                    " | Start: " + cursa.getStartDate() +
                    " | End: " + cursa.getEndDate() +
                    " | Pickup: " + cursa.getPickupAddress() +
                    " | Destination: " + cursa.getDestinationAddress() +
                    " | Client: " + cursa.getClientName());

            Button finishedButton = new Button("Finished");
            finishedButton.setOnAction(event -> {
                updateCursaStatusToFinished(cursa);
            });

            cursaItemContainer.getChildren().addAll(cursaDetailsLabel, finishedButton);

            tableContainer.getChildren().add(cursaItemContainer);
        }

        tablesContainer.getChildren().add(tableContainer);
    }

    private void updateCursaStatusToFinished(Cursa cursa) {
        cursaService.updateCursa(cursa, "FINISHED");
        refresh();
    }

    private void refresh() {
        Platform.runLater(() -> {
            List<Cursa> cursas = cursaService.getAllCursasForDriver(driver);
            tablesContainer.getChildren().clear();

            if (cursas.isEmpty()) {
                System.out.println("No orders found for this driver.");
                return;
            }

            VBox tableContainer = new VBox(10);
            tableContainer.setStyle("-fx-border-color: black; -fx-padding: 10;");

            Label categoryLabel = new Label("Orders for Driver: " + driver.getName());
            categoryLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            tableContainer.getChildren().add(categoryLabel);

            for (Cursa cursa : cursas) {
                HBox cursaItemContainer = new HBox(10);

                Label cursaDetailsLabel = new Label("ID: " + cursa.getId() +
                        " | Status: " + cursa.getStatus() +
                        " | Start: " + cursa.getStartDate() +
                        " | End: " + cursa.getEndDate() +
                        " | Pickup: " + cursa.getPickupAddress() +
                        " | Destination: " + cursa.getDestinationAddress() +
                        " | Client: " + cursa.getClientName());

                Button finishedButton = new Button("Finished");
                finishedButton.setOnAction(event -> updateCursaStatusToFinished(cursa));

                cursaItemContainer.getChildren().addAll(cursaDetailsLabel, finishedButton);
                tableContainer.getChildren().add(cursaItemContainer);
            }

            tablesContainer.getChildren().add(tableContainer);
        });
    }

    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCursaService(CursaService cursaService) {
        this.cursaService = cursaService;
    }

    @Override
    public void update(int id) {
        Cursa cursa = cursaService.findById(id);

        if (cursa == null) {
            throw new RuntimeException("Order not found with ID: " + id);
        }

        Platform.runLater(() -> showOrderNotification(cursa));
    }


    private void showOrderNotification(Cursa cursa) {
        String orderDetails = "New Order: " + cursa.getPickupAddress() + " -> " +
                cursa.getDestinationAddress();

        currentAlert = new Alert(Alert.AlertType.INFORMATION);
        currentAlert.setHeaderText("Order ID: " + cursa.getId() + " for Driver: " + driver.getName());
        currentAlert.setContentText(orderDetails);

        ButtonType acceptButton = new ButtonType("Accept");
        currentAlert.getButtonTypes().clear();
        currentAlert.getButtonTypes().addAll(acceptButton, ButtonType.CANCEL);

        isAlertVisible = true;

        currentAlert.showAndWait().ifPresent(response -> {
            if (response == acceptButton) {
                acceptOrder(cursa);
            }

            isAlertVisible = false;
        });
    }

    private void acceptOrder(Cursa cursa) {
        cursa.setDriverId(driver.getId());
        cursaService.updateCursa(cursa, "IN_PROGRESS");
        refresh();
    }


    public boolean hasInProgressOrder() {
        return cursaService.hasOrders(driver);
    }

    public Timestamp getLastFinishedOrderDate() {
        return cursaService.getLastFinishedOrderDate(driver);
    }

    public void removeOrderNotification() {
        if (isAlertVisible && currentAlert != null) {
            currentAlert.close();
            isAlertVisible = false;
            System.out.println("Order notification removed due to timeout.");
        }
    }
}