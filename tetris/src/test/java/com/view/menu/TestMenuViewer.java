package com.view.menu;

import com.gui.GUI;
import com.model.menu.Menu;
import net.bytebuddy.implementation.bind.MethodNameEqualityResolver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class TestMenuViewer {

    @Test
    void draw() throws IOException {
        MenuViewer viewer = new MenuViewer(new Menu());
        GUI gui = Mockito.mock(GUI.class);
        String tetris = "tetris";
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(tetris.length() + viewer.getModel().getNumberEntries()))
                .drawText(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
