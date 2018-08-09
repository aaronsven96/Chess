package com.mygdx.game;

import java.lang.*;

public class Board {

    protected Cell[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;
    protected boolean whiteTurn;


    public Board(){

        whiteTurn = true;
        whiteChecked = false;
        blackChecked = false;
        board = new Cell[8][8];


        String whiteP = "RNBQKBNRPPPPPPPP";
        String blackP = "PPPPPPPPRNBKQBNR";


        // Board setup details
        for (int x = 0; x < 8 ; x++){
            if (x == 0 || x == 1) {
                for (int y = 0; y < 8; y++) {
                    board[x][y] = new Cell(true, new Piece(whiteP.charAt((x*8) + y), true, x, y));
                }
            }
            else if (x == 6 || x == 7){
                for (int y = 0; y < 8; y++){
                    board[x][y] = new Cell(true, new Piece(blackP.charAt(((x-6)*8) + y), false, x, y));
                }
            }
            else{
                for (int y = 0; y<8; y++){
                    board[x][y] = new Cell(false, new Piece('E', false, x, y));

                }
            }
        }
    }

    // Given a piece and a destination, moves the piece to the cell if valid move
    protected Board movePiece(int x1, int y1, int x2, int y2){
        Cell c = board[x1][y1];
        if (whiteTurn && c.getPiece().isWhite()){         // check if white is eligible to move
            if (isMoveValid(c.getPiece(), x2, y2)){
                board[x1][y1] = new Cell(false, new Piece('E', false, x2, y2));
                c.getPiece().setX(x2);
                c.getPiece().setY(y2);
                board[x2][y2] = c;
                System.out.println("3nbiuh3obdvbiu43");
            }
            System.out.println("not valid");
        }
        System.out.println(whiteTurn + " " + c.getPiece().isWhite());
        if (!whiteTurn && !blackChecked && !c.getPiece().isWhite() && c.getPiece().getType() != 'E'){

        }
        return this;
    }

    // looks at destination cell and returns true if the path to the destination is empty and valid
    private boolean isMoveValid(Piece p, int x2, int y2) {

        int dx = Math.abs(p.getX() - x2);
        int dy = Math.abs(p.getY() - y2);
        char tp = p.getType();


        // check if king can move to that position without ben placed in check - fill out
        if (tp == 'K'){

        }
        // check knight moves - fill out
        else if (tp == 'N'){

        }
        else if (dx == dy) {                    // check if moving along diagonal
            if (tp == 'R' || tp == 'N') {       // invalid move for knights and rooks
                return false;
            }
            else if (tp == 'P' && dx>1){        // invalid move for pawn
                return false;
            }
            else if (dx == 1 && tp == 'P' && isEnemyInCell(x2, y2, p.isWhite())) {
                return true;
            }
            return (isDiagPathEmpty(p.getX(), p.getY(), x2, y2, p.isWhite()));
        }

        else if (dx == 0){
            if (tp == 'B' || tp == 'N') return false;
            else if (tp == 'P'){                // check pawn moves
                if (dy <= 2){
                    // check if possible to move 2 spaces as starting spawn
                    // or if pawn is only trying to move 1 space
                    if ((p.isWhite() && p.getX() == 1) || (!p.isWhite()
                            && p.getX() == 6) || dy == 1){
                         return (isVertPathEmpty(p.getX(), p.getY(), y2, p.isWhite()));
                    }
                }
                return false;
            }
            else return isVertPathEmpty(p.getX(), p.getY(), y2, p.isWhite());
        }
        // fill out this
        else if (dy == 0){
            if (tp == 'B' || tp == 'N' || tp == 'P') return false;
        }

        return false;
    }

    private  boolean isVertPathEmpty(int x1, int y1, int y2, boolean white){

        int i = (y1 - y2 < 0)? -1:1;        // y increment
        int dy = Math.abs(y1 - y2);         // times to increment

        for (int j = 0; j < dy; j++ ){
            if (board[x1][y1].isOccupied() && j == dy - 1 && isEnemyInCell(x1, y1, white)){
                return true;
            }
            else if (board[x1][y1].isOccupied()){
                return false;
            }
            y1 += i;
        }

        return true;

    }

    private boolean isDiagPathEmpty(int x1, int y1, int x2, int y2, boolean white){

        int i = (x1-x2>0)?-1:1;         // increment for x distance
        int j = (y1-y2>0)?-1:1;         // increment for y distance
        int dx = Math.abs(x1-x2);       // how many times to increment

        for (int l = 0; dx > l; l++) {
            if (board[x1][y1].isOccupied()){
                if (l == dx - 1)return isEnemyInCell(x1, y1, white);
                return false;
            }
            x1 += i;
            y1 += j;
        }
        return true;
    }

    // returns true if the piece in cell is opposite color
    private boolean isEnemyInCell(int x, int y, boolean white){
        if (board[x][y].isOccupied()){
            return board[x][y].getPiece().isWhite() != white;
        }
        return false;
    }

    void print(){
        for (Cell[] row: board){
            for(Cell p: row){
                if (p!=null) System.out.print(p.getPiece().getType());

            }
            System.out.println();
        }
    }
}
