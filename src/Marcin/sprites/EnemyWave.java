package Marcin.sprites;

import Marcin.game.Board;
import Marcin.game.Commons;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-01-18.
 */
public class EnemyWave implements Commons {

    private List<Enemy> enemies;
    private Integer numberOfEnemies;        //liczba pozostalych przeciwnikow na planszy
    private int enemySpeed;

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Integer getNumberOfEnemies() {
        return numberOfEnemies;
    }

    public void decreaseNumberOfEnemies() {
        numberOfEnemies--;
    }

    public EnemyWave() {
        enemies = new ArrayList<>();
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 8; j++) {
                enemies.add(new Enemy(ENEMY_X + 32 * j, ENEMY_Y + 32 * i)); //tworzymy 32 przeciwnikow
            }
        }
        numberOfEnemies=32;
        enemySpeed=1;
    }

    //rysowanie przeciwnikow
    public void draw(Graphics g, Board board) {
        for (Enemy e : enemies) {
            if (e.visible)
                e.draw(g, board);
            if (e.bomb.visible)
                e.bomb.draw(g, board);
        }
    }

    //sprawdzam czy przeciwnicy dotarli odpowiednio blisko playera
    public boolean reachedTheGround() {
        for(Enemy e: enemies) {
            if (e.visible && e.y + e.height > GUARD_POSY) {
                return true;
            }
        }
        return false;
    }

    //ruch przeciwnika lub ustawienie aby przeciwnik nie byl wyswietlany kiedy zginie
    public void fixStatus() {
        for(Enemy e : enemies) {
            if(e.dying) {
                e.setAlmostDied(true);
                e.setDying(false);
            }
            else if(e.almostDied) {     //aby za kazdym razem wyswietlala nam sie poprawnie eksplozja
                e.die();                //byla rysowana dwa razy(w dwoch cyklach)
                e.setAlmostDied(false);
            }
            else if(e.visible)
                e.move();
        }
    }

    //ruch bomby
    public void bombMove() {
        for(Enemy e: enemies) {
            if(e.bomb.visible) {
                e.bomb.move();
            }
        }
    }

    public void shooting() {
        for(Enemy e: enemies) {
            e.tryToShoot();
        }
    }

    //jezeli liczba przeciwnikow odpowiednio sie zmniejszy wszystkie przyspieszaja
    public void accelerateIfNeeded() {
        boolean b=false;

        if(numberOfEnemies==16) {
            enemySpeed = 2;
            b = true;
        }

        if(numberOfEnemies==8) {
            enemySpeed = 3;
            b = true;
        }

        if(b) {
            for (Enemy e : enemies) {
                if (e.dx > 0) e.dx = enemySpeed;
                else e.dx = -enemySpeed;
            }
        }
    }

    //jezeli przeciwnicy dotra do brzegu, zawracaja
    public void turnAroundIfHitTheWall() {
        for(Enemy e: enemies) {
            if(e.x>B_WIDTH-ENEMY_WIDTH) {
                for(Enemy e2 : enemies) {
                    e2.dx = -enemySpeed;
                    e2.y += 15;
                }
                break;
            }

            if(e.x<0) {
                for(Enemy e2 : enemies) {
                    e2.dx = enemySpeed;
                    e2.y += 15;
                }
                break;
            }
        }
    }
}
