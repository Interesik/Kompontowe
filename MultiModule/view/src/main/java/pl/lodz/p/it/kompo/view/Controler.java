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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.lodz.p.it.kompo.model.BacktrackingSudokuSolver;
import pl.lodz.p.it.kompo.model.Level;
import pl.lodz.p.it.kompo.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {
    @FXML
    private ChoiceBox<Level> levelChoiceBox;
    @FXML
    private ChoiceBox<Integer> numbers;
    @FXML
    private Button start;
    @FXML
    private Button setter;

    @FXML
    Canvas canvas;
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;
    private int selectRow;
    private int selectCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solver = new BacktrackingSudokuSolver();
        levelChoiceBox.getItems().setAll(Level.values());
        numbers.getItems().setAll(1,2,3,4,5,6,7,8,9);
        canvas.setOnMousePressed(e -> BoardMouseClicked());

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GraphicsContext board = canvas.getGraphicsContext2D();
                if (levelChoiceBox.getSelectionModel().getSelectedItem() == Level.Easy) {
                    sudokuBoard = new SudokuBoard(solver);
                    sudokuBoard.removeRandom(Level.Easy.getNumberEmpty());
                    drawBoard(board);
                } else if (levelChoiceBox.getSelectionModel().getSelectedItem() == Level.Medium) {
                    sudokuBoard = new SudokuBoard(solver);
                    sudokuBoard.removeRandom(Level.Medium.getNumberEmpty());
                    drawBoard(board);
                } else if (levelChoiceBox.getSelectionModel().getSelectedItem() == Level.Hard) {
                    sudokuBoard = new SudokuBoard(solver);
                    sudokuBoard.removeRandom(Level.Hard.getNumberEmpty());
                    drawBoard(board);
                }
            }
        });
        setter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GraphicsContext board = canvas.getGraphicsContext2D();
                if(numbers.getSelectionModel().getSelectedItem() != 0 && sudokuBoard.getIndex(selectCol, selectRow) == 0) {
                    sudokuBoard.setIndex(selectCol, selectRow, numbers.getSelectionModel().getSelectedItem());
                }
                if(!sudokuBoard.checkBoard()) {
                    sudokuBoard.setIndex(selectCol, selectRow, 0);
                    drawBoard(board);
                    board.setFill(Color.RED);
                    board.fillRoundRect(selectCol * 30 + 1, selectRow * 30 + 1, 30, 30, 0, 0);
                    board.setFill(Color.DARKRED);
                    board.fillText(String.valueOf(sudokuBoard.getIndex(selectCol, selectRow)), selectCol * 30 + 1 + 10, selectRow * 30 + 1 + 20);
                } else {
                    drawBoard(canvas.getGraphicsContext2D());
                    board.setFill(Color.LIGHTGREEN);
                    board.fillRoundRect(selectCol * 30 + 1, selectRow * 30 + 1, 30, 30, 0, 0);
                    board.setFill(Color.GREEN);
                    board.fillText(String.valueOf(sudokuBoard.getIndex(selectCol, selectRow)), selectCol * 30 + 1 + 10, selectRow * 30 + 1 + 20);
                    setter.setStyle("-fx-background-color: #00CC00; ");
                }
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
                if (sudokuBoard.getIndex(row, col) != 0) {
                    board.fillText(String.valueOf(sudokuBoard.getIndex(row, col)), x + 10, y + 20);
                } else {
                    board.setFill(Color.WHITE);
                    board.fillText(String.valueOf(sudokuBoard.getIndex(row, col)), x + 10, y + 20);
                }
            }
        }
        board.setFill(Color.AQUA);
        board.fillRoundRect(selectCol * 30 + 1, selectRow * 30 + 1, size, size, 5, 5);
        board.setFill(Color.BLUE);
        board.fillText(String.valueOf(sudokuBoard.getIndex(selectCol, selectRow)), selectCol * 30 + 1 + 10, selectRow * 30 + 1 + 20);
    }
    public void BoardMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                int mouse_x = (int) e.getX();
                int mouse_y = (int) e.getY();
                selectRow = mouse_y / 30;
                selectCol = mouse_x / 30;
                drawBoard(canvas.getGraphicsContext2D());
                setter.setStyle("");
            }
        });
    }
}
