package com.view;

import com.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    protected final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        draw(gui);
        gui.refresh();
    };

}