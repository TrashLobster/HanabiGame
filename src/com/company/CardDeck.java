package com.company;

public class CardDeck extends CardCollection {
    private final String name;

    int[] possibleValues = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 4, 5 };

    public CardDeck(String name) {
        super();
        this.name = name;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in " + name + ":\n");
        for (Card card : cards) {
            s.append("Card #").append(card.getId()).append(" ").append(card).append("\n");
        }
        return s.toString();
    }

}
