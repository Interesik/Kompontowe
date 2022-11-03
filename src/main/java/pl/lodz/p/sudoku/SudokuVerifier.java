package pl.lodz.p.sudoku;

/**
 * @ Interfejs SudokuVerifier.
 */

public interface SudokuVerifier {
    /**
     * Funkcja verify weryfikuje poprawność uzupełnionego boxu/rzędu/kolumny.
     * @return Zwraca True w przypadku poprawnego uzupełnienia,w przeciwnym wypadku zwraca False.
     */
    boolean verify();
}
