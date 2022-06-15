package com.company;

import java.lang.reflect.Array;
import java.util.Scanner;

public class RoundController {
    private static final Scanner scan = new Scanner(System.in);
    private Player player;

    public RoundController(Player player) {
        this.player = player;
    }

    public void runRound(int roundNumber, Player player) {
        System.out.println();
        System.out.println("Turn " + roundNumber);
        System.out.println(player.getName() + "'s turn.\nPlease choose one of the following actions:");
        printInstructions();
        boolean turnOver = false;

        while (!turnOver) {
            int choice = -1;
        }
    }

    public boolean respondToPlayerChoice(int choice, GameAttributes gameAttributes) {
        boolean turnOver = false;
        choice = checkPlayerChoice(choice);
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
                giveHint(gameAttributes);
                turnOver = true;
                break;
            case 7:
                discardCardProcess(gameAttributes);
                turnOver = true;
                break;
            // case 8:
            // turnOver = playCard(player, fireworkCollection, discardPile, shuffledDeck,
            // noteTokens,
            // stormTokens);
            // break;
            // case 9:
            // turnOver = true;
            // gameOn = false;
            // break;
            default:
                break;
            
        }
        return turnOver;
    }

    public int checkPlayerChoice(int choice) {

        boolean validChoice = false;

        while (!validChoice) {
            if (choice < 0 || choice > 9) {
                System.out.println("Invalid choice, try again.");
                choice = scan.nextInt();
            } else {
                validChoice = true;
            }
        }
        return choice;
    }

    public void giveHint(GameAttributes gameAttributes) {
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

    public void discardCardProcess(GameAttributes gameAttributes) {
        try {
            gameAttributes.getNoteTokens().flipBlackToken();
            System.out.println(player.getHand().getCards().toString());
            System.out.println("Which card would you like to drop?");
            // fix card position logic
            Card cardDiscarded = player.getHand().dropCard(cardPosition);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't discard any cards! All tokens have been flipped.");
        }
    }

    public int checkDiscardChoice(int cardPosition) {

        
        return cardPosition;
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
