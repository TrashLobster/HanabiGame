package com.company;

public interface CardAction {
    void addCard(Card card);
    Card dropCard(int cardPosition);
    int size();
    void shuffle();
}
