package Marcin.guard;

import Marcin.game.Commons;
import Marcin.sprites.MovingObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-01-15.
 */
public class Guard implements Commons {

    private List<Square> squares;

    //do listy dodajemy kwadraty
    public Guard(int x, int y) {
        squares=new ArrayList<>();
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 5; j++) {
                squares.add(new Square(x + SQUARE_SIZE * j, y + SQUARE_SIZE * i));
            }
        }
    }

    //sprawdzamy kolizje
    public void collisionWith(MovingObject o) {
        for(Square s : squares) {
            if(s.visible && s.intersects(o.getBoundary())) {
                s.setVisible(false);
                o.die();
            }
        }
    }

    public void draw(Graphics g) {
        for(Square s : squares) {
            if(s.visible) s.draw(g);
        }
    }


}
