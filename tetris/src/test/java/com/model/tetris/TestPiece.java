package com.model.tetris;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TestPiece {
    ArrayList<ArrayList<Boolean>> topLeft = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> topRight = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> botRight = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> botLeft = new ArrayList<>();

    @BeforeEach
  void setArrays(){
        topLeft.clear();
        topRight.clear();
        botRight.clear();
        botLeft.clear();

      ArrayList<Boolean> a1 = new ArrayList<>();
      a1.add(true);
      a1.add(false);
      a1.add(false);

      ArrayList<Boolean> a2 = new ArrayList<>();
      a2.add(true);
      a2.add(false);
      a2.add(false);

      ArrayList<Boolean> a3 = new ArrayList<>();
      a3.add(false);
      a3.add(false);
      a3.add(false);


      topLeft.add(a1);
      topLeft.add(a2);
      topLeft.add(a3);


      ArrayList<Boolean> a4 = new ArrayList<>();
      a4.add(false);
      a4.add(true);
      a4.add(true);

      ArrayList<Boolean> a5 = new ArrayList<>();
      a5.add(false);
      a5.add(false);
      a5.add(false);

      ArrayList<Boolean> a6 = new ArrayList<>();
      a6.add(false);
      a6.add(false);
      a6.add(false);


      topRight.add(a4);
      topRight.add(a5);
      topRight.add(a6);




      ArrayList<Boolean> a7 = new ArrayList<>();
      a7.add(false);
      a7.add(false);
      a7.add(false);

      ArrayList<Boolean> a8 = new ArrayList<>();
      a8.add(false);
      a8.add(false);
      a8.add(true);

      ArrayList<Boolean> a9 = new ArrayList<>();
      a9.add(false);
      a9.add(false);
      a9.add(true);


      botRight.add(a7);
      botRight.add(a8);
      botRight.add(a9);


      ArrayList<Boolean> a10 = new ArrayList<>();
      a10.add(false);
      a10.add(false);
      a10.add(false);

      ArrayList<Boolean> a11 = new ArrayList<>();
      a11.add(false);
      a11.add(false);
      a11.add(false);

      ArrayList<Boolean> a12 = new ArrayList<>();
      a12.add(true);
      a12.add(true);
      a12.add(false);


      botLeft.add(a10);
      botLeft.add(a11);
      botLeft.add(a12);

  }


    @Test
    void ArrayDeepCopy() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        List<Integer> newList = new ArrayList<>(list);
        newList.set(0, 1);
        Assertions.assertNotEquals(list.get(0), newList.get(0));

        List<List<Integer>> list2d = new ArrayList<>();
        list2d.add(list);
        List<List<Integer>> newList2d = new ArrayList<>(list2d);
        list2d.set(0, List.of(1));
        Assertions.assertNotEquals(list2d.get(0).get(0), newList2d.get(0).get(0));
    }

    @Test
    void orientation () {
        Piece piece = new Piece(0,0,0,0,0);
        Assertions.assertEquals(0,piece.getOrientation());
    }

    @Test
    void rotate() {


        Piece piece = new Piece(0, 0, 0, 0,0);
        piece.setSquares(topLeft);
        piece.rotateLeft();
        Assertions.assertEquals(botLeft, piece.getSquares());
        Assertions.assertEquals(0, piece.getCenterX());
        Assertions.assertEquals(2, piece.getCenterY());
        piece.rotateLeft();
        Assertions.assertEquals(botRight, piece.getSquares());
        Assertions.assertEquals(2, piece.getCenterX());
        Assertions.assertEquals(2, piece.getCenterY());
        piece.rotateLeft();
        Assertions.assertEquals(topRight, piece.getSquares());
        Assertions.assertEquals(2, piece.getCenterX());
        Assertions.assertEquals(0, piece.getCenterY());
        piece.rotateLeft();
        Assertions.assertEquals(topLeft, piece.getSquares());
        Assertions.assertEquals(0, piece.getCenterX());
        Assertions.assertEquals(0, piece.getCenterY());

        piece.rotateRight();
        Assertions.assertEquals(topRight, piece.getSquares());

        Assertions.assertEquals(2, piece.getCenterX());
        Assertions.assertEquals(0, piece.getCenterY());
        piece.rotateRight();
        Assertions.assertEquals(botRight, piece.getSquares());
        Assertions.assertEquals(2, piece.getCenterX());
        Assertions.assertEquals(2, piece.getCenterY());
        piece.rotateRight();
        Assertions.assertEquals(botLeft, piece.getSquares());
        Assertions.assertEquals(0, piece.getCenterX());
        Assertions.assertEquals(2, piece.getCenterY());
        piece.rotateRight();
        Assertions.assertEquals(topLeft, piece.getSquares());
        Assertions.assertEquals(0, piece.getCenterX());
        Assertions.assertEquals(0, piece.getCenterY());
    }

    @Property
    void MoveDown(@ForAll @IntRange(min = 1, max = 19) int x, @ForAll @IntRange(min = 2, max = 8) int y) throws FontFormatException, IOException, URISyntaxException {
        Piece piece = new Piece(x,y,0,0,0);
        piece.moveDown();

        Assertions.assertEquals(x,piece.getPosX());
        Assertions.assertEquals(y+1,piece.getPosY());
    }

    @Property
    void MoveUp(@ForAll @IntRange(min = 1, max = 19) int x, @ForAll @IntRange(min = 3, max = 8) int y) throws FontFormatException, IOException, URISyntaxException {
        Piece piece = new Piece(x,y,0,0,0);
        piece.moveUp();

        Assertions.assertEquals(x,piece.getPosX());
        Assertions.assertEquals(y-1,piece.getPosY());
    }

    @Property
    void MoveLeft(@ForAll @IntRange(min = 0, max = 18) int x, @ForAll @IntRange(min = 3, max = 7) int y) throws FontFormatException, IOException, URISyntaxException {
        Piece piece = new Piece(x,y,0,0,0);
        piece.moveLeft();

        Assertions.assertEquals(x-1,piece.getPosX());
        Assertions.assertEquals(y,piece.getPosY());
    }

    @Property
    void MoveRight(@ForAll @IntRange(min = 1, max = 19) int x, @ForAll @IntRange(min = 3, max = 7) int y) throws FontFormatException, IOException, URISyntaxException {
        Piece piece = new Piece(x,y,0,0,0);
        piece.moveRight();

        Assertions.assertEquals(x+1,piece.getPosX());
        Assertions.assertEquals(y,piece.getPosY());
    }





}
