package com.alextoebes.memorygame.view;

import com.alextoebes.memorygame.model.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;

/**
 * Created by alextoebes on 12/06/16.
 */
public class CardPane extends StackPane {
    private final ImageView image;
    private final Button button;
    private Card card;

    public CardPane(Card card) {
        this.getStyleClass().add("card-pane");
        this.card = card;
        this.image = new ImageView();
        this.image.setMouseTransparent(true);
        this.button = new Button();
        this.getChildren().addAll(this.button, this.image);
        this.image.getStyleClass().add("card-1");
    }

    public Card getCard() {
        return card;
    }

    public void setPosition(Point position) {
    }

    @Override
    protected void setWidth(double value) {
        super.setWidth(value);
        this.image.setFitWidth(value);
        this.button.setMinWidth(value);
        this.button.setMaxWidth(value);
    }

    @Override
    protected void setHeight(double value) {
        super.setHeight(value);
        this.image.setFitHeight(value);
        this.button.setMinHeight(value);
        this.button.setMaxHeight(value);
    }

    public void setOnAction (EventHandler<ActionEvent> e) {
        this.button.setOnAction(e);
    }
}
