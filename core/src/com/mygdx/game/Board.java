package com.mygdx.game;

public class Board {



    protected Piece[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;

    public Board(){
        whiteChecked = false;
        blackChecked = false;

        board = new Piece[8][8];
        for (Piece[] row: board){

        }
    }

    protected Board movePiece(String move){

        return this;
    }


    public boolean is() {
        return blackChecked;
    }


    public boolean checkCheckmate(){
        return false;
    }
}
