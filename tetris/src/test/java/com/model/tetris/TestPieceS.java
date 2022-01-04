package com.model.tetris;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestPieceS {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(false, true, true),
            Arrays.asList(true, true, false),
            Arrays.asList(false, false, false));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(true, false, false),
            Arrays.asList(true, true, false),
            Arrays.asList(false, true, false));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(false, false, false),
            Arrays.asList(false, true, true),
            Arrays.asList(true, true, false));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(false, true, false),
            Arrays.asList(false, true, true),
            Arrays.asList(false, false, true));

    @Test
    void pieceS() {
        Piece pieceS = new PieceS(1, 1);
        Assertions.assertEquals(up, pieceS.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceS = new PieceS(1, 1);
        pieceS.rotateLeft();
        Assertions.assertEquals(left, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateLeft();
        Assertions.assertEquals(down, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateLeft();
        Assertions.assertEquals(right, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateLeft();
        Assertions.assertEquals(up, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceS = new PieceS(1, 1);
        pieceS.rotateRight();
        Assertions.assertEquals(right, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateRight();
        Assertions.assertEquals(down, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateRight();
        Assertions.assertEquals(left, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());

        pieceS.rotateRight();
        Assertions.assertEquals(up, pieceS.getSquares());
        Assertions.assertEquals(1, pieceS.getCenterX());
        Assertions.assertEquals(1, pieceS.getCenterY());
    }
}