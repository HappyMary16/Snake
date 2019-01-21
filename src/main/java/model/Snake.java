package model;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Tile> body;
    private int size;
    private boolean isEaten;

    public Snake(Tile head, Tile tail) {
        body = new LinkedList<>();
        body.addFirst(head);
        body.addLast(tail);

        head.setSnakeHead(true);
        head.setSnake(true);
        tail.setSnake(true);
        size = 2;
    }

    public int getSize() {
        return size;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    private void eat(Tile tile) {
        tile.setSnakeHead(true);
        tile.setMeal(false);
        body.getFirst().setSnakeHead(false);
        body.addFirst(tile);
        size++;
        isEaten = true;
    }

    public boolean move(Tile nextTile) {
        if (nextTile.isSnake()) {
            return false;
        }

        if (nextTile.isMeal()) {
            eat(nextTile);
        } else {
            body.getFirst().setSnakeHead(false);
            nextTile.setSnakeHead(true);
            body.addFirst(nextTile);
            body.removeLast().setSnake(false);
        }

        return true;
    }

}
