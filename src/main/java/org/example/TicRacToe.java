package org.example;

import java.util.Arrays;

public class TicRacToe {

    private static int rowLength = 3;
    private static int[][] ticTacToeField = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};


    public static int[][] getTicTacToeField() {
        return ticTacToeField;
    }

    public static void setTicTacToeField(int[][] ticTacToeField) {
        TicRacToe.ticTacToeField = ticTacToeField;
    }

    public void refreshTicTacToeField() {
        setTicTacToeField(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
    }

    private boolean checkCell(int indexX, int indexY) {
        if (ticTacToeField[indexX][indexY] == 2 && ticTacToeField[indexX][indexY] == 1) {
            return false;
        }
        return true;
    }

    public int[][] move(int indexX, int indexY, int xo) {
        if (checkCell(indexX, indexY)) {
            ticTacToeField[indexX][indexY] = xo;
        }
        return ticTacToeField;
    }

    public boolean determineWinner(int[][] ticTacToeField, int player) {
        int[][] tictactoeCopy = ticTacToeField.clone();
        for (int position = 0, axis = 0; position < tictactoeCopy.length; position++) {
            if (checkRow(createRow(tictactoeCopy, position), player)) {
                return true;
            } else if (position == 2 && axis == 0) {
                rotate90Clockwise(tictactoeCopy);
                axis++;
                position = -1;
            }
        }

        rotate90Clockwise(tictactoeCopy);
        rotate90Clockwise(tictactoeCopy);
        rotate90Clockwise(tictactoeCopy);

        if (tictactoeCopy[1][1] == player) {
            if (checkRow(createDiagonalRow(tictactoeCopy, 0), player)
                    || checkRow(createDiagonalRow(tictactoeCopy, 1), player)) {
                return true;
            }
        }
        return false;

    }

    private int[] createRow(int[][] ticTacToeField, int position) {
        return ticTacToeField[position];
    }

    private boolean checkRow(int[] row, int player) {
        if (Arrays.stream(row).filter(el -> el == player).count() == 3) {
            return true;
        }
        return false;
    }

    private int[] createDiagonalRow(int[][] ticTacToeField, int mainOrSideDiagonal) {
        int[] diagonalRow = new int[ticTacToeField.length];
        if (mainOrSideDiagonal == 1) {
            for (int i = 0; i < ticTacToeField.length; i++) {
                diagonalRow[i] = ticTacToeField[i][ticTacToeField.length - (i + 1)];
            }
        } else if (mainOrSideDiagonal == 0) {
            for (int i = 0; i < ticTacToeField.length; i++) {
                diagonalRow[i] = ticTacToeField[i][i];
            }
        }
        return diagonalRow;
    }


    private void rotate90Clockwise(int[][] ticTacToeField) {

        for (int i = 0; i < rowLength / 2; i++) {
            for (int j = i; j < rowLength - i - 1; j++) {
                int temp = ticTacToeField[i][j];
                ticTacToeField[i][j] = ticTacToeField[rowLength - 1 - j][i];
                ticTacToeField[rowLength - 1 - j][i] = ticTacToeField[rowLength - 1 - i][rowLength - 1 - j];
                ticTacToeField[rowLength - 1 - i][rowLength - 1 - j] = ticTacToeField[j][rowLength - 1 - i];
                ticTacToeField[j][rowLength - 1 - i] = temp;
            }
        }
    }
}
