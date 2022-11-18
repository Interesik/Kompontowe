package pl.lodz.p.sudoku;


import java.io.Serializable;

public interface SudokuSolver extends Serializable {

    boolean solve(SudokuBoard sudokuBoard);
}
