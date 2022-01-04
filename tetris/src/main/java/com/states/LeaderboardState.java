package com.states;

import com.controller.Controller;
import com.controller.leaderboard.LeaderboardController;
import com.model.leaderboard.Leaderboard;
import com.view.leaderboard.LeaderboardViewer;

import java.io.FileNotFoundException;

public class LeaderboardState extends State<Leaderboard, LeaderboardViewer>{
    public LeaderboardState(Leaderboard model) throws FileNotFoundException { super(model); }

    @Override
    protected LeaderboardViewer getViewer() {
        return new LeaderboardViewer(getModel());
    }

    @Override
    protected Controller<Leaderboard, LeaderboardViewer> getController() {
        return new LeaderboardController(getModel());
    }
}
