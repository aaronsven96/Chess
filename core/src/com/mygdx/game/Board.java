package com.mygdx.game;

import java.lang.*;
import java.io.*;
import java.util.*;

public class Board {

    protected Piece[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;

    public Board(){

        whiteChecked = false;
        blackChecked = false;
        board = new Piece[8][8];

        String white = "RNBQKBNRPPPPPPPP";
        String black = "PPPPPPPPRNBKQBNR";


        // Board setup
        for (int i = 0; i < 8 ; i++){
            if (i == 0 || i == 1) {
                for (int k = 0; k < 8; k++) {
                    board[i][k] = new Piece(white.charAt((i * 8) + k), Integer.toString(i) + "|" + Integer.toString(k), false);
                }
            }
            else if (i == 6 || i == 7){
                for (int j = 0; j < 8; j++){
                    board[i][j] = new Piece(black.charAt(((i-6)*8)+ j), Integer.toString(i) + "|" + Integer.toString(j), true);
                }
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

    public void print(){
        for (Piece[] row: this.board){
            for(Piece p: row){
                if (p!=null) System.out.print(p.type);

            }
            System.out.println();
        }
    }
}
