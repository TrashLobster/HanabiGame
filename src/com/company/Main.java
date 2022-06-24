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

                // score recalculated at the end of each turn
                for (Firework firework : game.getFireworkCollection().getFireworks()) {
                    gameScore += firework.getNextValueExpected() - 1;
                }

                if (shuffledDeck.size() == 0) {
                    System.out.println("There are no cards left in the deck! Each player has one more turn.");
                    countdownOfRounds--;
                }

                if (stormTokens.getLightningTokens() == 3 || countdownOfRounds == 0 || gameScore == 25) {
                    if (stormTokens.getLightningTokens() == 3) {
                        System.out.println(
                                "As you turned over the last storm token, the gods delivered their wrath with a storm and put an end to the fireworks.");
                        gameScore = 0;
                    }
                    System.out.println("\nYour final score is " + gameScore + ". And...");

                    finalResultAnnouncement(gameScore);

                    System.out.println("\nGame over. Thanks for playing!");

                    game.endGame();
                    break;
                }
            }
        }
    }

    public static void finalResultAnnouncement(int score) {
        switch (score) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Oh dear! The crowd booed.");
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("Poor! A smattering of applause.");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                System.out.println("OK! The viewers have seen better.");
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                System.out.println("Good! The audience is pleased.");
                break;
            case 21:
            case 22:
            case 23:
            case 24:
                System.out.println("Very good! The audience is enthusiastic!");
                break;
            case 25:
                System.out.println("Legendary! The audience will never forget this show!");
                break;
            default:
                break;
        }
    }
}
