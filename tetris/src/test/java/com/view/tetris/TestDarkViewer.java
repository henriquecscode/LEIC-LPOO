package com.view.tetris;

import com.gui.GUI;
import com.model.tetris.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;


public class TestDarkViewer {
    private GUI gui;
    private DarkViewer viewer;
    private Board board;
    private Tetris tetris;
    private Random rand;

    @BeforeEach
    void setUp() throws FontFormatException, IOException, URISyntaxException {
        tetris = new Tetris(50,50, new Random());
        board = tetris.getBoard();
        gui = Mockito.mock(GUI.class);
        viewer = new DarkViewer(tetris);
        rand = new Mockito().mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        Piece piece0 = new PieceLine(0,16);
        Piece piece1 = new PieceLine(1,17);
        Piece piece2 = new PieceL(4,6);
        Piece piece3 = new PieceS(5,10);
        Piece piece4 = new PieceL(4,6);

        board.addPiece(piece1);
        board.addPiece(piece0);
        board.addPiece(piece3);

        board.setCur_piece(piece2);
        board.setGhost_piece(piece4);

        board.savePiece();
    }

    @Test
    void drawBoard() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBoard(tetris.getBoard(),"#A9A9A9", "#A9A9A9", "#A9A9A9",false);
    
}

    @Test
    void drawPieces() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
    }

    @Test
    void drawSaved() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawSavedPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class));
    }

    @Test
    void drawScore() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1))
                .drawText(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }

    @Test
    void cleared() {
        viewer.clearedLines(0);
    }

}
