package com.view.tetris;

import com.gui.GUI;
import com.model.tetris.Piece;

public class PieceViewer {
    public void drawPiece(Piece piece, GUI gui,boolean current,boolean two_boards){
        gui.drawPiece(piece,current, two_boards);
    }
}
