package com.controller.tetris;

import com.Game;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.tetris.*;
import com.view.tetris.GameViewer;

import java.io.IOException;
import java.util.ArrayList;

public class PieceController extends GameController {


    public PieceController(Tetris tetris) {
        super(tetris);

    }

    @Override
    public void step(Game game, GUI.ACTION action, long time, GameViewer viewer, Leaderboard lbd) throws IOException {
    }


    public void movePieceLeft(int move) {
        for (int i = 0; i < move; i++) {
            getModel().getBoard().getGhost_piece().moveLeft();
            getModel().getBoard().getCur_piece().moveLeft();
        }
    }

    public void movePieceRight(int move) {
        for (int i = 0; i < move; i++) {
            getModel().getBoard().getGhost_piece().moveRight();
            getModel().getBoard().getCur_piece().moveRight();
        }
    }


    public void movePieceDown(boolean ghost) {
        if (ghost) {
            getModel().getBoard().getGhost_piece().moveDown();
        } else {
            getModel().getBoard().getGhost_piece().moveDown();
            getModel().getBoard().getCur_piece().moveDown();
        }
    }

    public void movePieceUp(boolean ghost) {
        if (ghost) {
            getModel().getBoard().getGhost_piece().moveUp();
        } else {
            getModel().getBoard().getCur_piece().moveUp();
        }
    }

    public void rotatePieceLeft() {
        getModel().getBoard().getGhost_piece().rotateLeft();
        getModel().getBoard().getCur_piece().rotateLeft();
    }

    public void rotatePieceRight() {
        getModel().getBoard().getGhost_piece().rotateRight();
        getModel().getBoard().getCur_piece().rotateRight();
    }

    public void doAction(GUI.ACTION action, boolean ghost) {
        if (action == GUI.ACTION.RIGHT || action == GUI.ACTION.MPRIGHT) moveRightIfPossible(ghost);
        if (action == GUI.ACTION.DOWN || action == GUI.ACTION.MPDOWN) moveDownIfPossible(ghost);
        if (action == GUI.ACTION.LEFT || action == GUI.ACTION.MPLEFT) moveLeftIfPossible(ghost);
        if (action == GUI.ACTION.ROTATE_L || action == GUI.ACTION.MPROTATE_L) rotateDirectionIfPossible(false, ghost);
        if (action == GUI.ACTION.ROTATE_R || action == GUI.ACTION.MPROTATE_R) rotateDirectionIfPossible(true, ghost);
        if (action == GUI.ACTION.DROP || action == GUI.ACTION.MPDROP) dropIfPossible(ghost);
        if (action == GUI.ACTION.HOLD || action == GUI.ACTION.MPHOLD) holdPiece();
    }

    public boolean testCollision(boolean ghost) {
        ArrayList<ArrayList<Boolean>> squares;
        Piece piece;
        if (ghost) {
            squares = getModel().getBoard().getGhost_piece().getSquares();
            piece = getModel().getBoard().getGhost_piece();
        } else {
            squares = getModel().getBoard().getCur_piece().getSquares();
            piece = getModel().getBoard().getCur_piece();
        }
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        int centerX = piece.getCenterX();
        int centerY = piece.getCenterY();

        for (int i = 0; i < squares.size(); i++) {
            for (int j = 0; j < squares.get(i).size(); j++) {
                if (squares.get(i).get(j) && getModel().getBoard().getSpot(posX - centerX + j, posY - centerY + i)) {
                    return true;
                }
            }

        }
        return false;
    }

    public void rotateDirectionIfPossible(boolean isRight, boolean ghost) {
        // Do the rotation asked for
        if (getModel().getBoard().getCur_piece() instanceof PieceSquare) {
            return;
        }

        if (isRight) {
            rotatePieceRight();
        } else {
            rotatePieceLeft();
        }


        //Check if it is possible to keep the piece in that location
        if (testCollision(ghost)) {
            movePieceRight(1);
            if (testCollision(ghost)) {
                movePieceLeft(2);
                if (testCollision(ghost)) {
                    movePieceRight(3);
                    if (testCollision(ghost)) {
                        movePieceLeft(4);
                        if (testCollision(ghost)) {
                            movePieceRight(2);
                            if (isRight) {
                                rotatePieceLeft();
                            } else {
                                rotatePieceRight();
                            }
                        }
                    }
                }
            }
        }

    }

    public void moveRightIfPossible(boolean ghost) {
        movePieceRight(1);
        if (testCollision(ghost)) {
            movePieceLeft(1);
        }
    }

    public void moveLeftIfPossible(boolean ghost) {
        movePieceLeft(1);
        if (testCollision(ghost)) {
            movePieceRight(1);
        }
    }

    public boolean moveDownIfPossible(boolean ghost) {
        movePieceDown(ghost);

        if (testCollision(ghost)) {
            movePieceUp(ghost);
            return false;
        }

        return true;
    }

    public void dropIfPossible(boolean ghost) {
        while (moveDownIfPossible(ghost)) {
        }

    }

    public void holdPiece() {
        if (getModel().getBoard().getSave()) {
            if (getModel().getBoard().savePiece() == 0) {
                getModel().getBoard().getCur_piece().setPosX(getModel().getInitialX());
                getModel().getBoard().getCur_piece().setPosY(getModel().getInitialY());
            } else {
                Piece newPiece = getModel().getNextPiece();
                getModel().getBoard().setCur_piece(newPiece);
                Piece newPiece2 = new Piece(newPiece.getPosX(), newPiece.getPosY(), newPiece.getCenterX(), newPiece.getCenterY(), 0);
                ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();
                squares = newPiece.getSquares();
                newPiece2.setSquares(squares);
                getModel().getBoard().setGhost_piece(newPiece2);
            }

            getModel().getBoard().setSave(!getModel().getBoard().getSave());
        } else {
            // Not possible to change.
        }

    }


}
