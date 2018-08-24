package com.mygdx.game;

import java.lang.*;
import java.util.ArrayList;
import java.util.Stack;

public class Board {

    protected Cell[][] board;
    protected boolean whiteChecked;
    protected boolean blackChecked;
    protected boolean whiteTurn;
    protected boolean whiteCKingAllow;
    protected boolean whiteCQueenAllow;
    protected boolean blackCQueenAllow;
    protected boolean blackCKingAllow;
    protected Stack<Integer> moves;

    public Board(){

        whiteTurn = true;
        whiteChecked = false;
        blackChecked = false;
        board = new Cell[8][8];
        moves = new Stack<Integer>();
        whiteCKingAllow = true;
        whiteCQueenAllow = true;
        blackCQueenAllow  = true;
        blackCKingAllow = true;

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

        // catch non-moves
        if (x1 == x2 && y1 == y2){
            return this;
        }

        // check that the player is moving the correct color piece
        else if ((whiteTurn && c.getPiece().isWhite())|| !whiteTurn && !c.getPiece().isWhite()){
            if (isMoveValid(c.getPiece(), x2, y2)) {

                // disable white castling
                if (whiteTurn) {
                    if (c.getPiece().getType() == 'K') {
                        whiteCKingAllow = false;
                        whiteCQueenAllow = false;
                    } else if ((x1 == 0 && y1 == 0)) {
                        whiteCQueenAllow = false;
                    } else if (x1 == 0 && y1 == 7) {
                        whiteCKingAllow = false;
                    }
                }
                // disable black castling
                else{
                    if (c.getPiece().getType() == 'K') {
                        blackCKingAllow = false;
                        blackCQueenAllow = false;
                    }
                    else if ((x1 == 7 && y1 == 0)) {
                        blackCKingAllow = false;
                    }
                    else if(x1 == 7 && y1 == 7){
                        blackCQueenAllow = false;
                    }
                }

                // castle king-side
                if (c.getPiece().getType() == 'K' && y2-y1==2){
                    board[y2-1][x2] = board[y2+1][x2];
                    board[y2-1][x2].getPiece().updatePos(x2, y2-1);
                    board[y2+1][x2] = new Cell(false, new Piece('E',false,x2, y2+1 ));
                }
                // castle queen-side
                else if (c.getPiece().getType() == 'K' && y2-y1 == -2){
                    board[y2+1][x2] = board[y2-2][x2];
                    board[y2+1][x2].getPiece().updatePos(x2, y2+1);
                    board[y2-2][x2] = new Cell(false, new Piece('E',false,x2, y2-2 ));
                }

                // move pieces
                board[y2][x2] = c;
                board[y2][x2].getPiece().updatePos(x2, y2);
                board[y1][x1] = new Cell(false, new Piece('E', false, x1, y1));

                // change player to move
                whiteTurn = !whiteTurn;
            }
        }
        System.out.println("white move: "+ whiteTurn);
        return this;
    }

