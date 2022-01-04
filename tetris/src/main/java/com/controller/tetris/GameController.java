package com.controller.tetris;

import com.controller.Controller;
import com.model.leaderboard.Leaderboard;
import com.model.tetris.Tetris;
import com.view.tetris.GameViewer;

public abstract class GameController extends Controller<Tetris, GameViewer> {
    protected Leaderboard lbd;

    public GameController(Tetris tetris) {
        super(tetris);
    }

}
