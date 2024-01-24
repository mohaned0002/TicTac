

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

public class FrontGuiSingle extends JFrame {

    private Game game;
    private JButton[][] buttons;
    private JButton undobutton;
    private JButton snapshotbutton;
    private JButton snapshotrestbutton;
    Stack<Memento> stack = new Stack<Memento>();
    Stack<Memento> stackc = new Stack<Memento>();
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
            Memento m;
            m = stackc.pop();
            setPreviousm(m);
            buttons[m.getRow()][m.getCol()].setEnabled(true);
            int x = m.getRow();
            int y = m.getCol();
//            buttons[m.getRow()][m.getCol()].setText(null);
            m = stack.pop();
            setPreviousm(m);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String s = String.valueOf(m.getBoard().getSquare(i, j).getSymbol());
                    if (s.contains("-")) {
                        buttons[i][j].setText(null);

                    } else {
                        buttons[i][j].setText(s.toUpperCase());
                    }
                }
            }
            buttons[x][y].setText(null);

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
                if (!(game.board.getSquare(i, j).getSymbol() == 'x' || game.board.getSquare(i, j).getSymbol() == 'o')) {
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
                    String s = String.valueOf(s1.getBoard().getSquare(i, j).getSymbol());
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

    public FrontGuiSingle() {

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
        System.out.println(game.randomY());
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
        button.setIcon(new javax.swing.ImageIcon("rs.png"));
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
        button.setIcon(new javax.swing.ImageIcon("60690.png"));
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
        button.setIcon(new javax.swing.ImageIcon("snp.png"));
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
                handleButtonClick2();

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
            if (game.checkWinner() != null || game.checkDraw()) {
                handleGameEnd();
            }
        }
    }

    private void handleButtonClick2() {
        if (game.getCurrentPlayer() == 'o') {
            int x, y;

            do {
                x = game.randomX();
                y = game.randomY();
            } while (buttons[x][y].getText() != null && (buttons[x][y].getText().equals("X") || buttons[x][y].getText().equals("O")));
            stackc.push(CreateMemento(x, y));

            buttons[x][y].setText("O");
            buttons[x][y].setEnabled(false);

            game.board.fillBoardO(x, y);
            game.board.printBoard();

            if (game.checkWinner() != null || game.checkDraw()) {
                handleGameEnd();
            }

            game.switchPlayer();
        }
    }

    private void handleGameEnd() {
        try {
            GameSymbol result = game.checkGameResult();
            if (result.getSymbol() == 'x' || result.getSymbol() == 'o') {
                JOptionPane.showMessageDialog(this, "Player " + result.getSymbol() + " wins!");
                resetGame();
            }
        } catch (NullPointerException e) {
        }
        if (game.checkDraw()) {
            JOptionPane.showMessageDialog(this, "Draw");
            resetGame();
        }
    }

    private void resetGame() {
        Object[] options = {"Undo", "Restart", "Snapshot", "Exit"};
        int choice = JOptionPane.showOptionDialog(this, "Game Over. What would you like to do?", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Exit");

        if (choice == JOptionPane.YES_OPTION) {
            undo();
            game.switchPlayer();
        } else if (choice == JOptionPane.NO_OPTION) {
            game.end();
            game = getinstance();
            resetButtons();
        } else if (choice == options.length - 2) {
            restore();

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

}


