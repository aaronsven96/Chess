package com.mygdx.game;

public class Board {
    protected Piece[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;

    public Board(){
        board = new Piece[8][8];
        whiteChecked = false;
        blackChecked = false;

    }

    protected Board movePiece(String move){
        return this;
    }
    

    public boolean is() {
        return blackChecked;
    }
}
