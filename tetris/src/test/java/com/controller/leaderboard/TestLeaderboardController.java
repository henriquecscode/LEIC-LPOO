package com.controller.leaderboard;

import com.model.leaderboard.Leaderboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class TestLeaderboardController {
    Leaderboard lbd;

    @BeforeEach
    void setup() throws FileNotFoundException {
        lbd = new Leaderboard();
    }

    @Test
    void isSelectedMenu()  {
        Assertions.assertTrue(lbd.isSelectedMenu());
    }
}
