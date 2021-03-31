package application.minigame.spaceshooter;


import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SpaceShooter extends Application {

    private final Random rnd = new Random();
    private List<Enemy> enemies;
    private List<Shot> shots;
    private Player player;



    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void initialize(){
        IntStream.range(0,10).forEach(i -> enemies.add(new Enemy(rnd.nextInt(450),500,Info.SIZE_P,Info.ENEMY_IMG)));
        player = new Player(200,100,Info.SIZE_P, Info.PLAYER_IMG);
        shots = new ArrayList<>();
        Info.score = 0;
    }
}
