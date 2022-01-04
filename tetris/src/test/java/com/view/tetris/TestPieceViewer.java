package com.view.tetris;

import com.gui.GUI;
import com.model.tetris.Piece;
import com.model.tetris.PieceLine;
import com.view.tetris.PieceViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestPieceViewer {
    private Piece piece;
    private PieceViewer viewer;
    private GUI gui;

    @BeforeEach
    void setup(){
        piece = new PieceLine(3,6);
        viewer = new PieceViewer();
        gui = Mockito.mock(GUI.class);

    }

    @Test
    void drawPiece(){
        viewer.drawPiece(piece,gui,false,false);
        Mockito.verify(gui,Mockito.times(1)).drawPiece(piece,false,false);
    }
}

