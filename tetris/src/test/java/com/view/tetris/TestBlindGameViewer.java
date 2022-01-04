package com.view.tetris;

import com.Game;
import com.controller.tetris.BoardController;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.tetris.*;
import com.states.BlindGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class TestBlindGameViewer {
    private GUI gui;
    private BlindGameViewer viewer;
    private Board board;
    private Random rand;
    private Tetris tetris;
    private Game game;

    @BeforeEach
    void setUp() throws FontFormatException, IOException, URISyntaxException {
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);
        rand = new Mockito().mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        tetris = new Tetris(50, 50, rand);
        board = tetris.getBoard();
        viewer = new BlindGameViewer(tetris);
        Piece piece0 = new PieceT(1, 19);
        Piece piece1 = new PieceL(3, 19);
        Piece piece2 = new PieceT(8, 19);

        board.addPiece(piece0);
        board.addPiece(piece1);
        board.addPiece(piece2);

    }

    @Test
    public void noPrinting() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        BoardController controller = new BoardController(tetris);
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(4)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
        Thread.sleep(viewer.getFlashPiecestime());
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(5)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
        controller.step(game, GUI.ACTION.DROP, System.currentTimeMillis(), viewer, new Leaderboard());
        Thread.sleep(tetris.getDropInterval());
        controller.step(game, GUI.ACTION.NONE, System.currentTimeMillis(), viewer, new Leaderboard());
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(10)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
        Thread.sleep(viewer.getFlashPiecestime());
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(11)).drawPiece(Mockito.any(Piece.class), Mockito.any(Boolean.class), Mockito.any(Boolean.class));
    }

    @Test
    void cleared() {
        viewer.clearedLines(0);
    }
}
