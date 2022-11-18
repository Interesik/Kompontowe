package pl.lodz.p.sudoku;

import java.io.Serializable;

/**
 * @ Interfejs SudokuVerifier.
 */

public interface SudokuVerifier extends Serializable {
    /**
     * Funkcja verify weryfikuje poprawność uzupełnionego boxu/rzędu/kolumny.
     * @return Zwraca True w przypadku poprawnego uzupełnienia,w przeciwnym wypadku zwraca False.
     */
    boolean verify();
}
