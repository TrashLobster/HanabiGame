package com.company;

import java.util.*;

public class Game {

    private final PlayerList players;
    private final int numberOfPlayers;

    public Game() {
        this.numberOfPlayers = determineNumberOfPlayers();
        this.players = createPlayerList(numberOfPlayers, enterNamesOfPlayers(numberOfPlayers));
    }

}