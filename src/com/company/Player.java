package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
    private static final Scanner scan = new Scanner(System.in);
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
        System.out.println("Are you giving a colour hint or a number hint?");
        String typeOfHint = checkHintInput(scan.nextLine());
        String input = "";
        String amount = "0";
        
        switch (typeOfHint.toLowerCase()) {
            case "colour":
                System.out.println("Please enter the colour:");
                input = checkColourInput(scan.nextLine());
                amount = getHintAmount();
                break;
            case "number":
                System.out.println("Please enter the value: ");
                input = checkValueInput(scan.nextLine());
                amount = getHintAmount();
                break;
            default:
                break;
        }

        StringBuilder s = new StringBuilder(typeOfHint.toUpperCase());
        s.append(" : " + input + " (" + amount + "of them)");
        return s.toString();
    }

    public String getHintAmount() {
        System.out.println("How many of them are there?");
        return scan.nextLine();
    }

    public String checkHintInput(String hintInput) {
        boolean validHint = false;

        while (!validHint) {
            if (hintInput.equalsIgnoreCase("colour") || hintInput.equalsIgnoreCase("number")) {
                    validHint = true;
            }
            if(!validHint) {
                System.out.println("Invalid type of hint. Please choose another one:");
                hintInput = scan.nextLine();
            }
        }
        return hintInput;
    }

    public String checkColourInput(String colourInput) {
        boolean validColour = false;

        while (!validColour) {
            for (var colour : Colour.values()) {
                if (colour.toString().equalsIgnoreCase(colourInput)) {
                    validColour = true;
                }
            }
            if(!validColour) {
                System.out.println("Invalid colour. Please input another colour:");
                colourInput = scan.nextLine();
            }
        }
        return colourInput;
    }

    public String checkValueInput(String valueInput) {
        boolean validValue = false;

        while (!validValue) {
            for (String value : VALUES) {
                if (value.toString().equalsIgnoreCase(valueInput)) {
                    validValue = true;
                    continue;
                }
            }
            if(!validValue) {
                System.out.println("Invalid value. Please input another value:");
                valueInput = scan.nextLine();
            }
        }
        return valueInput;
    }

    public String printHint() {
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
