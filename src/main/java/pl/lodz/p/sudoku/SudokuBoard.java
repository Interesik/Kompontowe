package pl.lodz.p.sudoku;

import java.util.Random;


public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private SudokuSolver solver;
    /**
     * Konstruktor Klasy SudokuBorad, Generuje losową plansze Sudoku z 5 cyframi.
     * @param typesolver rodzaj metody rozwiązaywania sudoku
     */

    public SudokuBoard(SudokuSolver typesolver) {
        this.solver = typesolver;
        // random generate starting board
        Random ran = new Random();
        for (int r = 0; r < 9; r++) {
            int rand = ran.nextInt(9) + 1;
            int ranCol = ran.nextInt(9);
            int ranRow = ran.nextInt(9);
            boolean isAllowed = true;
            // check if can add new board to set
            for (int j = 0; j < board[ranRow].length; j++) {
                if (board[ranRow][j] == rand) {
                    isAllowed = false;
                    break;
                }
            }
            for (int j = 0; j < board.length; j++) {
                if (board[j][ranCol] == rand) {
                    isAllowed = false;
                    break;
                }
            }
            int startRow = ranRow - ranRow % 3;
            int startCol = ranCol - ranCol % 3;
            for (int j = 0; j < 3; j++) {
                for (int g = 0; g < 3; g++) {
                    if (board[j + startRow][g + startCol] == rand) {
                        isAllowed = false;
                        break;
                    }
                }
            }
            //check if safe to add number
            if (!isAllowed) {
                continue;
            }
            board[ranRow][ranCol] = rand;
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

    public int getIndex(int row,int col) {
        return board[row][col];
    }

    public void setIndex(int row,int col,int number) {
        board[row][col] = number;
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