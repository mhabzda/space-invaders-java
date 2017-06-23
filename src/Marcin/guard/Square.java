package Marcin.guard;

import Marcin.game.Commons;

import java.awt.*;

/**
 * Created by Marcin on 2017-01-15.
 */
class Square extends Rectangle implements Commons {

    boolean visible;

    Square(int x, int y) {
        super(x, y, SQUARE_SIZE, SQUARE_SIZE);      //korzystamy z konstruktora klasy Rectangle
        setVisible(true);

    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    void draw(Graphics g) {

        g.setColor(new Color(241, 59, 53));
        g.fillRect(x, y, width, height);

    }
}
