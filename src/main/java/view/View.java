package view;

import controller.Controller;
import model.Tile;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    private static final Color BG_COLOR = Color.GRAY;
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 24;
    private static final int TILE_MARGIN = 3;
    private boolean isGameOver;
    private Controller controller;

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                drawTile(g, controller.getGameField()[y][x], x, y);
            }
        }

        g.setColor(Color.BLACK);

        final Font font = new Font(FONT_NAME, Font.BOLD, 24);
        g.setFont(font);
        g.drawString("Score: " + controller.getSnakeSize(), 220, 560);

        if (isGameOver) {
            g.setFont(new Font(FONT_NAME, Font.BOLD, 36));
            g.drawString("Game over!", 170, 250);
        }


    }

    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        Color tileColor;

        if (tile.isSnake()) {
            tileColor = tile.isSnakeHead() ? Color.ORANGE : Color.GREEN;
        } else {
            tileColor = tile.isMeal() ? Color.RED : new Color(0xbbada0);
        }

        g.setColor(tileColor);
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 8, 8);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}