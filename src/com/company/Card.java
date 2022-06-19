package com.company;

public class Card {
    private final Colour colour;
    private final int cardValue;
    private final int id;

    public Card(Colour colour, int cardValue, int id) {
        this.colour = colour;
        this.cardValue = cardValue;
        this.id = id;
    }

    public Colour getColour() {
        return colour;
    }

    public String getColourAsString() {
        return colour.toString();
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return colour + " : " + cardValue;
    }

}
