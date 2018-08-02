package com.mygdx.game;

class Piece {
    protected char type;
    protected boolean white;
    protected String pos;

    public Piece(char type, String pos, boolean white){
        this.type = type;
        this.pos = pos;
        this.white = white;
    }

}
