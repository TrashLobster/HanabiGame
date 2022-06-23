package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerNames {
    private static final Scanner scan = new Scanner(System.in); 
    private String[] playerNames;

    public PlayerNames(int numberOfPlayers) {
        this.playerNames = new String[numberOfPlayers];
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(int numberOfPlayers) {
        Arrays.fill(playerNames, "");
        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = checkName();
        }
    }

    public String[] listOfPlayersWithoutCurrentPlayer(String currentPlayer) {
        int listLength = this.playerNames.length;
        String[] newListOfNames = new String[listLength - 1];
        int pointerLocation = 0;
        for (String playerName : playerNames) {
            if (!playerName.equalsIgnoreCase(currentPlayer)) {
                newListOfNames[pointerLocation] =  playerName;
                pointerLocation++;
            }
        }
        return newListOfNames;
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

    public void printPlayerNames() {
        StringBuilder s = new StringBuilder("The players are:\n");
        for(int i = 0; i < playerNames.length; i++) {
            s.append(playerNames[i] + "\n");
        }
        System.out.println(s.toString()); 
    }
}
