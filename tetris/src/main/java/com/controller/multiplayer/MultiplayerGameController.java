package com.controller.multiplayer;

import com.controller.Controller;
import com.model.leaderboard.Leaderboard;
import com.model.multiplayer.MultiplayerTetris;
import com.view.multiplayer.MultiplayerViewer;

public abstract class MultiplayerGameController extends Controller<MultiplayerTetris, MultiplayerViewer> {
    protected Leaderboard lbd;

    public MultiplayerGameController(MultiplayerTetris mpTetris) {
        super(mpTetris);
    }

}