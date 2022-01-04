package com.model.tetris;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestPieceLine {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(true, false, false, false),
            Arrays.asList(true, false, false, false),
            Arrays.asList(true, false, false, false),
            Arrays.asList(true, false, false, false));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(false, false, false, false),
            Arrays.asList(false, false, false, false),
            Arrays.asList(false, false, false, false),
            Arrays.asList(true, true, true, true));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(false, false, false, true),
            Arrays.asList(false, false, false, true),
            Arrays.asList(false, false, false, true),
            Arrays.asList(false, false, false, true));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(true, true, true, true),
            Arrays.asList(false, false, false, false),
            Arrays.asList(false, false, false, false),
            Arrays.asList(false, false, false, false));

    @Test
    void pieceLine() {
        Piece pieceLine = new PieceLine(1, 1);
        Assertions.assertEquals(up, pieceLine.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceLine = new PieceLine(1, 1);
        pieceLine.rotateLeft();
        Assertions.assertEquals(left, pieceLine.getSquares());
        Assertions.assertEquals(1, pieceLine.getCenterX());
        Assertions.assertEquals(3, pieceLine.getCenterY());

        pieceLine.rotateLeft();
        Assertions.assertEquals(down, pieceLine.getSquares());
        Assertions.assertEquals(3, pieceLine.getCenterX());
        Assertions.assertEquals(2, pieceLine.getCenterY());

        pieceLine.rotateLeft();
        Assertions.assertEquals(right, pieceLine.getSquares());
        Assertions.assertEquals(2, pieceLine.getCenterX());
        Assertions.assertEquals(0, pieceLine.getCenterY());

        pieceLine.rotateLeft();
        Assertions.assertEquals(up, pieceLine.getSquares());
        Assertions.assertEquals(0, pieceLine.getCenterX());
        Assertions.assertEquals(1, pieceLine.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceLine = new PieceLine(1, 1);
        pieceLine.rotateRight();
        Assertions.assertEquals(right, pieceLine.getSquares());
        Assertions.assertEquals(2, pieceLine.getCenterX());
        Assertions.assertEquals(0, pieceLine.getCenterY());

        pieceLine.rotateRight();
        Assertions.assertEquals(down, pieceLine.getSquares());
        Assertions.assertEquals(3, pieceLine.getCenterX());
        Assertions.assertEquals(2, pieceLine.getCenterY());

        pieceLine.rotateRight();
        Assertions.assertEquals(left, pieceLine.getSquares());
        Assertions.assertEquals(1, pieceLine.getCenterX());
        Assertions.assertEquals(3   , pieceLine.getCenterY());

        pieceLine.rotateRight();
        Assertions.assertEquals(up, pieceLine.getSquares());
        Assertions.assertEquals(0, pieceLine.getCenterX());
        Assertions.assertEquals(1, pieceLine.getCenterY());
    }
}