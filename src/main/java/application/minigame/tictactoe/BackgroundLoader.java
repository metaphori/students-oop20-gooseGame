package application.minigame.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BackgroundLoader {

    /* sfondo del bottone della griglia principale */
    static final BackgroundImage gameButtonBackground =
            new BackgroundImage(new Image("TicTacToe/Sfondo.png",200,170,false,false),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    /* bottone finale che dice chi ha vinto o no */
    static final BackgroundImage endGameButtonBackground =
            new BackgroundImage(new Image("TicTacToe/Sfondo.png",600,480,false,false),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);


    private static final BackgroundSize bs =
            new BackgroundSize(300,400,true,true,false,true);

    /* sfondo della schermata finale del gioco */
    static final BackgroundImage endGameButton =
            new BackgroundImage(new Image("TicTacToe/green_button01.png"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bs);


}