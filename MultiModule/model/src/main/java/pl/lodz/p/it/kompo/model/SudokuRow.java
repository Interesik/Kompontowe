package pl.lodz.p.it.kompo.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



/**
 * Implementacja klasy SudokuRow.
 */

public class SudokuRow implements SudokuVerifier {
    private List<SudokuField> row;

    /**
     * Konstruktor Klasy SudokuColumn,Generuje jedną z kolumn składających się na pole sudoku.
     * @param row jeden z 9 wierszy składających się na planszę.
     */

    public SudokuRow(List<SudokuField> row) {
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
            if (row.get(j).getValue() == 0) {
                continue;
            }
            if (check.contains(row.get(j).getValue())) {
                return false;
            }
            check.add(row.get(j).getValue());
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuRow sudokuRow = (SudokuRow) o;

        return new EqualsBuilder()
                .append(row, sudokuRow.row)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(row)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,SHORT_PREFIX_STYLE)
                .append("row", row)
                .toString();
    }
}
