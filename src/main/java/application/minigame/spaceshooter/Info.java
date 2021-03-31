package application.minigame.spaceshooter;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Info {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 480;
    public static final int SIZE_P = 50;
    public static final Image PLAYER_IMG = new Image("spaceShooter/player.png");
    public static final Image EXPLOSION_IMG = new Image("spaceShooter/explosion.png");
    public static final Image ENEMY_IMG = new Image("spaceShooter/enemy");
    public static int score;
    public static boolean isOver;
    public static double mouseX;
    public static int MAX_ENEMIES;


}
