package com.company;

import java.util.Arrays;

public abstract class TokenCollection {
    private boolean[] tokens;

    public TokenCollection(int numberOfTokens) {
        this.tokens = new boolean[numberOfTokens];
        Arrays.fill(tokens, false);
    }

    public boolean[] getTokens() {
        return this.tokens;
    }

    public int countTokens(boolean choice) {
        int trueTokensCount = 0;
        int falseTokenCount = 0;

        for (boolean token : tokens) {
            if (token) {
                trueTokensCount++;
            } else {
                falseTokenCount++;
            }
        }

        return choice ? trueTokensCount : falseTokenCount;
    }

    public int findIndexOfFirstInstanceOfToken(boolean choice) {
        int index = 0;
        boolean found = false;

        while (!found) {
            if (index >= tokens.length) {
                index = -1;
                break;
            }
            if (tokens[index] == choice) {
                break;
            }
            index++;
        }
        return index;
    }

    public void flipToken(boolean choice) throws ArrayIndexOutOfBoundsException{
        int index = findIndexOfFirstInstanceOfToken(choice);
    
        tokens[index] = !tokens[index];
    }

}
