package com.example.chatapp.controller;

import com.example.chatapp.ApplicationDemo;
import com.example.chatapp.entity.Message;
import com.example.chatapp.entity.User;
import com.example.chatapp.rabbitmq.Consumer;
import com.example.chatapp.rabbitmq.Producer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private String QUEUE_CURRENT;

    public void startApp(String queue) throws IOException, TimeoutException {
        this.QUEUE_CURRENT = queue;
        producer = new Producer();
        consumer = new Consumer();

        producer.start(QUEUE_CURRENT);
        consumer.start(QUEUE_CURRENT);
        consumer.subscribe(QUEUE_CURRENT, this);
    }

    @FXML
    private void initialize() throws IOException, TimeoutException {
        initCurrentUser();
        initViewChat();

        btnNewChat.setOnMousePressed(mouseEvent -> {
            newChat();
        });

        btnSend.setOnMousePressed(mouseEvent -> {
            Message message = new Message(QUEUE_CURRENT, txtChat.getText());
            try {
                producer.send(message, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void newChat() {
        Stage stage = new Stage();
        Parent parent = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader((ApplicationDemo.class.getResource("view.fxml")));
            parent = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Thông tin User");
            dialog.setHeaderText("Vui lòng nhập thông tin user");
            dialog.setContentText("Username:");
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void initCurrentUser() {
        ListView<String> listView = new ListView<>();
        listView.setItems(User.users);
        listView.setMaxWidth(189);
        listView.setMaxHeight(336);
        scrollUser.setContent(listView);
    }

    public void setChat(Message chat) {
        if(chat.getUser().equals(QUEUE_CURRENT))  listMess.add("[Me send]: " + chat.getMessage());
        else listMess.add("[From "+chat.getUser()+"]: " + chat.getMessage());
        textArea.setText(String.join(System.lineSeparator(), listMess));
        scrollViewChat.setContent(textArea);
        txtChat.clear();
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
