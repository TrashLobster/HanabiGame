package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    private static final Scanner scan = new Scanner(System.in);
    private final int numberOfPlayers;
    private String[] playerNames;
    private List<Player> players;

    public PlayerList() {
        this.numberOfPlayers = determineNumberOfPlayers();
        this.playerNames = new String[numberOfPlayers];
        this.players = new ArrayList<>();
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayerNames() {
        Arrays.fill(playerNames, "");
        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = checkName();
        }
    }

    public void setPlayers() throws ArrayIndexOutOfBoundsException {
        if (numberOfPlayers != playerNames.length) {
            throw new ArrayIndexOutOfBoundsException("Number of Players does not match length of player names");
        } else {
            for (int i = 0; i < numberOfPlayers; i++) {
                players.add(new Player(playerNames[i], i));
            }
        }
    }

    public void printPlayerNames() {
        StringBuilder s = new StringBuilder("The players are:\n");
        for(int i = 0; i < playerNames.length; i++) {
            s.append(playerNames[i] + "\n");
        }
        System.out.println(s.toString()); 
    }

    public String checkName() {
        boolean isNameValid = false;
        String nameToBeTested = "";
        
        while (!isNameValid) {
            System.out.println("\nPlease enter the name of a player to add:\n");
            String name = scan.nextLine();
            isNameValid = !Arrays.asList(this.playerNames).contains(name);
            nameToBeTested = name;
        }

        return nameToBeTested;
    }

    public int determineNumberOfPlayers() {
        int numberOfPlayers = 0;
        boolean validAmountOfPlayers = false;

        while (!validAmountOfPlayers) {
            System.out.println("How many players do you want in this game? Choose between 2 to 5: ");
            try {
                numberOfPlayers = scan.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Invalid input. We need a digit between 2 to 5");
                numberOfPlayers = 0;
                continue;
            }

            if (numberOfPlayers <= 5 && numberOfPlayers >= 2) {
                validAmountOfPlayers = true;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
        scan.nextLine();
        return numberOfPlayers;
    }

}
