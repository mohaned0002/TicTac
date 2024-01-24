package tictac;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entery extends JFrame {

    private BufferedImage backgroundImage;

    public Entery() {
        setTitle("Tic Tac Toe Game");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));
        pack();
        setLocationRelativeTo(null);
        setupGUI();
    }

    private void initComponents() {
        jPanel1 = new BackgroundPanel();
        jButton2 = createButton("Multiplayer");
        jButton1 = createButton("Single Player");
        label1 = new JLabel();

        jPanel1.setToolTipText("TicTacGame");

       

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        jPanel1.add(label1, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton2);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        jPanel1.add(buttonPanel, gbc);

        add(jPanel1, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(this::buttonActionPerformed);
        return button;
    }

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        if (source == jButton1) {
            
            FrontGuiSingle f = new FrontGuiSingle();
            Entery.super.setVisible(false);
         
        } else if (source == jButton2) {
               FrontGuiMulti f = new FrontGuiMulti();
            Entery.super.setVisible(false);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Entery().setVisible(true));
    }

    private JPanel jPanel1;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel label1;

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private void setupGUI() {
        loadBackgroundImage();
        initComponents();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("p.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
