package pl.lodz.p.it.kompo.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PostgressqlSudokuBoardDaoTest {
    SudokuBoard sb;
    SudokuBoard sb2;
    SudokuSolver ss = new BacktrackingSudokuSolver();
    SudokuBoardDaoFactory sbdf = new SudokuBoardDaoFactory();
    PostgressqlSudokuBoardDao pb = sbdf.getBaseDao("First");
    PostgressqlSudokuBoardDao pberror = sbdf.getBaseDao("Two");

    @Test
    void persistence(){
        this.sb = new SudokuBoard(ss);
        try {
            pb.write(this.sb);
        } catch (SaveToFileException e) {
            throw new SudokuException(e);
        }
        Exception exception = assertThrows(SaveToFileException.class,()->pb.write(null));
        assertEquals(exception.getCause().getClass(),NullPointerException.class);
        try {
            this.sb2 = pb.read();
            assertEquals(null,pberror.read());
        } catch (ReadFromFileException e) {
            throw new RuntimeException(e);
        }
        assertNotSame(sb,sb2);
        for(int i = 0; i < 9 ; i++){
            for(int j = 0; j < 9 ; j++){
                assertEquals(sb.getIndex(i,j), sb2.getIndex(i,j));
            }
        }
    }
}