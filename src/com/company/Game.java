package com.company;

import java.util.*;

public class Game {
    private static final Scanner scan = new Scanner(System.in);
    private PlayerList playerList;
    private NoteTokens noteTokens;
    private StormTokens stormTokens;
    private FireworkCollection fireworkCollection;
    private CardDeck deck;
    private CardDeck discardPile;

    public Game() {
        initialiseTokens();
        initialiseFireworkCollections();
        initalisePlayers();
        initaliseCardDecks();
    }

    public void initalisePlayers() {
        System.out.println("Welcome to the game, let's get you started.\nHow many players are there?");
        this.playerList = new PlayerList(scan.nextInt());
        this.playerList.setPlayerNames();
        this.playerList.setPlayers();
    }

    public void initialiseTokens() {
        this.noteTokens = new NoteTokens();
        this.stormTokens = new StormTokens();
    }

    public void initialiseFireworkCollections() {
        this.fireworkCollection = new FireworkCollection();
    }
    
    public void initaliseCardDecks() {
        this.deck = new CardDeck();
        this.deck.generateDeck();
        this.deck.shuffle();
        this.discardPile = new CardDeck();
    }

    public PlayerList getPlayerList() {
        return this.playerList;
    }

    public NoteTokens getNoteTokens() {
        return noteTokens;
    }

    public StormTokens getStormTokens() {
        return stormTokens;
    }

    public FireworkCollection getFireworkCollection() {
        return fireworkCollection;
    }

    public CardDeck getCardDeck() {
        return deck;
    }

    public CardDeck getDiscardPile() {
        return discardPile;
    }

    // no setters - there is no reason why these should be set again after initalisation


}