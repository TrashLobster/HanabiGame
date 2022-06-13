package com.company;

public class NoteTokens extends TokenCollection{
    // TODO: consider whether token classes are even necessary
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
