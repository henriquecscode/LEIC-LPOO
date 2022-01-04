package com.model.tetris;

import java.util.ArrayList;

public class PieceLine extends Piece{

    public PieceLine(int posX, int posY) {
        super(posX, posY, 0, 1,4);


        ArrayList<Boolean> a1 = new ArrayList<>();
        a1.add(true);
        a1.add(false);
        a1.add(false);
        a1.add(false);

        ArrayList<Boolean> a2 = new ArrayList<>();
        a2.add(true);
        a2.add(false);
        a2.add(false);
        a2.add(false);

        ArrayList<Boolean> a3 = new ArrayList<>();
        a3.add(true);
        a3.add(false);
        a3.add(false);
        a3.add(false);

        ArrayList<Boolean> a4 = new ArrayList<>();
        a4.add(true);
        a4.add(false);
        a4.add(false);
        a4.add(false);

        ArrayList<ArrayList<Boolean>> A = new ArrayList<>();
        A.add(a1);
        A.add(a2);
        A.add(a3);
        A.add(a4);

        super.setSquares(A);



    }
}