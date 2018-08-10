package com.mygdx.game;
import java.util.Scanner;

public class TestingClassForBoard {
    public static void main(String args[]){
        Board aBoard = new Board();
        aBoard.print();
        System.out.println();

        Scanner inpu = new Scanner(System.in);

        while (true){
            System.out.print("X: ");
            String x = inpu.nextLine();
            System.out.print("Y: ");
            String y = inpu.nextLine();
            System.out.print("X2: ");
            String xz = inpu.nextLine();
            System.out.print("Y2: ");
            String yz = inpu.nextLine();

            int x1 = Character.getNumericValue(x.charAt(0));
            int y1 = Character.getNumericValue(y.charAt(0));
            int x2 = Character.getNumericValue(xz.charAt(0));
            int y2 = Character.getNumericValue(yz.charAt(0));

            System.out.println(aBoard.getCell(x1,y1).getPiece());
            System.out.println();
            System.out.println();

            aBoard.movePiece(x1, y1, x2, y2);

            System.out.println();

            aBoard.print();
            System.out.println();

        }

    }
}
