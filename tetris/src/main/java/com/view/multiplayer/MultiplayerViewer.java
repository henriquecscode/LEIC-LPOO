package com.view.multiplayer;

import com.gui.GUI;
import com.model.multiplayer.MultiplayerTetris;
import com.model.tetris.Piece;
import com.view.Viewer;

import java.io.IOException;

public class MultiplayerViewer extends Viewer<MultiplayerTetris> {
    public MultiplayerViewer(MultiplayerTetris mpTetris) {
        super(mpTetris);
    }
    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawBoard(gui);
        drawPieces(gui);
        drawSaved(gui);
        drawPieceQueue(gui);
        drawGhostPiece(gui);
        drawCurPiece(gui);
        drawText(gui);
        gui.refresh();
    }

    public void drawBoard(GUI gui) {
        gui.drawBoard(getModel().getTetris(false).getBoard(), "#89CCF0", "#007D9A", "#004656",true);

    }
    public void drawPieces(GUI gui) {
        if (getModel().getTetris(false).getBoard().getPieces().size() != 0) {
            for (Piece piece :getModel().getTetris(false).getBoard().getPieces()) gui.drawPiece(piece,false, false);
        }

        if (getModel().getTetris(true).getBoard().getPieces().size() != 0) {
            for (Piece piece :getModel().getTetris(true).getBoard().getPieces()) gui.drawPiece(piece,false, true);
        }

    }

    public void drawSaved(GUI gui){
        if (getModel().getTetris(false).getBoard().getSaved() != null) gui.drawSavedPiece(getModel().getTetris(false).getBoard().getSaved(), false);
        if (getModel().getTetris(true).getBoard().getSaved() != null) gui.drawSavedPiece(getModel().getTetris(true).getBoard().getSaved(), true);

    }
    public void drawPieceQueue(GUI gui){
        gui.drawPieceQueue(getModel().getTetris(false).getPieceQueue(), false);
        gui.drawPieceQueue(getModel().getTetris(true).getPieceQueue(), true);

    }
    public void drawCurPiece(GUI gui){
        gui.drawPiece(getModel().getTetris(false).getBoard().getCur_piece(),false, false);
        gui.drawPiece(getModel().getTetris(true).getBoard().getCur_piece(),false, true);
    }

    public void drawGhostPiece(GUI gui){
        gui.drawPiece(getModel().getTetris(false).getBoard().getGhost_piece(),true, false);
        gui.drawPiece(getModel().getTetris(true).getBoard().getGhost_piece(),true, true);

    }

    public void drawText(GUI gui){
        gui.drawText(10, 1, "Score P1: " +getModel().getTetris(false).getBoard().getScore(), "#FFBB00");
        gui.drawText(30, 1, "Score P2: " +getModel().getTetris(true).getBoard().getScore(), "#FFBB00");
    }

    public void clearedLines(int noLines){
        return;
    }
}
