package com.company;

public class Card {
    private final Color color;
    private final int cardValue;
    private boolean played;
    private final int id;

    enum Color {
        RED,
        YELLOW,
        GREEN,
        BLUE,
        WHITE,
    }

    public Card(Color color, int cardValue, boolean played, int id) {
        this.color = color;
        this.cardValue = cardValue;
        this.played = played;
        this.id = id;
    }
    public Card(Color color, int cardValue, int id) {
        this.color = color;
        this.cardValue = cardValue;
        this.played = false;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getId() {
        return id;
    }

    public boolean isPlayed() {
        if(!isPlayed()) {
            played = true;
        }
        return played;
    }

    public void playCard() {
        // when a card is played, it will signal that its value and color are viewable to all players
        // otherwise, its value and color should only be viewable to its owner/player
        if(!played) {
            played = true;
        }
    }

    @Override
    public String toString() {
        return color + " : " + cardValue;
    }

}
