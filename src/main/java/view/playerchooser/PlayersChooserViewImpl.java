package view.playerchooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.View;
import view.ViewType;

import java.net.URL;
import java.util.*;

public class PlayersChooserViewImpl extends View implements Initializable,PlayersChooserView {
    @FXML
    private Button startButton;
    @FXML
    private List<TextField> playersName;
    @FXML
    private List<ComboBox<String>> playersComboBox;
    @FXML
    private Label errorLabel;

    public PlayersChooserViewImpl() {
    	super.createStage(ViewType.CHOOSE_PLAYER);
	}
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    	for (int i = 0; i < playersComboBox.size(); i++) {
    		final TextField currentPlayerText = playersName.get(i);
			playersComboBox.get(i).valueProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(final ObservableValue<? extends String> ob, final String ol, final String currentValue) {
					if("No Selection".equals(currentValue)) {
						currentPlayerText.setDisable(false);
					}else {
						currentPlayerText.setDisable(true);
					}
				}
			});
		}
    }

    @Override
    public Map<String,String> getPlayersInfo() {
    	final Map<String,String> l = new HashMap<>();
    	for (int i = 0; i < playersComboBox.size(); i++) {
    		final TextField txtPlayer = playersName.get(i);
    		final ComboBox<String> cb = playersComboBox.get(i);
    		System.out.println(cb.getValue());
    		if("No Selection".equals(cb.getValue()) || cb.getValue() == null) {
    			l.put(txtPlayer.getId(),txtPlayer.getText());
    		}else {
    			l.put(cb.getId(),cb.getValue());
    		}
    	}
		
    	return l;
    }

    @Override
    public void setErrorLabelText(final String s) {
    	errorLabel.setText(s);
    }

    @Override
    public void addButtonListener(final EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }
    
    public void setTextComboBox(final List<String> listNames) {
    	listNames.add(0, "No Selection");
    	for (final ComboBox<String> cb: playersComboBox) {
			cb.setItems(FXCollections.observableArrayList(listNames));
		}
    }

}
