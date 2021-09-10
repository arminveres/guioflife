package com.arminveres;

public class GameOfLifeModel {
    boolean[][] currGenMatrix;
    boolean[][] nextGenMatrix;

    public void setCurrGenMatrix(boolean[][] currGenMatrix) {
        this.currGenMatrix = currGenMatrix;
    }

    public boolean[][] getCurrGenMatrix() {
        return currGenMatrix;
    }

    public void setNextGenMatrix(boolean[][] nextGenMatrix) {
        this.nextGenMatrix = nextGenMatrix;
    }

    public boolean[][] getNextGenMatrix() {
        return nextGenMatrix;
    }
}
