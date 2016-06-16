package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.model.CardSet;
import com.alextoebes.memorygame.model.Game;
import com.alextoebes.memorygame.view.CardPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class GameController extends Controller {
    @FXML public BoardController boardController;
    @FXML public ControlsController controlsController;
    private Game game;

    private CardPane selectedCard = null;
    private boolean clickReady = true;

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

    public void displayGame() {
        clearGame();
        boardController.displayBoard();
        controlsController.update();
    }

    public void clearGame() {
        boardController.clearBoard();
    }

    public void clickedCard(CardPane cardPane) {
        if (!clickReady) {
            throw new NullPointerException("Not click ready");
        }
        cardPane.showCard(true);
        if (selectedCard == null) {
            selectedCard = cardPane;
        } else {

            clickReady = false;

            if (selectedCard == cardPane) {
                throw new NullPointerException("Card clicked twice");
            }

            if (cardPane.getCard().getCardSet() == selectedCard.getCard().getCardSet()) {
                CardSet cardSet = cardPane.getCard().getCardSet();
                game.getTurn().getScore().add(cardSet);
                cardSet.setGuessed(true);
                boardController.update();
                selectedCard = null;
                clickReady = true;
                game.nextTurn();
                controlsController.update();
            } else {
                CardPane prevSelectedCard = selectedCard;
                new Thread(() -> {
                    final CardPane treadPrevSelectedCard = prevSelectedCard;
                    try {
                        Thread.sleep(1000);
                        cardPane.showCard(false);
                        treadPrevSelectedCard.showCard(false);
                        selectedCard = null;
                        clickReady = true;
                        game.nextTurn();
                        controlsController.update();
                    }
                    catch (Exception ignored){
                    }
                }).start();
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
