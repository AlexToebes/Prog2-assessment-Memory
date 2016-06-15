package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.Main;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
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
        loadFile(fileChooser.showOpenDialog(stage));
    }

    public void handleSave(ActionEvent actionEvent) {
        // TODO: 15/06/16 implementation
        System.out.println("handleSave");
    }

    public void handleExit(ActionEvent actionEvent) {
        // TODO: 15/06/16 implementation 
        System.out.println("handleExit");
    }

    private boolean loadFile(File file) {
        if (file == null) {
            return false;
        }

        return true;
    }
}
