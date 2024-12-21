package com.example.seminar8.Controller;

import com.example.seminar8.Domain.Nota;
import com.example.seminar8.Domain.Student;
import com.example.seminar8.Domain.Tema;
import com.example.seminar8.Service.ServiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class NotaController {

    @FXML
    private TableView<Nota> tableView;

    @FXML
    private TableColumn<Nota, String> studentColumn;

    @FXML
    private TableColumn<Nota, String> temaColumn;

    @FXML
    private TableColumn<Nota, Double> notaColumn;

    @FXML
    private TableColumn<Nota, String> profesorColumn;

    private ServiceManager serviceManager;

    // Constructor to initialize the ServiceManager
    public NotaController() {
        this.serviceManager = new ServiceManager();
    }

    // Method to initialize the table with data
    @FXML
    public void initialize() {
        // Fetch the list of students and homeworks
        List<Student> students = serviceManager.findAllStudents();
        List<Tema> homeworks = serviceManager.findAllHomeworks();

        // Get the list of grades, passing the students and homeworks
        List<Nota> grades = serviceManager.findAllGrades(students, homeworks);

        // Set up the columns with PropertyValueFactory
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        temaColumn.setCellValueFactory(new PropertyValueFactory<>("tema"));
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        profesorColumn.setCellValueFactory(new PropertyValueFactory<>("profesor"));

        // Clear any existing items and add the new grades to the table
        tableView.getItems().clear();
        tableView.getItems().addAll(grades);
    }
}
