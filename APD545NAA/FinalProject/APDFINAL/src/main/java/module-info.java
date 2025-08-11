module org.example.apdfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens seneca.summer25.apdfinal to javafx.fxml;
    opens seneca.summer25.apdfinal.Controller to javafx.fxml;
    opens seneca.summer25.apdfinal.Utils to javafx.fxml;

    exports seneca.summer25.apdfinal;
}