package com.example.chatapp;

import com.example.chatapp.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ApplicationDemo extends Application {
    @Override
    public void start(Stage stage) throws IOException, TimeoutException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationDemo.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}