package Marcin.game;

/**
 * Created by Marcin on 2016-12-30.
 */
public interface Commons {
    public static final int DELAY = 18; //czas jednego cyklu, co tyle milisekund rysujemy na nowo obraz
    public static final int B_HEIGHT=450; //szerokosc planszy
    public static final int B_WIDTH=600;  //wysokosc planszy
    public static final int PLAYER_SPEED=4;
    public static final int PLAYER_WIDTH=36;
    public static final int PLAYER_HEIGHT=22;
    public static final int MISSILE_SPEED=7;
    public static final int MISSILE_WIDTH=2;
    public static final int MISSILE_HEIGHT=8;
    public static final int BOMB_SPEED=3;
    public static final int BOMB_WIDTH=4;
    public static final int BOMB_HEIGHT=12;
    public static final int ENEMY_WIDTH=28;
    public static final int ENEMY_HEIGHT=28;
    public final static int ENEMY_X=0; //wspolrzedne pierwszego wrogiego statku
    public final static int ENEMY_Y=30;
    public final static int START_X=300;  //wspolrzedne startowe playera
    public final static int START_Y=360;
    public final static int GROUND=390;  //wspolrzedna y powierzchni(zielona kreska)
    public final static int SQUARE_SIZE=10;
    public final static int GUARD_POSX=90;  //wspolrzedne pierwszego guarda(od lewej)
    public final static int GUARD_POSY=300;

}
