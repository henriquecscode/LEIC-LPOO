package com.controller.tetris;

import com.gui.GUI;
import com.model.tetris.*;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestPieceController {
    ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();


    @Test
    void TestRightRotationWithRightCollisionWalls() throws IOException, InterruptedException, FontFormatException, IOException, URISyntaxException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceL(8, 3);
        Tetris tetris = new Tetris(50,50, new Random());

        board.setCur_piece(piece);
        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(true,false);

        Assertions.assertEquals(squares, piece.getSquares());
        Assertions.assertEquals(8, piece.getPosX());
    }

    @Test
    void TestLeftRotationWithRightCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceL(8, 3);
        Tetris tetris = new Tetris(50,50, new Random());
        board.setCur_piece(piece);

        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(false,false);

        Assertions.assertEquals(squares, piece.getSquares());
        Assertions.assertEquals(8, piece.getPosX());
        Assertions.assertEquals(3, piece.getPosY());
    }

    @Test
    void TestRightRotationWithLeftCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceL(0, 3);
        Tetris tetris = new Tetris(50,50, new Random());
        board.setCur_piece(piece);

        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(true,false);

        Assertions.assertEquals(squares, piece.getSquares());
        Assertions.assertEquals(0, piece.getPosX());
        Assertions.assertEquals(3, piece.getPosY());
    }

    @Test
    void TestLeftRotationWithLeftCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceL(0, 3);
        Tetris tetris = new Tetris(50,50, new Random());
        board.setCur_piece(piece);

        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(false,false);

        Assertions.assertEquals(squares, piece.getSquares());
        Assertions.assertEquals(0, piece.getPosX());
        Assertions.assertEquals(3, piece.getPosY());
    }

    @Test
    void TestRightRotationWithRightCollisionPiece() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceS(6, 12);

        board.addPiece(piece);

        Piece piece2 = new PieceL(3, 12);

        board.setCur_piece(piece2);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(true,false);

        Assertions.assertEquals(squares, piece2.getSquares());
        Assertions.assertEquals(3, piece2.getPosX());
        Assertions.assertEquals(12, piece2.getPosY());
    }

    @Test
    void TestLeftRotationWithLeftCollisionPiece() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        squares.clear();
        ArrayList<Boolean> line1 = new ArrayList<>();
        ArrayList<Boolean> line2 = new ArrayList<>();
        ArrayList<Boolean> line3 = new ArrayList<>();

        line1.add(true);
        line1.add(false);
        line1.add(false);
        line2.add(true);
        line2.add(false);
        line2.add(false);
        line3.add(true);
        line3.add(true);
        line3.add(false);
        squares.add(line1);
        squares.add(line2);
        squares.add(line3);

        Board board = new Board();
        Piece piece = new PieceS(1, 12);

        board.addPiece(piece);

        Piece piece2 = new PieceL(3, 12);
        Tetris tetris = new Tetris(50,50, new Random());
        board.setCur_piece(piece2);
        PieceController controller = new PieceController(tetris);
        controller.rotateDirectionIfPossible(false,false);

        Assertions.assertEquals(squares, piece2.getSquares());
        Assertions.assertEquals(3, piece2.getPosX());
        Assertions.assertEquals(12, piece2.getPosY());
    }

    @Test
    void TestMoveRightWithCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();
        Piece piece = new PieceL(8, 3);

        board.setCur_piece(piece);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveRightIfPossible(false);

        Assertions.assertEquals(8, piece.getPosX());
    }


    @Test
    void TestMoveLeftWithCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();
        Piece piece = new PieceL(0, 3);

        board.setCur_piece(piece);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveLeftIfPossible(false);

        Assertions.assertEquals(0, piece.getPosX());
    }

    @Test
    void TestMoveDownWithCollisionWalls() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();
        Piece piece = new PieceL(0,19);

        board.setCur_piece(piece);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveDownIfPossible(false);

        Assertions.assertEquals(19, piece.getPosY());
    }

    @Test
    void TestMoveRightWithCollisionPiece() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();
        Piece piece = new PieceS(6,12);

        board.addPiece(piece);

        Piece piece2 = new PieceLine(4,12);

        board.setCur_piece(piece2);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveRightIfPossible(false);

        Assertions.assertEquals(4, piece2.getPosX());
    }

    @Test
    void TestMoveLeftWithCollisionPiece() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();
        Piece piece = new PieceS(6,12);

        board.addPiece(piece);

        Piece piece2 = new PieceLine(8,12);

        board.setCur_piece(piece2);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveLeftIfPossible(false);

        Assertions.assertEquals(8, piece2.getPosX());
    }

    @Test
    void TestMoveDownWithCollisionPiece() throws IOException, InterruptedException, URISyntaxException, FontFormatException {

        Board board = new Board();

        Piece piece = new PieceS(6,12);

        board.addPiece(piece);

        Piece piece2 = new PieceLine(6,8);

        board.setCur_piece(piece2);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.moveDownIfPossible(false);


        Assertions.assertEquals(8, piece2.getPosY());
    }

    @Test
    public void dropIfPossible() throws FontFormatException, IOException, URISyntaxException {

        Board board = new Board();


        Piece piece = new PieceInvertedL(4,3);

        board.setCur_piece(piece);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        controller.dropIfPossible(false);

        Assertions.assertEquals(3, piece.getPosY());

    }

    @Test
    public void holdPiece() throws FontFormatException, IOException, URISyntaxException {
        Piece piece = new PieceSquare(3, 4);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);
        tetris.getBoard().setCur_piece(piece);

        Assertions.assertEquals(true,tetris.getBoard().getSave());
        controller.holdPiece();
        Assertions.assertEquals(piece,tetris.getBoard().getSaved());
        Assertions.assertEquals(tetris.getInitialX(),tetris.getBoard().getCur_piece().getPosX());
        Assertions.assertEquals(tetris.getInitialY(),tetris.getBoard().getCur_piece().getPosY());
    }

    @Test
    public void doAction() throws IOException, URISyntaxException, FontFormatException {
        Piece piece = new PieceSquare(3, 4);
        Tetris tetris = new Tetris(50,50, new Random());
        PieceController controller = new PieceController(tetris);

        tetris.getBoard().setCur_piece(piece);

        controller.doAction(GUI.ACTION.DOWN, false);
        Assertions.assertEquals(5, piece.getPosY());
        Assertions.assertEquals(3, piece.getPosX());

        controller.doAction(GUI.ACTION.LEFT, false);
        Assertions.assertEquals(2, piece.getPosX());
        Assertions.assertEquals(5, piece.getPosY());

        controller.doAction(GUI.ACTION.RIGHT, false);
        Assertions.assertEquals(3, piece.getPosX());
        Assertions.assertEquals(5, piece.getPosY());

        controller.doAction(GUI.ACTION.ROTATE_R, false);
        Assertions.assertEquals(3, piece.getPosX());
        Assertions.assertEquals(5, piece.getPosY());

        controller.doAction(GUI.ACTION.ROTATE_L, false);
        Assertions.assertEquals(3, piece.getPosX());
        Assertions.assertEquals(5, piece.getPosY());

        Assertions.assertEquals(true,tetris.getBoard().getSave());
        controller.doAction(GUI.ACTION.HOLD, false);
        Assertions.assertEquals(piece,tetris.getBoard().getSaved());

    }


}
