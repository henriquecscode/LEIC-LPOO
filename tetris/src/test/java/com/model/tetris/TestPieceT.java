package com.model.tetris;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestPieceT {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(false, true, false),
            Arrays.asList(true, true, true),
            Arrays.asList(false, false, false));

    List<List<Boolean>> left = Arrays.asList(
            Arrays.asList(false, true, false),
            Arrays.asList(true, true, false),
            Arrays.asList(false, true, false));

    List<List<Boolean>> down = Arrays.asList(
            Arrays.asList(false, false, false),
            Arrays.asList(true, true, true),
            Arrays.asList(false, true, false));

    List<List<Boolean>> right = Arrays.asList(
            Arrays.asList(false, true, false),
            Arrays.asList(false, true, true),
            Arrays.asList(false, true, false));

    @Test
    void pieceT() {
        Piece pieceT = new PieceT(1, 1);
        Assertions.assertEquals(up, pieceT.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceT = new PieceT(1, 1);
        pieceT.rotateLeft();
        Assertions.assertEquals(left, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateLeft();
        Assertions.assertEquals(down, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateLeft();
        Assertions.assertEquals(right, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateLeft();
        Assertions.assertEquals(up, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceT = new PieceT(1, 1);
        pieceT.rotateRight();
        Assertions.assertEquals(right, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateRight();
        Assertions.assertEquals(down, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateRight();
        Assertions.assertEquals(left, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());

        pieceT.rotateRight();
        Assertions.assertEquals(up, pieceT.getSquares());
        Assertions.assertEquals(1, pieceT.getCenterX());
        Assertions.assertEquals(1, pieceT.getCenterY());
    }
}