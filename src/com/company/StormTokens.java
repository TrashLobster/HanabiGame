package com.company;

public class StormTokens {
    private int stormTokens;
    private int lightningTokens;

    public StormTokens() {
        this.stormTokens = 3;
        this.lightningTokens = 0;
    }

    public int getLightningTokens() {
        return lightningTokens;
    }

    public int getStormTokens() {
        return stormTokens;
    }

    public boolean flipStormTokens() {
        if(stormTokens > 0 && stormTokens <= 3) {
            stormTokens--;
            lightningTokens++;
            return true;
        }
        return false;
    }
}
