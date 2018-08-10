package com.mygdx.game;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Board {

    protected Cell[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;
    protected boolean whiteTurn;
    protected Stack<Integer> moves;

    public Board(){

        whiteTurn = true;
        whiteChecked = false;
        blackChecked = false;
        board = new Cell[8][8];


        String whiteP = "RNBQKBNRPPPPPPPP";
        String blackP = "PPPPPPPPRNBQKBNR";


        // Board setup details
        for (int x = 0; x < 8 ; x++){
            if (x == 0 || x == 1) {
                for (int y = 0; y < 8; y++) {
                    board[y][x] = new Cell(true, new Piece(whiteP.charAt((x*8) + y), true, x, y));
                }
            }
            else if (x == 6 || x == 7){
                for (int y = 0; y < 8; y++){
                    board[y][x] = new Cell(true, new Piece(blackP.charAt(((x-6)*8) + y), false, x, y));
                }
            }
            else{
                for (int y= 0; y<8; y++){
                    board[y][x] = new Cell(false, new Piece('E', false, x, y));

                }
            }
        }
    }

    // Given a piece and a destination, moves the piece to the cell if valid move
    protected Board movePiece(int x1, int y1, int x2, int y2){
        Cell c = board[y1][x1];

        if (whiteTurn && c.getPiece().isWhite()){         // check if white is eligible to move
            if (isMoveValid(c.getPiece(), x2, y2)){
                board[y2][x2] = c;
                c.getPiece().setY(y2);
                c.getPiece().setX(x2);
                board[y1][x1] = new Cell(false, new Piece('E', false, x1, y1));
            }

        }


        if (!whiteTurn && !blackChecked && !c.getPiece().isWhite() && c.getPiece().getType() != 'E'){

        }
        return this;
    }

    // looks at destination cell and returns true if the path to the destination is empty and valid
    private boolean isMoveValid(Piece p, int x2, int y2) {
        char pt = p.getType();

        int dx = Math.abs(p.getX() - x2);
        int dy = Math.abs(p.getY() - y2);

        if (pt == 'K'){
            // check king moves
        }
        else if (pt ==  'N'){
            // check knight moves
        }

        else if (pt == 'B'){
            // check diagonal paths
        }
        else if (pt == 'Q'){
            if (dy == dx){
                // check diagonal paths
            }
            else if (dy == 0){
                // check horizontal path
            }
            else if (dx == 0){
                //check vertical paths
            }
        }
        else if (pt == 'R' ){
            if (dy == 0){
                // check horizontal paths
            }
            else if (dy == 0){
                // check vertical paths
            }

        }
        else if (pt == 'P'){
            // check for en passant
            if (false){

            }
            else if (dx == dy && dx == 1){
                return isEnemyInCell(x2, y2, p.isWhite());
            }
            else if (dy == 0 && dx < 3){
                ArrayList<Cell> path = createList(p.getX(), x2, p.getY(), y2);
                for (Cell c: path){
                    if (c.isOccupied()) return false;
                }
                return true;
            }
        }
        return false;
    }

    private ArrayList<Cell> createList(int x1, int x2, int y1, int y2){

        ArrayList<Cell> cells = new ArrayList<Cell>();

        int ix = (x1 - x2 > 0)? -1:1;
        int iy = (y1 - y2 > 0)? -1:1;

        int adx = Math.abs(x1 - x2);
        int ady = Math.abs(y1 - y2);

        if (adx == ady){
            for (int i = 0; i < adx; i++){
                x1 += ix;
                y1 += iy;
                cells.add(board[y1][x1]);
            }
        }
        else if (adx == 0){
            for (int i = 0; i<ady; i++){
                y1 += iy;
                cells.add(board[y1][x1]);
            }

        }
        else if (ady == 0){
            for (int i = 0; i<adx; i++){
                x1 += ix;
                cells.add(board[y1][x1]);
            }
        }
        return cells;

    }

    // returns true if the piece in cell is opposite color
    private boolean isEnemyInCell(int x, int y, boolean white){
        if (board[x][y].isOccupied()){
            return board[x][y].getPiece().isWhite() != white;
        }
        return false;
    }

    protected Cell getCell(int x, int y){
        return board[y][x];
    }

    void print(){
        for (Cell[] row: board){
            for(Cell p: row){
                if (p!=null) System.out.print(p.getPiece().getType() + " ");

            }
            System.out.println();
        }
    }
}
