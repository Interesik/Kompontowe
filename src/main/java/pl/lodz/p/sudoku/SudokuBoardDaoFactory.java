package pl.lodz.p.sudoku;

public class SudokuBoardDaoFactory {
    public FileSudokuBoardDao getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
