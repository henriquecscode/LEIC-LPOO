package com.view.tetris;

import com.gui.GUI;
import com.model.tetris.Tetris;

import java.io.IOException;

public class BlindGameViewer extends GameViewer {
    private long lastPrintTime;
    private long flashPiecestime = 1000;

    public BlindGameViewer(Tetris tetris) {
        super(tetris);
        lastPrintTime = System.currentTimeMillis();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        super.draw(gui);
    }

    public long getFlashPiecestime() {
        return flashPiecestime;
    }

    @Override
    public void drawPieces(GUI gui) {
        long now = System.currentTimeMillis();
        if (now - lastPrintTime < flashPiecestime) {
            super.drawPieces(gui);
            return;
        }
    }


    @Override
    public void drawGhostPiece(GUI gui){}

    @Override
    public void clearedLines(int noLines) {
        if (noLines > 0) {
            lastPrintTime = System.currentTimeMillis();
        }
    }

}
