package pl.lodz.p.it.kompo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgressqlSudokuBoardDaoTest {
    SudokuBoard sb;
    SudokuBoard sb2;
    SudokuSolver ss = new BacktrackingSudokuSolver();
    PostgressqlSudokuBoardDao pb = new PostgressqlSudokuBoardDao("First");

    @Test
    void write() {
        this.sb = new SudokuBoard(ss);
        this.sb2 = new SudokuBoard(ss);
        try {
            pb.write(this.sb);
        } catch (SaveToFileException e) {
            throw new SudokuException(e);
        }
    }
}