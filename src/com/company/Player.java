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

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getHandSize() {
        return getHand().getCards().size();
    }

    public String printHand() {
        return getHand().toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Player: ");
        s.append(name + "\nOrder of Play: " + (orderOfPlay + 1));
        return s.toString();
    }
}
