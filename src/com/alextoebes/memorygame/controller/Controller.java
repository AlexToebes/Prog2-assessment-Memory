package com.alextoebes.memorygame.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public abstract class Controller implements Initializable {
    private Controller parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public Controller getParent() {
        return parent;
    }
}
