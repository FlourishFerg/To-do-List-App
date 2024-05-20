package com.example.todolistapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Page-1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("To-do!");
        stage.initStyle(StageStyle.DECORATED);
        // Center the stage on the screen
        stage.centerOnScreen();

        stage.show();
        stage.setScene(scene);
       stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}