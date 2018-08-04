package com.mygdx.game;

class Piece {
    protected char type;
    protected boolean white;
    protected String pos;

    /*
    * @param type   type of piece to be added
    * @param pos    position of the peice, format y\x
    * @param white  true if piece is white
     */
    public Piece(char type, String pos, boolean white){
        this.type = type;
        this.pos = pos;
        this.white = white;
    }

    public String toString(){
        return "TYPE: " + type + " POS: " + pos + "WHITE?: " + white;
    }

}
