package com.model.tetris;

import java.util.ArrayList;

public class PieceInvertedL extends Piece{
    public PieceInvertedL(int posX, int posY) {
       super(posX, posY, 2, 2,6);


        ArrayList<Boolean> a1 = new ArrayList<>();
        a1.add(false);
        a1.add(false);
        a1.add(true);

        ArrayList<Boolean> a2 = new ArrayList<>();
        a2.add(false);
        a2.add(false);
        a2.add(true);

        ArrayList<Boolean> a3 = new ArrayList<>();
        a3.add(false);
        a3.add(true);
        a3.add(true);

        ArrayList<ArrayList<Boolean>> A = new ArrayList<>();
        A.add(a1);
        A.add(a2);
        A.add(a3);

        super.setSquares(A);



    }
}