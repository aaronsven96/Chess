package com.mygdx.game;

public class Cell {

    private boolean occupied;
    private Piece piece;

    public Cell(boolean occupied){
        this(occupied, null);
    }

    public Cell(boolean occupied, Piece t){
        this.piece = t;
        this.occupied = occupied;
    }

    protected boolean isOccupied(){
        return occupied;
    }

    public Piece getPiece(){
        return piece;
    }
}
