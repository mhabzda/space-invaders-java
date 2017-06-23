package Marcin.sprites;

import Marcin.game.Commons;
import java.awt.event.KeyEvent;

/**
 * Created by Marcin on 2016-12-29.
 */
public class Player extends MovingObject implements Commons {

    private Missile m;

    public Player (int x, int y) {

        super(x, y);
        loadImage("player.png");
        width=PLAYER_WIDTH;
        height=PLAYER_HEIGHT;
        m = new Missile(0, 0);
        m.die();                //tworzymy pocisk, ale na poczatku jest niewidoczny
    }

    public Missile getM() {
        return m;
    }

    //po zestrzeleniu ozywia gracza, jezeli ma jeszcze zycia
    public void revive() {
        loadImage("player3.png");
        setDying(false);
        x=B_WIDTH/2;
    }

    //ruch pocisku
    public void missleMove() {
        if(m.isVisible()) {
            m.move();
        }
    }

    //akcja w przypadku nacisniecia klawisza
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -PLAYER_SPEED;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = PLAYER_SPEED;
        }
        if (key == KeyEvent.VK_SPACE) {
            if(!m.visible) {
                m.visible=true;
                m.x=this.x + PLAYER_WIDTH/2;
                m.y=this.y;
            }
        }
    }

    //akcja w przypadku puszczenia klawisza
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if(key==KeyEvent.VK_LEFT) {
            dx=0;
        }
        if(key==KeyEvent.VK_RIGHT) {
            dx=0;
        }
    }

    @Override
    public void move() {

        if(x>B_WIDTH-PLAYER_WIDTH)
            x=B_WIDTH-PLAYER_WIDTH;     //aby nie wychodzic poza ekran
        else if(x<0)
            x=0;
        else
            super.move();

    }


}
