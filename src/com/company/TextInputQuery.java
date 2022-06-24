package com.company;

import java.util.Scanner;

public class TextInputQuery implements InputQuery{
    private static final Scanner scan = new Scanner(System.in);
    private String inputReceived;
    private String[] acceptableWords = {};
    private boolean isInputAcceptable;

    public TextInputQuery(String[] acceptableWords) {
        this.inputReceived = "";
        this.acceptableWords = acceptableWords;
        this.isInputAcceptable = false;
        runInputQuery();
    }

    public TextInputQuery() {
        this.inputReceived = "";
        this.isInputAcceptable = false;
        runInputQueryWithoutComparison();
    }

    public String getInputReceived() {
        return inputReceived;
    }

    public boolean getIsInputAcceptable() {
        return isInputAcceptable;
    }

    public void setInputReceived(String newInput) {
        inputReceived = newInput;
    }
    
    public void accceptInput() {
        this.isInputAcceptable = true;
    }

    public void runInputQuery() {
        while(!isInputAcceptable) {
            checkInputAgainstAcceptableWords();
        }
    }

    public void runInputQueryWithoutComparison() {
        while(!isInputAcceptable) {
            checkInput();
        }
    }

    public void checkInputAgainstAcceptableWords() {
        System.out.println("Please enter an input:");
        enterInput();
        for (String acceptableWord : acceptableWords) {
            if (inputReceived.equalsIgnoreCase(acceptableWord)) {
                accceptInput();
            }
        }

        if (!isInputAcceptable) {
            System.out.println("\nInvalid search term entered. Please try another:");
        }
    }

    public void checkInput() {
        System.out.println("Please enter an input:");
        enterInput();
        accceptInput();
    }

    public void enterInput() {
        setInputReceived(scan.nextLine());
    }

}
