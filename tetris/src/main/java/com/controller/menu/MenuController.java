package com.controller.menu;

import com.Game;
import com.controller.Controller;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.model.multiplayer.MultiplayerTetris;
import com.model.tetris.Tetris;
import com.states.*;
import com.model.menu.Menu;
import com.view.menu.MenuViewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class MenuController extends Controller<Menu, MenuViewer> {
    public MenuController(Menu menu) throws FileNotFoundException {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time, MenuViewer viewer, Leaderboard lbd) throws IOException, URISyntaxException, FontFormatException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedStart()) {
                    Tetris tetris = new Tetris(50, 50, new Random());
                    game.setState(new GameState(tetris));
                } else if (getModel().isSelectedBlind()) {
                    Tetris tetris = new Tetris(50, 50, new Random());
                    game.setState(new BlindGameState(tetris));
                } else if (getModel().isSelectedLight()) {
                    Tetris tetris = new Tetris(50, 50, new Random());
                    game.setState(new LightState(tetris));
                } else if (getModel().isSelectedDark()) {
                    Tetris tetris = new Tetris(50, 50, new Random());
                    game.setState(new DarkState(tetris));
                } else if (getModel().isSelectedMultiplayer()) {
                    MultiplayerTetris mpTetris = new MultiplayerTetris(50, 50, new Random());
                    game.setState(new MultiplayerGameState(mpTetris));
                } else if (getModel().isSelectedLeaderboard()) {
                    game.setState(new LeaderboardState(lbd));
                } else if (getModel().isSelectedExit()) game.setState(null);
        }
    }
}