package com.alextoebes.memorygame.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * 
 */
public class Game extends Observable {
    private Card[] cards;
    private int cardsInCardSet = 2;
    private int cartSetsInGame = 18;
    private Player[] players;
    private Player turn;

    /**
     * Default constructor
     */
    public Game() {
        this.players = new Player[]{new Player(), new Player()};
        this.cards = Card.createNewGameCards(cartSetsInGame, cardsInCardSet);
        this.cards = shuffle(this.cards);
    }

    private Card[] shuffle(Card[] cards) {
        List<Card> cardsList = Arrays.asList(cards);
        Collections.shuffle(cardsList);
        return (Card[]) cardsList.toArray();
    }

    /**
     * Load Savegame
     */
    public static Game fromSave() {
        return new Game();
    }

    /**
     * @param card1 
     * @param card2 
     * @return
     */
    public boolean makeGuess(Card card1, Card card2) {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void determineFirstTurn() {
        // TODO implement here
    }

    public Card[] getCards() {
        return cards;
    }
}