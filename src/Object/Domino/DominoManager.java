package Object.Domino;

import java.awt.*;
import java.util.Random;

public class DominoManager {

    private Domino[][] board;
    private final int rows = 2;
    private final int cols = 3;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private final int tileSize;

    public DominoManager(int screenWidth, int screenHeight, int tileSize) {
        this.tileSize = tileSize;
        this.width = tileSize * 2 * cols;
        this.height = tileSize * 3 * rows;
        this.x = screenWidth / 2 - width / 2;
        this.y = screenHeight / 2 - height / 2;

        board = new Domino[rows][cols];
        rollBoard();
    }

    public void rollBoard() {
        resetBoard();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j] = createDomino(x + j * tileSize*2, y + i * tileSize* 3);
            }
        }
    }

    public void resetBoard() {
        board = new Domino[rows][cols];
    }

    public void draw(Graphics2D g) {
        if(board == null) return;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j].draw(g);
            }
        }
    }

    public Domino createDomino(int x, int y) {
        return new Domino(x, y, tileSize * 2, tileSize * 3);
    }

    public Domino createDomino(int x, int y, int upValue, int downValue) {
        return new Domino(x, y, tileSize * 2, tileSize * 3, upValue, downValue);
    }


    public Domino getDomino(int row, int col) {
        return board[row][col];
    }

    public void setDomino(int row, int col, Domino domino) {
        board[row][col] = domino;
    }

    public Domino[] getAllDominos() {
        Domino[] dominos = new Domino[rows * cols];
        int index = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                dominos[index] = board[i][j];
                index++;
            }
        }
        return dominos;
    }

    public void updateDominos() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j].update();
            }
        }
    }

    public void playEffect(Domino domino, String effect) {
        switch (effect) {
            case "shake":
                domino.playShakeEffect();
                break;
        }
    }
}
