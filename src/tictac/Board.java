/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

import java.lang.reflect.Array;

/**
 *
 * @author moham
 */
public class Board {

    public char[][] arr = new char[3][3];

    public Board() {
    }

    public Board(Board board) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.arr[i][j] = board.getSquare(i, j);
            }
        }
    }

    public void initializBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = '-';
            }

        }

    }

    public void fillBoardX(int x, int y) {
        arr[x][y] = 'x';

    }

    public void fillBoardO(int x, int y) {
        arr[x][y] = 'o';

    }

    public char getSquare(int x, int y) {
        return arr[x][y];

    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println("");
        }

    }

}
