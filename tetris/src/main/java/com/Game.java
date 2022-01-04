package com;

import com.gui.LanternaGUI;
import com.model.leaderboard.Leaderboard;
import com.states.MenuState;
import com.model.menu.Menu;
import com.states.State;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private Leaderboard lbd;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(50, 50);
        this.state = new MenuState(new Menu());
        this.lbd = new Leaderboard();
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException, URISyntaxException, FontFormatException {
        int FPS = 60;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();


            state.step(this, gui, startTime,lbd);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        gui.close();
    }
}


