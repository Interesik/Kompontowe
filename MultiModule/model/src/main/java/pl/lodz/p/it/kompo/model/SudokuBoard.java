package pl.lodz.p.it.kompo.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity(name = "Board")
public class SudokuBoard implements Serializable,Cloneable {
    @Transient
    private Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    @OneToMany(
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[81]);
    @Transient
    private List<SudokuVerifier> lisners = new ArrayList<>();

    @Transient
    private SudokuSolver solver;

    @Id
    @Column(name = "PR_KEY", unique = true)
    private String prKey;


    public String getPrKey() {
        return prKey;
    }

    public void setPrKey(String prKey) {
        this.prKey = prKey;
    }

    public SudokuBoard(SudokuSolver resolver) {
        for (int i = 0; i < 81; i++) {
            sudokuFields.set(i, new SudokuField());
        }
        logger.debug("Created empty board");
        this.solver = resolver;
        logger.debug("Created solver instance");
        // random generate starting board
        for (int r = 0; r < 9; r++) {
            sudokuFields.get(r).setValue(r);
        }
        Collections.shuffle(sudokuFields);
        logger.debug("Inserts random values");
        this.solveGame();
        logger.debug("Solving board");
        for (int i = 0; i < 9; i++) {
            lisners.add(getRow(i));
            lisners.add(getColumn(i));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lisners.add(getBox(i, j));
            }
        }
    }

    protected SudokuBoard() {

    }

    public boolean solveGame() {
        return solver.solve(this);
    }

    public boolean checkBoard() {
        for (SudokuVerifier lis : lisners) {
            if (!lis.verify()) {
                return false;
            }
        }
        return true;
    }

    public int getIndex(int row, int col) {
        return sudokuFields.get(row * 9 + col).getValue();
    }

    public SudokuField getSudokuField(int row, int col) {
        return sudokuFields.get(row * 9 + col);
    }
    // For binding in javaFX, because i have to access class address to bind.

    public void setIndex(int row, int col, int number) {
        sudokuFields.get(row * 9 + col).setValue(number);
        // on every value change checkBoard
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> serializeSubList = new ArrayList<>(9);
        serializeSubList.addAll(sudokuFields.subList(row * 9, row * 9 + 9));
        return new SudokuRow(serializeSubList);
    }

    public SudokuColumn getColumn(int col) {
        List<SudokuField> column = new ArrayList<>(9);
        for (int row = 0; row < 9; row++) {
            column.add(sudokuFields.get(col + (row * 9)));
        }
        return new SudokuColumn(column);
    }

    public void removeRandom(int amount) {
        for (int i = 0; i < amount;) {
            int rowRandom = new Random().nextInt(9);
            int colRandom = new Random().nextInt(9);
            if (getIndex(rowRandom,colRandom) != 0) {
                setIndex(rowRandom, colRandom, 0);
                i++;
            }
        }
        logger.debug("Removed random {} numbers",amount);
    }

    public SudokuBox getBox(int row, int col) {
        List<SudokuField> box = new ArrayList<>(9);
        List<SudokuField> serializeSubList = new ArrayList<>(9);
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int j = 0; j < 3; j++) {
            serializeSubList.addAll((sudokuFields.subList(startCol + (9 * startRow) + (9 * j),
                    startCol + 3 + (9 * startRow) + (9 * j))));
        }
        box.addAll(serializeSubList);
        return new SudokuBox(box);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return new EqualsBuilder()
                .append(sudokuFields, that.sudokuFields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuFields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("sudokuFields", sudokuFields)
                .toString();
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard clone = new SudokuBoard(this.solver);
        List<SudokuField> cloneSudokuFields = Arrays.asList(new SudokuField[81]);
        List<SudokuVerifier> cloneLisners = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cloneLisners.add(this.getRow(i).clone());
            cloneLisners.add(this.getColumn(i).clone());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cloneLisners.add(this.getBox(i, j).clone());
            }
        }
        for (int i = 0; i < 81; i++) {
            cloneSudokuFields.set(i, this.sudokuFields.get(i).clone());
        }
        clone.lisners = cloneLisners;
        clone.sudokuFields = cloneSudokuFields;
        return clone;
    }
}