package com.model.tetris;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class TestPlayer {
    @Test
    void CreatePlayer(){
        Player p1 = new Player();
        Player p2 = new Player("Capitulino Andrioleta");

        Assertions.assertNotEquals( "Player 1",p1.getName());
        Assertions.assertEquals( "Capitulino Andrioleta",p2.getName());
    }

}
