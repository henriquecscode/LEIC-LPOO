package com.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.model.leaderboard.Leaderboard;
import com.model.tetris.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class TestLanternaGui {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() throws IOException {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);


        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawText() {
        gui.drawText(1,1, "Score", "#336699");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 102, 153));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "Score");
    }

    @Test
    void drawPieceL(){
        Piece piece = new PieceL(8, 3);
        gui.drawPiece(piece, false,false);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(0, 255, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(18, 2, "L"); //board_x = 10, so column has always 10 units more than what you think it should have
        Mockito.verify(tg, Mockito.times(1)).putString(18, 3, "L");
        Mockito.verify(tg, Mockito.times(1)).putString(18, 4, "L");
        Mockito.verify(tg, Mockito.times(1)).putString(19, 4, "L");
    }

    @Test
    void drawPieceInvertedL(){
        Piece piece = new PieceInvertedL(5, 5);
        gui.drawPiece(piece, false,false);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(255, 128, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 4, "L");
        Mockito.verify(tg, Mockito.times(1)).putString(15, 5, "L");
        Mockito.verify(tg, Mockito.times(1)).putString(15, 6, "L");
        Mockito.verify(tg, Mockito.times(1)).putString(14, 6, "L");
    }

    @Test
    void drawPieceLine(){
        Piece piece = new PieceLine(2, 5);
        gui.drawPiece(piece, false,false);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(0, 255, 255));
        Mockito.verify(tg, Mockito.times(1)).putString(12, 5, "H");
        Mockito.verify(tg, Mockito.times(1)).putString(12, 6, "H");
        Mockito.verify(tg, Mockito.times(1)).putString(12, 7, "H");
        Mockito.verify(tg, Mockito.times(1)).putString(12, 8, "H");
    }

    @Test
    void drawPieceS(){
        Piece piece = new PieceS(4, 5);
        gui.drawPiece(piece, false,false);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(255, 255, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(13, 6, "S");
        Mockito.verify(tg, Mockito.times(1)).putString(14, 5, "S");
        Mockito.verify(tg, Mockito.times(1)).putString(15, 5, "S");
        Mockito.verify(tg, Mockito.times(1)).putString(14, 6, "S");
    }

    @Test
    void drawPieceSquare(){
        Piece piece = new PieceSquare(4, 4);
        gui.drawPiece(piece, false,true);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(204, 0, 102));
        Mockito.verify(tg, Mockito.times(1)).putString(34, 5, "M");
        Mockito.verify(tg, Mockito.times(1)).putString(35, 5, "M");
        Mockito.verify(tg, Mockito.times(1)).putString(35, 6, "M");
        Mockito.verify(tg, Mockito.times(1)).putString(34, 6, "M");
    }

    @Test
    void drawPieceT(){
        Piece piece = new PieceT(4, 4);
        gui.drawPiece(piece, true,false);

        Mockito.verify(tg, Mockito.times(2)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(102, 0, 204));
        Mockito.verify(tg, Mockito.times(1)).putString(14, 5, "T");
        Mockito.verify(tg, Mockito.times(1)).putString(14, 4, "T");
        Mockito.verify(tg, Mockito.times(1)).putString(13, 5, "T");
        Mockito.verify(tg, Mockito.times(1)).putString(15, 5, "T");
    }

    @Test
    void drawPieceZ(){
        Piece piece = new PieceZ(5, 4);
        gui.drawPiece(piece, false,false);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(255, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 5, "Z");
        Mockito.verify(tg, Mockito.times(1)).putString(16, 5, "Z");
        Mockito.verify(tg, Mockito.times(1)).putString(15, 4, "Z");
        Mockito.verify(tg, Mockito.times(1)).putString(14, 4, "Z");
    }

    @Test
    void drawQueuePiece() {
        Piece piece = new PieceZ(5,4);
        Piece piece2 = new PieceL(5,4);
        Piece piece3 = new PieceSquare(5,4);
        Piece piece4 = new PieceLine(5,4);
        Piece piece5 = new PieceS(5,4);
        Piece piece7 = new PieceT(5,4);

        gui.drawQueuePiece(piece,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(21, 6, "Z");
        gui.clear();
        gui.drawQueuePiece(piece2,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(21, 6, "L");
        gui.clear();
        gui.drawQueuePiece(piece3,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 6, "M");
        gui.clear();
        gui.drawQueuePiece(piece4,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 6, "H");
        gui.clear();
        gui.drawQueuePiece(piece5,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 6, "S");
        gui.clear();
        gui.drawQueuePiece(piece7,4, false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 6, "T");


    }

    @Test
    void drawSavedPiece() {
        Piece piece = new PieceZ(5,4);
        Piece piece2 = new PieceL(5,4);
        Piece piece3 = new PieceSquare(5,4);
        Piece piece4 = new PieceLine(5,4);
        Piece piece5 = new PieceS(5,4);
        Piece piece7 = new PieceT(5,4);

        gui.drawSavedPiece(piece,false);
        Mockito.verify(tg, Mockito.times(1)).putString(21, 2, "Z");
        gui.clear();
        gui.drawSavedPiece(piece2,false);
        Mockito.verify(tg, Mockito.times(1)).putString(21, 2, "L");
        gui.clear();
        gui.drawSavedPiece(piece3,false);
        Mockito.verify(tg, Mockito.times(1)).putString(21, 2, "M");
        gui.clear();
        gui.drawSavedPiece(piece4,false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 1, "H");
        gui.clear();
        gui.drawSavedPiece(piece5,false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 2, "S");
        gui.clear();
        gui.drawSavedPiece(piece7,false);
        Mockito.verify(tg, Mockito.times(1)).putString(22, 2, "T");


    }

    @Test
    void cleaners () throws IOException {
        gui.clear();
        gui.refresh();
        gui.close();
    }



}
