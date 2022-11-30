package pl.lodz.p.it.kompo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {
    @FXML
    private ChoiceBox<Level> levelChoiceBox;
    @FXML
    private Button start;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        levelChoiceBox.getItems().setAll(Level.values());
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You clicked me!");
            }
        });
    }

    public enum Level{

        Easy(2),
        Medium(4),
        Hard(6);

        private int numberEmpty;

        Level(int numberEmpty) {
            this.numberEmpty = numberEmpty;
        }

        public int getNumberEmpty() {
            return numberEmpty;
        }

        public void setNumberEmpty(int numberEmpty) {
            this.numberEmpty = numberEmpty;
        }
    }
    public Controler() {
    }

}
