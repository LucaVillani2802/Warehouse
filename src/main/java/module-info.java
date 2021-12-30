module com.warehouse.warehouse {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires java.sql;
    requires sqlite.jdbc;

    opens com.warehouse.gui to javafx.fxml;
    exports com.warehouse.gui;
    exports com.warehouse.database;
    opens com.warehouse.database to javafx.fxml;

}