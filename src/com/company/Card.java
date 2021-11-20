package com.company;

public class Card {
    private final Color color;
    private final int cardValue;
    private final int id;

    enum Color {
        RED, YELLOW, GREEN, BLUE, WHITE,
    }

    public Card(Color color, int cardValue, int id) {
        this.color = color;
        this.cardValue = cardValue;
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

    @Override
    public String toString() {
        return color + " : " + cardValue;
    }

}
