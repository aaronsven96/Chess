package com.mygdx.game;

class Piece {

    private char type;
    private boolean white;
    private int x, y;

    public Piece(char type, boolean white, int x, int y){
        this.type = type;
        this.white = white;
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public char getType(){
        return this.type;
    }

    public boolean isWhite(){
        return this.white;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void updatePos(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public String toString(){
        return "TYPE: " + type + " POS: (" + x + ", " + y + ")" + " WHITE: " + white;
    }

}
