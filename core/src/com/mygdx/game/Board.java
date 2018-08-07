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
                    board[x][y] = new Piece('B',false, x, y);
                }
            }
        }
    }


    // returns true if the piece in cell is opposite white
    protected boolean isEnemyInCell(int x, int y, boolean white){
        if (this.board[x][y].type != 'B') {
            return this.board[x][y].white != white;
        }
        return false;
    }

    // Given a piece and a destination, moves the piece to the cell if valid move
    protected Board movePiece(Piece p, int x2, int y2){
        if (whiteTurn && !whiteChecked && p.white){         // check if white is eligible to move
            if(p.x - x2 == p.y - y2){
                if ((p.x - x2 > 1) && (p.type != 'P')){      // make sure not pawn

                }
                else if (p.x - x2 == 1 && ((p.type == ('Q') || p.type =='B' || p.type == 'P'))){
                    if (p.type == 'P' && isEnemyInCell(x2, y2, p.white)){   // if pawn and able to
                        board[x2][y2] = p;                                  // capture, moves piece
                    }
                }
                else{

                }
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
