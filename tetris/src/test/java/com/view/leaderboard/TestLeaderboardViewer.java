package com.view.leaderboard;

import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

public class TestLeaderboardViewer {
    private GUI gui;
    private LeaderboardViewer viewer;
    private Leaderboard ldb;

    @BeforeEach
    void setUp() {
        ldb = Mockito.mock(Leaderboard.class);
        viewer = new LeaderboardViewer(ldb);
        gui = Mockito.mock(GUI.class);

    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(viewer.getColors().isEmpty());
    }

    @Test
    void draw() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.atLeast(viewer.getModel().getPlayers().size()))
                .drawText(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
