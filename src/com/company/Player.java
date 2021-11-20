package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private Hand hand;
    private final int orderOfPlay;
    private List<String> hintReceived;

    public Player(String name, int orderOfPlay) {
        this.name = name;
        this.hand = new Hand();
        this.orderOfPlay = orderOfPlay;
        this.hintReceived = new ArrayList<>();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getOrderOfPlay() {
        return orderOfPlay;
    }

    public List<String> getHintReceived() {
        return hintReceived;
    }

    public boolean addCard(Card card) {
        if (hand.getCards().size() < 5) {
            hand.addCard(card);
            return true;
        }
        return false;
    }

    public Card playCard(int cardPosition) {
        if (hand.getCards().size() > 0) {
            return hand.dropCard(cardPosition);
        }
        return null;
    }

    public int getHandSize() {
        return getHand().getCards().size();
    }

    public String printHand() {
        return getHand().toString();
    }
}
