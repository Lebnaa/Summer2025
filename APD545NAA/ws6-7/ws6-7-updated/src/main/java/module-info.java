module org.example.assignment45 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens seneca.example.ws45 to javafx.fxml;
    opens seneca.example.ws45.Controller to javafx.fxml;  // ✅ Added this line
    opens seneca.example.ws45.Model to javafx.base;       // Optional if using ObservableList in model

    exports seneca.example.ws45;
    exports seneca.example.ws45.Controller;                // Optional, but good for clarity
    exports seneca.example.ws45.Model;                     // Optional, for external usage
}
