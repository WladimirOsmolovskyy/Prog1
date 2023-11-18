package de.osmolovskyy.wladimir.tu.tasks.memory;

import java.util.Scanner;

public class VierGewinnt {

    //  User's Tokens
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = '_';

    // Dimensions of Playground
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    // Directions for Movement (relative to set Point)
    private static final int STAY = 0;
    private static final int GO_WEST = -1;
    private static final int GO_EAST = 1;
    private static final int GO_SOUTH = -1;
    private static final int GO_NORTH = 1;

    // Coordinates of Points the youser set ( only for testing)
    private static final int R = 2;
    private static final int C = 1;

    // Counts sequency of User's Tokens
    private static int counter = 1;

    // Playground with defined dimentions
    private static char[][] field = new char[ROWS][COLUMNS];

    private static void initPlayground(){
        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                field[i][j] = EMPTY;
            }
        }
    }

    public static void main(String[] args) {
        initPlayground();
        printField(field);
        int column = makeMove();
        int row = defineRowInColumn(column);

        goLeft(R, C);
        System.out.println();
        goRight(R, C);
        System.out.println();
        if (evaluate()) return;

        goDown(R, C);
        System.out.println();
        if (evaluate()) return;

        goNorthEast(R, C);
        System.out.println();
        goSouthWest(R, C);
        System.out.println();
        if (evaluate()) return;

        goNorthWest(R, C);
        System.out.println();
        goSouthEast(R, C);
        System.out.println();
        if (evaluate()) return;

        System.out.println(counter);

    }

    private static int defineRowInColumn(int column) {
        for(int i = ROWS -1; i <= 0; i--){
            if(field[i][column] != EMPTY){
                return i;
            }
        }
    }

    private static int makeMove() {
        String str = String.format("Player1, please give the column number (0-%d): ", COLUMNS -1 );
        return readInteger(str);

    }

    private static int readInteger(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

    private static boolean evaluate() {
        if (counter == 4) {
            return true;
        } else {
            counter = 1;
        }
        return false;
    }

    static void printField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (isEmptyCell(i, j)) {
                    printEmptyCell();
                } else {
                    printFilledCell(i, j);
                }
            }
            System.out.println();
        }
        System.out.println("===============");
    }

    private static boolean isEmptyCell(int i, int j) {
        return field[i][j] == EMPTY;
    }

    private static void printEmptyCell() {
        System.out.print("[ ]");
    }

    private static void printFilledCell(int i, int j) {
        System.out.print("[" + field[i][j] + "]");
    }


    private static char[] [] insertToken(char[][] field, char token, int index) {
        char [][] copy = field;
        if (index >= 0 && index < COLUMNS) {
            for (int i = ROWS- 1; i >= 0; i--) {
                if (isEmptyCell(i, index)) {
                    field[i][index] = token;
                    return copy;
                }
            }
        } else {
            System.out.println("ERROR: Index Out Of Bounds");
            return null;
        }

    }


    /**
     * This method
     *
     * @param raw
     * @param col
     * @param v
     * @param h
     * @return
     */
    public static int goRecursive(int raw, int col, int v, int h) {
        int newRaw = raw + v;
        int newCol = col + h;

        System.out.printf("THIS FILED:[%d][%d] = [%c].", raw, col, field[raw][col]);

        if (counter == 4 || isBorderTouched(newRaw, newCol)) {
            return counter;
        }
        System.out.printf("NEXT FILED:[%d][%d] = [%c] - Current counter = %d\"\n", newRaw, newCol, field[newRaw][newCol], counter);

        if (field[newRaw][newCol] != X) {
            System.out.println("Found wrong token. < -- > Change direction");
            return counter;
        } else {
            counter++;
        }

        return goRecursive(newRaw, newCol, v, h);
    }

    private static boolean isBorderTouched(int row, int column) {
        boolean crossed = row < 0 || row >= ROWS || column < 0 || column >= COLUMNS;
        if (crossed) {
            System.out.printf("NEXT FILED:[%d][%d]  will be 'Out of Bounds'\n", row, column);
            System.out.println("Border Touched. Change Direction. Current counter=" + counter);
        }
        return crossed;
    }

    private static int goLeft(int row, int column) {
        System.out.println("Moving LEFT from:(" + row + ", " + column + ")");
        return goRecursive(row, column, STAY, GO_WEST);

    }

    private static int goRight(int row, int column) {
        System.out.println("Moving RIGHT from:(" + row + ", " + column + ")");
        return goRecursive(row, column, STAY, GO_EAST);

    }

    private static int goDown(int row, int column) {
        System.out.println("Moving DOWN from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_SOUTH, STAY);

    }

    private static int goNorthWest(int row, int column) {
        System.out.println("Moving NORTH-WEST from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_NORTH, GO_WEST);
    }

    private static int goNorthEast(int row, int column) {
        System.out.println("Moving NORTH-EAST from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_NORTH, GO_EAST);

    }


    private static int goSouthEast(int row, int column) {
        System.out.println("Moving  SOUTH-EAST from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_SOUTH, GO_EAST);

    }

    private static int goSouthWest(int row, int column) {
        System.out.println("Moving  SOUTH-WEST from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_SOUTH, GO_WEST);

    }


    private static void play() {
        // makeMove();
        // if(! hasWon())
        //togglePlayer();
    }

    // makeMove
    // readInput
    // checkMove
    // repaint

    //
}
