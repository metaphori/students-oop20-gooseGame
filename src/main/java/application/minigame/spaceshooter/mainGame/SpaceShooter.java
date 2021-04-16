package application.minigame.spaceshooter.mainGame;


import application.minigame.spaceshooter.entity.Enemy;
import application.minigame.spaceshooter.entity.Player;
import application.minigame.spaceshooter.entity.Shot;
import application.minigame.spaceshooter.info.GettersGraphics;
import application.minigame.spaceshooter.info.InfoGame;
import controller.minigame.MinigameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class SpaceShooter extends Application implements MinigameController {


    private final Random rnd = new Random();

    /**
     * List of enemies and shots and player
     */
    private List<Enemy> enemies;
    private List<Shot> shots;
    private Player player;

    /**
     * Take x for move player
     */
    private double mouseX;


    /**
     * get canvas from class GetterSgraphics
     */
    private Canvas canvas = new GettersGraphics().getCanvas();
    public static GraphicsContext gc;
    private boolean isOver;


    public SpaceShooter(){
        start(new Stage());
    }

    /**
     * @param primaryStage
     */

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD", "RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT"})
    @Override
    public void start(Stage primaryStage) {
        gc = canvas.getGraphicsContext2D();

        /**
         * Create a timeline for the animation.
         * Every 60millis run(gc) is executed
         */
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(60), e -> run(gc)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        /**
         * Seguo il movimento del cursore.
         * Ogni volta che lo muovo prendo la coordinata X.
         */
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());

        /**
         * Ogni volta che clicco, sparo un colpo.
         * Se la variabile isOver è true allora riinizio tutto da capo.
         */
        canvas.setOnMouseClicked(e -> {
            if(shots.size() < 20) {
                shots.add(player.shot());
            }
            if(this.isOver){
                getResult();
            }
        });

        /**
         * Inizializzo le strutture dati atte a tenere le entità
         */
        initialize();
        primaryStage.setTitle("SpaceShooter");
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Inizializzo la lista di nemici, colpi, creo il player e setto lo score a 0
     */
    public void initialize(){
        enemies = new ArrayList<>();
        shots = new ArrayList<>();
        IntStream.range(0,10).forEach(i -> enemies.add(new Enemy(rnd.nextInt(600),0, InfoGame.SIZE_ENEMY,InfoGame.ENEMY_IMG)));
        player = new Player(InfoGame.WIDTH/2,InfoGame.HEIGHT-InfoGame.SIZE_PLAYER,InfoGame.SIZE_PLAYER, InfoGame.PLAYER_IMG);

    }

    /**
     * Metodo che viene eseguito ogni 60millis
     * @param gc GraphicsContext dato dalla classe GettersGraphics
     */
    private void run(GraphicsContext gc) {

        /**
         * Scrivo lo score in alto al centro
         */
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, InfoGame.WIDTH, InfoGame.HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + InfoGame.score, 300,  17);

        /**
         * Disegno lo sfondo
         */
        gc.drawImage(InfoGame.BACKGROUND_IMG,0,20,InfoGame.WIDTH,InfoGame.HEIGHT);

        /**
         * Se è finito il gioco, lo scrivo e ritorno il risultato
         */
        if(isOver){
            gc.setFill(Color.RED);
            gc.fillText("You lost, score: " + InfoGame.score, 300, 300);
            gc.setFont(Font.font(55));
            gc.setTextAlign(TextAlignment.LEFT);

        }

        /**
         * Aggiorno il player, lo disegno e setto la nuova posizione, con la nuova X
         */
        player.update();
        player.draw();
        player.position_player = new Point2D(mouseX,player.position_player.getY() );

        /**
         * Streammo nella lista dei nemici, per ognuno lo aggiorno e lo disegno.
         * Controllo che i nemici abbiano toccato il player.
         * Se lo tocca isOver diventa true e il player esplode.
         */
        enemies.stream().peek(Enemy::update).peek(Enemy::draw).forEach(e -> {
            if(player.touch(e) && !player.exploding){
                player.explode();
                isOver = true;
            }
        });

        /**
         * Per ogni shot nella lista shots, Se la y è fuori dallo schermo o se noShot è true
         * allora lo rimuovo dalla lista
         * Aggiorno e disegno i colpi.
         *
         * Il secondo for, gestisce le collisioni dei shot coi nemici
         * Se colpisce il nemico e se non c'è l'animazione dell'esplosione allora lo
         * faccio esplodere ed elimino il colpo, aumento lo score.
         */
        for(int i = shots.size()-1; i >= 0; i--){
            Shot shot = shots.get(i);
            if(shot.position_shot.getY() < 0 || shot.noShot){
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for(Enemy enemy: enemies){
                if(shot.collide(enemy) && !enemy.exploding){
                    InfoGame.score++;
                    enemy.explode();
                    shot.noShot = true;
                }
            }
        }

        /**
         * Controllo nella lista dei nemici, se la variabile destroyed è true, ricreo il nemico
         */
        for(int i = enemies.size()-1; i >= 0; i--){
            if(enemies.get(i).destroyed){
                enemies.set(i, new Enemy(rnd.nextInt(450),0,InfoGame.SIZE_ENEMY,InfoGame.ENEMY_IMG));
            }
        }

    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public Object getPlayer() {
        return player;
    }

    public boolean getOver() {
        return isOver;
    }



    @Override
    public int getResult() {
        return InfoGame.score;
    }
}
