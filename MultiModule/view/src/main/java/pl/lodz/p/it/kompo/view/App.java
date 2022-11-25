package pl.lodz.p.it.kompo.view;

import pl.lodz.p.it.kompo.model.BacktrackingSudokuSolver;
import pl.lodz.p.it.kompo.model.SudokuBoard;


public class App {
    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        sb.solveGame();
        System.out.println(sb);

    }
}