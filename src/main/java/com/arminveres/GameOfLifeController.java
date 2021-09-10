package com.arminveres;

import java.util.Random;

public class GameOfLifeController {
    GameOfLifeModel model;
    GameOfLife view;
    int gen;
    int mapSize;
    boolean playToggle;
    boolean reset;

    public GameOfLifeController(GameOfLifeModel model, GameOfLife view) {
        this.model = model;
        this.view = view;
        this.gen = 0;
        this.mapSize = 100;
        this.model.currGenMatrix = new boolean[mapSize][mapSize];
        randomizeCurr();
        this.model.nextGenMatrix = new boolean[mapSize][mapSize];
        this.playToggle = false;
        this.reset = false;

        view.playPause.addActionListener(actionEvent -> playToggle = !playToggle);

        view.resetButton.addActionListener(actionEvent -> reset = true);
    }

    public void setModelCurr(boolean[][] currGenMatrix) {
        model.setCurrGenMatrix(currGenMatrix);
    }

    public boolean[][] getModelCurr() {
        return model.getCurrGenMatrix();
    }

    public void setModelNext(boolean[][] nextGenMatrix) {
        model.setNextGenMatrix(nextGenMatrix);
    }

    public boolean[][] getModelNext() {
        return model.getNextGenMatrix();
    }

    void gameStart() {

        int aliveCells = countAliveCells(model.currGenMatrix);

        while (aliveCells > 0 && playToggle==true && reset==false) {

            view.refresh(model.currGenMatrix, gen, aliveCells);
            gen++;
            aliveCells = countAliveCells(model.currGenMatrix);

            checkCells(model.currGenMatrix, model.nextGenMatrix);
            model.currGenMatrix = model.nextGenMatrix;
            model.nextGenMatrix = new boolean[mapSize][mapSize];

            try {
                Thread.sleep(100L);
            } catch (InterruptedException ignored) {}
        }

    }

    public void updateView() {
        while (true) {

            if (reset==true) {
                reset();
            }
            if (playToggle==true && reset==false) {
                gameStart();
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ignored) {}
        }
    }

    void reset() {
        gen = 0;
        model.currGenMatrix = new boolean[mapSize][mapSize];
        randomizeCurr();
        model.nextGenMatrix = new boolean[mapSize][mapSize];
        reset = false;
        view.refresh(model.nextGenMatrix, gen, 0);
        gameStart();
    }

    int countAliveCells(boolean[][] matrix) {
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void checkCells(boolean[][] currGen, boolean[][] nextGen) {
        int len = currGen.length;
//        first two loops go through each cell
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int cnt = 0;
//                    going through the neighbours
                for (int row = i - 1; row <= i + 1; row++) {
                    for (int column = j - 1; column <= j + 1; column++) {
                        if (row == i && column == j) {
                            continue;
                        }
                        if (row >= len && column >= len || row < 0 && column < 0 ||
                                row >= len && column < 0 || column >= len && row < 0) {
                            if (currGen[Math.floorMod(row, len)][Math.floorMod(column, len)]) {
                                cnt++;
                            }
                        } else if (row < 0 || row >= len) {
                            if (currGen[Math.floorMod(row, len)][column]) {
                                cnt++;
                            }
                        } else if (column < 0 || column >= len) {
                            if (currGen[row][Math.floorMod(column, len)]) {
                                cnt++;
                            }
                        } else if (currGen[row][column]) {
                            cnt++;
                        }
                    }
                }
//                    set nextGen
//                    living cell
                if (currGen[i][j]) {
                    if (cnt == 3 || cnt == 2) {
                        nextGen[i][j] = true;
                    } else {
                        nextGen[i][j] = false;
                    }
//                     dead cell
                } else {
                    if (cnt == 3) {
                        nextGen[i][j] = true;
                    }
                }
            }
        }
    }

    void randomizeCurr() {
        Random random = new Random();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                model.currGenMatrix[i][j] = random.nextBoolean();
            }
        }
    }

}
