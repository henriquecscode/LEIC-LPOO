package com.model.tetris;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestPieceSquare {

    List<List<Boolean>> up = Arrays.asList(
            Arrays.asList(true, true),
            Arrays.asList(true, true));

    @Test
    void pieceSquare() {
        Piece pieceSquare = new PieceSquare(0, 0);
        Assertions.assertEquals(up, pieceSquare.getSquares());
    }

    @Test
    void rotateLeft() {

        Piece pieceSquare = new PieceSquare(0, 0);
        pieceSquare.rotateLeft();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateLeft();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateLeft();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateLeft();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());
    }

    @Test
    void rotateRight() {
        Piece pieceSquare = new PieceSquare(0, 0);
        pieceSquare.rotateRight();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateRight();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateRight();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());

        pieceSquare.rotateRight();
        Assertions.assertEquals(up, pieceSquare.getSquares());
        Assertions.assertEquals(0, pieceSquare.getCenterX());
        Assertions.assertEquals(0, pieceSquare.getCenterY());
    }
}