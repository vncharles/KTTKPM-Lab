module com.example.chatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.rabbitmq.client;
    requires org.apache.commons.lang3;
    requires org.slf4j;

    opens com.example.chatapp to javafx.fxml;
    exports com.example.chatapp;
    opens com.example.chatapp.controller to javafx.fxml;
    exports com.example.chatapp.controller;
}