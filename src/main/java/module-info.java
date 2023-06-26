module com.example.chatapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.rabbitmq.client;
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires javafx.graphics;

    opens com.example.chatapp to javafx.fxml;
    exports com.example.chatapp;
    opens com.example.chatapp.controller to javafx.fxml;
    exports com.example.chatapp.controller;
    opens com.example.chatapp.rabbitmq to javafx.fxml;
    exports com.example.chatapp.rabbitmq;
}