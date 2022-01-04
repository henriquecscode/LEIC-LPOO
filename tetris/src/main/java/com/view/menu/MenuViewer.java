package com.view.menu;

import com.gui.GUI;
import com.model.menu.Menu;
import com.view.Viewer;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {
    static int color = 0;

    public MenuViewer(Menu menu) {
        super(menu);
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

        gui.drawText(5, 5, "T", "#FF0000");
        gui.drawText(6, 5, "E", "#FF8C00");
        gui.drawText(7, 5, "T", "#FFFF00");
        gui.drawText(8, 5, "R", "#32CD32");
        gui.drawText(9, 5, "I", "#00FFFF");
        gui.drawText(10, 5, "S", "#8A2BE2");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    5, 7 + i,
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? getColor() : "#FFFFFF");
        }

        gui.refresh();
    }
}