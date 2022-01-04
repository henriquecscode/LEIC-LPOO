package com.states;

import com.Game;
import com.controller.Controller;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.view.Viewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T, V extends Viewer<T>> {
    private final T model;
    private final Controller<T, V> controller;
    private final V viewer;

    public State(T model) throws FileNotFoundException {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract V getViewer();

    protected abstract Controller<T, V> getController() throws FileNotFoundException;

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time, Leaderboard lbd) throws IOException, URISyntaxException, FontFormatException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time, viewer,lbd);
        viewer.draw(gui);
    }
}
