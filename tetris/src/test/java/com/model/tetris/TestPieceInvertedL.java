package com.model.tetris;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class TestPieceInvertedL {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(false, false, true),
            Arrays.asList(false, false, true),
            Arrays.asList(false, true, true));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(true, true, true),
            Arrays.asList(false, false, true),
            Arrays.asList(false, false, false));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(true, true, false),
            Arrays.asList(true, false, false),
            Arrays.asList(true, false, false));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(false, false, false),
            Arrays.asList(true, false, false),
            Arrays.asList(true, true, true));

    @Test
    void pieceInvertedL() {
        Piece pieceInvertedL = new PieceInvertedL(1, 1);
        Assertions.assertEquals(up, pieceInvertedL.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceInvertedL = new PieceInvertedL(1, 1);
        pieceInvertedL.rotateLeft();
        Assertions.assertEquals(left, pieceInvertedL.getSquares());
        Assertions.assertEquals(2, pieceInvertedL.getCenterX());
        Assertions.assertEquals(0, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateLeft();
        Assertions.assertEquals(down, pieceInvertedL.getSquares());
        Assertions.assertEquals(0, pieceInvertedL.getCenterX());
        Assertions.assertEquals(0, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateLeft();
        Assertions.assertEquals(right, pieceInvertedL.getSquares());
        Assertions.assertEquals(0, pieceInvertedL.getCenterX());
        Assertions.assertEquals(2, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateLeft();
        Assertions.assertEquals(up, pieceInvertedL.getSquares());
        Assertions.assertEquals(2, pieceInvertedL.getCenterX());
        Assertions.assertEquals(2, pieceInvertedL.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceInvertedL = new PieceInvertedL(1, 1);
        pieceInvertedL.rotateRight();
        Assertions.assertEquals(right, pieceInvertedL.getSquares());
        Assertions.assertEquals(0, pieceInvertedL.getCenterX());
        Assertions.assertEquals(2, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateRight();
        Assertions.assertEquals(down, pieceInvertedL.getSquares());
        Assertions.assertEquals(0, pieceInvertedL.getCenterX());
        Assertions.assertEquals(0, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateRight();
        Assertions.assertEquals(left, pieceInvertedL.getSquares());
        Assertions.assertEquals(2, pieceInvertedL.getCenterX());
        Assertions.assertEquals(0, pieceInvertedL.getCenterY());

        pieceInvertedL.rotateRight();
        Assertions.assertEquals(up, pieceInvertedL.getSquares());
        Assertions.assertEquals(2, pieceInvertedL.getCenterX());
        Assertions.assertEquals(2, pieceInvertedL.getCenterY());
    }
}