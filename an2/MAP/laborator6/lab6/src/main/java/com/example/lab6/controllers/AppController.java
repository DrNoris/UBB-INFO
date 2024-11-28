package com.example.lab6.controllers;

import com.example.lab6.domain.FriendRequest;
import com.example.lab6.domain.Utilizator;
import com.example.lab6.service.AppService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Optional;

public class AppController {
    private final AppService service;
    private final Utilizator currentUser;

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Utilizator> friendsTable;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> statusColumn;
    @FXML
    private VBox notificationContainer;
    @FXML
    private VBox userListVBox;
    @FXML
    private TableColumn<Utilizator, Void> actionColumn;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        configureActionColumn();
        loadFriends();
        loadNotifications();
    }

    private void handleDeleteFriend(Utilizator user) {
        // Implement the logic to delete a friendship
        System.out.println("Deleting friendship with " + user.getUsername());
        service.deletePrietenie(user.getId(), currentUser.getId());
        loadFriends(); // Refresh the friends list
    }

    private void handleChat(Utilizator user) {
        // Implement the logic to open a chat window or display a chat interface
        System.out.println("Opening chat with " + user.getUsername());
    }


    private void configureActionColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final Button chatButton = new Button("Chat");
            private final HBox buttonContainer = new HBox(deleteButton, chatButton);

            {
                buttonContainer.setSpacing(10); // Space between buttons

                deleteButton.setOnAction(event -> {
                    Utilizator user = (Utilizator) getTableView().getItems().get(getIndex());
                    handleDeleteFriend(user);
                });

                chatButton.setOnAction(event -> {
                    Utilizator user = (Utilizator) getTableView().getItems().get(getIndex());
                    handleChat(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonContainer);
                }
            }
        });
    }



    private void loadFriends() {
        List<Utilizator> friends = service.getAllFriendships(currentUser);
        ObservableList<Utilizator> friendsList = FXCollections.observableArrayList(friends);

        friendsTable.getItems().clear();

        if (friendsList.isEmpty()) {
            System.out.println("Nu există prieteni de încărcat!");
        } else {
            friendsTable.setItems(friendsList); // Aici setezi lista de prieteni în TableView
        }
    }

    private void loadNotifications(){
        Optional<List<Utilizator>> requests = service.getAllRequests(currentUser);

        notificationContainer.getChildren().clear();

        if (requests.isEmpty()) {
            System.out.println("Nu există cereri noi!");
            Label nothing = new Label("Nothing new!");
            notificationContainer.getChildren().add(nothing);
        } else {
            requests.get().forEach(user -> {
                HBox row = new HBox();
                row.setSpacing(10); // Add some spacing between elements
                row.setStyle("-fx-background-color: #c8c8c8; -fx-padding: 5;");


                Label userLabel = new Label("Friend request: " + user.getUsername());
                Button addUser = new Button("Accept");

                addUser.setOnAction(event -> handleAccept(user));

                // Add a spacer to push the Button to the far right
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                // Add elements to the row
                row.getChildren().addAll(userLabel, spacer, addUser);

                // Add the row to the VBox
                notificationContainer.getChildren().add(row);
            });
        }
    }

    private void handleAccept(Utilizator user) {
        System.out.println("Prieteni acceptat");

        service.acceptPrietenie(currentUser.getId(), user.getId());
    }


    public AppController(AppService service, Utilizator currentUser){
        this.service = service;
        this.currentUser = currentUser;
    }

    @FXML
    public void handleSearch() {
        String username = searchField.getText();

        if (username.isEmpty())
            userListVBox.getChildren().clear();
        else {
            userListVBox.getChildren().clear();

            Optional<List<Utilizator>> users = service.findAllName(username);

            if (users == null) {
                userListVBox.getChildren().add(new Label("No users found."));
            } else {
                // Loop through the found users and display them as labels in the VBox
                users.get().forEach(user -> {
                    HBox row = new HBox();
                    row.setSpacing(10); // Add some spacing between elements
                    row.setStyle("-fx-background-color: #6a6a6a; -fx-padding: 5;");


                    Label userLabel = new Label(user.getUsername());
                    Button addUser = new Button("Add");

                    addUser.setOnAction(event -> handleAddFriend(user));

                    // Add a spacer to push the Button to the far right
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    // Add elements to the row
                    row.getChildren().addAll(userLabel, spacer, addUser);

                    // Add the row to the VBox
                    userListVBox.getChildren().add(row);
                });
            }
        }
    }

    private void handleAddFriend(Utilizator user){
        service.sendRequest(currentUser.getId(), user.getId());
    }

    @FXML
    public void handleViewNotifications() {

    }
}
