package com.alextoebes.memorygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/fxml/container.fxml"));
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
        DocumentReady.triggerReady(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
