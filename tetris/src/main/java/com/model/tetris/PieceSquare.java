package com.model.tetris;

import java.util.ArrayList;

public class PieceSquare extends Piece {

    public PieceSquare(int posX, int posY) {
       super(posX, posY, 0, 0,5);


        ArrayList<Boolean> a1 = new ArrayList<>();
        a1.add(true);
        a1.add(true);

        ArrayList<Boolean> a2 = new ArrayList<>();
        a2.add(true);
        a2.add(true);

        ArrayList<ArrayList<Boolean>> A = new ArrayList<>();
        A.add(a1);
        A.add(a2);

        super.setSquares(A);

    }

    @Override
    public void rotateLeft() {
    }

    @Override
    public void rotateRight() {
    }
}