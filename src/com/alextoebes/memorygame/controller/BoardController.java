package com.alextoebes.memorygame.controller;

import com.alextoebes.memorygame.DocumentReady;
import com.alextoebes.memorygame.model.Card;
import com.alextoebes.memorygame.model.CardSet;
import com.alextoebes.memorygame.view.CardPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;

import java.net.URL;
import java.util.*;

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
        cardSets.sort((a, b) -> {
            System.out.println(a.toString() + " - " + b.toString());
            return a.toString().compareTo(b.toString());
        });

        Map<CardSet, Integer> cardSetIndex = new HashMap<>();

        for (int i = 0; i < cardSets.size(); i++) {
            cardSetIndex.put(cardSets.get(i), i);
        }

        for (Card card : cards) {

            CardPane cardPane = new CardPane(card, cardSetIndex.get(card.getCardSet())+1);
            board.getChildren().add(cardPane);
            cardPane.setOnAction(e -> selectCardPane(cardPane));
        }

        DocumentReady.onReady(e -> {
            renderGridPosition();
        });
    }

    public void selectCardPane(CardPane cardPane) {
        try {
            ((GameController) getParent()).clickedCard(cardPane);
        } catch (NullPointerException ignored) {

        }

        //((GameController) getParent()).isFinished();
    }

    public void addToGrid(CardPane cardPane, int padding) {
        List<Node> children = board.getChildren();
        children.add(cardPane);
    }

    public void clearBoard() {
        board.getChildren().clear();
    }

    public void renderGridPosition() {
        List<Node> children = board.getChildren();
        for (int i = 0; i < children.size(); i++) {
            CardPane cardPane = (CardPane) children.get(i);
            Insets padding = cardPane.getPadding();
            cardPane.setLayoutX(((cardPane.getWidth() + padding.getLeft() + padding.getRight()) * (i % boardGridColumns)));
            cardPane.setLayoutY(((cardPane.getHeight() + padding.getTop() + padding.getBottom()) * ((i - (i % boardGridColumns)) / boardGridColumns)));
        }
        update();
    }

    public void update() {
        for (Node cardPane : board.getChildren()) {
            ((CardPane) cardPane).update(cheatMode);
        }
    }

    public void setCheatMode(boolean cheatMode) {
        this.cheatMode = cheatMode;
        update();
    }

    public boolean getCheatMode() {
        return cheatMode;
    }
}
