package com.model.menu;

import java.util.Arrays;
import java.util.List;

public class Menu extends java.awt.Menu {
    private final List<String> entries;
    private int currentEntry = 0;

    public Menu() {
        this.entries = Arrays.asList(
                "Start",
                "Blind",
                "Light",
                "Dark",
                "Multiplayer",
                "Leaderboard",
                "Exit"
        );
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedBlind() {
        return isSelected(1);
    }

    public boolean isSelectedLight() {
        return isSelected(2);
    }

    public boolean isSelectedDark() {
        return isSelected(3);
    }

    public boolean isSelectedMultiplayer() {return isSelected(4);}

    public boolean isSelectedLeaderboard() {
        return isSelected(5);
    }

    public boolean isSelectedExit() {
        return isSelected(6);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
