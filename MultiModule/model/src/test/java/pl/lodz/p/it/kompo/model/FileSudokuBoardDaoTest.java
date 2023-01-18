package pl.lodz.p.it.kompo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    String dullfilename ="";
    String filename = "..\\Testfile.txt";
    SudokuBoardDaoFactory sbdf;
    FileSudokuBoardDao fsbd;
    FileSudokuBoardDao fsbderror;
    SudokuBoard sb;
    SudokuBoard sb2;
    SudokuSolver ss = new BacktrackingSudokuSolver();
    @BeforeEach
    void setUp() throws SudokuException{
        this.sbdf = new SudokuBoardDaoFactory();
        this.fsbd = sbdf.getFileDao(filename);
        this.fsbderror = sbdf.getFileDao(dullfilename);
        this.sb = new SudokuBoard(ss);
        this.sb2 = new SudokuBoard(ss);
        try {
            fsbd.write(this.sb);
        } catch (SaveToFileException e) {
            throw new SudokuException(e);
        }
    }
    @Test
    void read() throws ReadFromFileException {
        SudokuBoard sb3 = fsbd.read();
        assertEquals(sb3,sb);

        Exception exception = assertThrows(ReadFromFileException.class,() -> fsbderror.read());
        assertEquals(exception.getCause().getClass(), FileNotFoundException.class);
    }

    @Test
    void write() throws ReadFromFileException, SaveToFileException {
        fsbd.write(this.sb2);
        SudokuBoard sb4 = fsbd.read();
        assertEquals(sb4,sb2);
        Exception exception = assertThrows(SaveToFileException.class,()->fsbderror.write(sb));
        assertEquals(exception.getCause().getClass(), FileNotFoundException.class);
    }
    @Test
    void close(){
        assertDoesNotThrow(()->fsbd.close());
    }
}