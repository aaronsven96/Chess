package com.mygdx.game;

public class Board {

    protected Piece[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;

    public Board(){
        whiteChecked = false;
        blackChecked = false;
        board = new Piece[8][8];

        String setup = "RNBQKBNRPPPPPPPP";

        for (int i = 0; i < 8 ; i ++){
            if (i == 0 || 1){
                for (int j = 0; j < 0; j++)
                    c = new Piece(setup.charAt((i*8)+j), Integer.toString(i)+"|"+Integer.toString(j), false);
            }
            else if (){
                //white pieces set up
            }
            else{
                // blank space - taylor swift
            }
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
