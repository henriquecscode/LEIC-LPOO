package com.model.tetris;

import java.util.ArrayList;
import java.util.List;

/*
    -> The 1st line (board[i][0]) is the game over line - if any space in it is filled, it's game over time.
*/
public class Board {
    private int rows = 20, columns = 10;
    int score = 0;
    private boolean finish;
    private ArrayList<ArrayList<Boolean>> board = new ArrayList<ArrayList<Boolean>>(rows);
    private List<Piece> pieces; //needed to know which pieces are on the board to draw
    private Piece cur_piece; //current piece we are controlling
    private Piece ghost_piece;
    Piece saved;
    boolean save;

    public Board() {

        for (int j = 0; j < columns; j++) {
            ArrayList<Boolean> initialRow = new ArrayList<Boolean>();
            for (int i = 1; i < rows + 1; i++) {

                initialRow.add(Boolean.FALSE);
            }
            board.add(initialRow);
        }

        this.save = true;
        this.pieces = new ArrayList<>();
    };

    public void setCur_piece(Piece cur_piece) {
        this.cur_piece = cur_piece;
    }

    public void setGhost_piece(Piece ghost_piece){
        this.ghost_piece = ghost_piece;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece getCur_piece() {
        return cur_piece;
    }

    public Piece getGhost_piece(){
        return ghost_piece;
    }

    public int getScore() {
        return this.score;
    }

    public void fullLineScore(double score) {
        this.score += score;
    }

    public boolean getSpot(int x, int y) {
        if (x < 0 || x >= getColumns() || y <= 0 || y >= getRows()) { //if y==0->gameover
            return true;
        }
        return board.get(x).get(y);
    }

    public void checkPiece(Piece piece, int line) {
        if (line >= piece.getPosY() - piece.getCenterY()
                && line <= piece.getPosY() - piece.getCenterY() + piece.getSquares().size() - 1) {
            //Means that the piece is intersected by the line in a way that will require a drop
            int affectedLines = line - (piece.getPosY() - piece.getCenterY());
            for (int i = affectedLines; i >= 1; i--) {
                for (int j = 0; j < piece.getSquares().get(i).size(); j++) {
                    piece.getSquares().get(i).set(j, piece.getSquares().get(i - 1).get(j));
                }
            }
            for(int i = 0 ;i < piece.getSquares().get(0).size(); i++){
                piece.getSquares().get(0).set(i, false);
            }
        }

        else if(line > piece.getPosY() - piece.getCenterY() + piece.getSquares().size()-1){
            //The line that was moved is below. This will render the board state incomprehensible until all the pieces are processed
            piece.moveDown();
        }
    }

    public boolean isLineFull(int line) {
        for (int i = 0; i < columns; i++) {
            if (!(board.get(i).get(line))) {
                return false;
            }
        }
        return true;
    }

    public void clearLine(int line) {
        for (int j = line; j >= 1; j--) {
            for (int i = 0; i < columns; i++) {
                board.get(i).set(j, board.get(i).get(j-1));
            }
        }

        for (int i = 0; i < columns; i++) {
            board.get(i).set(0, false);
        }

        for (Piece piece : pieces) {
            checkPiece(piece, line);
        }

    }

    public int fill(int x, int y) {
        //Game over + out of bounds.
        if (y == 0) {
            this.finish();
            return -2;
        }
        if (y < 0 || y >= rows || x >= columns || x < 0) return -1;

        if (board.get(x).get(y)) {
            //It's already filled.
            return 0;
        }
        ArrayList<Boolean> column = board.get(x);
        column.set(y, true);

        for (int i = 0; i < rows; i++) {
            isLineFull(i);
        }


        return 1;
    }

    public void finish() {
        this.finish = true;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public void addPiece(Piece piece) { //it's only added to list when every position parts of the piece will occupy can be filled
        pieces.add(piece);

        for (int i = 0; i < piece.getSquares().size(); i++) {
            for (int j = 0; j < piece.getSquares().get(i).size(); j++) {
                if (piece.getSquares().get(i).get(j)) {
                    fill(piece.getPosX() - piece.getCenterX() + j, piece.getPosY() - piece.getCenterY() + i);
                }
            }

        }

        setSave(true);
    }

    public void setSave(boolean save){
        this.save = save;
    }

    public boolean getSave(){
        return this.save;
    }

    public int savePiece(){
        if (saved != null){
            Piece temp = saved;
            saved = getCur_piece();
            temp.setPosY(getCur_piece().getPosY());
            temp.setPosX(getCur_piece().getPosX());
            setCur_piece(temp);
            Piece newPiece2 = new Piece(temp.getPosX(), temp.getPosY(), temp.getCenterX(), temp.getCenterY(),0);
            ArrayList<ArrayList<Boolean>> squares = new ArrayList<>();
            squares= temp.getSquares();
            newPiece2.setSquares(squares);
            setGhost_piece(newPiece2);
            return 0;
        } else {
            saved = getCur_piece();
            return 1;
        }

    }

    public Piece getSaved(){
        return saved;
    }
}