package org.example.recap1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.recap1.Controllers.RestaurantView;
import org.example.recap1.Controllers.TableView;
import org.example.recap1.Domain.Table;
import org.example.recap1.Repo.MenuItemRepo;
import org.example.recap1.Repo.OrderRepo;
import org.example.recap1.Repo.TableRepo;
import org.example.recap1.Service.MenuService;
import org.example.recap1.Service.OrderService;
import org.example.recap1.Service.TableService;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuItemRepo menuItemRepo = new MenuItemRepo();
        MenuService menuService = new MenuService(menuItemRepo);

        TableRepo tableRepo = new TableRepo();
        TableService tableService = new TableService(tableRepo);

        OrderRepo orderRepo = new OrderRepo(menuItemRepo, tableRepo);
        OrderService orderService = new OrderService(orderRepo);

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("restaurant-view.fxml"));
        fxmlLoader1.setControllerFactory(controllerClass -> {
            if (controllerClass == RestaurantView.class) {
                RestaurantView restaurantView = new RestaurantView();
                restaurantView.setServices(orderService);
                return restaurantView;
            }
            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 500);
        Stage staffStage = new Stage();
        staffStage.setTitle("Staff");
        staffStage.setScene(scene1);
        staffStage.show();


        List<Table> tableList = tableService.getAllTables();

        for (Table table : tableList) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table-view.fxml"));

            fxmlLoader.setControllerFactory(controllerClass -> {
                if (controllerClass == TableView.class) {
                    TableView tableViewController = new TableView();
                    tableViewController.setServices(table.getId(), menuService, orderService);
                    return tableViewController;
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage tableStage = new Stage();
            tableStage.setTitle("Table Number: " + table.getId());
            tableStage.setScene(scene);
            tableStage.show();
        }



    }

    public static void main(String[] args) {
        launch();
    }
}