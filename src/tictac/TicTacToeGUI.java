/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;

/**
 *
 * @author Jana
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Stack;
import static tictac.Game.getinstance;

public class TicTacToeGUI extends JFrame {

    private Game game;
    private JButton[][] buttons;
    private JButton undobutton;
    private JButton snapshotbutton;
    private JButton snapshotrestbutton;
    Stack<Memento> stack = new Stack<Memento>();
    Stack<Snapshot> spStack = new Stack<Snapshot>();

    public Memento CreateMemento(int x, int y) {
        System.out.println("Memento Created");
        return new Memento(new Board(game.board), game.gameStatues, game.currentPlayer, x, y);
    }

    public Snapshot Createsnapshot() {
        System.out.println("Memento Created");
        return new Snapshot(new Board(game.board), game.currentPlayer, game.gameStatues);
    }

    public void setPreviousm(Memento m) {
        game.board = m.getBoard();
        game.currentPlayer = m.getCurrentplayer();
        game.gameStatues = m.getGameStatues();
        buttons[m.getRow()][m.getCol()].setEnabled(true);
    }

    public void undo() {

        try {
            Memento m = stack.pop();
            setPreviousm(m);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String s = String.valueOf(m.getBoard().getSquare(i, j));
                    if (s.contains("-")) {
                        buttons[i][j].setText(null);
                    } else {
                        buttons[i][j].setText(s.toUpperCase());
                    }
                }
            }
            System.out.println("Memento restorted ");
        } catch (EmptyStackException e) {

        }

    }

    public void setPreviouss(Snapshot s) {

        game.board = s.getBoard();
        game.currentPlayer = s.getCurrentplayer();
        game.gameStatues = s.getGameStatues();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(game.board.getSquare(i, j)=='X' ||game.board.getSquare(i, j)=='O')) {
                    buttons[i][j].setEnabled(true);
                }
            }
        }

    }

    public void restore() {
        try {
            Snapshot s1 = spStack.pop();
            setPreviouss(s1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String s = String.valueOf(s1.getBoard().getSquare(i, j));
                    if (s.contains("-")) {
                        buttons[i][j].setText(null);
                    } else {
                        buttons[i][j].setText(s.toUpperCase());
                    }
                }
            }
            System.out.println("Memento restorted ");
        } catch (EmptyStackException e) {

        }

    }

    public TicTacToeGUI() {
        super("Tic Tac Toe");
        game = getinstance();
        buttons = new JButton[3][3];
        undobutton = new JButton();
        snapshotbutton = new JButton();
        snapshotrestbutton = new JButton();
        setLayout(new GridLayout(4, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = createButton(i, j);
                add(buttons[i][j]);
            }
        }
        undobutton = Createundobutton();
        add(undobutton);
        snapshotbutton = Createsnapshotbutton();
        add(snapshotbutton);
        snapshotrestbutton = Createsnapshotrestbutton();
        add(snapshotrestbutton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton Createsnapshotrestbutton() {
        JButton button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("C:\\Users\\RH\\Documents\\NetBeansProjects\\TicTac\\src\\tictac\\rs.png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restore();
            }
        });
        return button;
    }

    private JButton Createundobutton() {
        JButton button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("C:\\Users\\RH\\Documents\\NetBeansProjects\\TicTac\\src\\tictac\\60690.png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();

            }
        });
        return button;

    }

    private JButton Createsnapshotbutton() {
        JButton button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("C:\\Users\\RH\\Documents\\NetBeansProjects\\TicTac\\src\\tictac\\snp.png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spStack.push(Createsnapshot());

            }
        });
        return button;

    }

    private JButton createButton(int row, int col) {
        JButton button = new JButton("");
        button.setFont(button.getFont().deriveFont(24.0f));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stack.push(CreateMemento(row, col));
                handleButtonClick(button, row, col);
                System.out.println(game.getCurrentPlayer());
                game.switchPlayer();

            }
        });
        return button;
    }

    private void handleButtonClick(JButton button, int row, int col) {

        if (game.getCurrentPlayer() == 'x') {
            button.setText("X");
            button.setEnabled(false);
            game.board.fillBoardX(row, col);
            game.board.printBoard();
            if (game.checkWinner() != ' ' || game.checkDraw()) {
                handleGameEnd();
            }
        } else if (game.getCurrentPlayer() == 'o') {
            button.setText("O");
            button.setEnabled(false);
            game.board.fillBoardO(row, col);
            game.board.printBoard();
            if (game.checkWinner() != ' ' || game.checkDraw()) {
                handleGameEnd();
            }
        }
    }

    private void handleGameEnd() {
        char result = game.checkGameResult();
        if (result == 'x' || result == 'o') {
            JOptionPane.showMessageDialog(this, "Player " + result + " wins!");
            resetGame();
        } else if (game.checkDraw()) {
            JOptionPane.showMessageDialog(this, "Draw");
            resetGame();
        }
    }

    private void resetGame() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            game.end();
            game = getinstance();

            resetButtons();
        } else {
            System.exit(0);
        }
    }

    private void resetButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                game.setCurrentPlayer('o');
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();

    }
}
