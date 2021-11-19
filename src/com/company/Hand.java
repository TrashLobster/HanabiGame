package com.company;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card dropCard(int cardPosition) {
        return cards.remove(cardPosition);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in hand" + ":\n");
        for(Card card : cards) {
            s.append(card.toString()).append("\n");
        }
        return s.toString();
    }
}
