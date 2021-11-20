package com.company;

import java.util.*;

import static com.company.ThreadColour.*;

public class Main {

    final static List<String> COLORS = new ArrayList<>(Arrays.asList("yellow", "red", "blue", "white", "red"));
    final static List<String> VALUES = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "one", "two", "three", "four", "five"));

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        FireworkCollection fireworkCollection = new FireworkCollection();

        CardDeck deck = new CardDeck("Playing Deck");
        CardDeck discardPile = new CardDeck("Discard Pile");
        NoteTokens noteTokens = new NoteTokens();
        StormTokens stormTokens = new StormTokens();
        List<String> listOfPlayerNames = new ArrayList<>();

        int numberOfPlayers = determineNumberOfPlayers();

        List<Player> playerList = createPlayersAndOrderOfPlay(numberOfPlayers, listOfPlayerNames);

        for (Player player : playerList) {
            System.out.println(player.getName() + " : Order of Play " + player.getOrderOfPlay());
        }

        deck.generateDeck();
        deck.shuffleDeck();

        for (Player player : playerList) {
            int handHasCards = 4;
            if (playerList.size() == 2 || playerList.size() == 3) {
                handHasCards = 5;
            }
            while (handHasCards != 0) {
                player.addCard(deck.dropCard(0));
                handHasCards--;
            }
        }

        boolean gameOn = true;
        int countdownOfRounds = playerList.size() + 1;
        int turn = 0;
        while (gameOn) {
            turn++;
            for (Player player : playerList) {
                int score = 0;
                System.out.println();
                System.out.println("Turn " + turn);
                System.out.println(player.getName() + "'s turn.\nPlease choose one of the following actions:");
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
                            switch (command) {
                            case 0:
                                printInstructions();
                                break;
                            case 1:
                                reviewTokens(noteTokens, stormTokens);
                                System.out.println("Please choose another action.");
                                break;
                            case 2:
                                printPlayersHands(player, playerList);
                                System.out.println("Please choose another action.");
                                break;
                            case 3:
                                printFireworks(fireworkCollection);
                                System.out.println("Please choose another action.");
                                break;
                            case 4:
                                printDiscardPile(discardPile);
                                System.out.println("Please choose another action.");
                                break;
                            case 5:
                                viewHintsGiven(player);
                                System.out.println("Please choose another action.");
                                break;
                            case 6:
                                turnOver = giveHint(playerList, listOfPlayerNames, player, noteTokens);
                                break;
                            case 7:
                                turnOver = discardCard(player, discardPile, noteTokens, deck);
                                break;
                            case 8:
                                turnOver = playCard(player, fireworkCollection, discardPile, deck, noteTokens, stormTokens);
                                break;
                            case 9:
                                turnOver = true;
                                gameOn = false;
                                break;
                            default:
                                break;
                            }
                            // }
                        } else {
                            scanner.nextLine();
                            System.out.println("Invalid input. Try again.");
                        }
                    }
                }

                for (Firework firework : fireworkCollection.getFireworks()) {
                    score += firework.getNextValueExpected() - 1;
                }

                if (deck.size() == 0) {
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
                    System.out.println(ANSI_RESET + "\nGame over. Thanks for playing!");

                    gameOn = false;
                    break;
                }
            }
        }
    }

    public static int determineNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = 0;
        boolean validAmountOfPlayers = false;
        while (!validAmountOfPlayers) {
            System.out.println("How many players do you want in this game? Choose between 2 to 5: ");
            try {
                numberOfPlayers = scanner.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Invalid input. We need a digit between 2 to 5");
                numberOfPlayers = 0;
                scanner.nextLine();
                continue;
            }
            if (numberOfPlayers <= 5 && numberOfPlayers >= 2) {
                System.out.println("There are " + numberOfPlayers + " players in this game.");
                validAmountOfPlayers = true;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
        scanner.close();
        return numberOfPlayers;
    }

    public static List<Player> createPlayersAndOrderOfPlay(int numberOfPlayers, List<String> listOfPlayerNames) {
        List<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Please enter the player's name:");
            String name = scanner.nextLine();
            Player player = new Player(name, i);
            playerList.add(player);
            listOfPlayerNames.add(name.toLowerCase());
        }
        scanner.close();
        return playerList;
    }

    public static void printInstructions() {
        System.out.println(
                ANSI_BLUE + "0 : Show possible actions" + "\n1 : Check tokens" + "\n2 : View other players' cards"
                        + "\n3 : View fireworks" + "\n4 : View discard pile" + "\n5 : View hints given to you"
                        + "\n6 : Give a hint" + "\n7 : Discard a card" + "\n8 : Play a card" + "\n9 : Quit the game");
        System.out.println(ANSI_RESET);
    }

    public static boolean giveHint(List<Player> playerList, List<String> listOfPlayerNames, Player player,
            NoteTokens noteTokens) {
        boolean continueLoop = true;
        boolean validHintType = false;
        boolean correctPersonToGiveHint = false;
        String targetPlayer = "";

        while (!correctPersonToGiveHint) {
            StringBuilder s = new StringBuilder("Which of these players do you want to give a hint to:");
            for (Player otherPlayer : playerList) {
                if (!player.equals(otherPlayer)) {
                    s.append("\n").append(otherPlayer.getName());
                }
            }
            System.out.println(s);
            targetPlayer = scanner.nextLine();
            if (targetPlayer.equalsIgnoreCase(player.getName())) {
                System.out.println("You cannot give yourself a hint. Please try again.");
            } else if (!listOfPlayerNames.contains(targetPlayer.toLowerCase())) {
                System.out.println("Invalid player name. Please try again.");
            } else if (cancelEntry(targetPlayer)) {
                validHintType = true;
                continueLoop = false;
            } else {
                correctPersonToGiveHint = true;
            }
        }

        StringBuilder hint = new StringBuilder();

        while (!validHintType) {
            System.out.println("What type of hint do you want to make? Colour or value?");
            String hintType = scanner.nextLine();
            if (hintType.equalsIgnoreCase("colour") || hintType.equalsIgnoreCase("color")) {
                System.out.println("What colour?");
                String colour = scanner.nextLine();
                if (checkIfValidColour(colour)) {
                    hint.append("Colour: ").append(colour);
                    System.out.println("How many of them are there?");
                    int numberOfCardsMatchingColour = scanner.nextInt();
                    scanner.nextLine();
                    hint.append("\nNumber of matching cards: ").append(numberOfCardsMatchingColour);
                    hint.append("\nPosition(s):");

                    if (numberOfCardsMatchingColour == 0) {
                        hint.append("You have no ").append(colour.toLowerCase()).append(" cards.");
                        validHintType = true;
                    } else if (numberOfCardsMatchingColour <= player.getHandSize()) {
                        for (int i = 0; i < numberOfCardsMatchingColour; i++) {
                            System.out.println("What is the position of card " + (i + 1) + "?");
                            hint.append(" ").append(scanner.nextInt());
                        }
                        validHintType = true;
                    } else {
                        System.out.println("Invalid number of cards entered. Please try again.");
                    }
                } else if (cancelEntry(colour)) {
                    validHintType = true;
                    continueLoop = false;
                } else {
                    System.out.println("Invalid colour. Please try again.");
                }

            } else if (hintType.equalsIgnoreCase("value")) {
                System.out.println("What value?");
                String value = scanner.nextLine();
                if (checkIfValidValue(value)) {
                    hint.append("Value: ").append(value);
                    System.out.println("How many of them are there?");
                    int numberOfCardsMatchingColour = scanner.nextInt();
                    scanner.nextLine();
                    hint.append("\nNumber of matching cards: ").append(numberOfCardsMatchingColour);
                    hint.append("\nPosition(s):");

                    if (numberOfCardsMatchingColour == 0) {
                        hint.append(" You have no cards of value ").append(value.toLowerCase());
                        validHintType = true;
                    } else if (numberOfCardsMatchingColour <= player.getHandSize()) {
                        for (int i = 0; i < numberOfCardsMatchingColour; i++) {
                            System.out.println("What is the position of card " + (i + 1) + "?");
                            hint.append(" ").append(scanner.nextInt());
                        }
                        validHintType = true;
                    } else {
                        System.out.println("Invalid number of cards entered. Please try again.");
                    }
                } else if (cancelEntry(value)) {
                    validHintType = true;
                    continueLoop = false;
                } else {
                    System.out.println("Invalid colour. Please try again.");
                }
            } else if (cancelEntry(hintType)) {
                validHintType = true;
                continueLoop = false;
            } else {
                System.out.println("Not a valid type of hint. Try again.");
                hint = new StringBuilder();
            }
        }

        for (Player otherPlayer : playerList) {
            if (otherPlayer.getName().equalsIgnoreCase(targetPlayer)) {
                otherPlayer.getHintReceived().add(hint.toString());
            }
        }

        if (continueLoop) {
            noteTokens.flipWhiteToken();
        }

        return continueLoop;
    }

    public static boolean cancelEntry(String input) {
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("You have cancelled. Choose an action.\n");
            printInstructions();
            return true;
        }
        return false;
    }

    public static boolean checkIfValidColour(String colourInput) {
        return (COLORS.contains(colourInput));
    }

    public static boolean checkIfValidValue(String value) {
        return (VALUES.contains(value));
    }

    public static boolean playCard(Player player, FireworkCollection fireworkCollection, CardDeck discardPile, CardDeck cardDeck,
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
                    String cardColor = cardPlayed.getColor().toString();
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

    public static void reviewTokens(NoteTokens noteTokens, StormTokens stormTokens) {
        System.out.println(
                "White tokens: " + noteTokens.getWhiteToken() + "\nBlack tokens " + noteTokens.getBlackToken());
        System.out.println();
        System.out.println("Storm tokens: " + stormTokens.getStormTokens() + "\nLightning tokens: "
                + stormTokens.getLightningTokens());
        System.out.println();
    }

    public static void printPlayersHands(Player currentPlayer, List<Player> playerList) {
        for (Player playerInGame : playerList) {
            if (!playerInGame.equals(currentPlayer)) {
                System.out.println(playerInGame.getName() + "\n" + playerInGame.printHand());
            }
        }
    }

    public static void printFireworks(FireworkCollection fireworkCollection) {
        System.out.println("Fireworks on display:");
        for (var firework : fireworkCollection.getFireworks()) {
            System.out.println(firework.toString());
        }
    }

    public static void printDiscardPile(CardDeck discardPile) {
        if (discardPile.size() == 0) {
            System.out.println("No cards in discard pile");
        } else {
            System.out.println(discardPile);
        }
        System.out.println();
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
