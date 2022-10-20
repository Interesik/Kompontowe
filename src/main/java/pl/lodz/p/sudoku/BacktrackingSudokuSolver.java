package pl.lodz.p.sudoku;

/**
 * Implementacja interfejsu SudokuSolver za pomocą Backtrakingu.
 */
public class BacktrackingSudokuSolver implements SudokuSolver {

    /**
     * Rekurecyjna próba rozwiązania sudoku.
     * @see SudokuSolver
     */
    @Override
    public boolean solve(SudokuBoard sudokuBoard) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if ((sudokuBoard.getIndex(row,col) == 0)) {
                    for (int i = 1; i <= 9; i++) {
                        boolean isAllowed = true;
                        // check if can add new board to set
                        for (int j = 0; j < 9; j++) {
                            if (sudokuBoard.getIndex(row,j) == i) {
                                isAllowed = false;
                                break;
                            }
                        }
                        // check if in row can add number
                        if (!isAllowed) {
                            continue;
                        }
                        for (int j = 0; j < 9; j++) {
                            if (sudokuBoard.getIndex(j,col) == i) {
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
                                if (sudokuBoard.getIndex(j + startRow,g + startCol) == i) {
                                    isAllowed = false;
                                    break;
                                }
                            }
                        }
                        //check if safe to add number matrix 3x3
                        if (!isAllowed) {
                            continue;
                        }
                        sudokuBoard.setIndex(row,col,i);
                        if (solve(sudokuBoard)) {
                            return true;
                        } else {
                            sudokuBoard.setIndex(row,col,0);
                        }
                    }
                    return false;
                }
            }

        }
        return true;
    }
}
