package Marcin.sprites;

import Marcin.game.Board;
import Marcin.game.Commons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin on 2016-12-29.
 */
public class MovingObject implements Commons {

    private Image image;
    int x;
    int y;
    int dx; //predkosc w kierunku x
    int dy; //predkosc w kierunku y
    int width;
    int height;
    boolean dying;
    boolean visible;

    MovingObject(int x, int y) {

        this.x=x;
        this.y=y;
        visible=true;
        dying=false;

    }

    void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    //w przypadku zginiecia wyswietlany obrazek eksplozji i ustawiane umieranie
    public void explosion() {

        loadImage("explosion.png");
        setDying(true);
    }

    //w celu badania kolizji zwracany obiekt typu Rectangle
    public Rectangle getBoundary() {
        return new Rectangle(x, y, width, height);
    }

    //sprawdza kolizje z uzyciem metody intersects z klasy Rectangle
    public boolean collisionWith(MovingObject o) {
        return this.getBoundary().intersects(o.getBoundary());
    }

    //rysuje obiekt
    public void draw(Graphics g, Board board) {
        g.drawImage(image, x, y, width, height, board);
    }

    public void die() {
        visible=false;
    }

    public boolean isVisible() {
        return visible;
    }

    void setDying(boolean b) {
        dying=b;
    }

    public boolean isDying() {
        return dying;
    }

    public void move() {

        x+=dx;
        y+=dy;

    }


}
