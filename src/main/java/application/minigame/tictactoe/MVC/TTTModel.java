package application.minigame.tictactoe.MVC;

import javafx.scene.control.Button;
import java.util.List;

public class TTTModel {
    /**
     * Numero di click che viene fatto, viene usata in caso che nessuno vinca
     */
    private int numberOfClick=0;

    final List<Button> listButtonGrid = TTTView.getListButton();

    private final List<String> sign = List.of("X", "O");

    /**
     * Metodo che ritorna il vincitore
     * @return Stringa vincitore
     */
    public String checkWin(){
        numberOfClick++;
        for(int i = 0; i < 7; i+=3){
            for(int j = 0; j < 2; j++){
                if(listButtonGrid.get(i).getText().equals(sign.get(j))
                        && listButtonGrid.get(i+1).getText().equals(sign.get(j))
                        && listButtonGrid.get(i+2).getText().equals(sign.get(j)))
                {
                    return sign.get(j);
                }
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                if(listButtonGrid.get(i).getText().equals(sign.get(j))
                        && listButtonGrid.get(i+3).getText().equals(sign.get(j))
                        && listButtonGrid.get(i+6).getText().equals(sign.get(j)))
                {
                    return sign.get(j);
                }
            }
        }
        for(int j = 0; j < 2; j++) {
            if (listButtonGrid.get(0).getText().equals(sign.get(j))
                    && listButtonGrid.get(4).getText().equals(sign.get(j))
                    && listButtonGrid.get(8).getText().equals(sign.get(j))) {
                return sign.get(j);
            }
            if (listButtonGrid.get(2).getText().equals(sign.get(j))
                    && listButtonGrid.get(4).getText().equals(sign.get(j))
                    && listButtonGrid.get(6).getText().equals(sign.get(j))) {
                return sign.get(j);
            }
        }
        if(numberOfClick==9){
            return "No one";
        }
        return "";
    }

}
