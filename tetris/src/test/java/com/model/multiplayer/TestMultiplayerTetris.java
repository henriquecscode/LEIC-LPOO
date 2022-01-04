package com.model.multiplayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class TestMultiplayerTetris {
    @Test
    void getTetris() throws FontFormatException, IOException, URISyntaxException {
        MultiplayerTetris mt = new MultiplayerTetris(50,50,new Random());
        Assertions.assertEquals(mt.tetris1,mt.getTetris(false));
        Assertions.assertEquals(mt.tetris2,mt.getTetris(true));
    }
}
