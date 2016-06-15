package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.Main;
import com.alextoebes.memorygame.model.Game;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class MenuBarController extends Controller {

    private Stage stage;
    FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Memory saves (*.mem)", "*.mem");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        Main.onDocumentReady((stage) -> this.stage = (Stage) stage.getSource());
    }

    public void handleLoad(ActionEvent actionEvent) {
        // TODO: 15/06/16 implementation
        System.out.println("handleLoad");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extentionFilter);
        try {
            Game.fromSave(fileChooser.showOpenDialog(stage));
        } catch (IOException e) {
            // TODO: 15/06/16 Give error message
            e.printStackTrace();
        }
    }

    public void handleSave(ActionEvent actionEvent) {
        // TODO: 15/06/16 implementation
        System.out.println("handleSave");
    }

    public void handleExit(ActionEvent actionEvent) {
        // TODO: 15/06/16 implementation 
        System.out.println("handleExit");
    }
}
