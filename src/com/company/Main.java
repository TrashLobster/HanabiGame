package com.company;

import java.util.*;

import static com.company.ThreadColour.*;

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
        NoteTokens noteTokens = game.getNoteTokens();
        CardDeck shuffledDeck = game.getCardDeck();
        CardDeck discardPile = game.getDiscardPile();

        // TODO: turn PlayerList List<Player> into an iterable
        for (int i; i < playerSize; i++) {
            System.out.println(players.get(i).getName() + " : Order of Play " + players.get(i).getOrderOfPlay());
        }

        boolean gameOn = true;
        int countdownOfRounds = playerSize + 1;
        int turn = 0;
        while (gameOn) {
            turn++;
            for (int i; i < playerSize; i++) {
                System.out.println();
                System.out.println("Turn " + turn);
                System.out.println(players.get(i).getName() + "'s turn.\nPlease choose one of the following actions:");
                
                RoundController roundController = new RoundController(game.getPlayer(i), game);
                roundController.runRound(turn);

                for (Firework firework : fireworkCollection.getFireworks()) {
                    score += firework.getNextValueExpected() - 1;
                }

                if (shuffledDeck.size() == 0) {
                    System.out.println("There are no cards left in the deck! Each player has one more turn.");
                    countdownOfRounds--;
                }

                if (!gameOn || stormTokens.getLightningTokens() == 3 || countdownOfRounds == 0 || score == 25) {
                    if (stormTokens.getLightningTokens() == 3) {
                        System.out.println(
                                "As you turned over the last storm token, the gods delivered their wrath with a storm and put an end to the fireworks.");
                        score = 0;
                    }
                    System.out.println("\nYour final score is " + score + ". And...");

                    finalResultAnnouncement(score);

                    System.out.println(ANSI_RESET + "\nGame over. Thanks for playing!");

                    gameOn = false;
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
                System.out.println(ANSI_BLACK + "Oh dear! The crowd booed.");
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println(ANSI_PURPLE + "Poor! A smattering of applause.");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                System.out.println(ANSI_CYAN + "OK! The viewers have seen better.");
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                System.out.println(ANSI_BLUE + "Good! The audience is pleased.");
                break;
            case 21:
            case 22:
            case 23:
            case 24:
                System.out.println(ANSI_GREEN + "Very good! The audience is enthusiastic!");
                break;
            case 25:
                System.out.println(ANSI_RED + "Legendary! The audience will never forget this show!");
                break;
            default:
                break;
        }
    }
}
