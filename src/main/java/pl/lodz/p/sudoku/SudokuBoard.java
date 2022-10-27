package pl.lodz.p.sudoku;

import java.util.Random;


public class SudokuBoard {
    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver solver;
    /**
     * Konstruktor Klasy SudokuBorad, Generuje losową plansze Sudoku z 5 cyframi.
     * @param resolver rodzaj metody rozwiązaywania sudoku
     */

    public SudokuBoard(SudokuSolver resolver) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new SudokuField();
            }
        }
        this.solver = resolver;
        // random generate starting board
        Random ran = new Random();
        for (int r = 0; r < 9; r++) {
            int rand = ran.nextInt(9) + 1;
            int ranCol = ran.nextInt(9);
            int ranRow = ran.nextInt(9);
            boolean isAllowed = true;
            // check if can add new board to set
            for (int j = 0; j < board[ranRow].length; j++) {
                if (board[ranRow][j].getValue() == rand) {
                    isAllowed = false;
                    break;
                }
            }
            for (int j = 0; j < board.length; j++) {
                if (board[j][ranCol].getValue() == rand) {
                    isAllowed = false;
                    break;
                }
            }
            int startRow = ranRow - ranRow % 3;
            int startCol = ranCol - ranCol % 3;
            for (int j = 0; j < 3; j++) {
                for (int g = 0; g < 3; g++) {
                    if (board[j + startRow][g + startCol].getValue() == rand) {
                        isAllowed = false;
                        break;
                    }
                }
            }
            //check if safe to add number
            if (!isAllowed) {
                continue;
            }
            board[ranRow][ranCol].setValue(rand);
        }
        this.solveGame();
    }

    /**
     * Funkcja rozwiązująca Sudoku.
     * Wykorzystuje Implementacje BacktrakingSudokuSolver z intefejsu SudokuSolver.
     * @see BacktrackingSudokuSolver
     * @return zrwraca true gdy powiedzie się uzupełnienie planszy, false jeżeli nie.
     */
    public boolean solveGame() {
        return solver.solve(this);
    }

    private boolean checkBoard() {
        boolean isCorrect = true;
        for (int i = 0; i < 9;i++) {
            isCorrect = isCorrect && getRow(i).verify();
            isCorrect = isCorrect && getColumn(i).verify();
        }
        for (int i = 0; i < 3;i++) {
            for (int j = 0; j < 3;j++) {
                isCorrect = isCorrect && getBox(i,j).verify();
            }
        }
        return isCorrect;
    }

    public int getIndex(int row,int col) {
        return board[row][col].getValue();
    }

    public void setIndex(int row,int col,int number) {
        board[row][col].setValue(number);
    }

    public SudokuRow getRow(int row) {
        return new SudokuRow(board[row]);
    }

    public SudokuColumn getColumn(int col) {
        SudokuField[] column = new SudokuField[9];
        for (int row = 0;row < board.length;row++) {
            column[row] = board[row][col];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int row,int col) {
        SudokuField[] box = new SudokuField[9];
        int index = 0;
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int j = 0; j < 3; j++) {
            for (int g = 0; g < 3; g++) {
               box[index++] = board[j + startRow][g + startCol];
            }
        }
        return new SudokuBox(box);
    }

    public static void main(String[] args) {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(back);
        for (int i = 0; i < s.board.length; i++) {
            for (int j = 0; j < s.board[0].length; j++) {
                System.out.print(s.getIndex(i, j) + " ");
            }
            System.out.println();
        }
    }
}