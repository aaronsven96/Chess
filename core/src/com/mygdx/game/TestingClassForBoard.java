package com.mygdx.game;
import java.util.Scanner;

public class TestingClassForBoard {
    public static void main(String args[]){
        Board aBoard = new Board();
        aBoard.print();
        System.out.println();

        Scanner inpu = new Scanner(System.in);

        while (true){
            System.out.print("Coords: ");
            String next = inpu.nextLine();
            int x1 = Character.getNumericValue(next.charAt(0));
            int y1 = Character.getNumericValue(next.charAt(1));
            int x2 = Character.getNumericValue(next.charAt(2));
            int y2 = Character.getNumericValue(next.charAt(3));

            aBoard = aBoard.movePiece(x1,y1,x2,y2);
            aBoard.print();
            System.out.println("");


        }

    }
}
