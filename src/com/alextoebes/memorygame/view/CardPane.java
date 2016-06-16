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

    public CardPane(Card card, int index) {
        this.getStyleClass().add("card-pane");
        this.card = card;
        this.image = new ImageView();
        this.image.setMouseTransparent(true);
        this.button = new Button();
        this.getChildren().addAll(this.button, this.image);
        this.showCard(false);
        this.getStyleClass().add("card-" + index);
        this.getStyleClass().add("nocheat");
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

    public void showCard(boolean b) {
        Thread.currentThread().getStackTrace();
        if (b) {
            this.image.getStyleClass().removeIf(styleClass -> styleClass.equals("hidden"));
        } else {
            this.image.getStyleClass().add("hidden");
        }
    }

    public void update(boolean cheatmode) {

        if (!cheatmode) {
            this.getStyleClass().add("nocheat");
        } else {
            this.getStyleClass().removeIf(styleClass -> styleClass.equals("nocheat"));
        }

        if (card.getCardSet().isGuessed()) {
            this.getStyleClass().add("guessed");
        } else {
            this.getStyleClass().removeIf(styleClass -> styleClass.equals("guessed"));
        }
    }
}
