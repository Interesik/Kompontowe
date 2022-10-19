package pl.lodz.p.sudoku;

import java.util.Random;


public class SudokuBoard {
    private final int[][] board = new int[9][9];

    /**
     * Konstruktor Klasy SudokuBorad, Generuje losową plansze Sudoku z 5 cyframi.
     */
    public SudokuBoard() {
        // random generate starting board
        Random ran = new Random();
        for (int r = 0; r < 5; r++) {
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
            // check if in row can add number
            if (!isAllowed) {
                continue;
            }
            for (int j = 0; j < board.length; j++) {
                if (board[j][ranCol] == rand) {
                    isAllowed = false;
                    break;
                }
            }
            // check if in col can add number
            if (!isAllowed) {
                continue;
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
            //check if safe to add number matrix 3x3
            if (!isAllowed) {
                continue;
            }
            board[ranRow][ranCol] = rand;
        }
        this.fillBoard();
    }

    /**
     * Rekurencyjna postać SudokuSolvera.
     * @return zrwraca true gdy powiedzie się uzupełnienie planszy, false jeżeli nie.
     */
    public boolean fillBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if ((board[row][col] == 0)) {
                    for (int i = 1; i <= 9; i++) {
                        boolean isAllowed = true;
                        // check if can add new board to set
                        for (int j = 0; j < board[row].length; j++) {
                            if (board[row][j] == i) {
                                isAllowed = false;
                                break;
                            }
                        }
                        // check if in row can add number
                        if (!isAllowed) {
                            continue;
                        }
                        for (int j = 0; j < board.length; j++) {
                            if (board[j][col] == i) {
                                isAllowed = false;
                                break;
                            }
                        }
                        // check if in col can add number
                        if (!isAllowed) {
                            continue;
                        }
                        int startRow = row - row % 3;
                        int startCol = col - col % 3;
                        for (int j = 0; j < 3; j++) {
                            for (int g = 0; g < 3; g++) {
                                if (board[j + startRow][g + startCol] == i) {
                                    isAllowed = false;
                                    break;
                                }
                            }
                        }
                        //check if safe to add number matrix 3x3
                        if (!isAllowed) {
                            continue;
                        }
                        board[row][col] = i;
                        if (fillBoard()) {
                            return true;
                        } else {
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }

        }
        return true;
    }

    public int getIndex(int row,int col) {
        return board[row][col];
    }
}