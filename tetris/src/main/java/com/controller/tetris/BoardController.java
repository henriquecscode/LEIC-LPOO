package com.controller.tetris;

import com.Game;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.menu.Menu;
import com.model.tetris.Piece;
import com.model.tetris.Tetris;
import com.states.LeaderboardState;
import com.states.MenuState;
import com.view.tetris.GameViewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BoardController extends GameController {
    private final PieceController pieceController;
    int FPS = 60;
    int frameTime = 1000 / FPS;
    long lastDropTime;
    int totalLinesCleared = 0;
    int linesForLevel = 5;


    public BoardController(Tetris tetris) throws FileNotFoundException {
        super(tetris);
        this.pieceController = new PieceController(tetris);
        lastDropTime = System.currentTimeMillis();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time, GameViewer viewer, Leaderboard lbd) throws IOException, URISyntaxException, FontFormatException {
        if (action == GUI.ACTION.QUIT || getModel().getBoard().isFinish()) {
            if (lbd.checkIfEnters(getModel().getBoard().getScore())) {
                lbd.addScore(getModel().getBoard().getScore(), getModel().getPlayer());
                game.setState(new LeaderboardState(lbd));
                return;
            }
            game.setState(new MenuState(new Menu()));
            return;
        }

        long now = System.currentTimeMillis();
        if (now - lastDropTime > getModel().getDropInterval()) {
            lastDropTime = now;
            if (!(pieceController.moveDownIfPossible(false))) {
                //Couldn't move piece
                addPiece();
            }
        }


        int noLinesCleared = clearBoard();
        totalLinesCleared += noLinesCleared;
        if(totalLinesCleared >= linesForLevel){
            totalLinesCleared -= linesForLevel;
            getModel().setDropInterval(getModel().getDropInterval()*2/3);
        }

        viewer.clearedLines(noLinesCleared);

        if (getModel().getBoard().getCur_piece().getPosY() == 1) {
            getModel().getBoard().finish();
        }

        pieceController.doAction(action, false);
        getModel().getBoard().getGhost_piece().setPosY(getModel().getBoard().getCur_piece().getPosY());
        getModel().getBoard().getGhost_piece().setPosX(getModel().getBoard().getCur_piece().getPosX());
        pieceController.dropIfPossible(true);
    }

    public void addPiece() {
        getModel().getBoard().addPiece(getModel().getBoard().getCur_piece()); // It's no longer a moving piece
        Piece newPiece = getModel().getNextPiece();
        getModel().getBoard().setCur_piece(newPiece);
        Piece newPiece2 = new Piece(newPiece.getPosX(), newPiece.getPosY(), newPiece.getCenterX(), newPiece.getCenterY(),0);
        ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();
        squares= newPiece.getSquares();
        newPiece2.setSquares(squares);
        getModel().getBoard().setGhost_piece(newPiece2);
    }

    public int clearBoard() {
        int clearedLine = 0;
        int nline = 1;
        for (int i = 1; i < 20; i++) {
            if (getModel().getBoard().isLineFull(i)) {
                clearedLine++;
                getModel().getBoard().clearLine(i);
                getModel().getBoard().fullLineScore((100) * (1 + 0.10 * (nline - 1)));
                nline++;
            }
        }
        return clearedLine;
    }

    public Tetris getTetris() {
        return getModel();
    }

}



