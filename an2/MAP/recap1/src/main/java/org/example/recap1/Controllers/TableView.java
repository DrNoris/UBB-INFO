package org.example.recap1.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Service.MenuService;
import org.example.recap1.Service.OrderService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TableView {
    @FXML
    public ScrollPane orderScrollPane;
    @FXML
    public VBox orderContainer;
    @FXML
    public Button orderButton;
    @FXML
    private Label tableLabel;
    @FXML
    private VBox tablesContainer;
    @FXML
    public ScrollPane scrollPane;

    private int id;
    private MenuService menuService;
    private OrderService orderService;
    private ArrayList<MenuItem> orderList = new ArrayList<>();


    @FXML
    public void initialize() {
        if (menuService == null) {
            System.out.println("MenuService is not injected properly!");
            return;
        }

        System.out.println("Initialize method is called");
        Hashtable<String, List<MenuItem>> menu = menuService.getAllItems();

        for (String category : menu.keySet()) {
            VBox tableContainer = new VBox(10);
            tableContainer.setStyle("-fx-border-color: black; -fx-padding: 10;");

            Label categoryLabel = new Label("Table - " + category);
            categoryLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            tableContainer.getChildren().add(categoryLabel);

            List<MenuItem> items = menu.get(category);

            for (MenuItem item : items) {
                HBox menuItemContainer = new HBox(10);

                Label menuItemLabel = new Label(item.getItem() + " - " + item.getPrice() + " " + item.getCurrency());

                Button minusButton = new Button("-");
                minusButton.setOnAction(event -> decreaseQuantity(item));

                Button plusButton = new Button("+");
                plusButton.setOnAction(event -> increaseQuantity(item));


                menuItemContainer.getChildren().addAll(menuItemLabel, minusButton, plusButton);

                //updateQuantityLabel(item, quantityLabel);

                tableContainer.getChildren().add(menuItemContainer);
            }

            tablesContainer.getChildren().add(tableContainer);
        }


    }

    private void updateOrderButtonState() {
        orderButton.setDisable(orderList.isEmpty());
    }

    private void increaseQuantity(MenuItem item) {
        orderList.add(item);
        updateOrder();
        updateOrderButtonState();
    }

    private void decreaseQuantity(MenuItem item) {
        orderList.remove(item);
        updateOrder();
        updateOrderButtonState();
    }

    private void updateOrder() {
        orderContainer.getChildren().clear();
        for (MenuItem item : orderList) {
            HBox menuItemContainer = new HBox(10);

            Label menuItemLabel = new Label(item.getItem() + " - " + item.getPrice() + " " + item.getCurrency());

            menuItemContainer.getChildren().addAll(menuItemLabel);

            orderContainer.getChildren().add(menuItemContainer);
        }
    }

    @FXML
    private void placeOrder(){
        orderService.addOrder(id, orderList);
        orderList.clear();
        orderContainer.getChildren().clear();
        updateOrderButtonState();
    }


    public void setServices(int id, MenuService menuService, OrderService orderService){
        this.id = id;
        this.menuService = menuService;
        this.orderService = orderService;
    }

}
