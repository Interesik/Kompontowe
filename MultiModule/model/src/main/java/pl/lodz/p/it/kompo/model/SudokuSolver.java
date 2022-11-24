package pl.lodz.p.it.kompo.model;


import java.io.Serializable;

public interface SudokuSolver extends Serializable {

    boolean solve(SudokuBoard sudokuBoard);
}
