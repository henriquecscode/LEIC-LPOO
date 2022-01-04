package com.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMenu {
    @Test
    void nextEntry(){
        Menu menu = new Menu();

        Assertions.assertEquals(true, menu.isSelected(0));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(1));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(2));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(3));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(4));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(5));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(6));
        menu.nextEntry();
        Assertions.assertEquals(true, menu.isSelected(0));

    }

    @Test
    void previousEntry(){
        Menu menu = new Menu();

        Assertions.assertEquals(true, menu.isSelected(0));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(6));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(5));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(4));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(3));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(2));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(1));
        menu.previousEntry();
        Assertions.assertEquals(true, menu.isSelected(0));

    }
}
