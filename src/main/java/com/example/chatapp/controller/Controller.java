package com.example.chatapp.controller;

import com.example.chatapp.ApplicationDemo;
import com.example.chatapp.rabbitmq.Consumer;
import com.example.chatapp.rabbitmq.Producer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Controller {
    @FXML
    private ScrollPane scrollUser;
    @FXML
    private ScrollPane scrollViewChat;
    @FXML
    private Button btnNewChat;
    @FXML
    private Button btnSend;
    @FXML
    private TextField txtChat;

    private List<String> listMess = new ArrayList<>();
    private TextArea textArea = new TextArea();
    private Producer producer;
    private Consumer consumer;

    @FXML
    private void initialize() throws IOException, TimeoutException {
        producer = new Producer();
        consumer = new Consumer();

        producer.start();
        consumer.start();

        initViewChat();

        btnNewChat.setOnMousePressed(mouseEvent -> {
            Stage stage = new Stage();
            Parent parent = null;
            try {
                parent = (new FXMLLoader((ApplicationDemo.class.getResource("view.fxml")))).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("App chat");
            stage.setScene(new Scene(parent));
            stage.show();
        });

        btnSend.setOnMousePressed(mouseEvent -> {
            try {
                producer.send(txtChat.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listMess.add(txtChat.getText());
            textArea.setText(String.join(System.lineSeparator(), listMess));
            scrollViewChat.setContent(textArea);
            txtChat.clear();
        });
    }

    private void initViewChat() {
        listMess.add("Welcome to Chat");
        textArea.setText(String.join(System.lineSeparator(), listMess));
        scrollViewChat.setContent(textArea);
        textArea.setEditable(false);
        textArea.setMaxWidth(365);
        textArea.setMinHeight(330);
        textArea.setMaxHeight(330);
    }
}
