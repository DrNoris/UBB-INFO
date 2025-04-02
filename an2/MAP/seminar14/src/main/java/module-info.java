module com.example.seminar14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.seminar14 to javafx.fxml;
    exports com.example.seminar14;
}