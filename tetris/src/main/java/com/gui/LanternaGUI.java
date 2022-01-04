package com.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.model.tetris.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;


public class LanternaGUI implements GUI {
    private final Screen screen;
    private int board_x = 10, board_y = 1;
    static Hashtable<KeyType, Boolean> pressed;


    public LanternaGUI(Screen screen){
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height,fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }


    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;
        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;

        //FIRST PLAYER
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 32) return ACTION.DROP;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'b') return ACTION.ROTATE_L;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'n') return ACTION.HOLD;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'm') return ACTION.ROTATE_R;

        //SECOND PlAYER
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'd') return ACTION.MPRIGHT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 's') return ACTION.MPDOWN;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'a') return ACTION.MPLEFT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'e') return ACTION.MPDROP;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'z') return ACTION.MPROTATE_L;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'x') return ACTION.MPHOLD;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'c') return ACTION.MPROTATE_R;

        return ACTION.NONE;
    }

    static public boolean isSPAction(ACTION action) {
        return action == ACTION.RIGHT || action == ACTION.LEFT || action == ACTION.DOWN || action == ACTION.DROP || action == ACTION.HOLD
                || action == ACTION.ROTATE_L || action == ACTION.ROTATE_R;
    }

    static public boolean isMPAction(ACTION action) {
        return action == ACTION.MPRIGHT || action == ACTION.MPLEFT || action == ACTION.MPDOWN || action == ACTION.MPDROP || action == ACTION.MPHOLD
                || action == ACTION.MPROTATE_L || action == ACTION.MPROTATE_R;
    }

    public void drawPieceQueue(ArrayList<Piece> pieceQueue, boolean two_boards){
        int y = board_y + 3;
        for (Piece p: pieceQueue){
            drawQueuePiece(p, y,two_boards);

            if ((p.getOg_centerX() == 0 && p.getOg_centerY() == 1) || (p.getOg_centerX() == 2 && p.getOg_centerY() == 2)){
                y += 5;
            } else {
                y += 4;
            }
        }
    }

    public void drawBoard(Board board, String board_color, String queue_color, String hold_color, boolean two_boards) {
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString(queue_color));
        graphics.fillRectangle(
                new TerminalPosition(board_x*2 + 1, board_y),
                new TerminalSize(3, 4),
                ' '
        );
        graphics.setBackgroundColor(TextColor.Factory.fromString(hold_color));
        graphics.fillRectangle(
                new TerminalPosition(board_x*2 + 1, board_y + 5),
                new TerminalSize(3, 15),
                ' '
        );

        graphics.setBackgroundColor(TextColor.Factory.fromString(board_color));

        graphics.fillRectangle(
                new TerminalPosition(board_x, board_y),
                new TerminalSize(board.getColumns(), board.getRows()),
                ' '
        );

        if(two_boards){
            graphics.fillRectangle(
                    new TerminalPosition(3*board_x, board_y ),
                    new TerminalSize(board.getColumns(), board.getRows()),
                    ' '
            );
            graphics.setBackgroundColor(TextColor.Factory.fromString(queue_color));
            graphics.fillRectangle(
                    new TerminalPosition((board_x+board_x)*2 + 1, board_y),
                    new TerminalSize(3, 4),
                    ' '
            );
            graphics.setBackgroundColor(TextColor.Factory.fromString(hold_color));
            graphics.fillRectangle(
                    new TerminalPosition((board_x+board_x)*2 + 1, board_y + 5),
                    new TerminalSize(3, 15),
                    ' '
            );
        }

    }

    public void drawQueuePiece(Piece piece, int y, boolean two_boards){
        if ((piece.getOg_centerX() == 0 && piece.getOg_centerY() == 1) || (piece.getOg_centerX() == 0 && piece.getOg_centerY() == 0)) {
            drawCharacter(piece.getSquares(), board_x + 2, y + 1, piece.getCharacter(), piece.getColor(), two_boards);
            return;
        }

        drawCharacter(piece.getSquares(), board_x + 1, y + 1, piece.getCharacter(), piece.getColor(), two_boards);
    }

    public void drawSavedPiece(Piece piece, boolean two_boards){
        if (piece.getOg_centerX() == 0 && piece.getOg_centerY() == 1) {
            drawCharacter(piece.getSquares(), board_x + 2, board_y - 1, piece.getCharacter(), piece.getColor(), two_boards);
            return;
        }

        drawCharacter(piece.getSquares(), board_x + 1, board_y, piece.getCharacter(), piece.getColor(), two_boards);

    }

    public void drawPiece(Piece piece, boolean current, boolean two_boards) {
        drawCharacter(piece.getSquares(), piece.getPosX() - piece.getCenterX(), piece.getPosY() - piece.getCenterY(), piece.getCharacter(), piece.getColor(), two_boards);

        if (current) {
            drawCharacter(piece.getSquares(), piece.getPosX() - piece.getCenterX(), piece.getPosY() - piece.getCenterY(), " ", piece.getGhostColor(), two_boards);

        }
    }

    public void drawCharacter(ArrayList<ArrayList<Boolean>> positions, int x, int y, String character, String color, boolean two_boards) {

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        int x_pos;

        for (int i = 0; i < positions.size(); i++) {
            for (int j = 0; j < positions.get(i).size(); j++) {
                if (positions.get(i).get(j)) {
                    if(two_boards) {
                        x_pos = x+j+3*board_x;
                    }
                    else {
                        x_pos = x+j+board_x;
                    }
                    graphics.putString(x_pos, y + i + board_y, character);
                }
            }

        }
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override //Might be useful to write the score + player?
    public void drawText(int x, int y,String text,String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y, text);
    }


}
