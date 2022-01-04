package com.model.tetris;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private static final int UP = 0;
    private String ghostColor;
    private int orientation;
    private int posX, posY, centerX, centerY, size, index, og_centerX, og_centerY;
    private final List<String> colors = new ArrayList<String>();
    private final List<String> characters = new ArrayList<String>();

    private ArrayList<ArrayList<Boolean>> squares;

    public Piece(int posX, int posY, int centerX, int centerY, int index) {
        orientation = UP;
        this.posX = posX;
        this.posY = posY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.og_centerX = centerX;
        this.og_centerY = centerY;
        this.index = index;

        this.ghostColor = "#000000";

        colors.add("#00FF00");
        colors.add("#FFFF00");
        colors.add("#FF0000");
        colors.add("#6600CC");
        colors.add("#00FFFF");
        colors.add("#CC0066");
        colors.add("#FF8000");


        characters.add("L");
        characters.add("S");
        characters.add("Z");
        characters.add("T");
        characters.add("H");
        characters.add("M");
        characters.add("L");
    }

    public void setSquares(ArrayList<ArrayList<Boolean>> squares){
        while (squares.size() > squares.get(0).size()) {
            for (int i = 0; i < squares.get(0).size(); i++) {
                squares.get(i).add(false);
            }
        }
        while (squares.get(0).size() > squares.size()) {
            squares.add(squares.get(0));
            for (int i = 0; i < squares.get(-1).size(); i++) {
                squares.get(-1).set(i, false);
            }
        }
        this.squares = squares;
        this.size = squares.size();
    }

    public int getOrientation() {
        return orientation;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getColor() {
        return colors.get(index);
    }

    public String getGhostColor() {
        return ghostColor;
    }

    public String getCharacter() {
        return characters.get(index);
    }

    public void setPosY(int posY) { this.posY = posY; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getOg_centerX() { return og_centerX;}

    public int getOg_centerY() {return og_centerY;}

    public ArrayList<ArrayList<Boolean>> getSquares() {
        return squares;
    }

    public void rotateLeft() {
        orientation = (orientation + 1) % 4;
        ArrayList<ArrayList<Boolean>> newSquares = new ArrayList<>(size);
        List<Boolean> line = new ArrayList<>(size);

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                line.add(squares.get(j).get(i));
            }
            newSquares.add(new ArrayList<>(line));
            line.clear();
        }
        int temp  = centerX;
        centerX = centerY;
        centerY = size - 1- temp;
        squares = newSquares;
    }

    public void rotateRight() {
        orientation = (orientation + 3) % 4;
        ArrayList<ArrayList<Boolean>> newSquares = new ArrayList<>(size);
        List<Boolean> line = new ArrayList<>(size);


        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                line.add(squares.get(j).get(i));
            }
            newSquares.add(new ArrayList<>(line));
            line.clear();
        }
        int temp  = centerX;
        centerX=  size - 1 - centerY;
        centerY = temp;
        squares = newSquares;
    }

    public void moveDown() {
        posY++;
    }

    public void moveUp(){ //piece can't actually move up but this function is used in case moveDown makes piece go out of bounds
        posY--;
    }

    public void moveRight() {
        posX++;
    }

    public void moveLeft() {
        posX--;
    }


}