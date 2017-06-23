package Marcin.game;

import Marcin.guard.*;
import Marcin.sprites.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2016-12-29.
 */
public class Board extends JPanel implements Runnable, Commons {

    private Player player;
    private EnemyWave enemyWave;
    private List<Guard> guards;

    private boolean inGame; //do sprawdzania czy nadal jestesmy w grze
    private Integer lives;  //liczba zyc
    private String message; //komunikat wyswietlany na koniec gry

    Board() {

        inGame=true;
        lives=3;

        player=new Player(START_X, START_Y);
        enemyWave = new EnemyWave();

        guards = new ArrayList<>();
        for(int i=0; i<4 ; i++) {
            guards.add(new Guard(GUARD_POSX + i * 125, GUARD_POSY));    //tworzymy 4 guardow
        }

        addKeyListener(new KAdapter());     //do reagowania na wcisniecia klawiszy
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Thread animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis(); //wyliczamy aktualny czas

        while(inGame) {
            repaint();          //rysujemy na nowo
            animationCycle();   //wykonujemy odpowiednie ruchy, sprawdzamy kolizje

            timeDiff = System.currentTimeMillis() - beforeTime;     //wyliczamy czas ile nam to zajelo
            sleep = DELAY - timeDiff;       //ile czasu powinnismy jeszcze poczekac

            if(sleep<0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);            //czekamy opdowiednia ilosc czasu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            beforeTime=System.currentTimeMillis();
        }
        gameOver();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Font font = new Font("Helvetica", Font.PLAIN, 15);
        g.setColor(Color.WHITE);
        g.setFont(font);

        //wyspisujemy informacje o liczbie pozostalych przeciwnikow oraz zyc
        g.drawString("Lives: " + lives.toString(), B_WIDTH - 90, 20);
        g.drawString("Enemies Left: " + enemyWave.getNumberOfEnemies().toString(), 28, 20);

        g.setColor(Color.GREEN);
        g.drawLine(0, GROUND, B_WIDTH, GROUND);

        //rysujemy wszystkie komponenty
        player.draw(g, this);
        if (player.getM().isVisible())
            player.getM().draw(g, this);

        enemyWave.draw(g, this);

        for (Guard guard : guards) {
            guard.draw(g);
        }

    }

    private void animationCycle() {

        //sprawdzamy liczbe pozostalych przeciwnikow
        if(enemyWave.getNumberOfEnemies()==0) {
            inGame=false;
            message = "You Won! Congrats!";
        }

        //sprawdzamy liczbe zyc i albo ozywiamy gracza albo konczymy gre
        if(player.isDying()) {
            lives--;
            if(lives!=0) player.revive();
            else {
                inGame=false;
                message = "Game Over!";
            }
        }

        //sprawdzamy czy przeciwnicy dotarli opowiednio blisko gracza(dotrali do ziemii)
        if(enemyWave.reachedTheGround()) {
            inGame=false;
            message="Game Over!";
        }

        player.move();
        player.missleMove();
        enemyWaveMove();
        collisionMissileEnemies();
        collisionBombPlayer();
        collisionWithGuards();

    }


    private void enemyWaveMove() {

        enemyWave.fixStatus();
        enemyWave.bombMove();
        enemyWave.shooting();
        enemyWave.accelerateIfNeeded();
        enemyWave.turnAroundIfHitTheWall();
    }

    //sprawdzamy kolizje pocisku z przeciwnikami
    private void collisionMissileEnemies() {

        if(player.getM().isVisible()) {
            for (Enemy e : enemyWave.getEnemies())
                if(e.isVisible() && player.getM().collisionWith(e)) {
                    e.explosion();
                    enemyWave.decreaseNumberOfEnemies();
                    player.getM().die();
                }
        }
    }

    //sprawdzamy kolizje bomby z graczem
    private void collisionBombPlayer() {

        for(Enemy e : enemyWave.getEnemies()) {
            if (e.getBomb().isVisible() && e.getBomb().collisionWith(player)) {
                player.explosion();
                e.getBomb().die();
            }
        }
    }

    //sprawdzamy kolizje pocisku i bomb z oslonami
    private void collisionWithGuards() {

        for(Guard guard : guards) {
            guard.collisionWith(player.getM());
            for (Enemy e : enemyWave.getEnemies()) {
                guard.collisionWith(e.getBomb());
            }
        }
    }

    //wypisujemu odpowiedni komunikat na zakonczenie gry
    private void gameOver() {

        Graphics g = this.getGraphics();
        super.paintComponent(g);

        Font font = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics ft = this.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(message, (B_WIDTH-ft.stringWidth(message))/2, B_HEIGHT/2);
    }

    //klasa Kadapter z metodami zwiazanymi z akcjami dla przyciskow
    private class KAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

    }
}
