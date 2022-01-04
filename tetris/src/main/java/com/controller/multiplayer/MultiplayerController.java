package com.controller.multiplayer;

import com.Game;
import com.controller.tetris.PieceController;
import com.gui.GUI;
import com.gui.LanternaGUI;
import com.model.leaderboard.Leaderboard;
import com.model.menu.Menu;
import com.model.multiplayer.MultiplayerTetris;
import com.model.tetris.Piece;
import com.model.tetris.Tetris;
import com.states.LeaderboardState;
import com.states.MenuState;
import com.view.multiplayer.MultiplayerViewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MultiplayerController extends MultiplayerGameController {
    private final Tetris tetris1;
    private final Tetris tetris2;
    private final PieceController pieceController;
    private final PieceController pieceController2;
    long lastDropTime;


    public MultiplayerController(MultiplayerTetris mpTetris) throws FileNotFoundException {
        super(mpTetris);
        tetris1 = mpTetris.getTetris(false);
        tetris2 = mpTetris.getTetris(true);
        this.pieceController = new PieceController(this.tetris1);
        this.pieceController2 = new PieceController(this.tetris2);
        lastDropTime = System.currentTimeMillis();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time, MultiplayerViewer viewer, Leaderboard ldb) throws IOException {
        if (action == GUI.ACTION.QUIT) {
            endMpGame(game, ldb);
            return;
        }
        if (tetris1.getBoard().isFinish() && tetris2.getBoard().isFinish()) {
            endMpGame(game, ldb);
            return;
        }

        long now = System.currentTimeMillis();
        if (now - lastDropTime > tetris1.getDropInterval()) {
            lastDropTime = now;
            if (!tetris1.getBoard().isFinish()) {
                if (!(pieceController.moveDownIfPossible(false))) {
                    //Couldn't move piece
                    addPiece(false);
                }
            }
            if (!tetris2.getBoard().isFinish()) {
                if (!(pieceController2.moveDownIfPossible(false)) && !tetris2.getBoard().isFinish()) {
                    addPiece(true);
                }
            }
        }

        clearBoard(false);
        clearBoard(true);

        if (tetris1.getBoard().getCur_piece().getPosY() == 1) {
            tetris1.getBoard().finish();
        }

        if (tetris2.getBoard().getCur_piece().getPosY() == 1) {
            tetris2.getBoard().finish();
        }


        if (!tetris1.getBoard().isFinish()) {
            if (LanternaGUI.isSPAction(action)) {
                pieceController.doAction(action, false);
            }
            tetris1.getBoard().getGhost_piece().setPosY(tetris1.getBoard().getCur_piece().getPosY());
            tetris1.getBoard().getGhost_piece().setPosX(tetris1.getBoard().getCur_piece().getPosX());
            pieceController.dropIfPossible(true);
        }

        if (!tetris2.getBoard().isFinish()) {
            if (LanternaGUI.isMPAction(action)) {
                pieceController2.doAction(action, false);
            }
            tetris2.getBoard().getGhost_piece().setPosY(tetris2.getBoard().getCur_piece().getPosY());
            tetris2.getBoard().getGhost_piece().setPosX(tetris2.getBoard().getCur_piece().getPosX());
            pieceController2.dropIfPossible(true);
        }

    }

    public void endMpGame(Game game, Leaderboard ldb) throws IOException {
        boolean entered = false;
        if (ldb.checkIfEnters(tetris1.getBoard().getScore())) {
            ldb.addScore(tetris1.getBoard().getScore(), tetris1.getPlayer());
            entered = true;
        }
        if (ldb.checkIfEnters(tetris2.getBoard().getScore())) {
            ldb.addScore(tetris2.getBoard().getScore(), tetris2.getPlayer());
            entered = true;
        }
        if (entered) {
            game.setState(new LeaderboardState(ldb));
        } else {
            game.setState(new MenuState(new Menu()));
        }
        return;
    }

    public void addPiece(boolean isMP) {
        Tetris playerTetris;
        if (!isMP) {
            playerTetris = tetris1;
        } else {
            playerTetris = tetris2;
        }
        playerTetris.getBoard().addPiece(playerTetris.getBoard().getCur_piece()); // It's no longer a moving piece
        Piece newPiece = playerTetris.getNextPiece();
        playerTetris.getBoard().setCur_piece(newPiece);
        Piece newPiece2 = new Piece(newPiece.getPosX(), newPiece.getPosY(), newPiece.getCenterX(), newPiece.getCenterY(), 0);
        ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();
        squares = newPiece.getSquares();
        newPiece2.setSquares(squares);
        playerTetris.getBoard().setGhost_piece(newPiece2);
    }

    public int clearBoard(boolean isMP) {
        Tetris playerTetris;
        if (!isMP) {
            playerTetris = tetris1;
        } else {
            playerTetris = tetris2;
        }
        int clearedLine = 0;
        int nline = 1;
        for (int i = 1; i < 20; i++) {
            if (playerTetris.getBoard().isLineFull(i)) {
                clearedLine++;
                playerTetris.getBoard().clearLine(i);
                playerTetris.getBoard().fullLineScore((100) * (1 + 0.10 * (nline - 1)));

                nline++;
            }
        }
        return clearedLine;
    }
}
