package com.mygdx.game;

import java.lang.*;

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
    private boolean isEnemyInCell(int x, int y, boolean white){
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
    private boolean isMoveValid(Piece p, int x2, int y2) {

        int dx = Math.abs(p.getX() - x2);
        int dy = Math.abs(p.getY() - y2);
        char tp = p.getType();


        if (dx == dy) {                 // check if moving along diagonal
            if (tp == 'R' || tp == 'N') {
                return false;
            }
            else if (dx == 1 && tp == 'P' && isEnemyInCell(x2, y2, p.isWhite())) {
                return true;
            }
            return (isDiagPathEmpty(p.getX(), p.getY(), x2, y2, p.isWhite()));
        }
        else if (dx == 0){
            if (tp == 'B' || tp == 'N') return false;
            else return isVertPathEmpty(p.getX(), p.getY(), y2, p.isWhite());
        }
        return false;
    }

    private  boolean isVertPathEmpty(int x1, int y1, int y2, boolean white){

        int i = (y1 - y2 < 0)? -1:1;    // y increment
        int dy = Math.abs(y1 - y2);     // times to increment

        

        return true;

    }

    private boolean isDiagPathEmpty(int x1, int y1, int x2, int y2, boolean white){

        int i = (x1-x2>0)?-1:1;         // increment for x distance
        int j = (y1-y2>0)?-1:1;         // increment for y distance
        int dx = Math.abs(x1-x2);       // how many times to increment

        for (int l = 0; dx > l; l++) {
            if ((board[x1][y1].getType() != 'E')){
                if (l == dx - 1)return isEnemyInCell(x1, y1, white);
                return false;
            }
            x1 += i;
            y1 += j;
        }
        return true;
    }

    void print(){
        for (Piece[] row: this.board){
            for(Piece p: row){
                if (p!=null) System.out.print(p.getType());

            }
            System.out.println();
        }
    }
}
