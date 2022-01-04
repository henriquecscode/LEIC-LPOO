package com.controller.tetris;

import com.Game;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.tetris.*;
import com.view.tetris.BlindGameViewer;
import com.view.tetris.GameViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBoardController {
    @Test
    public void basicNeeds() throws FontFormatException, IOException, URISyntaxException {
        Tetris tetris = new Tetris(50, 50, new Random());
        BoardController controller = new BoardController(tetris);
        Assertions.assertEquals(tetris, controller.getTetris());
    }

    @Test
    public void step() throws FontFormatException, IOException, URISyntaxException, InterruptedException {

        Game game = Mockito.mock(Game.class);
        Leaderboard ldb = Mockito.mock(Leaderboard.class);
        Random rand = new Mockito().mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        Tetris tetris = new Tetris(50, 50, rand);
        Board board = tetris.getBoard();
        GameViewer viewer = new GameViewer(tetris);
        Piece piece0 = new PieceT(1, 19);
        Piece piece1 = new PieceL(3, 19);
        Piece piece2 = new PieceT(8, 19);

        board.addPiece(piece0);
        board.addPiece(piece1);
        board.addPiece(piece2);
        BoardController controller = new BoardController(tetris);

        Piece piece = controller.getTetris().getBoard().getCur_piece();
        int piecey = piece.getPosY();
        int piecex = piece.getPosX();
        Thread.sleep(controller.getModel().getDropInterval());
        controller.step(game, GUI.ACTION.NONE, System.currentTimeMillis(), viewer, ldb);
        Assertions.assertEquals(piecex, piece.getPosX());
        Assertions.assertEquals(piecey+1 , piece.getPosY());
        controller.step(game, GUI.ACTION.DROP, System.currentTimeMillis(), viewer, ldb);
        Thread.sleep(controller.getModel().getDropInterval());
        controller.step(game, GUI.ACTION.NONE, System.currentTimeMillis(), viewer, ldb);
        Assertions.assertNotEquals(piece, controller.getModel().getBoard().getCur_piece());

    }

    @Test
    public void addPiece() throws FontFormatException, IOException, URISyntaxException {
        Tetris tetris = new Tetris(50, 50, new Random());
        BoardController controller = new BoardController(tetris);
        Piece piece = new PieceSquare(4, 3);

        int size = tetris.getBoard().getPieces().size();
        controller.getTetris().getBoard().setCur_piece(piece);
        controller.addPiece();
        Assertions.assertNotEquals(size, controller.getTetris().getBoard().getPieces().size());
    }

    @Test
    public void clearBoard() throws FontFormatException, IOException, URISyntaxException {
        Tetris tetris = new Tetris(50, 50, new Random());
        Piece square = new PieceSquare(0, 18);
        Piece square2 = new PieceSquare(2, 18);
        Piece square3 = new PieceSquare(4, 18);
        Piece square4 = new PieceSquare(6, 18);
        Piece square5 = new PieceSquare(8, 18);
        BoardController controller = new BoardController(tetris);

        List<Piece> pieces = new ArrayList<>();
        pieces.add(square);
        pieces.add(square2);
        pieces.add(square3);
        pieces.add(square4);
        pieces.add(square5);

        controller.getModel().getBoard().addPiece(square);
        controller.getModel().getBoard().addPiece(square2);
        controller.getModel().getBoard().addPiece(square3);
        controller.getModel().getBoard().addPiece(square4);
        controller.getModel().getBoard().addPiece(square5);

        Assertions.assertEquals(pieces, controller.getModel().getBoard().getPieces());

        for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(controller.getModel().getBoard().getSpot(i, 19));
            Assertions.assertTrue(controller.getModel().getBoard().getSpot(i, 18));
        }

        int nlines = controller.clearBoard();

        Assertions.assertEquals(210, controller.getModel().getBoard().getScore());

        for (int i = 0; i < 10; i++) {
            Assertions.assertFalse(controller.getModel().getBoard().getSpot(i, 19));
            Assertions.assertFalse(controller.getModel().getBoard().getSpot(i, 18));
        }

        Assertions.assertEquals(2, nlines);
    }
}
