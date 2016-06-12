package com.alextoebes.memorygame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

/**
 * Created by alextoebes on 12/06/16.
 */
public class ApplicationController extends Controller {
    @FXML private MenuBar menuBar;
    @FXML private BorderPane content;
    @FXML private MenuBarController menuBarController;
    @FXML private GameController contentController;
}

