package com.states;

import com.controller.Controller;
import com.controller.tetris.BoardController;
import com.model.tetris.Tetris;
import com.view.tetris.GameViewer;

import java.io.FileNotFoundException;

public class GameState extends State<Tetris, GameViewer> {
    public GameState(Tetris tetris) throws FileNotFoundException {
        super(tetris);
    }

    @Override
    protected GameViewer getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Tetris, GameViewer> getController() throws FileNotFoundException {
        return new BoardController(getModel());
    }
}