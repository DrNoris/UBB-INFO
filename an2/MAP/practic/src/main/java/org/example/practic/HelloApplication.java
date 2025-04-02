package org.example.practic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.practic.Controller.DispeceratView;
import org.example.practic.Controller.DriverView;
import org.example.practic.Domain.Driver;
import org.example.practic.Repository.CursaRepo;
import org.example.practic.Repository.DriverRepo;
import org.example.practic.Service.CursaService;
import org.example.practic.Service.DriverService;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CursaRepo cursaRepo = new CursaRepo();
        CursaService cursaService = new CursaService(cursaRepo);

        DriverRepo driverRepo = new DriverRepo();
        DriverService driverService = new DriverService(driverRepo);

        List<Driver> driverList = driverService.getAllDrivers();

        for (Driver driver : driverList) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("driver-view.fxml"));

            fxmlLoader.setControllerFactory(controllerClass -> {
                if (controllerClass == DriverView.class) {
                    DriverView driverViewController = new DriverView();
                    driverViewController.setDriverService(driverService);
                    driverViewController.setCursaService(cursaService);
                    driverViewController.setDriver(driver);
                    return driverViewController;
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage driverStage = new Stage();
            driverStage.setTitle("Driver: " + driver.getName());
            driverStage.setScene(scene);
            driverStage.show();
        }


        FXMLLoader dispeceratLoader = new FXMLLoader(HelloApplication.class.getResource("dispecerat-view.fxml"));

        dispeceratLoader.setControllerFactory(controllerClass -> {
            if (controllerClass == DispeceratView.class) {
                return new DispeceratView(cursaService);
            }
            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene dispeceratScene = new Scene(dispeceratLoader.load(), 600, 400);
        Stage dispeceratStage = new Stage();
        dispeceratStage.setTitle("Dispecerat");
        dispeceratStage.setScene(dispeceratScene);
        dispeceratStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
