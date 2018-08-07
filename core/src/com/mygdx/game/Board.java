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


        // Board setup details
        for (int i = 0; i < 8 ; i++){
            if (i == 0 || i == 1) {
                for (int k = 0; k < 8; k++) {
                    board[i][k] = new Piece(black.charAt((k+(i*8)-1)), false,  i, k);
                }
            }
            else if (i == 6 || i == 7){
                for (int k = 0; k < 8; k++){
                    board[i][k] = new Piece(white.charAt((k+(i*8)-1)), true,  i, k);
                }
            }
            else{
                for (int k = 0; k<8; k++){
                    board[i][k] = new Piece('B', false, i, k);
                }
            }
        }
    }


    protected Board receiveMove(String move){
        String start = move.substring(0,1);
        String end = move.substring(2,3);

        return this;
    }

    protected String sendMove(){
        return "3134";
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
