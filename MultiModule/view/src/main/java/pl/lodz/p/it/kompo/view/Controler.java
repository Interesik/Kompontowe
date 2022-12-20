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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.lodz.p.it.kompo.model.BacktrackingSudokuSolver;
import pl.lodz.p.it.kompo.model.FileSudokuBoardDao;
import pl.lodz.p.it.kompo.model.Level;
import pl.lodz.p.it.kompo.model.SudokuBoard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controler implements Initializable {
    @FXML
    private ChoiceBox<String> levelChoiceBox;
    @FXML
    private ChoiceBox<Integer> numbers;
    @FXML
    private Button start;
    @FXML
    private Button setter;
    @FXML
    private Menu file;
    @FXML
    private Menu help;
    @FXML
    private Menu language;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveas;
    @FXML
    private MenuItem English;
    @FXML
    private MenuItem Polski;
    @FXML
    private MenuItem about;
    @FXML
    Canvas canvas;
    final FileChooser fileChooser = new FileChooser();

    Locale loc = new Locale("en");
    private FileSudokuBoardDao fileSudokuBoardDao;
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;
    private int selectRow;
    private int selectCol;
    private String[] levels = new String[3];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle finalResourceBundle = ResourceBundle.getBundle("ResourceBundle",loc);
        levels[0] = finalResourceBundle.getString("easy");
        levels[1] = finalResourceBundle.getString("medium");
        levels[2] = finalResourceBundle.getString("hard");
        solver = new BacktrackingSudokuSolver();
        levelChoiceBox.getItems().setAll(levels);
        numbers.getItems().setAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
        canvas.setOnMousePressed(e -> BoardMouseClicked());

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GraphicsContext board = canvas.getGraphicsContext2D();
                if (levelChoiceBox.getSelectionModel().getSelectedItem() == levels[0]) {
                    sudokuBoard = new SudokuBoard(solver);
                    sudokuBoard.removeRandom(Level.Easy.getNumberEmpty());
                    drawBoard(board);
                } else if (levelChoiceBox.getSelectionModel().getSelectedItem() == levels[1]) {
                    sudokuBoard = new SudokuBoard(solver);
                    sudokuBoard.removeRandom(Level.Medium.getNumberEmpty());
                    drawBoard(board);
                } else if (levelChoiceBox.getSelectionModel().getSelectedItem() == levels[2]) {
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
                if (numbers.getSelectionModel().getSelectedItem() != 0 && sudokuBoard.getIndex(selectCol, selectRow) == 0) {
                    sudokuBoard.setIndex(selectCol, selectRow, numbers.getSelectionModel().getSelectedItem());
                }
                if (!sudokuBoard.checkBoard()) {
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
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!sudokuBoard.equals(null)) {
                    fileSudokuBoardDao = new FileSudokuBoardDao("..\\SudokuFile.txt");
                    fileSudokuBoardDao.write(sudokuBoard);
                }
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!sudokuBoard.equals(null)) {
                    fileSudokuBoardDao = new FileSudokuBoardDao("..\\SudokuFile.txt");
                    fileSudokuBoardDao.write(sudokuBoard);
                }
            }
        });
        saveas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!sudokuBoard.equals(null)) {
                    fileSudokuBoardDao = new FileSudokuBoardDao(fileChooser.showSaveDialog(new Stage()).getAbsoluteFile().toString());
                    fileSudokuBoardDao.write(sudokuBoard);
                }
            }
        });
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileChooser.setTitle(finalResourceBundle.getString("OpenResourceFile"));
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("TXT", "*.txt")
                );
                fileSudokuBoardDao = new FileSudokuBoardDao(fileChooser.showOpenDialog(new Stage()).getAbsoluteFile().toString());
                sudokuBoard = fileSudokuBoardDao.read();
                drawBoard(canvas.getGraphicsContext2D());
            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                About about = new About();
                about.showStage(loc);
            }
        });

        English.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loc = new Locale("en");
                ResourceBundle finalResourceBundle = ResourceBundle.getBundle("ResourceBundle",loc);
                setText(finalResourceBundle);
            }
        });

        Polski.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loc = new Locale("pl");
                ResourceBundle finalResourceBundle = ResourceBundle.getBundle("ResourceBundle",loc);
                setText(finalResourceBundle);
            }
        });
    }

    private void setText(ResourceBundle resourceBundle) {
        open.setText(resourceBundle.getString("open"));
        setter.setText(resourceBundle.getString("set"));
        about.setText(resourceBundle.getString("about"));
        save.setText(resourceBundle.getString("save"));
        saveas.setText(resourceBundle.getString("saveas"));
        start.setText(resourceBundle.getString("start"));
        file.setText(resourceBundle.getString("file"));
        help.setText(resourceBundle.getString("help"));
        language.setText(resourceBundle.getString("language"));
        levels[0] = resourceBundle.getString("easy");
        levels[1] = resourceBundle.getString("medium");
        levels[2] = resourceBundle.getString("hard");
        levelChoiceBox.getItems().setAll(levels);
        levelChoiceBox.setValue("");
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
                if(sudokuBoard != null) {
                    int mouse_x = (int) e.getX();
                    int mouse_y = (int) e.getY();
                    selectRow = mouse_y / 30;
                    selectCol = mouse_x / 30;
                    drawBoard(canvas.getGraphicsContext2D());
                    setter.setStyle("");
                }
            }
        });
    }
}
