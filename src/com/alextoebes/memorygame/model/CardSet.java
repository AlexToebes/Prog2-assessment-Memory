package com.alextoebes.memorygame.model;

import java.util.*;
import java.util.stream.Collectors;

public class CardSet {

    private String uuid;
    private Card[] cards;

    public CardSet(String uuid, int size) {
        this.uuid = uuid;
        this.cards = new Card[size];
    }

    public static CardSet createNewCardSetWithCards(int cardAmount) {
        CardSet cardSet = new CardSet(UUID.randomUUID().toString(), cardAmount);
        for (int i = 0; i < cardAmount; i++) {
            cardSet.add(new Card(cardSet));
        }
        return cardSet;
    }

    private boolean add(Card card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                cards[i] = card;
                return true;
            }
        }
        return false;
    }

    public static CardSet[] getSetsFromCards(Card[] cards) {
        Set<CardSet> cardSets = new HashSet<>();
        cardSets.addAll(Arrays.asList(cards).stream().map(Card::getCardSet).collect(Collectors.toList()));

        return cardSets.toArray(new CardSet[cardSets.size()]);
    }

    @Override
    public String toString() {
        return this.uuid;
    }

    public Card[] getCards() {
        return this.cards;
    }
}