package com.controller.leaderboard;

import com.Game;
import com.controller.Controller;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.states.MenuState;
import com.model.menu.Menu;
import com.view.leaderboard.LeaderboardViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.gui.GUI.ACTION.SELECT;

public class LeaderboardController extends Controller<Leaderboard, LeaderboardViewer> {
    public LeaderboardController(Leaderboard model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time, LeaderboardViewer viewer, Leaderboard lbd) throws IOException, URISyntaxException, FontFormatException {
       if(action == SELECT){
           if (getModel().isSelectedMenu()) {
               game.setState(new MenuState(new Menu()));
           }
        }
    }
}
