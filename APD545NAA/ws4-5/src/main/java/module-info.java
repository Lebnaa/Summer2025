module org.example.assignment45 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens ws.part.ws45 to javafx.fxml;
    opens ws.part.ws45.Controller to javafx.fxml;
    opens ws.part.ws45.Model to javafx.base;

    exports ws.part.ws45;
    exports ws.part.ws45.Controller;
    exports ws.part.ws45.Model;
}
