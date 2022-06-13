package com.company;

public class CardDeck extends CardCollection implements CardsRemovable{
    
    int[] possibleValues = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 4, 5 };

    public CardDeck() {
        super();
    }

    public void generateDeck() {
        int id = 0;
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

    public Card dropCard() throws ArrayIndexOutOfBoundsException {
        if (cards.size() > 0) {
            return cards.remove(cards.size() - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException("There are no cards to drop.");
        }
    }

    public Card dropCard(int cardPosition) throws ArrayIndexOutOfBoundsException {
        return cards.remove(cardPosition);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Cards in deck" + ":\n");
        s.append(super.toString());
        return s.toString();
    }

}
