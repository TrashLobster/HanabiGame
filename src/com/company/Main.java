package com.company;

import java.util.*;

public class Main {

    final static List<String> VALUES = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "one", "two", "three", "four", "five"));

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        GameAttributes game = new GameAttributes();
        PlayerList playerList = game.getPlayerList();
        List<Player> players = playerList.getPlayers();
        int playerSize = playerList.getNumberOfPlayers();
        StormTokens stormTokens = game.getStormTokens();
        CardDeck shuffledDeck = game.getCardDeck();

        System.out.println();
        // TODO: turn PlayerList List<Player> into an iterable
        for (int i = 0; i < playerSize; i++) {
            System.out.println(players.get(i).getName() + " : Order of Play " + players.get(i).getOrderOfPlay());
        }

        int countdownOfRounds = playerSize + 1;
        int turn = 0;
        int gameScore = game.getScore();
        while (game.getGameOn()) {
            turn++;
            for (int i = 0; i < playerSize; i++) {
                System.out.println();

                RoundController roundController = new RoundController(game.getPlayer(i), game);
                roundController.runRound(turn);

                game.calculateScore();

                if (shuffledDeck.size() == 0) {
                    System.out.println("There are no cards left in the deck! Each player has one more turn.");
                    countdownOfRounds--;
                }

                if (stormTokens.getLightningTokens() == 3 || countdownOfRounds == 0 || gameScore == 25) {
                    if (stormTokens.getLightningTokens() == 3) {
                        System.out.println(
                                "As you turned over the last storm token, the gods delivered their wrath with a storm and put an end to the fireworks.");
                        game.setScore(0);
                    }
                    game.endGame();
                    break;
                }
            }
        }
        System.out.println("\nYour final score is " + gameScore + ". And...");
        finalResultAnnouncement(gameScore);
    }

    public static void finalResultAnnouncement(int score) {
        if (score < 0) {
            System.out.println("Something is wrong. You shouldn't be able to get this score!");
        } else if (score <= 5) {
            System.out.println("Oh dear! The crowd booed.");
        } else if (score <= 10) {
            System.out.println("Poor! A smattering of applause.");
        } else if (score <= 15) {
            System.out.println("OK! The viewers have seen better.");
        } else if (score <= 20) {
            System.out.println("Good! The audience is pleased.");
        } else if (score <= 24) {
            System.out.println("Very good! The audience is enthusiastic!");
        } else if (score == 25) {
            System.out.println("Legendary! The audience will never forget this show!");
        }
    }
}
