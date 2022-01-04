package com.gui;


import com.model.tetris.*;
import java.io.IOException;
import java.util.ArrayList;


public interface GUI {

    void drawPieceQueue(ArrayList<Piece> pieceQueue, boolean two_boards);

    enum ACTION {DOWN, MPDOWN, LEFT, MPLEFT, ROTATE_L, MPROTATE_L, RIGHT, MPRIGHT, ROTATE_R, MPROTATE_R, QUIT, DROP, MPDROP, HOLD, MPHOLD, NONE,UP,SELECT};
     ACTION getNextAction() throws IOException;

    void drawBoard(Board board, String board_color, String queue_color,String hold_color, boolean two_boards);

    void drawPiece(Piece piece, boolean current, boolean two_boards);

    void drawSavedPiece(Piece saved, boolean two_boards);

    void drawCharacter(ArrayList<ArrayList<Boolean>> positions, int x, int y, String character, String color, boolean two_boards);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    void drawText(int x, int y, String text,String color);
}
