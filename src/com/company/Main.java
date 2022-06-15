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

        // initalise players' hand
        for (int i; i < playerSize; i++) {
            int handHasCards = playerSize == 2 || playerSize == 3 ? 5 : 4;

            for (int j = handHasCards; j > 0; j--) {
                Card topCard = shuffledDeck.dropCard(0);
                players.get(i).addCard(topCard);
            }

        }

        boolean gameOn = true;
        int countdownOfRounds = playerSize + 1;
        int turn = 0;
        while (gameOn) {
            turn++;
            for (int i; i < playerSize; i++) {
                int score = 0;
                System.out.println();
                System.out.println("Turn " + turn);
                System.out.println(players.get(i).getName() + "'s turn.\nPlease choose one of the following actions:");
                printInstructions();
                boolean turnOver = false;

                while (!turnOver) {
                    boolean hasNextInt = scanner.hasNextInt();
                    int command = 10;
                    try {
                        command = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception ignored) {
                    } finally {
                        if (hasNextInt && command < 10 && command >= 0) {

                        } else {
                            scanner.nextLine();
                            System.out.println("Invalid input. Try again.");
                        }
                    }
                }

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

    public static boolean cancelEntry(String input) {
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("You have cancelled. Choose an action.\n");
            printInstructions();
            return true;
        }
        return false;
    }

    public static boolean playCard(Player player, FireworkCollection fireworkCollection, CardDeck discardPile,
            CardDeck cardDeck,
            NoteTokens noteTokens, StormTokens stormTokens) {
        while (true) {
            System.out.println("You currently have " + player.getHandSize() + " cards.");
            Card cardPlayed = chooseCardFromHand(player);
            scanner.nextLine();
            boolean cardAddedToPile = false;
            if (cardPlayed != null) {
                System.out.println(cardPlayed);
                for (Firework firework : fireworkCollection.getFireworks()) {
                    String fireworkColor = firework.getColor();
                    String cardColor = cardPlayed.getColour().toString();
                    int fireworkNextValue = firework.getNextValueExpected();
                    int cardValue = cardPlayed.getCardValue();

                    if (fireworkColor.equalsIgnoreCase(cardColor) && fireworkNextValue == cardValue) {
                        firework.addCard(cardPlayed);
                        cardAddedToPile = true;
                        System.out.println(cardPlayed + " has been added to the fireworks stack.");
                        fireworkCompletedReward(firework, noteTokens);
                    }
                }
                if (!cardAddedToPile) {
                    discardPile.addCard(cardPlayed);
                    stormTokens.flipStormTokens();
                    System.out.println(cardPlayed + " has been sent to the discard pile.");
                }
                addCardToHand(player, cardDeck);
                return true;
            } else {
                System.out.println("Incorrect selection. Please try again.");
            }
        }
    }

    public static void fireworkCompletedReward(Firework firework, NoteTokens noteTokens) {
        if (firework.checkCompletedStatus()) {
            System.out.println("The " + firework.getColor() + " is completed.");
            if (noteTokens.getBlackToken() > 0) {
                noteTokens.flipBlackToken();
            }
        }
    }

    public static void addCardToHand(Player player, CardDeck deck) {
        if (deck.size() != 0) {
            player.addCard(deck.dropCard(0));
        }
    }

    public static Card chooseCardFromHand(Player player) {
        int cardPosition = 0;
        boolean validChoice = false;
        if (player.getHandSize() != 0) {
            System.out.println("Which card do you want to play?");
            while (!validChoice) {
                boolean hasNextInt = scanner.hasNextInt();
                try {
                    cardPosition = scanner.nextInt();
                } catch (Exception ignored) {
                } finally {
                    if (hasNextInt && cardPosition < player.getHandSize() && cardPosition >= 0) {
                        validChoice = true;
                    } else {
                        scanner.nextLine();
                        System.out.println("Invalid input. Try again.");
                    }
                }
            }
        }
        return player.playCard(cardPosition);
    }

    public static void printPlayersHands(Player currentPlayer, List<Player> playerList) {
        for (Player playerInGame : playerList) {
            if (!playerInGame.equals(currentPlayer)) {
                System.out.println(playerInGame.getName() + "\n" + playerInGame.printHand());
            }
        }
    }

    public static void viewHintsGiven(Player player) {
        for (String hint : player.getHintReceived()) {
            System.out.println(hint);
            System.out.println();
        }
        if (player.getHintReceived().isEmpty()) {
            System.out.println("You have not yet received a hint.");
        }
    }

    public static boolean discardCard(Player player, CardDeck discardPile, NoteTokens noteTokens, CardDeck deck) {
        if (noteTokens.flipBlackToken()) {
            Card cardDiscarded = chooseCardFromHand(player);
            discardPile.addCard(cardDiscarded);
            System.out.println(cardDiscarded.toString() + " has been discarded.");
            addCardToHand(player, deck);
            return true;
        } else {
            System.out
                    .println("You cannot discard a card. You don't have a token to flip." + "\n\nTry another action.");
            printInstructions();
            return false;
        }
    }
}
