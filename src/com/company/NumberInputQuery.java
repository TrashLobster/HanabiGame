package com.company;

import java.util.Scanner;

public class NumberInputQuery implements InputQuery{
    private static final Scanner scan = new Scanner(System.in);
    private String inputReceived;
    private int upperBound;
    private int lowerBound;
    private boolean isEntryANumber;
    private boolean isNumberAcceptable;

    public NumberInputQuery(int upperBound, int lowerBound) {
        this.inputReceived = "";
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        runInputQuery();
    }

    public String getInputReceived() {
        return inputReceived;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public boolean getIsEntryANumber() {
        return isEntryANumber;
    }

    public boolean getIsNumberAcceptable() {
        return isNumberAcceptable;
    }
        
    public int convertEntryToInteger() {
        return Integer.parseInt(inputReceived);
    }

    public void setInputReceived(String newInput) {
        inputReceived = newInput;
    }

    public void confirmEntryAsNumber() {
        this.isEntryANumber = true;
    }

    public void accceptNumber() {
        this.isNumberAcceptable = true;
    }
    
    public void runInputQuery() {
       
        while(!isNumberAcceptable) {
            checkIfInputIsNumber();
            if (isNumberWithinBounds()) {
                accceptNumber();
            } else {
                System.out.println("Please try another number");
                enterInput();
            }
        }
    }

    public void checkIfInputIsNumber() {
        while(!isEntryANumber) {
            System.out.println("Please enter a number:");
            enterInput();
            if (isEntryCorrectType()) {
                confirmEntryAsNumber();

            } else {
                System.out.println("Entry is not a number. Please try again:");
            }
        }
    }

    public boolean isEntryCorrectType() {
        return inputReceived.matches("[0-9]+");
    }

    public void enterInput() {
        setInputReceived(scan.nextLine());
    }

    public boolean isNumberWithinBounds() {
        return convertEntryToInteger() >= lowerBound && convertEntryToInteger() <= upperBound;
    }

}
