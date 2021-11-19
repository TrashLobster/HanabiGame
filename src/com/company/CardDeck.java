package com.company;

import java.util.*;

public class CardDeck extends Hand{
    private final String name;
    int[] possibleValues = new int[] {1, 1, 1, 2, 2, 3, 3, 4, 4, 5};

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
        for(Card.Color color : Card.Color.values()) {
            for(int value : possibleValues) {
                addCard(generateCard(color, value, id));
                id++;
            }
        }
    }

    public Card generateCard(Card.Color color, int value, int id) {
        return new Card(color, value, id);
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    @Override
    public void addCard(Card card) {
        this.cards.add(card);
    }


//    public Card playCard(int cardPosition) {
//        // TODO: remove card from deck
//    }

    // the deck has multiple cards that have identical color and number

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in " + name + ":\n");
        for(Card card : cards) {
            s.append("Card #").append(card.getId()).append(" ").append(card).append("\n");
        }
        return s.toString();
    }

    public int size() {
        return cards.size();
    }

//    public void removeAll(CardDeck deck) {
//        for(Card card: cards) {
//            for(Card deckCard : deck.getCards()) {
//                if(card.equals(deckCard)) {
//                    cards.remove(card);
//                }
//            }
//        }
//    }
}
