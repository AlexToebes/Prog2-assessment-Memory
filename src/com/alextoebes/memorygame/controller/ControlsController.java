package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.model.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class ControlsController extends Controller {
    @FXML private TextField player1Field;
    @FXML private HBox player1Score;
    @FXML private TextField player2Field;
    @FXML private HBox player2Score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        player1Field.textProperty().addListener((observable, oldValue, newValue) -> updateName(((GameController) getParent()).getGame().getPlayer(0), newValue));
        player2Field.textProperty().addListener((observable, oldValue, newValue) -> updateName(((GameController) getParent()).getGame().getPlayer(1), newValue));
    }

    private void updateName(Player model, String newName) {
        model.setName(newName);
    }

    public void update() {
        player1Field.setText(((GameController) getParent()).getGame().getPlayer(0).getName());
        player2Field.setText(((GameController) getParent()).getGame().getPlayer(1).getName());
    }
}
