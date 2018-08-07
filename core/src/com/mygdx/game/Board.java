package com.mygdx.game;

import java.lang.*;
import java.io.*;
import java.util.*;

public class Board {

    protected Piece[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;
    protected boolean whiteTurn;

    public Board(){

        whiteTurn = true;
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


    protected Board receiveMove(Piece p, int x2, int y2){
        if (whiteTurn && !whiteChecked && p.white){         // check if white is eligible to move
            if(p.x - x2 == p.y - y2){                       // check if bishop/queen/pawn

            }
        }
        else if (!whiteTurn && !blackChecked && !p.white && p.type != 'B'){

        }
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
