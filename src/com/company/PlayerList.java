package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    private static final Scanner scan = new Scanner(System.in);
    private final int numberOfPlayers;
    private PlayerNames playerNames;
    private List<Player> players;

    public PlayerList(int numberOfPlayers) {
        this.numberOfPlayers = checkNumberOfPlayers(numberOfPlayers);
        this.playerNames = new PlayerNames(numberOfPlayers);
        this.players = new ArrayList<>();
    }

    public PlayerNames getPlayerNames() {
        return playerNames;
    }
    
    public void setPlayerNames() {
        this.playerNames.setPlayerNames(numberOfPlayers);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int setNumberOfPlayers(int numberOfPlayers) {
        numberOfPlayers = checkNumberOfPlayers(numberOfPlayers);
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers() throws ArrayIndexOutOfBoundsException {
        if (numberOfPlayers != playerNames.getPlayerNames().length) {
            throw new ArrayIndexOutOfBoundsException("Number of Players does not match length of player names");
        } else {
            for (int i = 0; i < numberOfPlayers; i++) {
                players.add(new Player(playerNames.getPlayerNames()[i], i));
            }
        }
    }

    public int checkNumberOfPlayers(int numberOfPlayers) {
        
        boolean validAmountOfPlayers = numberOfPlayers >= 2 && numberOfPlayers <= 5 ? true : false;

        while (!validAmountOfPlayers) {
            System.out.println("Invalid input. We need a digit between 2 to 5\n");
            try {
                numberOfPlayers = scan.nextInt();
            } catch (Exception InputMismatchException) {
                numberOfPlayers = 0;
                continue;
            }

            if (numberOfPlayers <= 5 && numberOfPlayers >= 2) {
                validAmountOfPlayers = true;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
            scan.nextLine();
        }

        return numberOfPlayers;
    }


}
