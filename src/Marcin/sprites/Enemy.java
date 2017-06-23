package Marcin.sprites;

import Marcin.game.Commons;
import java.util.Random;

/**
 * Created by Marcin on 2016-12-29.
 */
public class Enemy extends MovingObject implements Commons {

    Bomb bomb;
    /*pole uzywane do poprawnego wyswietlania ekpslozji przeciwnikow(aby wyswietac w  dwoch cyklach,
     poniewaz w jednym cyklu czasem nie można dostrzec eksplozji)*/
    boolean almostDied;
    private Random rand;

    public Bomb getBomb() {
        return bomb;
    }

    Enemy(int x, int y) {

        super(x, y);
        rand = new Random();
        loadImage("enemy.png");
        width=ENEMY_WIDTH;
        height=ENEMY_HEIGHT;
        dx=1;
        almostDied=false;
        bomb=new Bomb(0, 0);    //tworzymy bombe, ale na poczatku jest niewidoczna
        bomb.die();
    }

    void tryToShoot() {
        int random = rand.nextInt()%400;
        if(random==1 && !this.bomb.visible && this.visible){
            this.bomb.x=this.x+ENEMY_WIDTH/2;
            this.bomb.y=this.y+ENEMY_HEIGHT;
            this.bomb.visible=true;
        }
    }

    void setAlmostDied(boolean almostDied) {
        this.almostDied = almostDied;
    }

    @Override
    public void move() {

        super.move();

    }




}
