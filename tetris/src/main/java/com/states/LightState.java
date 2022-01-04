package com.states;

import com.controller.Controller;
import com.controller.tetris.BoardController;
import com.model.tetris.Tetris;
import com.view.tetris.GameViewer;
import com.view.tetris.LightViewer;

import java.io.FileNotFoundException;

public class LightState extends State<Tetris, GameViewer> {
    public LightState(Tetris tetris) throws FileNotFoundException {
        super(tetris);
    }

    @Override
    protected GameViewer getViewer() {
        return new LightViewer(getModel());
    }

    @Override
    protected Controller<Tetris, GameViewer> getController() throws FileNotFoundException {
        return new BoardController(getModel());
    }
}
