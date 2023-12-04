module writ1co2system {
    requires javafx.controls;
    requires javafx.fxml;

    opens writ1co2system to javafx.fxml;
    exports writ1co2system;
}
