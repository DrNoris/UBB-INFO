module com.example.seminar8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    opens com.example.seminar8 to javafx.fxml;
    exports com.example.seminar8;
}