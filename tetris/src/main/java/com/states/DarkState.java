package com.states;

import com.controller.Controller;
import com.controller.tetris.BoardController;
import com.model.tetris.Tetris;
import com.view.tetris.DarkViewer;
import com.view.tetris.GameViewer;

import java.io.FileNotFoundException;

public class DarkState extends State<Tetris, GameViewer> {
    public DarkState(Tetris tetris) throws FileNotFoundException {
        super(tetris);
    }

    @Override
    protected GameViewer getViewer() {
        return new DarkViewer(getModel());
    }

    @Override
    protected Controller<Tetris, GameViewer> getController() throws FileNotFoundException {
        return new BoardController(getModel());
    }
}
