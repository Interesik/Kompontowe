package pl.lodz.p.it.kompo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    String dullfilename ="";
    String filename = "C:\\Users\\Karol\\IdeaProjects\\KOMPO\\MultiModule\\model\\Testfile.txt";
    SudokuBoardDaoFactory sbdf;
    FileSudokuBoardDao fsbd;
    FileSudokuBoardDao fsbderror;
    SudokuBoard sb;
    SudokuBoard sb2;
    SudokuSolver ss = new BacktrackingSudokuSolver();
    @BeforeEach
    void setUp() {
        this.sbdf = new SudokuBoardDaoFactory();
        this.fsbd = sbdf.getFileDao(filename);
        this.fsbderror = sbdf.getFileDao(dullfilename);
        this.sb = new SudokuBoard(ss);
        this.sb2 = new SudokuBoard(ss);
        fsbd.write(this.sb);
    }
    @Test
    void read() {
        SudokuBoard sb3 = fsbd.read();
        assertEquals(sb3,sb);

        Exception exception = assertThrows(RuntimeException.class,() -> fsbderror.read());
        assertEquals(exception.getCause().getClass(), FileNotFoundException.class);
    }

    @Test
    void write() {
        fsbd.write(this.sb2);
        SudokuBoard sb4 = fsbd.read();
        assertEquals(sb4,sb2);
        Exception exception = assertThrows(RuntimeException.class,()->fsbderror.write(sb));
        assertEquals(exception.getCause().getClass(), FileNotFoundException.class);
    }
    @Test
    void close(){
        assertDoesNotThrow(()->fsbd.close());
    }
}