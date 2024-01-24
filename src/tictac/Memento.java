/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

/**
 *
 * @author Jana
 */
public class Memento {
    private Board board;
    private char currentplayer;
    private GameStatues gameStatues;
    int row;
    int col;
        public  Memento(Board board, GameStatues status, char currentplayer ,int i,int j) {
        this.board = board;
        this.gameStatues = status;
        this.currentplayer = currentplayer;
        this.row=i;
        this.col=j;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Board getBoard() {
        return board;
    }

    public char getCurrentplayer() {
        return currentplayer;
    }

    public GameStatues getGameStatues() {
        return gameStatues;
    }
    
}
