package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private Tile[][] field;
    private Snake snake;
    private int headX;
    private int headY;
    private int dx;
    private int dy;
    private int timerDelay;
    private boolean isGameOver;

    private static int FIELD_WIDTH = 20;

    public GameModel() {
        resetField();
    }

    public void resetField() {
        field = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = new Tile();
            }
        }

        headX = 1;
        headY = 9;
        dx = 1;
        dy = 0;
        timerDelay = 200;
        isGameOver = false;

        snake = new Snake(field[headY][headX], field[headY][headX - 1]);
        addMeal();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();

        for (Tile[] line :
                field) {
            for (Tile tile:
                 line) {
                if (tile.isEmpty()) {
                    emptyTiles.add(tile);
                }
            }
        }

        return emptyTiles;
    }

    public void moveSnake() {
        headX += dx;
        headY += dy;

        if (headX == -1
                || headY == -1
                || headX == FIELD_WIDTH
                || headY == FIELD_WIDTH
                || !snake.move(field[headY][headX])) {

            isGameOver = true;

        } else {
            if (snake.isEaten()) {
                addMeal();
                timerDelay -= 5;
                snake.setEaten(false);
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void addMeal() {
        List<Tile> emptyTiles = getEmptyTiles();
        emptyTiles.get((int) (Math.random() * emptyTiles.size())).setMeal(true);
    }

    public Tile[][] getField() {
        return field;
    }

    public int getSnakeSize() {
        return snake.getSize();
    }

    public void turnSnake(int dx, int dy) {
        if ((this.dx == 0 && this.dx - dx != 0) || (this.dy == 0 && this.dy - dy != 0)) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public int getTimerDelay() {
        return timerDelay;
    }
}
