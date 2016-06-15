package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.Main;
import com.alextoebes.memorygame.model.Card;
import com.alextoebes.memorygame.model.CardSet;
import com.alextoebes.memorygame.view.CardPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by alextoebes on 12/06/16.
 */
public class BoardController extends Controller {

    private boolean gridMode = true;
    private boolean cheatMode = false;
    private int boardGridColumns = 6;

    @FXML
    public Group board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    public void displayBoard() {
        Card[] cards = ((GameController) getParent()).getGame().getCards();
        List<CardSet> cardSets = Arrays.asList(CardSet.getSetsFromCards(cards));
        cardSets.sort((a,b) -> a.toString().compareTo(b.toString()));

        for (Card card : cards) {
            CardPane cardPane = new CardPane(card);
            board.getChildren().add(cardPane);
            cardPane.setOnAction(e -> selectCardPane(cardPane));
        }

        Main.onDocumentReady(e -> {
            renderGridPosition();
        });
    }

    public void selectCardPane(CardPane cardPane) {
        try {
            ((GameController) getParent()).clickedCard(cardPane);
            cardPane.showCard(true);
        } catch (NullPointerException ignored) {

        }
    }

    public void addToGrid(CardPane cardPane, int padding) {
        List<Node> children = board.getChildren();
        children.add(cardPane);
    }

    public void renderGridPosition() {
        List<Node> children = board.getChildren();
        for (int i = 0; i < children.size(); i++) {
            CardPane cardPane = (CardPane) children.get(i);
            Insets padding = cardPane.getPadding();
            cardPane.setLayoutX(((cardPane.getWidth() + padding.getLeft() + padding.getRight()) * (i % boardGridColumns)));
            cardPane.setLayoutY(((cardPane.getHeight() + padding.getTop() + padding.getBottom()) * ((i - (i % boardGridColumns)) / boardGridColumns)));
        }
    }
}
