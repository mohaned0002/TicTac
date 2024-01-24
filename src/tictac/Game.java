/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author moham
 */
public class Game {

    private static Game instance = null;
    char currentPlayer;
    GameStatues gameStatues = GameStatues.INPROGRESS;
    Board board = new Board();
    //Stack<Memento> stack = new Stack<Memento>();

    private Game() {
        this.currentPlayer = 'x';
        board.initializBoard();

    }

    /*public Memento CreateMemento() {
        return new Memento(new Board(this.board), this.gameStatues, this.currentPlayer);
    }

    public void setPrevious(Memento m) {
        this.board = m.getBoard();
        this.currentPlayer = m.getCurrentplayer();
        this.gameStatues = m.getGameStatues();
    }

    public void undo() {

        try {

            setPrevious(stack.pop());
        } catch (EmptyStackException e) {

        }

    }*/

    public synchronized static Game getinstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void end() {
        instance = null;
    }

    /**
     * @return the currentPlayer
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {

        setCurrentPlayer((getCurrentPlayer() == 'x') ? 'o' : 'x');
    }

    public GameSymbol checkWinner() {

        for (int row = 0; row < 3; row++) {
            if (board.arr[row][0].getSymbol() != '-' && board.arr[row][0] == board.arr[row][1] && board.arr[row][1] == board.arr[row][2]) {
                return board.arr[row][0];
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board.arr[0][col].getSymbol() != '-' && board.arr[0][col] == board.arr[1][col] && board.arr[1][col] == board.arr[2][col]) {
                return board.arr[0][col];
            }
        }

        if (board.arr[0][0].getSymbol() != '-' && board.arr[0][0] == board.arr[1][1] && board.arr[1][1] == board.arr[2][2]) {
            return board.arr[0][0];
        }

        if (board.arr[0][2].getSymbol() != '-' && board.arr[0][2] == board.arr[1][1] && board.arr[1][1] == board.arr[2][0]) {
            return board.arr[0][2];
        }
        return null;
    }

    public boolean checkDraw() {
        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {
                if (board.arr[row][col].getSymbol() == '-') {
                    return false;
                }
            }
        }
        gameStatues = GameStatues.DRAW;
        return true;
    }

    public GameSymbol checkGameResult() {
        GameSymbol winner = checkWinner();

        if (winner.getSymbol() != ' ') {

            if (winner.getSymbol() == 'x') {
                gameStatues = GameStatues.XWON;
            } else if (winner.getSymbol() == 'o') {
                gameStatues = GameStatues.OWON;
            }
            return winner;

        } else {
            return null;
        }
    }

}
