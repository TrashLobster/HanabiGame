package com.company;

import java.util.Scanner;

public class RoundController {
    private static final Scanner scan = new Scanner(System.in);
    private Player player;
    private GameAttributes gameAttributes;

    public RoundController(Player player, GameAttributes gameAttributes) {
        this.player = player;
        this.gameAttributes = gameAttributes;
    }

    public void runRound(int roundNumber) {
        System.out.println();
        System.out.println("Turn " + roundNumber);
        System.out.println(player.getName() + "'s turn.\nPlease choose one of the following actions:");
        printInstructions();
        boolean turnOver = false;

        while (!turnOver) {
            turnOver = respondToPlayerChoice(scan.nextInt());
        }
    }

    public boolean respondToPlayerChoice(int choice) {
        boolean turnOver = false;
        choice = checkInstructionChoice(choice);
        switch (choice) {
            case 0:
                printInstructions();
                break;
            case 1:
                System.out.println(gameAttributes.checkTokens());
                break;
            case 2:
                System.out.println(gameAttributes.checkOtherPlayersHands(this.player));
                break;
            case 3:
                System.out.println(gameAttributes.checkFireworks());
                break;
            case 4:
                System.out.println(gameAttributes.checkDiscardPile());
                break;
            case 5:
                System.out.println(player.printHint());
                break;
            case 6:
                giveHint();
                turnOver = true;
                break;
            case 7:
                runDiscardCardProcess();
                turnOver = true;
                break;
            case 8:
                runPlayCardProcess();
                turnOver = true;
                break;
            case 9:
                quitGame();
                turnOver = true;
                break;
            default:
                break;
            
        }
        return turnOver;
    }

    public void quitGame() {
        gameAttributes.endGame();
    }

    public int checkChoice(int choice, int choiceUpperLimit, int choiceLowerLimit) {
        boolean validChoice = false;

        while (!validChoice) {
            if (choice < choiceLowerLimit || choice > choiceUpperLimit) {
                System.out.println("Invalid choice, try again.");
                choice = scan.nextInt();
            } else {
                validChoice = true;
            }
        }

        return choice;
    }

    public int checkInstructionChoice(int choice) {
        return checkChoice(choice, 9, 0);
    }

    public int checkCardChoice(int cardPosition) {
        return checkChoice(cardPosition, player.getHandSize(), 0);
    }

    public void giveHint() {
        try {
            gameAttributes.getNoteTokens().flipWhiteToken();
            
            PlayerList playerList = gameAttributes.getPlayerList();

            System.out.println("Which player will you be giving a hint to?\n");
            String searchName = scan.nextLine();
            Player targetPlayer = playerList.findPlayerByName(searchName, player);

            System.out.println("What hint will you give them?\n");
            String hint = player.giveHint();
            targetPlayer.receiveHint(hint);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You don't have anymore hints to give! All tokens have been flipped.");
        }
    }

    public Card chooseCardToPlay() {
        System.out.println(player.getHand().getCards().toString());
        System.out.println("Which card would you like to play?");

        int cardPosition = checkCardChoice(scan.nextInt());

        return player.playCard(cardPosition);
    }

    public void runPlayCardProcess() {
        Card cardPlayed = chooseCardToPlay();

        boolean isCardCorrectlyPlayed = addCardToFireworks(cardPlayed);

        if (!isCardCorrectlyPlayed) {
            gameAttributes.getStormTokens().flipStormTokens();
            gameAttributes.getDiscardPile().addCard(cardPlayed);
        }
        
        try {
            Card cardDeckTopCard = gameAttributes.getCardDeck().dropCard();
            player.getHand().addCard(cardDeckTopCard);
        } catch (Exception e) {
            // TODO: fix up this part of exceptionc catching - what happens when deck is empty?
            System.out.println("There are no cards to add to the deck.");
        }        
    }

    public void runDiscardCardProcess() {
        try {
            gameAttributes.getNoteTokens().flipBlackToken();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't discard any cards! All tokens have been flipped.");
        }

        Card cardDiscarded = chooseCardToPlay();

        gameAttributes.getDiscardPile().addCard(cardDiscarded);
    }

    public boolean addCardToFireworks(Card cardPlayed) {
        for (Firework firework : gameAttributes.getFireworkCollection().getFireworks()) {
            String fireworkColor = firework.getColor();
            int fireworkNextValue = firework.getNextValueExpected();
            String cardColor = cardPlayed.getColour().toString();
            int cardValue = cardPlayed.getCardValue();

            if (fireworkColor.equalsIgnoreCase(cardColor) && fireworkNextValue == cardValue) {
                firework.addCard(cardPlayed);
                System.out.println(cardPlayed + " has been added to the fireworks stack.");
                fireworkCompletedReward();
                return true;
            }
        }
        return false;
    } 

    public void fireworkCompletedReward() {
        try {
            gameAttributes.getNoteTokens().flipBlackToken();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("All note tokens have been flipped to white.");
        }
    }

    public static void printInstructions() {
        System.out.println(
                "0 : Show possible actions" +
                        "\n1 : Check tokens" +
                        "\n2 : View other players' cards" +
                        "\n3 : View fireworks" +
                        "\n4 : View discard pile" +
                        "\n5 : View hints given to you" +
                        "\n6 : Give a hint" +
                        "\n7 : Discard a card" +
                        "\n8 : Play a card" +
                        "\n9 : Quit the game");
    }

}
