package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.model.Card;
import com.alextoebes.memorygame.view.CardPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * Created by alextoebes on 12/06/16.
 */
public class BoardController extends Controller {

    @FXML
    public StackPane board;

    public void displayBoard() {

        Card[] cards = ((GameController) getParent()).getGame().getCards();

        for (Card card : cards) {
            CardPane cardPane = new CardPane(card);
            /*cardPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("Tile pressed " + cardPane.getWidth() + "x" + cardPane.getHeight());
                event.consume();
            });*/
            board.getChildren().add(cardPane);
            cardPane.setOnAction(e -> selectCardPane(cardPane));
        }
    }

    public void selectCardPane(CardPane cardPane) {
        System.out.println(board.getStyleClass());
        System.out.println(cardPane.getWidth() + "x" + cardPane.getHeight());
    }
}
