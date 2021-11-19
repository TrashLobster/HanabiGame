package com.company;

import java.util.ArrayList;
import java.util.List;

public class Fireworks {
    private List<FireworksCollection> fireworksCollections;
    private boolean allCollectionComplete;

    public Fireworks() {
        FireworksCollection redFireworks = new FireworksCollection("Red");
        FireworksCollection yellowFireworks = new FireworksCollection("Yellow");
        FireworksCollection greenFireworks = new FireworksCollection("Green");
        FireworksCollection blueFireworks = new FireworksCollection("Blue");
        FireworksCollection whiteFireworks = new FireworksCollection("White");
        this.fireworksCollections = new ArrayList<>();
        this.fireworksCollections.add(redFireworks);
        this.fireworksCollections.add(yellowFireworks);
        this.fireworksCollections.add(greenFireworks);
        this.fireworksCollections.add(blueFireworks);
        this.fireworksCollections.add(whiteFireworks);
        this.allCollectionComplete = false;
    }

    public List<FireworksCollection> getFireworksCollections() {
        return fireworksCollections;
    }

    public void addCardToCollection(Card card) {
        for(FireworksCollection fireworksCollection : fireworksCollections) {
            if(fireworksCollection.getColor().toString().toLowerCase().equals(card.getColor().toString().toLowerCase())) {
                fireworksCollection.addCard(card);
            }
        }
    }

    public boolean areAllCollectionComplete() {
        for(FireworksCollection fireworks : fireworksCollections) {
            if(fireworks.checkCompletedStatus()) {
                allCollectionComplete = true;
            } else {
                allCollectionComplete = false;
                break;
            }
        }
        return allCollectionComplete;
    }

    public class FireworksCollection {
        private List<Card> cards;
        private String color;
        private boolean completed;
        private int nextValueExpected;

        public FireworksCollection(String color) {
            this.cards = new ArrayList<>();
            this.color = color;
            this.completed = false;
            this.nextValueExpected = 1;
        }

        public boolean checkCompletedStatus() {
            if(nextValueExpected > 5) {
                completed = true;
            }
            return completed;
        }

        public List<Card> getCards() {
            return cards;
        }

        public String getColor() {
            return color;
        }

        public int getNextValueExpected() {
            return nextValueExpected;
        }

        public void addCard(Card card) {
            cards.add(card);
            nextValueExpected++;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(color + ": ");
            for(Card card : cards) {
                s.append(card.getCardValue() + " ");
            }
            return s.toString();
        }
    }
}
