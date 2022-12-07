package pl.lodz.p.it.kompo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.lodz.p.it.kompo.model.BacktrackingSudokuSolver;
import pl.lodz.p.it.kompo.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {
    @FXML
    private ChoiceBox<Level> levelChoiceBox;
    @FXML
    private Button start;

    @FXML
    Canvas canvas;
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver);
        levelChoiceBox.getItems().setAll(Level.values());
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GraphicsContext board = canvas.getGraphicsContext2D();
                drawBoard(board);
            }
        });

    }

    public void showStage() {
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Kompo.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void drawBoard(GraphicsContext board) {
        int size = 29;
        int x;
        int y;
        board.clearRect(1, 1, canvas.getHeight(), canvas.getWidth());
        board.setFill(Color.BLACK);
        board.fillRoundRect(0, 0,9*30+1, 9*30+1, 0, 0);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                x = row * 30 + 1;
                y = col * 30 + 1;
                board.setFill(Color.LIGHTGRAY);
                board.fillRoundRect(x, y, size, size, 0, 0);
                board.setFill(Color.BLACK);
                board.setFont(new Font(15));
                if (sudokuBoard.getIndex(col, row) != 0) {
                    board.fillText(String.valueOf(sudokuBoard.getIndex(col, row)), x + 10, y + 20);
                } else {
                    board.setFill(Color.WHITE);
                    board.fillText(String.valueOf(sudokuBoard.getIndex(col, row)), x + 10, y + 20);
                }
            }
        }
    }
}
