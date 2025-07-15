module org.example.assignment45 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.assignment45 to javafx.fxml;
    opens org.example.assignment45.Controller to javafx.fxml;  // ✅ Added this line
    opens org.example.assignment45.Model to javafx.base;       // Optional if using ObservableList in model

    exports org.example.assignment45;
    exports org.example.assignment45.Controller;                // Optional, but good for clarity
    exports org.example.assignment45.Model;                     // Optional, for external usage
}
