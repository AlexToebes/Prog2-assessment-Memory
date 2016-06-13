package com.alextoebes.memorygame.model;

import java.util.Observable;

public class CardSet {

    private int uuid;

    public CardSet(int uuid) {

        this.uuid = uuid;
    }

    public static Card[] getSet(CardSet cardSetObject, int cardsPerSet) {

        Card[] cardSet = new Card[cardsPerSet];

        for (int i = 0; i < cardsPerSet; i++) {
            cardSet[i] = new Card(cardSetObject);
        }
        return cardSet;
    }
}