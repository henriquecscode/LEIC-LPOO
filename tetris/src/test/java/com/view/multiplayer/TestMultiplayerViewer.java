package com.view.multiplayer;

import com.gui.GUI;
import com.model.multiplayer.MultiplayerTetris;
import com.model.tetris.*;
import com.view.multiplayer.MultiplayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;


public class TestMultiplayerViewer {
    private GUI gui;
    private MultiplayerViewer viewer;
    private Board board1, board2;
    private MultiplayerTetris tetris;
    private Random rand;

    @BeforeEach
    void setUp() throws FontFormatException, IOException, URISyntaxException {
        MultiplayerTetris multiplayerTetris = new MultiplayerTetris(50,50,new Random());
        board1 = multiplayerTetris.getTetris(false).getBoard();
        board2 = multiplayerTetris.getTetris(true).getBoard();
        gui = Mockito.mock(GUI.class);
        viewer = new MultiplayerViewer(multiplayerTetris);
        rand = new Mockito().mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        Piece piece0 = new PieceLine(0,16);
        Piece piece1 = new PieceLine(1,17);
        Piece piece2 = new PieceL(4,6);
        Piece piece3 = new PieceS(5,10);

        board1.addPiece(piece1);
        board1.addPiece(piece0);
        board2.addPiece(piece3);
        board2.addPiece(piece2);

        board2.savePiece();
    }

    @Test
    void drawBoard() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBoard(Mockito.any(Board.class),Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),Mockito.any(Boolean.class));
    }

    @Test
    void drawPieces() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(4*2)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
    }

    @Test
    void drawScore() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(2))
                .drawText(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void drawSaved() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawSavedPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class));
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
