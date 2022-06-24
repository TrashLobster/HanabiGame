package com.company;

public class GameAttributes {
    private PlayerList playerList;
    private NoteTokens noteTokens;
    private StormTokens stormTokens;
    private FireworkCollection fireworkCollection;
    private CardDeck deck;
    private CardDeck discardPile;
    private boolean gameOn = true;
    private int score = 0;

    public GameAttributes() {
        initialiseTokens();
        initialiseFireworkCollections();
        initaliseCardDecks();
        initalisePlayers();
    }

    public void initalisePlayers() {
        System.out.println("Welcome to the game, let's get you started.\nHow many players are there?");
        boolean isGameSetUp = false;
        while (!isGameSetUp) {
            this.playerList = new PlayerList();
            this.playerList.setPlayerNames();
            this.playerList.setPlayers();
            this.playerList.fillPlayersHands(deck);
            isGameSetUp = true;
        }
    }

    public boolean getGameOn() {
        return this.gameOn;
    }

    public void endGame() {
        System.out.println("\nGame over. Thanks for playing!");
        this.gameOn = false;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void changeScore(int updateScoreBy) {
        score += updateScoreBy;
    }

    public void calculateScore() {
        setScore(0);
        for (Firework firework : getFireworkCollection().getFireworks()) {
            score += firework.getNextValueExpected() - 1;
        }
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
            s.append("\n" + firework.toString());
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