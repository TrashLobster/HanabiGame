package com.company;

import java.util.*;

public class GameAttributes {
    private static final Scanner scan = new Scanner(System.in);
    private PlayerList playerList;
    private NoteTokens noteTokens;
    private StormTokens stormTokens;
    private FireworkCollection fireworkCollection;
    private CardDeck deck;
    private CardDeck discardPile;
    private boolean gameState = true;
    private int score = 0;

    public GameAttributes() {
        initialiseTokens();
        initialiseFireworkCollections();
        initaliseCardDecks();
        initalisePlayers();
    }

    public void initalisePlayers() {
        System.out.println("Welcome to the game, let's get you started.\nHow many players are there?");
        this.playerList = new PlayerList(scan.nextInt());
        this.playerList.setPlayerNames();
        this.playerList.setPlayers();
        this.playerList.fillPlayersHands(deck);
    }

    public boolean getGameState() {
        return this.gameState;
    }

    public void endGame() {
        this.gameState = false;
    }

    public int getScore() {
        return this.score;
    }

    public void changeScore(int updateScoreBy) {
        score += updateScoreBy;
    }

    public Player getPlayer(int playerPosition) {
        return this.playerList.getPlayers().get(playerPosition);
    }

    public PlayerList getPlayerList() {
        return this.playerList;
    }

    public String checkOtherPlayersHands(Player currentPlayer) {
        return playerList.checkOtherPlayersHands(currentPlayer);
    }
    
    public void initialiseTokens() {
        this.noteTokens = new NoteTokens();
        this.stormTokens = new StormTokens();
    }

    public NoteTokens getNoteTokens() {
        return noteTokens;
    }

    public StormTokens getStormTokens() {
        return stormTokens;
    }

    public String checkTokens() {
        StringBuilder s = new StringBuilder();
        s.append("White tokens: " + noteTokens.getWhiteToken() + "\nBlack tokens " + noteTokens.getBlackToken());
        s.append("\nStorm tokens: " + stormTokens.getStormTokens() + "\nLightning tokens: "
                + stormTokens.getLightningTokens());
        s.append("\n");
        return s.toString();
    }

    public void initialiseFireworkCollections() {
        this.fireworkCollection = new FireworkCollection();
    }

    public FireworkCollection getFireworkCollection() {
        return fireworkCollection;
    }

    public String checkFireworks() {
        StringBuilder s = new StringBuilder("\nFireworks on display:");
        for (var firework : fireworkCollection.getFireworks()) {
            s.append(firework.toString());
        }
        s.append("\n");
        return s.toString();
    }

    public void initaliseCardDecks() {
        this.deck = new CardDeck();
        this.deck.generateDeck();
        this.deck.shuffle();
        this.discardPile = new CardDeck();
    }

    public CardDeck getCardDeck() {
        return deck;
    }

    public int getDeckSize() {
        return deck.size();
    }

    public CardDeck getDiscardPile() {
        return discardPile;
    }

    public int getDiscardPileSize() {
        return discardPile.size();
    }

    public String checkDiscardPile() {
        return discardPile.toString();
    }

}