package Marcin.sprites;

import Marcin.game.Commons;

/**
 * Created by Marcin on 2016-12-30.
 */
public class Bomb extends MovingObject implements Commons {

    Bomb(int x, int y) {

        super(x, y);
        loadImage("bomb.png");
        width=BOMB_WIDTH;
        height=BOMB_HEIGHT;
        dy=BOMB_SPEED;
    }

    @Override
    public void move() {
        if(y>GROUND-BOMB_HEIGHT) this.die();    //po opuszczeniu ekranu staje sie niewidoczny
        super.move();
    }
}
