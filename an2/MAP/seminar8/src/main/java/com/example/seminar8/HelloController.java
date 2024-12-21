package com.example.seminar8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HelloController {
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> studentColumn;

    @FXML
    private TableColumn<?, ?> temaColumn;

    @FXML
    private TableColumn<?, ?> notaColumn;
}