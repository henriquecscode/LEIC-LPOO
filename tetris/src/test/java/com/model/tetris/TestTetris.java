package com.model.tetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class TestTetris {
    private Tetris tetris;
    private Random random;

    @BeforeEach
    void setUp() throws FontFormatException, IOException, URISyntaxException {
        random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(Mockito.anyInt()))
                .thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3) //Tetris construction
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4)
                .thenReturn(5)
                .thenReturn(6);
        tetris = new Tetris(50, 50, random);

    }

    @Test
    void startGame(){
        Assertions.assertEquals(tetris.getBoard().getCur_piece().getSquares(), tetris.getBoard().getGhost_piece().getSquares());
    }

    @Test
    void startPieceQueue() {
        Assertions.assertTrue(tetris.getBoard().getCur_piece() instanceof PieceInvertedL);
        Assertions.assertTrue(tetris.getPieceQueue().get(0) instanceof PieceL);
        Assertions.assertTrue(tetris.getPieceQueue().get(1) instanceof PieceLine);
        Assertions.assertTrue(tetris.getPieceQueue().get(2) instanceof PieceS);
    }

    @Test
    void getNextPiece() {
        Assertions.assertEquals(3, tetris.getPieceQueue().size());
        Piece piece = tetris.getNextPiece();
        Assertions.assertTrue(piece instanceof PieceL);
        Assertions.assertTrue(tetris.getPieceQueue().get(0) instanceof PieceLine);
        Assertions.assertTrue(tetris.getPieceQueue().get(1) instanceof PieceS);
        Assertions.assertTrue(tetris.getPieceQueue().get(2) instanceof PieceInvertedL);
        Assertions.assertEquals(3, tetris.getPieceQueue().size());

    }

    @Test
    void getNewPiece() {
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceInvertedL);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceL);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceLine);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceS);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceT);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceSquare);
        Assertions.assertTrue(tetris.getNewPiece() instanceof PieceZ);

    }

    @Test
    void filled() {
        tetris.getBoard().fill(tetris.getInitialX(),tetris.getInitialY());
        tetris.getNewPiece();
        Assertions.assertEquals(1,tetris.getInitialY());
    }

    @Test
    public void addPieceToQueue() {
        Assertions.assertTrue(tetris.getBoard().getCur_piece() instanceof PieceInvertedL);
        Assertions.assertTrue(tetris.getPieceQueue().get(0) instanceof PieceL);
        Assertions.assertTrue(tetris.getPieceQueue().get(1) instanceof PieceLine);
        Assertions.assertTrue(tetris.getPieceQueue().get(2) instanceof PieceS);
        tetris.addPieceToQueue();
        Assertions.assertTrue(tetris.getPieceQueue().get(3) instanceof PieceInvertedL);
    }

    @Test
    public void removePieceFromQueue() {

        Assertions.assertTrue(tetris.getBoard().getCur_piece() instanceof PieceInvertedL);
        Assertions.assertTrue(tetris.getPieceQueue().get(0) instanceof PieceL);
        Assertions.assertTrue(tetris.getPieceQueue().get(1) instanceof PieceLine);
        Assertions.assertTrue(tetris.getPieceQueue().get(2) instanceof PieceS);
        Piece piece1, piece2, piece3;
        piece1 = tetris.removePieceFromQueue();
        piece2 = tetris.removePieceFromQueue();
        piece3 = tetris.removePieceFromQueue();
        Assertions.assertTrue(piece1 instanceof PieceL);
        Assertions.assertTrue(piece2 instanceof PieceLine);
        Assertions.assertTrue(piece3 instanceof PieceS);
        Assertions.assertEquals(new ArrayList<>(), tetris.getPieceQueue());

    }
}

