package com.arminveres;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame { // will be the view / GUI class

    JPanel upperPart;
    JPanel grid;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    JToggleButton playPause;
    JButton resetButton;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(new BorderLayout());

        // upper part being the counters
        upperPart = new JPanel();
        upperPart.setLayout(new GridLayout(4, 1, 100, 0));

        playPause = new JToggleButton("Play / Pause");
        playPause.setName("PlayToggleButton");
        upperPart.add(playPause);

        resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        upperPart.add(resetButton);

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        GenerationLabel.setText("Generation: " + 0);

        upperPart.add(GenerationLabel);

        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText("Alive: " + 0);

        upperPart.add(AliveLabel);

        add(upperPart, BorderLayout.NORTH);

//        lower part will be added through refresh function
        grid = new JPanel();
        add(grid);
        refresh(new boolean[100][100], 0, 0);
        setVisible(true);
    }

    public void refresh(boolean[][] boolMatrix, int genNumber, int aliveNumber) {

        remove(this.grid);
        this.grid = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                for (int i = 0; i < boolMatrix.length; i++) {
                    for (int j = 0; j < boolMatrix.length; j++) {
                        g.drawRect(i * 10, j * 10, 10, 10);
                        if (boolMatrix[i][j]) {
                            g.fillRect(i * 10, j * 10, 10, 10);
                        }
                    }
                }
            }
        };
        add(this.grid, BorderLayout.CENTER);
        GenerationLabel.setText("Generation: " + genNumber);
        AliveLabel.setText("Alive: " + aliveNumber);

        setVisible(true);

    }
}
