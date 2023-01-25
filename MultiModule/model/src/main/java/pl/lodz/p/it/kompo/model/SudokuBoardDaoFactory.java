package pl.lodz.p.it.kompo.model;

public class SudokuBoardDaoFactory {
    public FileSudokuBoardDao getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public PostgressqlSudokuBoardDao getBaseDao(String filename) {
        return new PostgressqlSudokuBoardDao(filename);
    }
}
