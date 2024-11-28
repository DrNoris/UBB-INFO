package com.example.lab6;

import com.example.lab6.controllers.LoginController;
import com.example.lab6.domain.validators.UtilizatorValidator;
import com.example.lab6.repository.database.PrietenieDatabaseRepository;
import com.example.lab6.repository.database.RequestDatabaseRepository;
import com.example.lab6.repository.database.UtilizatorDatabaseRepository;
import com.example.lab6.service.AppService;
import com.example.lab6.service.LoginService;
import com.example.lab6.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        UtilizatorValidator validator = new UtilizatorValidator();
        UtilizatorDatabaseRepository usersDB = new UtilizatorDatabaseRepository("postgres", "noris2580"
                ,"jdbc:postgresql://localhost:5432/postgres", validator);
        PrietenieDatabaseRepository priteniDB = new PrietenieDatabaseRepository("postgres", "noris2580"
                ,"jdbc:postgresql://localhost:5432/postgres");
        RequestDatabaseRepository requestDB = new RequestDatabaseRepository("postgres", "noris2580"
                ,"jdbc:postgresql://localhost:5432/postgres");
        LoginService loginService = new LoginService(usersDB);
        Service service = new Service(priteniDB, usersDB, requestDB);
        AppService appService = new AppService(usersDB, priteniDB, requestDB);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab6/login-view.fxml"));

        // Set the controller with the LoginService dependency
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == LoginController.class) {
                return new LoginController(loginService, appService, service);  // Inject LoginService here
            }
            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Load the layout
        AnchorPane root = loader.load();

        // Create the scene and set it to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chill Login");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}