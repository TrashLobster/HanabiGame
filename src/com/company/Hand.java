package com.company;

public class Hand extends CardCollection{

    public Hand() {
        super();
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
