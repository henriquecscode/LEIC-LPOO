package com.controller;

import com.Game;
import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.view.Viewer;
import com.view.tetris.GameViewer;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Controller<T, V extends Viewer<T>> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GUI.ACTION action, long time, V viewer, Leaderboard lbd) throws IOException, URISyntaxException, FontFormatException;
}