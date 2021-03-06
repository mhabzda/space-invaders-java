package sprites;

import static game.Commons.*;

public class Bomb extends MovingObject {

    Bomb(int x, int y) {
        super(x, y);
        loadImage("bomb.png");
        width=BOMB_WIDTH;
        height=BOMB_HEIGHT;
        dy=BOMB_SPEED;
    }

    @Override
    public void move() {
        if(y>GROUND-BOMB_HEIGHT)
            this.die();
        super.move();
    }
}
