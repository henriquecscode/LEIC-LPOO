package com.model.tetris;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class TestPieceL {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(true, false, false),
            Arrays.asList(true, false, false),
            Arrays.asList(true, true, false));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(false, false, false),
            Arrays.asList(false, false, true),
            Arrays.asList(true, true, true));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(false, true, true),
            Arrays.asList(false, false, true),
            Arrays.asList(false, false, true));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(true, true, true),
            Arrays.asList(true, false, false),
            Arrays.asList(false, false, false));

    @Test
    void pieceL() {
        Piece pieceL = new PieceL(1, 1);
        Assertions.assertEquals(up, pieceL.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceL = new PieceL(1, 1);
        pieceL.rotateLeft();
        Assertions.assertEquals(left, pieceL.getSquares());
        Assertions.assertEquals(2, pieceL.getCenterX());
        Assertions.assertEquals(2, pieceL.getCenterY());

        pieceL.rotateLeft();
        Assertions.assertEquals(down, pieceL.getSquares());
        Assertions.assertEquals(2, pieceL.getCenterX());
        Assertions.assertEquals(0, pieceL.getCenterY());

        pieceL.rotateLeft();
        Assertions.assertEquals(right, pieceL.getSquares());
        Assertions.assertEquals(0, pieceL.getCenterX());
        Assertions.assertEquals(0, pieceL.getCenterY());

        pieceL.rotateLeft();
        Assertions.assertEquals(up, pieceL.getSquares());
        Assertions.assertEquals(0, pieceL.getCenterX());
        Assertions.assertEquals(2, pieceL.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceL = new PieceL(1, 1);
        pieceL.rotateRight();
        Assertions.assertEquals(right, pieceL.getSquares());
        Assertions.assertEquals(0, pieceL.getCenterX());
        Assertions.assertEquals(0, pieceL.getCenterY());

        pieceL.rotateRight();
        Assertions.assertEquals(down, pieceL.getSquares());
        Assertions.assertEquals(2, pieceL.getCenterX());
        Assertions.assertEquals(0, pieceL.getCenterY());

        pieceL.rotateRight();
        Assertions.assertEquals(left, pieceL.getSquares());
        Assertions.assertEquals(2, pieceL.getCenterX());
        Assertions.assertEquals(2, pieceL.getCenterY());

        pieceL.rotateRight();
        Assertions.assertEquals(up, pieceL.getSquares());
        Assertions.assertEquals(0, pieceL.getCenterX());
        Assertions.assertEquals(2, pieceL.getCenterY());
    }
}