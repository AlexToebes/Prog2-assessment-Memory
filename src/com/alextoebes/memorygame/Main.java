package com.alextoebes.memorygame;

import com.alextoebes.memorygame.view.Container;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Container root = FXMLLoader.load(getClass().getResource("/fxml/container.fxml"));
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
