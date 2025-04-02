package org.example.recap1.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Domain.Order;
import org.example.recap1.Service.Observer;
import org.example.recap1.Service.OrderService;

import java.util.Comparator;
import java.util.List;

public class RestaurantView extends Observer {
    private OrderService orderService;

    @FXML
    private VBox ordersContainer;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void update() {
        Platform.runLater(this::refreshOrders);
    }

    @FXML
    public void initialize() {
        if (ordersContainer == null) {
            System.out.println("ordersContainer is null!");
        } else {
            System.out.println("ordersContainer is loaded successfully.");
        }
        refreshOrders();
    }


    public void setServices(OrderService orderService) {
        this.orderService = orderService;
        orderService.subscribe(this);
    }

    private void refreshOrders() {
        ordersContainer.getChildren().clear();

        List<Order> orders = orderService.getAll();
        orders.sort(Comparator.comparing(Order::getDate));

        for (Order order : orders) {
            VBox orderBox = new VBox(5);
            orderBox.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: #f4f4f4;");

            Label tableLabel = new Label("Table ID: " + order.getTable().getId());
            Label dateLabel = new Label("Date: " + order.getDate().toString());

            VBox itemsBox = new VBox(2);
            for (MenuItem item : order.getMenuItems()) {
                Label itemLabel = new Label("- " + item.getItem());
                itemsBox.getChildren().add(itemLabel);
            }

            orderBox.getChildren().addAll(tableLabel, dateLabel, itemsBox);
            ordersContainer.getChildren().add(orderBox);
        }
    }
}
