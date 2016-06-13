package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.model.Card;
import com.alextoebes.memorygame.model.Game;
import com.alextoebes.memorygame.view.CardPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class GameController extends Controller {
    @FXML private StackPane board;
    @FXML private VBox controls;
    @FXML private BoardController boardController;
    @FXML private ControlsController controlsController;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        boardController.setParent(this);
        controlsController.setParent(this);
    }

    public void startNewGame() {
        this.game = new Game();
        displayGame();
    }

    public void loadGame() {
        this.game = Game.fromSave();
    }

    public void displayGame() {
        clearGame();

        boardController.displayBoard();
    }

    public void clearGame() {

    }

    public Game getGame() {
        return game;
    }
}
