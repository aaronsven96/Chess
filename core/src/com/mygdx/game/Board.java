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
        for (int x = 0; x < 8 ; x++){
            if (x == 0 || x == 1) {
                for (int y = 0; y < 8; y++) {
                    board[x][y] = new Piece(black.charAt((y+(x*8)-1)), false,  x, y);
                }
            }
            else if (x == 6 || x == 7){
                for (int y = 0; y < 8; y++){
                    board[x][y] = new Piece(white.charAt((y+(x*8)-1)), true,  x, y);
                }
            }
            else{
                for (int y = 0; y<8; y++){
                    board[x][y] = new Piece('B', false, x, y);
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

    protected boolean checkValidMove(String start, String end){

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
