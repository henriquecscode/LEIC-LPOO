package com.controller.multiplayer;

import com.Game;
import com.controller.tetris.BoardController;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.multiplayer.MultiplayerTetris;
import com.model.tetris.*;
import com.view.multiplayer.MultiplayerViewer;
import com.view.tetris.GameViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMultiplayerController {
    @Test
    public void basicNeeds() throws FontFormatException, IOException, URISyntaxException {
        MultiplayerTetris multiplayerTetris = new MultiplayerTetris(50,50,new Random());
        MultiplayerController controller = new MultiplayerController(multiplayerTetris);
        Assertions.assertEquals(multiplayerTetris, controller.getModel());
    }

    @Test
    public void addPiece() throws FontFormatException, IOException, URISyntaxException {
        MultiplayerTetris multiplayerTetris = new MultiplayerTetris(50,50,new Random());
        MultiplayerController controller = new MultiplayerController(multiplayerTetris);
        Piece piece = new PieceSquare(4, 3);
        Piece piece2 = new PieceL(3,4);

        int size = multiplayerTetris.getTetris(false).getBoard().getPieces().size();
        controller.getModel().getTetris(false).getBoard().setCur_piece(piece);
        controller.addPiece(false);
        Assertions.assertNotEquals(size, controller.getModel().getTetris(false).getBoard().getPieces().size());

        int size2 = multiplayerTetris.getTetris(true).getBoard().getPieces().size();
        controller.getModel().getTetris(true).getBoard().setCur_piece(piece);
        controller.addPiece(true);
        Assertions.assertNotEquals(size2, controller.getModel().getTetris(true).getBoard().getPieces().size());
    }

    @Test
    public void clearBoard() throws FontFormatException, IOException, URISyntaxException {
        MultiplayerTetris multiplayerTetris = new MultiplayerTetris(50, 50, new Random());
        Piece square = new PieceSquare(0, 18);
        Piece square2 = new PieceSquare(2, 18);
        Piece square3 = new PieceSquare(4, 18);
        Piece square4 = new PieceSquare(6, 18);
        Piece square5 = new PieceSquare(8, 18);
        Piece line = new PieceLine(0, 17);
        Piece line2 = new PieceLine(1, 17);
        Piece line3 = new PieceLine(2, 17);
        Piece line4 = new PieceLine(3, 17);
        Piece line5 = new PieceLine(4, 17);
        Piece line6 = new PieceLine(5, 17);
        Piece line7 = new PieceLine(6, 17);
        Piece line8 = new PieceLine(7, 17);
        Piece line9 = new PieceLine(8, 17);
        Piece line10 = new PieceLine(9, 17);
        MultiplayerController controller = new MultiplayerController(multiplayerTetris);

        List<Piece> pieces = new ArrayList<>();
        pieces.add(square);
        pieces.add(square2);
        pieces.add(square3);
        pieces.add(square4);
        pieces.add(square5);

        controller.getModel().getTetris(false).getBoard().addPiece(square);
        controller.getModel().getTetris(false).getBoard().addPiece(square2);
        controller.getModel().getTetris(false).getBoard().addPiece(square3);
        controller.getModel().getTetris(false).getBoard().addPiece(square4);
        controller.getModel().getTetris(false).getBoard().addPiece(square5);

        Assertions.assertEquals(pieces, controller.getModel().getTetris(false).getBoard().getPieces());

        for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(controller.getModel().getTetris(false).getBoard().getSpot(i, 19));
            Assertions.assertTrue(controller.getModel().getTetris(false).getBoard().getSpot(i, 18));
        }

        int nlines = controller.clearBoard(false);

        Assertions.assertEquals(210, controller.getModel().getTetris(false).getBoard().getScore());

        for (int i = 0; i < 10; i++) {
            Assertions.assertFalse(controller.getModel().getTetris(false).getBoard().getSpot(i, 19));
            Assertions.assertFalse(controller.getModel().getTetris(false).getBoard().getSpot(i, 18));
        }

        Assertions.assertEquals(2, nlines);


        List<Piece> pieces2 = new ArrayList<>();
        pieces2.add(line);
        pieces2.add(line2);
        pieces2.add(line3);
        pieces2.add(line4);
        pieces2.add(line5);
        pieces2.add(line6);
        pieces2.add(line7);
        pieces2.add(line8);
        pieces2.add(line9);
        pieces2.add(line10);

        controller.getModel().getTetris(true).getBoard().addPiece(line);
        controller.getModel().getTetris(true).getBoard().addPiece(line2);
        controller.getModel().getTetris(true).getBoard().addPiece(line3);
        controller.getModel().getTetris(true).getBoard().addPiece(line4);
        controller.getModel().getTetris(true).getBoard().addPiece(line5);
        controller.getModel().getTetris(true).getBoard().addPiece(line6);
        controller.getModel().getTetris(true).getBoard().addPiece(line7);
        controller.getModel().getTetris(true).getBoard().addPiece(line8);
        controller.getModel().getTetris(true).getBoard().addPiece(line9);
        controller.getModel().getTetris(true).getBoard().addPiece(line10);


        Assertions.assertEquals(pieces2, controller.getModel().getTetris(true).getBoard().getPieces());

        for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(controller.getModel().getTetris(true).getBoard().getSpot(i, 19));
            Assertions.assertTrue(controller.getModel().getTetris(true).getBoard().getSpot(i, 18));
        }

        nlines = controller.clearBoard(true);

        Assertions.assertEquals(460, controller.getModel().getTetris(true).getBoard().getScore());

        for (int i = 0; i < 10; i++) {
            Assertions.assertFalse(controller.getModel().getTetris(true).getBoard().getSpot(i, 19));
            Assertions.assertFalse(controller.getModel().getTetris(true).getBoard().getSpot(i, 18));
        }

        Assertions.assertEquals(4, nlines);
    }

    @Test
    public void step() throws FontFormatException, IOException, URISyntaxException, InterruptedException {
        Game game = Mockito.mock(Game.class);
        Leaderboard ldb = Mockito.mock(Leaderboard.class);
        Random rand = new Mockito().mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        MultiplayerTetris multiplayerTetris = new MultiplayerTetris(50,50,rand);
        Board board1 = multiplayerTetris.getTetris(false).getBoard();
        Board board2 = multiplayerTetris.getTetris(true).getBoard();
        MultiplayerViewer viewer = new MultiplayerViewer(multiplayerTetris);
        Piece piece0 = new PieceT(1, 19);
        Piece piece1 = new PieceL(3, 19);
        Piece piece2 = new PieceT(8, 19);

        board1.addPiece(piece0);
        board1.addPiece(piece1);
        board2.addPiece(piece2);
        MultiplayerController controller = new MultiplayerController(multiplayerTetris);

        Piece piece = controller.getModel().getTetris(false).getBoard().getCur_piece();
        int piecey = piece.getPosY();
        int piecex = piece.getPosX();

        Thread.sleep(controller.getModel().getTetris(false).getDropInterval());
        controller.step(game, GUI.ACTION.NONE, System.currentTimeMillis(), viewer, ldb);
        Assertions.assertEquals(piecex, piece.getPosX());
        Assertions.assertEquals(piecey + 1, piece.getPosY());
        controller.step(game, GUI.ACTION.DROP, System.currentTimeMillis(), viewer, ldb);
        Thread.sleep(controller.getModel().getTetris(false).getDropInterval());
        controller.step(game, GUI.ACTION.NONE, System.currentTimeMillis(), viewer, ldb);
        Assertions.assertNotEquals(piece, controller.getModel().getTetris(false).getBoard().getCur_piece());
    }
}