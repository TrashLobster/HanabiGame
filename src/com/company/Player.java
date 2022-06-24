package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private final String name;
    private Hand hand;
    private final int orderOfPlay;
    private List<String> hintReceived;
    final static List<String> VALUES = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "one", "two", "three", "four", "five"));

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

    public Card playCard(int cardPosition) {
        return getHand().dropCard(cardPosition);
    }

    public void fillHands(int amountOfPlayers, CardDeck deck) {
        int handHasCards = amountOfPlayers == 2 || amountOfPlayers == 3 ? 5 : 4;
        for (int j = handHasCards; j > 0; j--) {
            Card topCard = deck.dropCard(0);
            addCard(topCard);
        }
    }

    public String printHand() {
        return getHand().toString();
    }

    public void receiveHint(String hint) {
        hintReceived.add(hint);
    }

    public String giveHint() {
        String typeOfHint = checkHintInput();
        String input = "";
        String amount = "0";

        switch (typeOfHint.toLowerCase()) {
            case "colour":
                System.out.println("Please enter the colour:");
                input = checkColourInput();
                amount = getHintAmount();
                break;
            case "number":
                System.out.println("Please enter the value: ");
                input = checkValueInput();
                amount = getHintAmount();
                break;
            default:
                break;
        }

        StringBuilder s = new StringBuilder(typeOfHint.toUpperCase());
        s.append(" : " + input + " (" + amount + " of them)");
        return s.toString();
    }

    public String getHintAmount() {
        // not sorting out whether there should be 5 cards or 4 cards in hand for each person
        System.out.println("How many of them are there?");
        NumberInputQuery numberInputQuery = new NumberInputQuery(5, 0);
        return numberInputQuery.getInputReceived();
    }

    public String checkHintInput() {
        System.out.println("Are you giving a colour hint or a number hint?");
        String[] acceptableWords = {"colour", "number"};
        TextInputQuery textInputQuery = new TextInputQuery(acceptableWords);
        return textInputQuery.getInputReceived();
    }

    public String checkColourInput() {
        Colour[] colours = Colour.values();
        String[] colourStringList = new String[colours.length];
        for (int i=0; i<Colour.values().length; i++) {
            colourStringList[i] = colours[i].name();
        }
        TextInputQuery textInputQuery = new TextInputQuery(colourStringList);
        return textInputQuery.getInputReceived();
    }

    public String checkValueInput() {
        // hardcoded the upperbound and lowerbound
        NumberInputQuery numberInputQuery = new NumberInputQuery(5, 1);
        return numberInputQuery.getInputReceived();
    }

    public String hintAsString() {
        StringBuilder s = new StringBuilder("Hints:");
        if (hintReceived.size() == 0) {
            s.append("\nThere were no hints given");
        }
        for (var hint : hintReceived) {
            s.append("\n" + hint);
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Player: ");
        s.append(name + "\nOrder of Play: " + (orderOfPlay + 1));
        return s.toString();
    }
}
