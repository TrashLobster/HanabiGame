package com.company;

import java.util.Collections;
import java.util.List;

public class CardDeck extends Hand {
    private final String name;
    int[] possibleValues = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 4, 5 };

    public CardDeck(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void generateDeck() {
        int id = 1;
        for (Colour colour : Colour.values()) {
            for (int value : possibleValues) {
                addCard(generateCard(colour, value, id));
                id++;
            }
        }
    }

    public Card generateCard(Colour colour, int value, int id) {
        return new Card(colour, value, id);
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    @Override
    public void addCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in " + name + ":\n");
        for (Card card : cards) {
            s.append("Card #").append(card.getId()).append(" ").append(card).append("\n");
        }
        return s.toString();
    }

    public int size() {
        return cards.size();
    }
}
