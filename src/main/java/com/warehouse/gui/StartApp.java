package com.warehouse.gui;

import com.warehouse.database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StartApp extends Application {
    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    public DatabaseManager databaseManager;
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        databaseManager= new DatabaseManager();
        databaseManager.createOrderListTable();
        databaseManager.createItemsTable();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("window-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600  , 500);
        stage.setTitle("Warehouse Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}