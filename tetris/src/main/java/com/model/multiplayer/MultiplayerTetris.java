package com.model.multiplayer;

import com.model.tetris.Tetris;
import com.view.tetris.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class MultiplayerTetris {
    Tetris tetris1;
    Tetris tetris2;
    static int width, height;

    public MultiplayerTetris(int width, int height, Random rand) throws IOException, URISyntaxException, FontFormatException {
        this.tetris1 = new Tetris(width, height, rand);
        this.tetris2 = new Tetris(width, height, rand);
        this.width = width;
        this.height = height;
    }

    public Tetris getTetris(boolean is2ndP){
        if(!is2ndP){
            return tetris1;
        }
        return tetris2;
    }
}
