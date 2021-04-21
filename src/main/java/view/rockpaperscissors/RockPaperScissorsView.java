package view.rockpaperscissors;

import model.choice.RockPaperScissorsChoice;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.MinigameView;
import view.ViewType;


public class RockPaperScissorsView extends MinigameView {

    @FXML
    private Button rockButton, paperButton, scissorsButton;

    @FXML
    private Label playerScoreLabel, computerScoreLabel, resultLabel;

    @FXML
    private ImageView playerImage, computerImage;

    public RockPaperScissorsView() {
        super(ViewType.ROCK_PAPER_SCISSORS);
    }

    public void playerWin() {
        resultLabel.setText("You Win");
    }

    public void computerWin() {
        resultLabel.setText("You Lose");
    }

    public void draw() {
        resultLabel.setText("Draw");
    }

    public void setRockButtonHandler(final EventHandler<ActionEvent> e) {
        rockButton.setOnAction(e);
    }

    public void setPaperButtonHandler(final EventHandler<ActionEvent> e) {
        paperButton.setOnAction(e);
    }

    public void setScissorsButtonHandler(final EventHandler<ActionEvent> e) {
        scissorsButton.setOnAction(e);
    }

    public void setPlayerScoreLabel(int score) {
        playerScoreLabel.setText(" " + score);
    }

    public void setComputerScoreLabel(int score) {
        computerScoreLabel.setText(" " + score);
    }

    public void setPlayerChoiceImage(final RockPaperScissorsChoice choice) {
        playerImage.setImage(new Image(parseChoiceImagePath(choice)));
    }

    public void setComputerChoiceImage(final RockPaperScissorsChoice choice) {
        computerImage.setImage(new Image(parseChoiceImagePath(choice)));
    }

    private String parseChoiceImagePath(final RockPaperScissorsChoice choice) {
        String imagePath;
        if (choice == RockPaperScissorsChoice.ROCK) {
            imagePath = "rockpaperscissors/rock.png";
        } else if (choice == RockPaperScissorsChoice.PAPER) {
            imagePath = "rockpaperscissors/paper.png";
        } else {
            imagePath = "rockpaperscissors/scissors.png";
        }
        return imagePath;
    }

}