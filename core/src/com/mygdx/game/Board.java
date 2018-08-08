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


        String whiteP = "RNBQKBNRPPPPPPPP";
        String blackP = "PPPPPPPPRNBKQBNR";


        // Board setup details
        for (int x = 0; x < 8 ; x++){
            if (x == 0 || x == 1) {
                for (int y = 0; y < 8; y++) {
                    board[x][y] = new Piece(blackP.charAt((y+(x*8)-1)), false,  x, y);
                }
            }
            else if (x == 6 || x == 7){
                for (int y = 0; y < 8; y++){
                    board[x][y] = new Piece(whiteP.charAt((y+(x*8)-1)), true,  x, y);
                }
            }
            else{
                for (int y = 0; y<8; y++){
                    board[x][y] = new Piece('E',false, x, y);
                }
            }
        }
    }


    // returns true if the piece in cell is opposite color
    protected boolean isEnemyInCell(int x, int y, boolean white){
        if (this.board[x][y].getType() != 'B') {
            return this.board[x][y].isWhite() != white;
        }
        return false;
    }

    // Given a piece and a destination, moves the piece to the cell if valid move
    protected Board movePiece(Piece p, int x2, int y2){
        if (whiteTurn && p.isWhite()){         // check if white is eligible to move

        }
        else if (!whiteTurn && !blackChecked && !p.isWhite() && p.getType() != 'E'){

        }
        return this;
    }


    // looks at destination cell and returns true if the path to the destination is empty and valid
    public boolean isPathValid(Piece p, int x2, int y2){
        int dx = Math.abs(p.getX() - x2);
        int dy = Math.abs(p.getY() - y2);

        if (dx == dy) {           // check if moving along diagonal
            if (p.getType() != 'Q' || p.getType()== 'P' ||p.getType()=='B'||p.getType()=='K'){
                return false;
            }
            if (dx == 1 && p.getType() == 'P' && isEnemyInCell(x2, y2, p.isWhite())) {
                return true;
            }
        }

        return false;
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
                if (p!=null) System.out.print(p.getType());

            }
            System.out.println();
        }
    }
}
