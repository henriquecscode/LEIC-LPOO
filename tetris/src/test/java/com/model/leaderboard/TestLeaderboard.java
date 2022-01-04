package com.model.leaderboard;

import com.model.tetris.Player;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestLeaderboard {
    Leaderboard ldb;

    @BeforeEach
    void setup() throws FileNotFoundException {
        ldb = new Leaderboard();
    }

    @Test
    void checkIfEnters() throws FileNotFoundException {
        int score = ldb.getScores().get(0)*2;

        Assertions.assertTrue(ldb.checkIfEnters(score));

        score = 0;

        Assertions.assertFalse(ldb.checkIfEnters(score));
    }

    @Test
    void addScore() throws IOException {
        Player player = new Player();
        int size = ldb.getScores().size();
        int lastScore = ldb.getScores().get(size-1);
        ldb.addScore(lastScore+1, player);
        Assertions.assertEquals(player, ldb.getPlayers().get(size-1));
        Assertions.assertEquals(lastScore+1, ldb.getScores().get(size-1));
    }

    @Test
    void closeFile() throws IOException {
        ldb.save();

        String filePath = "src/main/resources/Leaderboard.txt";
        File leaderbd = new File(filePath);
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        Scanner s = new Scanner(leaderbd);


        for (int i = 0; i < 6; i++){
            String line = ldb.getPlayers().get(i).getName() + "," + ldb.getScores().get(i) + "\n";
            sb.append(line);
        }

        while (s.hasNextLine()){
            sb2.append(s.nextLine());
            sb2.append("\n");
        }

        Assertions.assertEquals(sb.toString(), sb2.toString());

    }
}
