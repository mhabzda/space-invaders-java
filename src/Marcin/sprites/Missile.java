package Marcin.sprites;

import Marcin.game.Commons;

/**
 * Created by Marcin on 2016-12-29.
 */

public class Missile extends MovingObject implements Commons {

    Missile(int x, int y) {

        super(x, y);
        loadImage("missile.png");
        width=MISSILE_WIDTH;
        height=MISSILE_HEIGHT;
        dy=-MISSILE_SPEED;
    }

    @Override
    public void move() {
        if(y<=0) this.die();        //po opuszczeniu ekranu staje sie niewidoczny
        super.move();
    }

}
