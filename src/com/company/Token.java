package com.company;

public class Token implements TokenAction{
    private boolean sideUp;

    public Token(boolean initialState) {
        this.sideUp = initialState;
    }

    public boolean getSideUp() {
        return this.sideUp;
    }

    public void flipToken() {
        this.sideUp = !this.sideUp;
    }
}
