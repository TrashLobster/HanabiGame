package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    private static final Scanner scan = new Scanner(System.in);
    private final int numberOfPlayers;
    private PlayerNames playerNames;
    private List<Player> players;

    public PlayerList() {
        this.numberOfPlayers = setNumberOfPlayers();
        this.playerNames = new PlayerNames(numberOfPlayers);
        this.players = new ArrayList<>();
    }

    public PlayerNames getPlayerNames() {
        return playerNames;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayerNames() {
        this.playerNames.setPlayerNames(numberOfPlayers);
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

    public int setNumberOfPlayers() {
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
