package com.company;

public class NoteTokens {
    private int whiteToken;
    private int blackToken;

    public NoteTokens() {
        this.whiteToken = 8;
        this.blackToken = 0;
    }

    public int getWhiteToken() {
        return whiteToken;
    }

    public int getBlackToken() {
        return blackToken;
    }

    public boolean flipWhiteToken() {
        if(whiteToken > 0 && whiteToken <= 8) {
            whiteToken--;
            blackToken++;
            return true;
        }
        return false;
    }

    public boolean flipBlackToken() {
        if(blackToken > 0 && blackToken <= 8) {
            whiteToken++;
            blackToken--;
            return true;
        }
        return false;
    }
}
