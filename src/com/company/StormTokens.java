package com.company;

public class StormTokens extends TokenCollection {
    // TODO: consider whether token classes are even necessary
    // storm tokens are the starters and value is false
    public StormTokens() {
        super(3);
    }

    public int getStormTokens() {
        return countTokens(false);
    }

    public int getLightningTokens() {
        return countTokens(true);
    }

    public void flipStormTokens() {
        flipToken(false);
    }
}
