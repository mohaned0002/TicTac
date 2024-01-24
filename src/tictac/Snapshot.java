/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

/**
 *
 * @author Jana
 */
public class Snapshot {
        private Board board;
    private char currentplayer;
    private GameStatues gameStatues;

    public Snapshot(Board board, char currentplayer, GameStatues gameStatues) {
        this.board = board;
        this.currentplayer = currentplayer;
        this.gameStatues = gameStatues;
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