    // looks at destination cell and returns true if the path to the destination is empty and valid
    private boolean isMoveValid(Piece p, int x2, int y2) {

        char pt = p.getType();
        int dx = Math.abs(p.getX() - x2);
        int dy = Math.abs(p.getY() - y2);

        if (pt == 'K'){
            if (dx > 1) return false;
            else if (dx == 0 && dy == 2){
                if (x2 == 0 && y2 == 6 && whiteCKingAllow && !board[0][6].isOccupied() && !board[0][5].isOccupied()
                        && !isPieceAttacked(0, 6, p.isWhite())&& isPieceAttacked(0, 5,p.isWhite())
                        && !isPieceAttacked(0, 4, p.isWhite())) {
                    return true;
                }
                else if (x2 == 0 && y2 == 2 && whiteCQueenAllow && !board[0][1].isOccupied() && !board[0][2].isOccupied()
                        && !board[0][3].isOccupied() && !isPieceAttacked(0, 2, p.isWhite()) &&
                        isPieceAttacked(0, 3, p.isWhite()) && !isPieceAttacked(0, 4, p.isWhite())){
                    return true;
                }
                else if (x2 == 7 && y2 == 2 && blackCKingAllow && !board[7][6].isOccupied() && !board[7][5].isOccupied()
                        && !isPieceAttacked(7, 6, p.isWhite()) && isPieceAttacked(7, 5, p.isWhite())
                        && !isPieceAttacked(7, 4, p.isWhite())){
                    return true;
                }
                else if (x2 == 7 && y2 == 2 && blackCQueenAllow && !board[7][1].isOccupied() && !board[7][2].isOccupied()
                        && !board[7][3].isOccupied() && !isPieceAttacked(7, 2, p.isWhite()) &&
                        isPieceAttacked(7, 3, p.isWhite()) && !isPieceAttacked(7, 4, p.isWhite())){
                    return true;
                }
                return false;
            }
            else if (isEnemyInCell(x2, y2, p.isWhite())){
                return !isPieceAttacked(x2, y2, p.isWhite());
            }
            else if (board[y2][x2].isOccupied()){
                return false;
            }
            return !isPieceAttacked(x2, y2, p.isWhite());


        }
        else if (pt ==  'N'){
            if ((((x2 == p.getX() + 2)||(x2 == p.getX() - 2)) &&  ((y2 == p.getY() + 1) || (y2 == p.getY() - 1)))
                || (((x2 == p.getX() + 1) || x2 == p.getX() - 1) && ((y2 == p.getY() + 2) || y2 == p.getY() -2))) {
                if (board[y2][x2].isOccupied()) return isEnemyInCell(x2, y2, p.isWhite());
                return true;
            }
        }

        else if (pt == 'R' || pt == 'Q' || pt == 'B' ){
            ArrayList<Cell> path = createList(p.getX(), x2, p.getY(), y2);
            for (int i = 0; i<path.size(); i++){
                if ((i == path.size() -1) && isEnemyInCell(x2,y2,p.isWhite())) return true;
                if (path.get(i).isOccupied()) return false;
            }
            return true;
        }
        else if (pt == 'P'){
            System.out.println("Pawn Move: dx: "+ dx+" dy: "+dy );
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

        if (board[y][x].isOccupied()){
            return board[y][x].getPiece().isWhite() != white;
        }
        return false;
    }

    private boolean isPieceAttacked(int x, int y, boolean white){
        for (int i = 0; i< (7 - y); i++){
            if(board[y+i][x].isOccupied()){
                char p = board[y+i][x].getPiece().getType();
                if (board[y+i][x].getPiece().isWhite() == white){
                    break;
                }
                else if (p == 'Q' || p == 'R' || (p == 'K'&& i == 1)){
                    return true;
                }
            }
        }
        for (int i = 0; i < (7 - y); i++){
            if(board[y-i][x].isOccupied()) {
                char p = board[y - i][x].getPiece().getType();
                if (board[y - i][x].getPiece().isWhite() == white) {
                    break;
                } else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                    return true;
                }
            }
        }
            for (int i = 0; i< (7 - y); i++){
                if(board[y][x+i].isOccupied()){
                    char p = board[y][x+i].getPiece().getType();
                    if (board[y][x+i].getPiece().isWhite() == white){
                        break;
                    }
                    else if (p == 'Q' || p == 'R' || (p == 'K'&& i == 1)){
                        return true;
                    }
                }
            }
            for (int i = 0; i < (7 - y); i++) {
                if (board[y][x-i].isOccupied()) {
                    char p = board[y][x-i].getPiece().getType();
                    if (board[y][x-i].getPiece().isWhite() == white) {
                        break;
                    } else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                        return true;
                    }
                }
            }
            for (int i = 0; i < (7 - y); i++) {
                if (board[y-i][x-i].isOccupied()) {
                    char p = board[y - i][x - i].getPiece().getType();
                    if (board[y - i][x - i].getPiece().isWhite() == white) {
                        break;
                    } else if (p == 'Q' || p == 'B' || (p == 'K' && i == 1)) {
                        return true;
                    }
                    else if (p == 'P'){
                        if (board[y - i][x - i].getPiece().isWhite()) {
                            return true;
                        }
                        break;
                    }
                }
            }
            for (int i = 0; i < (7 - y); i++) {
                if (board[y+i][x-i].isOccupied()) {
                    char p = board[y + i][x - i].getPiece().getType();
                    if (board[y + i][x - i].getPiece().isWhite() == white) {
                        break;
                    } else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                        return true;
                    }
                    else if (p == 'P'){
                        if (board[y + i][x - i].getPiece().isWhite()){
                            return true;
                        }
                        break;
                    }
                }
            }

            for (int i = 0; i < (7 - y); i++) {
                if (board[y-i][x-i].isOccupied()) {
                    char p = board[y- i ][x - i].getPiece().getType();
                    if (board[y - i][x - i].getPiece().isWhite() == white) {
                        break;
                    } else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                        return true;
                    }else if (p == 'P'){
                        if (board[y + i][x - i].getPiece().isWhite()){
                            return true;
                        }
                        break;
                    }
                }
            }
            for (int i = 0; i < (7 - y); i++) {
                if (board[y-i][x+i].isOccupied()) {
                    char p = board[y- i ][x + i].getPiece().getType();
                    if (board[y - i][x + i].getPiece().isWhite() == white) {
                        break;
                    }else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                        return true;
                    }else if (p == 'P'){
                        if (!board[y - i][x + i].getPiece().isWhite()){
                            return true;
                        }
                        break;
                    }
                }
            }
            for (int i = 0; i < (7 - y); i++) {
                if (board[y+i][x+i].isOccupied()) {
                    char p = board[+ i ][x + i].getPiece().getType();
                    if (board[y + i][x + i].getPiece().isWhite() == white) {
                        break;
                    }else if (p == 'Q' || p == 'R' || (p == 'K' && i == 1)) {
                        return true;
                    }else if (p == 'P'){
                        if (!board[y + i][x + i].getPiece().isWhite()){
                            return true;
                        }
                        break;
                    }
                }
            }

            // checks knight positions
            if (y < 6 && x < 7){
                if (board[y + 2][x + 1].getPiece().getType() == 'N' && board[y + 2][x + 1].getPiece().isWhite() != white) {
                return true;
                }
            }
            if (y < 6 && x > 0){
                if (board[y+2][x-1].getPiece().getType() == 'N' && board[y+2][x-1].getPiece().isWhite() != white) {
                    return true;
                }
            }
            if (y < 7 && x > 2){
                if (board[y+1][x-2].getPiece().getType() == 'N' && board[y+1][x-2].getPiece().isWhite() != white) {
                return true;
                }
            }
            if (y < 7 && x <  6){
                if (board[y+1][x+2].getPiece().getType() == 'N' && board[y+1][x+2].getPiece().isWhite() != white) {
                    return true;
                }
            }
            if (y > 0 && x < 6) {
                if (board[y - 1][x + 2].getPiece().getType() == 'N' && board[y - 1][x + 2].getPiece().isWhite() != white) {
                    return true;
                }
            }
            if (y > 0 &&  x > 1){
                if (board[y-1][x-2].getPiece().getType() == 'N' && board[y-1][x-2].getPiece().isWhite() != white){
                    return true;
                }
            }
            if (y > 1 && x < 7){
                if (board[y-2][x+1].getPiece().getType() == 'N' && board[y-2][x+1].getPiece().isWhite() != white) {
                    return true;
                }
            }
            if (y > 1 && x > 0){
                if (board[y-2][x-1].getPiece().getType() == 'N' && board[y-2][x-1].getPiece().isWhite() != white) {
                    return true;
                }
            }


        return false;
    }

    public Cell getCell(int x, int y)
    {
        return board[y][x];
    }
    protected char getPiece(int x, int y){
        return board[y][x].getPiece().getType();
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
