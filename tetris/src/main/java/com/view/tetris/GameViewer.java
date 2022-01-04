package com.view.tetris;

import com.gui.GUI;
import com.model.tetris.Piece;
import com.model.tetris.Tetris;
import com.view.Viewer;

import java.io.IOException;

public class GameViewer extends Viewer<Tetris> {
    public GameViewer(Tetris tetris) {
        super(tetris);
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
        gui.drawBoard(getModel().getBoard(), "#89CCF0", "#007D9A", "#004656", false);
    }

    public void drawPieces(GUI gui) {
        if (getModel().getBoard().getPieces().size() != 0) {
            for (Piece piece : getModel().getBoard().getPieces()) {
                gui.drawPiece(piece, false, false);
            }
        }
    }

    public void drawSaved(GUI gui) {
        if (getModel().getBoard().getSaved() != null) gui.drawSavedPiece(getModel().getBoard().getSaved(), false);
    }

    public void drawPieceQueue(GUI gui) {
        gui.drawPieceQueue(getModel().getPieceQueue(), false);

    }

    public void drawCurPiece(GUI gui) {
        gui.drawPiece(getModel().getBoard().getCur_piece(), false, false);
    }

    public void drawGhostPiece(GUI gui) {
        gui.drawPiece(getModel().getBoard().getGhost_piece(), true, false);
    }

    public void drawText(GUI gui) {
        gui.drawText(0, 1, "Score: " + getModel().getBoard().getScore(), "#FFBB00");
    }

    public void clearedLines(int noLines) {
        return;
    }
}
