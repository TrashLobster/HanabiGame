package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardCollection implements CardAction{
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

    public Card dropCard(int cardPosition) {
        return cards.remove(cardPosition);
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in deck" + ":\n");
        for(Card card : cards) {
            s.append(card.toString()).append("\n");
        }
        return s.toString();
    }
}
    