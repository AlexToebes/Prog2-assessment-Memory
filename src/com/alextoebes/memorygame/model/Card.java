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

    public static Card[] getCompleteGame(int sets, int cardsPerSet) {
        Card[] completeGame = new Card[sets * cardsPerSet];

        for (int i = 0; i < sets; i++) {
            Card[] cardSet = CardSet.getSet(new CardSet(UUID.randomUUID().hashCode()), cardsPerSet);
            for (int ii = 0; ii < cardSet.length; ii++) {
                completeGame[(i * cardsPerSet) + ii] = cardSet[ii];
            }
        }

        return completeGame;
    }


    /**
     * @return
     */
    public CardSet getCardSet() {
        // TODO implement here
        return null;
    }

}