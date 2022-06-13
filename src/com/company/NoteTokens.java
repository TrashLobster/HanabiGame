package com.company;

public class NoteTokens extends TokenCollection{
    public NoteTokens() {
        super(8);
    }

    public int getWhiteToken() {
        return countTokens(false);
    }

    public int getBlackToken() {
        return countTokens(true);
    }

    public void flipWhiteToken() {
        flipToken(false);
    }

    public void flipBlackToken() {
        flipToken(true);
    }
}
