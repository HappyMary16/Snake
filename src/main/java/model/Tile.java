package model;

public class Tile {

    private boolean isMeal;
    private boolean isSnake;
    private boolean isSnakeHead;

    public boolean isEmpty() {
        return !isMeal && !isSnake;
    }

    public boolean isMeal() {
        return isMeal;
    }

    public boolean isSnake() {
        return isSnake;
    }

    public boolean isSnakeHead() {
        return isSnakeHead;
    }

    public void setMeal(boolean meal) {
        isMeal = meal;
    }

    public void setSnake(boolean snake) {
        isSnake = snake;
    }

    public void setSnakeHead(boolean snakeHead) {
        if (snakeHead) {
            isSnake = true;
        }
        isSnakeHead = snakeHead;
    }
}
