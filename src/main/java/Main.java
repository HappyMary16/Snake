import controller.Controller;
import model.GameModel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GameModel model = new GameModel();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();

        game.setTitle("Snake");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(550, 600);
        game.setResizable(false);

        game.add(controller.getView());

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}