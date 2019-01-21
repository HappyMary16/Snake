package controller;

import model.GameModel;
import model.Tile;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class Controller extends KeyAdapter implements ActionListener {

    private GameModel model;
    private View view;
    private Timer timer;

    public Controller(GameModel model) {
        this.model = model;
        view = new View(this);
        timer = new Timer(model.getTimerDelay(), this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                model.turnSnake(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                model.turnSnake(1, 0);
                break;
            case KeyEvent.VK_DOWN:
                model.turnSnake(0, 1);
                break;
            case KeyEvent.VK_UP:
                model.turnSnake(0, -1);
                break;
            case KeyEvent.VK_SPACE:
                startNewGame();
        }

        if (!timer.isRunning() && !model.isGameOver()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_UP:
                    timer.start();
            }
        }

    }

    private void startNewGame() {
        timer.stop();
        model.resetField();
        view.repaint();
        view.setGameOver(false);
    }

    public View getView() {
        return view;
    }

    public int getSnakeSize() {
        return model.getSnakeSize();
    }

    public Tile[][] getGameField() {
        return model.getField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.moveSnake();
        view.repaint();

        if (model.isGameOver()) {
            view.setGameOver(true);
            timer.stop();
        }
        timer.setDelay(model.getTimerDelay());
    }

}
