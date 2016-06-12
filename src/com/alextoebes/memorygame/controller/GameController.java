package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.view.BoardPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class GameController extends Controller {
    @FXML private BoardPane board;
    @FXML private VBox controls;
    @FXML private BoardController boardController;
    @FXML private ControlsController controlsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        boardController.setParent(this);
        controlsController.setParent(this);
    }
}
