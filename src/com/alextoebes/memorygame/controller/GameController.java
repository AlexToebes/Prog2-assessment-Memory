package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.view.BoardPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Created by alextoebes on 12/06/16.
 */
public class GameController extends Controller {
    @FXML private BoardPane board;
    @FXML private VBox controls;
    @FXML private BoardController boardController;
    @FXML private ControlsController controlsController;
}
