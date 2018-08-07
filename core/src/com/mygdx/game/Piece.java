package com.mygdx.game;

class Piece {
    protected char type;
    protected boolean white;
    protected int x, y;

    /*
    * @param type   type of piece to be added
    * @param pos    position of the peice, format y\x
    * @param white  true if piece is white
     */
    public Piece(char type, boolean white, int x, int y){
        this.type = type;
        this.white = white;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "TYPE: " + type + " POS: (" + x + ", " + y + ")" + "WHITE?: " + white;
    }

}
