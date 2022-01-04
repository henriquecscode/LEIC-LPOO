package com.view.leaderboard;

import com.gui.GUI;
import com.model.leaderboard.Leaderboard;
import com.view.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardViewer extends Viewer<Leaderboard> {
    static List<String> colors = new ArrayList<String>();
    int color = 0;

    public LeaderboardViewer(Leaderboard model) {
        super(model);

        if (colors.isEmpty()) {
            colors.add("#FF0000");
            colors.add("#FF8C00");
            colors.add("#FFFF00");
            colors.add("#32CD32");
            colors.add("#00FFFF");
            colors.add("#8A2BE2");
        }

    }

    public List<String> getColors() {
        return colors;
    }

    public String getColor(){
        color++;
        color = color % 6;

        switch (color){
            case 0:
                return "#FF0000";
            case 1:
                return "#FF8C00";
            case 2:
                return "#FFFF00";
            case 3:
                return "#32CD32";
            case 4:
                return "#00FFFF";
            case 5:
                return "#8A2BE2";
            default:
                return "#FFFFFF";

        }
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText(3, 3, "LEADERBOARD", "#FFFFFF");

        int y = 5;
        for (int i = 0; i < getModel().getPlayers().size(); i++){
            String text = (i + 1) + " - " + getModel().getPlayers().get(i).getName() + " (" + getModel().getScores().get(i) + ")";
            gui.drawText(7, y, text, colors.get(i));
            y += 2;
        }
        y += 2;
        gui.drawText(20, y, "MENU", getColor());
        gui.refresh();

    }
}
