package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CardCollection implements CardBasicAction {
    protected List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (cards.size() <= 0) {
            s.append("There are no cards.");
        } else if (cards.size() > 0) {
            for (Card card : cards) {
                s.append("Position: " + (cards.indexOf(card) + 1) + " - ");
                s.append(card.toString()).append("\n");
            }
        }
        return s.toString();
    }

    // add sort method?
}
