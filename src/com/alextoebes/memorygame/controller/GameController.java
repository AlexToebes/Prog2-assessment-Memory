package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.model.Game;
import com.alextoebes.memorygame.view.CardPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class GameController extends Controller {
    @FXML private BoardController boardController;
    @FXML private ControlsController controlsController;
    private Game game;

    private CardPane selectedCard = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        boardController.setParent(this);
        controlsController.setParent(this);
    }

    public void startNewGame() {
        clearGame();
        this.game = new Game();
        displayGame();
    }

    public void displayGame() {
        clearGame();

        boardController.displayBoard();
    }

    public void clearGame() {

    }

    public void clickedCard(CardPane cardPane) {
        if (selectedCard == null) {
            selectedCard = cardPane;
        } else {
            if (selectedCard == cardPane) {
                throw new NullPointerException("Card clicked twice");
            }
            System.out.println("Compare: " + System.identityHashCode(cardPane) + " to: " + System.identityHashCode(selectedCard));
            selectedCard = null;
        }
    }

    public Game getGame() {
        return game;
    }
}
