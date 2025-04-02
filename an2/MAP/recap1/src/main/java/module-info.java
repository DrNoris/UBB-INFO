module org.example.recap1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.recap1 to javafx.fxml;
    exports org.example.recap1;
    exports org.example.recap1.Service;
    opens org.example.recap1.Service to javafx.fxml;
    exports org.example.recap1.Controllers;
    opens org.example.recap1.Controllers to javafx.fxml;
}