package com.model.tetris;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class TestBoard {
    private int rows = 20, columns =10;
    @Test
    public void Constructor(){
        Board board = new Board();
        int count = 0;
        for (int i = 0; i < columns; i++){
            for (int j = 1; j < rows; j++){
                if (board.getSpot(i, j) == false){
                    count++;
                }
                System.out.println(board.getSpot(i, j));
            }
        }

        Assertions.assertEquals(count, 190);
    }

    @Test
    public void Fill(){
        Board board = new Board();

        Assertions.assertEquals(1,board.fill(1,1));
        Assertions.assertEquals(0,board.fill(1,1));
        Assertions.assertEquals(-1,board.fill(-1,10));
        Assertions.assertEquals(-2,board.fill(11,0));
        Assertions.assertTrue(board.isFinish());
    }

    @Test
    public void lineFull(){
        Board board = new Board();

        for (int i = 0; i < columns; i++){
            board.fill(i, 19); board.fill(i,13);
        }

        Assertions.assertEquals(true,board.isLineFull(19));
        Assertions.assertEquals( true,board.isLineFull(13));
        Assertions.assertEquals(false,board.isLineFull(2));
        Assertions.assertEquals(false,board.isLineFull(4));

    }

    @Test
    public void addPiece(){
        Board board = new Board();

        for(int i = 0; i < board.getColumns(); i++){
            for(int j = 1; j < board.getRows(); j++){
                Assertions.assertEquals(false, board.getSpot(i,j));
            }
        }
        Piece pieceSquare = new PieceSquare(1,1);
        board.addPiece(pieceSquare);

        Assertions.assertEquals(1, board.getPieces().size());
        Assertions.assertEquals(true, board.getSpot(1,1));
        Assertions.assertEquals(true, board.getSpot(1,2));
        Assertions.assertEquals(true, board.getSpot(2,1));
        Assertions.assertEquals(true, board.getSpot(2,2));


    }

    @Test
    public void lineClearMove(){
        Board board = new Board();

        //Lines full->16,17,18,19

        Piece piece = new PieceLine(0,17);
        board.addPiece(piece);
        Piece piece1 = new PieceLine(1,17);
        board.addPiece(piece1);
        Piece piece2 = new PieceLine(2,17);
        board.addPiece(piece2);
        Piece piece3 = new PieceLine(3,17);
        board.addPiece(piece3);
        Piece piece4 = new PieceLine(4,17);
        board.addPiece(piece4);
        Piece piece5 = new PieceLine(5,17);
        board.addPiece(piece5);
        Piece piece6 = new PieceLine(6,17);
        board.addPiece(piece6);
        Piece piece7 = new PieceLine(7,17);
        board.addPiece(piece7);
        Piece piece8 = new PieceLine(8,17);
        board.addPiece(piece8);
        Piece piece9 = new PieceLine(9,17);
        board.addPiece(piece9);
        Piece piece10 = new PieceL(5,14);
        board.addPiece(piece10);

        for(int i=16;i<=19;i++){
            board.clearLine(i);
        }
        ArrayList<ArrayList<Boolean>> test_squares = new ArrayList<>();

        ArrayList<Boolean> a1 = new ArrayList<>();
        a1.add(false);
        a1.add(false);
        a1.add(false);
        a1.add(false);

        ArrayList<Boolean> a2 = new ArrayList<>();
        a2.add(false);
        a2.add(false);
        a2.add(false);
        a2.add(false);

        ArrayList<Boolean> a3 = new ArrayList<>();
        a3.add(false);
        a3.add(false);
        a3.add(false);
        a3.add(false);

        ArrayList<Boolean> a4 = new ArrayList<>();
        a4.add(false);
        a4.add(false);
        a4.add(false);
        a4.add(false);

        test_squares.add(a1);
        test_squares.add(a2);
        test_squares.add(a3);
        test_squares.add(a4);

        for(int i=0;i<board.getPieces().size()-1;i++){
            Assertions.assertEquals(test_squares, board.getPieces().get(i).getSquares());

        }

        Assertions.assertEquals(18,piece10.getPosY()); //Piece10 isn't cleared-->checks if it was dropped after the other lines were cleared


    }

    @Test
    void savePiece() {
        Board board = new Board();
        Piece piece = new PieceS(5,4);
        board.setCur_piece(piece);

        Assertions.assertEquals(1,board.savePiece());
        Assertions.assertEquals(0,board.savePiece());

        Assertions.assertEquals(piece.getPosY(),board.getCur_piece().getPosY());
        Assertions.assertEquals(piece.getPosX(),board.getCur_piece().getPosX());

        Assertions.assertEquals(piece.getSquares(),board.getGhost_piece().getSquares());
    }
}
