package com.alextoebes.memorygame.model;

import java.util.Observable;
import java.util.UUID;

public class Card {

    private CardSet cardSet;

    /**
     * Default constructor
     */
    public Card(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public static Card[] createNewGameCards(int sets, int cardsPerSet) {
        Card[] completeGame = new Card[sets * cardsPerSet];

        for (int i = 0; i < sets; i++) {
            Card[] cardSet = CardSet.createNewCardSetWithCards(cardsPerSet).getCards();
            System.arraycopy(cardSet, 0, completeGame, i * cardsPerSet, cardSet.length);
        }

        return completeGame;
    }


    /**
     * @return
     */
    public CardSet getCardSet() {
        return cardSet;
    }

}