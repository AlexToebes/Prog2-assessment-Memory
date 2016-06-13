package com.alextoebes.memorygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends Application {

    protected static List<ActionListener> listenerList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/fxml/container.fxml"));
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
        this.triggerDocumentReady();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void onDocumentReady(ActionListener listener) {
        listenerList.add(listener);
    }

    private void triggerDocumentReady() {
        ActionEvent event = new ActionEvent(this, UUID.randomUUID().hashCode(), "Document ready");
        for (ActionListener listener:listenerList) {
            listener.actionPerformed(event);
        }
    }
}
