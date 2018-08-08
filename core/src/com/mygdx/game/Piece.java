package com.mygdx.game;

class Piece {
    private char type;
    private boolean white;
    private int x, y;

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

    protected int getX(){
        return this.x;
    }

    protected int getY(){
        return this.y;
    }

    protected char getType(){
        return this.type;
    }

    protected boolean isWhite(){
        return this.white;
    }

    public String toString(){
        return "TYPE: " + type + " POS: (" + x + ", " + y + ")" + "WHITE?: " + white;
    }

}
