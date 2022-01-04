package com.model.tetris;

import com.view.tetris.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Tetris {
    GameViewer viewer;
    Board board;
    Random random;
    static int upperBound = 7, initialX = 5, initialY = 2;
    static int width, height;
    ArrayList<Piece> pieceQueue = new ArrayList<>();
    Player player;
    long dropInterval = 1000;

    public Tetris(int width, int height, Random rand) throws IOException, URISyntaxException, FontFormatException {
        this.board = new Board();
        this.player = new Player();
        this.random = rand;
        this.width = width;
        this.height = height;
        startGame();
        startPieceQueue();
    }

    public void startGame() throws IOException, URISyntaxException, FontFormatException {
        Piece newPiece = getNewPiece();
        board.setCur_piece(newPiece);
        Piece newPiece2 = new Piece(newPiece.getPosX(), newPiece.getPosY(), newPiece.getCenterX(), newPiece.getCenterY(), 0);
        ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();
        squares= newPiece.getSquares();
        newPiece2.setSquares(squares);
        board.setGhost_piece(newPiece2);
    }

    public void startPieceQueue() {
        for (int i = 0; i < 3; i++) {
            addPieceToQueue();
        }
    }

    public Piece getNextPiece(){
        addPieceToQueue();
        return removePieceFromQueue();
    }

    public Piece getNewPiece(){
       if(initialY == 1){
           initialY=2;
       }
        int rand = random.nextInt(upperBound);
        if(this.board.getSpot(initialX,initialY)){
            initialY--;
        }
        switch (rand){
            case 0:
                return new PieceInvertedL(initialX, initialY);
            case 1:
                return new PieceL(initialX, initialY);
            case 2:
                return new PieceLine(initialX, initialY);
            case 3:
                return new PieceS(initialX, initialY);
            case 4:
                return new PieceT(initialX, initialY);
            case 5:
                return new PieceSquare(initialX, initialY);
            case 6:
                return new PieceZ(initialX, initialY);
            default:
                //Program broke, should never get here
                return new Piece(0,0,0,0,0);
        }
    }

    public ArrayList<Piece> getPieceQueue() {
        return pieceQueue;
    }

    public void addPieceToQueue() {
        Piece p = getNewPiece();

        if (p.getOg_centerY() == 1 && p.getOg_centerX() == 1){
            p.rotateRight();
        }


        pieceQueue.add(p);
    }

    public Piece removePieceFromQueue() {
        Piece toReturn = pieceQueue.get(0);
        pieceQueue.remove(0);
        toReturn.setPosX(initialX);
        toReturn.setPosY(initialY);
        return toReturn;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public int getInitialX() { return initialX;}

    public int getInitialY() { return initialY;}

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(){return player;}

    public GameViewer getViewer() {
        return viewer;
    }

    public long getDropInterval() { return dropInterval; }

    public void setDropInterval(long drop) {  dropInterval = drop; }

}
