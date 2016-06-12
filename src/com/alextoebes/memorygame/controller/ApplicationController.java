package com.alextoebes.memorygame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class ApplicationController extends Controller {
    @FXML private MenuBar menuBar;
    @FXML private BorderPane game;
    @FXML private MenuBarController menuBarController;
    @FXML private GameController gameController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        menuBarController.setParent(this);
        gameController.setParent(this);
    }
}

