package Marcin.game;

import javax.swing.*;

/**
 * Created by Marcin on 2016-12-29.
 */
public class SpaceInvaders extends JFrame implements Commons{

    public SpaceInvaders() {

        initUI();
    }

    private void initUI() {

        this.add(new Board());

        setSize(B_WIDTH, B_HEIGHT);
        setResizable(false);

        setTitle("SpaceInvaders");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {

        new SpaceInvaders();

    }
}
