public class Sudoku {
    public static void displayBoard(int[][] board) {
        System.out.println("==========================");
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                for (int j = 0; j < 12; ++j) {
                    System.out.print("- ");
                }
                System.out.println();
            }
        }
        System.out.println("==========================");
    }

    public static int[] findNextFreeSpot(int[][] board) {
        int spot[] = { -1, -1 };
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == 0) {
                    spot[0] = i;
                    spot[1] = j;
                    return spot;
                }
            }
        }
        return spot;
    }

    public static boolean isValid(int[][] board, int guess, int row, int column) {
        for (int i = 0; i < 9; ++i) {
            if (board[i][column] == guess || board[row][i] == guess) {
                return false;
            }
        }
        int row_start = (row / 3) * 3;
        int column_start = (column / 3) * 3;
        for (int i = row_start; i < row_start + 3; ++i) {
            for (int j = column_start; j < column_start + 3; ++j) {
                if (board[i][j] == guess) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        int nextSpot[] = findNextFreeSpot(board);
        int row = nextSpot[0], column = nextSpot[1];
        if (row < 0) {
            return true;
        }
        for (int guess = 1; guess < 10; ++guess) {
            if (isValid(board, guess, row, column)) {
                board[row][column] = guess;
                if (solveSudoku(board)) {
                    return true;
                }
            }
            board[row][column] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        int board[][] = {
            {7, 8, 0, 4, 0, 0, 1, 2, 0},
            {6, 0, 0, 0, 7, 5, 0, 0, 9},
            {0, 0, 0, 6, 0, 1, 0, 7, 8},
            {0, 0, 7, 0, 4, 0, 2, 6, 0},
            {0, 0, 1, 0, 5, 0, 9, 3, 0},
            {9, 0, 4, 0, 6, 0, 0, 0, 5},
            {0, 7, 0, 3, 0, 0, 0, 1, 2},
            {1, 2, 0, 0, 0, 7, 4, 0, 0},
            {0, 4, 9, 2, 0, 6, 0, 0, 7},
        };
        if (solveSudoku(board)) {
            displayBoard(board);
        } else {
            System.out.println("Sodoku board not solvable.");
        }
    }
}
