package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.Set;
/**
 * Implementacja klasy SudokuRow.
 */

public class SudokuRow implements SudokuVerifier {
    private SudokuField[] row;

    /**
     * Konstruktor Klasy SudokuColumn,Generuje jedną z kolumn składających się na pole sudoku.
     * @param row jeden z 9 wierszy składających się na planszę.
     */

    public SudokuRow(SudokuField[] row) {
        this.row = row;
    }
    /**
     * Funkcja verify weryfikuje poprawność uzupełnionego wiersza.
     * @return Zwraca True w przypadku poprawnego uzupełnienia wiersza,jeśli nie False.
     */

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (row[j].getValue() == 0) {
                continue;
            }
            if (check.contains(row[j].getValue())) {
                return false;
            }
            check.add(row[j].getValue());
        }
        return true;
    }
}
