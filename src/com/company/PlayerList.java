package com.company;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private final int numberOfPlayers;
    private PlayerNames playerNames;
    private List<Player> players;

    public PlayerList() {
        this.numberOfPlayers = setNumberOfPlayers();
        this.playerNames = new PlayerNames(this.numberOfPlayers);
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

    public int setNumberOfPlayers() {
        int playersAsNumbers = checkNumberOfPlayers();
        return playersAsNumbers;
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

    public int checkNumberOfPlayers() {
        NumberInputQuery numberInputQuery = new NumberInputQuery(5, 2);
        return numberInputQuery.convertEntryToInteger();
    }

    public String checkOtherPlayersHands(Player currentPlayer) {
        StringBuilder s = new StringBuilder("\n");

        for (Player player : players) {
            if (currentPlayer == player) {
                continue;
            }
            String message = player.getName() + "\n" + player.printHand() + "\n";
            s.append(message);
        }
        s.append("\n");
        return s.toString();
    }

    public Player findOtherPlayerByName(Player currentPlayer) {
        System.out.println("Who are you looking for?");
        TextInputQuery textInputQuery = new TextInputQuery(playerNames.listOfPlayersWithoutCurrentPlayer(currentPlayer.getName()));
        String searchName = textInputQuery.getInputReceived();
        Player playerFound = currentPlayer;
        
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(searchName)) {
                playerFound = player;
            }
        }
        return playerFound;
    }

    public void fillPlayersHands(CardDeck cardDeck) {
        for (var player : players) {
            player.fillHands(getNumberOfPlayers(), cardDeck);
        }
    }
}
