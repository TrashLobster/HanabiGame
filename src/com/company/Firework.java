package com.company;

import java.util.ArrayList;
import java.util.List;

public class Firework {
    private final List<Card> cards;
    private final String color;
    private boolean completed;
    private int nextValueExpected;

    public Firework(String color) {
        this.cards = new ArrayList<>();
        this.color = color;
        this.completed = false;
        this.nextValueExpected = 1;
    }

    public boolean checkCompletedStatus() {
        if(nextValueExpected > 5) {
            completed = true;
        }
        return completed;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getColor() {
        return color;
    }

    public int getNextValueExpected() {
        return nextValueExpected;
    }

    public void addCard(Card card) {
        if(nextValueExpected <= 5) {
            cards.add(card);
            nextValueExpected++;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(color).append(": ");
        for(Card card : cards) {
            s.append(card.getCardValue()).append(" ");
        }
        return s.toString();
    }
}
