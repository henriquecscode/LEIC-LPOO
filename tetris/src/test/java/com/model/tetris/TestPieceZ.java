package com.model.tetris;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestPieceZ {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(true, true, false),
            Arrays.asList(false, true, true),
            Arrays.asList(false, false, false));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(false, true, false),
            Arrays.asList(true, true, false),
            Arrays.asList(true, false, false));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(false, false, false),
            Arrays.asList(true, true, false),
            Arrays.asList(false, true, true));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(false, false, true),
            Arrays.asList(false, true, true),
            Arrays.asList(false, true, false));

    @Test
    void pieceZ() {
        Piece pieceZ = new PieceZ(1, 1);
        Assertions.assertEquals(up, pieceZ.getSquares());
    }

    @Test
    void rotateLeft(){

        Piece pieceZ = new PieceZ(1, 1);
        pieceZ.rotateLeft();
        Assertions.assertEquals(left, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateLeft();
        Assertions.assertEquals(down, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateLeft();
        Assertions.assertEquals(right, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateLeft();
        Assertions.assertEquals(up, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());
    }

    @Test
    void rotateRight(){
        Piece pieceZ = new PieceZ(1, 1);
        pieceZ.rotateRight();
        Assertions.assertEquals(right, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateRight();
        Assertions.assertEquals(down, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateRight();
        Assertions.assertEquals(left, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());

        pieceZ.rotateRight();
        Assertions.assertEquals(up, pieceZ.getSquares());
        Assertions.assertEquals(1, pieceZ.getCenterX());
        Assertions.assertEquals(1, pieceZ.getCenterY());
    }

}
