package com.example.chatapp;

import com.example.chatapp.controller.Controller;
import com.example.chatapp.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class ApplicationDemo extends Application {
    @Override
    public void start(Stage stage) throws IOException, TimeoutException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationDemo.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thông tin User");
        dialog.setHeaderText("Vui lòng nhập thông tin user");
        dialog.setContentText("Nội dung:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(value -> {
            try {
                controller.startApp(value);
                User.users.add(value);
                stage.setTitle("App chat ["+value+"]");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}