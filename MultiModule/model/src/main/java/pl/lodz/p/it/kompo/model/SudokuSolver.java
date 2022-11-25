package pl.lodz.p.it.kompo.model;


import java.io.Serializable;

public interface SudokuSolver extends Serializable,Cloneable {

    boolean solve(SudokuBoard sudokuBoard);
}
