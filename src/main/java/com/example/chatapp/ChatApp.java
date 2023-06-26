package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = (new FXMLLoader(ChatApp.class.getResource("view.fxml"))).load();
        stage.setTitle("LOGIN");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
