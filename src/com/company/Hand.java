package com.company;

public class Hand extends CardCollection implements CardsRemovable{

    public Hand() {
        super();
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
        StringBuilder s = new StringBuilder("Cards in hand" + ":\n");
        s.append(super.toString());
        return s.toString();
    }
}
