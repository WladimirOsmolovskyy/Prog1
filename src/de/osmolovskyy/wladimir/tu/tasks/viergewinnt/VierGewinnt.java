package de.osmolovskyy.wladimir.tu.tasks.viergewinnt;

import java.util.Random;
import java.util.Scanner;

public class VierGewinnt {

    //      User's Tokens
    //=========================================================================
    private static final char TOKEN_X = 'X';
    private static final char TOKEN_0 = 'O';
    private static final char EMPTY = '_';
    private static char CURRENT_TOKEN = TOKEN_X;

    //
    //      Dimensions of Playground
    //=========================================================================
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    //      Directions for Movement (relative to set Point)
    //=========================================================================
    private static final int STAY = 0;
    private static final int GO_WEST = -1;
    private static final int GO_EAST = 1;
    private static final int GO_SOUTH = 1;
    private static final int GO_NORTH = -1;

    //=========================================================================
    private static boolean PLAYER_1_WON = false;
    private static boolean COMPUTER_WON = false;

    //=========================================================================

    // Counts sequency of User's Tokens
    private static int counter = 1;
    private static boolean DEBUG = false;

    // Playground with defined dimensions
    private static char[][] field = new char[ROWS][COLUMNS];

    //=========================================================================
    private static char[][] newFieldState = null;


    public static void main(String[] args) {
        do {
            initPlayground();
            printField(field);

            while (!isGameOver()) {
                field = makePlayer1Move();
                printField(field);
                if (PLAYER_1_WON) {
                    break;
                }

                field = makeComputedMove();
                printField(field);
            }

            System.out.println(" =============== GAME OVER ===============");

            if (PLAYER_1_WON) {
                System.out.println("WINNER IS: PLAYER 1");
            }
            if (COMPUTER_WON) {
                System.out.println("WINNER IS: COMPUTER");
            }
            if (noFreeFieldsLeft()) {
                System.out.println("NO FREE FIELDS LEFT: DRAW");
            }


            reset();
        } while (readInteger("1: for Replay \n 0: for Exit") == 1);
    }

    /**
     * @return true if one of the player has won, or no empty cells are left
     */
    public static boolean isGameOver() {
        return PLAYER_1_WON || COMPUTER_WON || noFreeFieldsLeft();
    }

    /**
     * @return true if no empty cells are left on the field
     */
    public static boolean noFreeFieldsLeft() {
        for (int i = 0; i < COLUMNS; i++) {
            if (field[0][i] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method resets the game state, after the game is over, in order to start the new game
     */
    private static void reset() {
        PLAYER_1_WON = false;
        COMPUTER_WON = false;
    }


    /**
     * Initializes the playground of given dimension with empty field signs
     */
    private static void initPlayground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                field[i][j] = EMPTY;
            }
        }
    }

    /**
     * Print out the playground
     *
     * @param field - Array in which game state is collected
     */
    private static void printField(char[][] field) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (isEmptyCell(i, j)) {
                    printEmptyCell();
                } else {
                    printFilledCell(i, j);
                }
            }
            System.out.println();
        }
        for (int i = 0; i < COLUMNS; i++) {
            System.out.print("[" + i + "]");
        }
        System.out.println();
    }

    /** Checks if the cell with cordinates  [row,col] is empty
     *
     * @param row
     * @param col
     * @return true  - if empty
     */
    private static boolean isEmptyCell(int row, int col) {
        return field[row][col] == EMPTY;
    }

    private static void printEmptyCell() {
        System.out.print("[_]");
    }

    private static void printFilledCell(int i, int j) {
        System.out.print("[" + field[i][j] + "]");
    }

    /** Reads the user's input from command line and inserts user's token into the column user selected
     * The input request will be repeated, until the user gives the correct number [ 0 - COLUMNS)
     * It also checks, if the user has won by making this move
     *
     * @return -  Array with new state of the game
     */
    public static char[][] makePlayer1Move() {
        int givenColumn;
        int nextFeeRow;

        do {
            givenColumn = makeMove();
            nextFeeRow = defineFirstFreeRowInColumn(givenColumn);
            newFieldState = insertToken(field, TOKEN_X, givenColumn);
        } while (isRepeatOfInputNeeded(newFieldState));

        PLAYER_1_WON = checkInputForVictory(nextFeeRow, givenColumn, TOKEN_X);
        return newFieldState;
    }


    /** Request the user to make his input
     *
     * @return int - users input
     */
    private static int makeMove() {
        String str = String.format("Player1, please give the column number (0-%d): ", COLUMNS - 1);
        return readInteger(str);
    }

    /** Reads user's input from command line.
     * If input is outside defined range, the request will be repeated
     *
     * @param s - Text which will be printed out, to request the user to meke the input
     * @return int - User's input as int
     */
    private static int readInteger(String s) {
        int input;
        do {
            System.out.println(s);
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
        } while (input < 0 || input > COLUMNS - 1);

        return input;
    }

    /**
     * Defines first free row for the given column
     *
     * @param column - which column we want to place the token
     * @return - index of the row the token can be placed<br>
     *           -1  - if column is already completely filled
     */
    private static int defineFirstFreeRowInColumn(int column) {
        for (int i = 0; i < ROWS; i++) {
            if (field[i][column] != EMPTY) {
                return i - 1;
            }
        }
        return ROWS - 1;
    }


    /**
     * Inserts user's token into the given column
     *
     * @param field - playground
     * @param token - X or O  as user token
     * @param index - column number, where the token will be placed
     * @return the copy of playground with new token or null, if the given column is already full
     */
    private static char[][] insertToken(char[][] field, char token, int index) {
        char[][] copy = field;
        if (index >= 0 && index < COLUMNS) {
            for (int i = ROWS - 1; i >= 0; i--) {
                if (isEmptyCell(i, index)) {
                    field[i][index] = token;
                    counter = 1;
                    return copy;
                }
            }
        } else {
            System.out.println("ERROR: Index Out Of Bounds");
        }
        return null;
    }


    /**
     * This method checks, if the victory will be achieved, if the token will be placed into the particular cell
     * It goes into different directions, increasing/ reseting the counter and after that evaluetes the counter
     *
     * @param row - starting row
     * @param col - starting column
     * @param token - for particular token
     * @return - true, if counter is 4
     *  or false - if every direction was checked, but counter is still less than 4
     */
    private static boolean checkInputForVictory(int row, int col, char token) {
        CURRENT_TOKEN = token;

        checkWestToEastDirection(row, col);
        if (evaluate()) return true;

        goSouth(row, col);
        print();
        if (evaluate()) return true;

        checkNorthEastToSouthWest(row, col);
        if (evaluate()) return true;

        checkNorthWestToSouthEast(row, col);
        if (evaluate()) return true;

        print(counter + "");
        return false;
    }




    /**
     * This method checks recursively, how long is a sequency of the same tokens.
     * It starts from the cell defining with row and col parameter and goes into direction you can define using
     * v and h parameters.
     * It returns the length of sequence from the cell to the end of playground
     *
     * @param raw - row in which the token is placed
     * @param col - column in which the token is places
     * @param v   - offset in vertical dimension
     * @param h   - offset in horizontal dimension
     * @return - counter, how big is a line of tokens from starting cell to the end of field or other token
     */
    public static int goRecursive(int raw, int col, int v, int h) {
        int newRaw = raw + v;
        int newCol = col + h;

        if (DEBUG) {
            System.out.printf("THIS FILED:[%d][%d] = [%c].", raw, col, field[raw][col]);
        }

        if (counter == 4 || isBorderTouched(newRaw, newCol)) {
            return counter;
        }
        if (DEBUG) {
            System.out.printf("NEXT FILED:[%d][%d] = [%c] - Current counter = %d\"\n", newRaw, newCol, field[newRaw][newCol], counter);
        }

        if (field[newRaw][newCol] != CURRENT_TOKEN) {
            print("Found wrong token. < -- > Change direction");
            return counter;
        } else {
            counter++;
        }

        return goRecursive(newRaw, newCol, v, h);
    }


    /**  Method checks if the index of row and col will be out of bounds
     *
     * @param row -
     * @param column
     * @return true - if the index is out of bounds
     */
    private static boolean isBorderTouched(int row, int column) {
        boolean touched = row < 0 || row >= ROWS || column < 0 || column >= COLUMNS;
        if (touched) {
            if (DEBUG) System.out.printf("NEXT FILED:[%d][%d]  will be 'Out of Bounds'\n", row, column);
            print("Border Touched. Change Direction. Current counter=" + counter);
        }
        return touched;
    }



    private static void checkNorthWestToSouthEast(int R, int C) {
        goNorthWest(R, C);
        print();
        goSouthEast(R, C);
        print();
    }

    private static void checkNorthEastToSouthWest(int R, int C) {
        goNorthEast(R, C);
        print();
        goSouthWest(R, C);
        print();
    }

    private static void checkWestToEastDirection(int R, int C) {
        goWest(R, C);
        print();
        goEast(R, C);
        print();
    }

    /** Evaluates the counter, and resets it, if it not equals to 4
     *
     * @return true if counter is 4
     *
     */
    private static boolean evaluate() {
        if (counter == 4) {
            return true;
        } else {
            counter = 1;
        }
        return false;
    }

    /** Checks if the given field is null.
     *
     * @param newFieldState - Array representing new state of the game
     * @return true if input is null and game state cannot be set.
     */
    private static boolean isRepeatOfInputNeeded(char[][] newFieldState) {
        if (newFieldState == null) {
            System.out.println("This column seems already to be filled. Try another one");
            return true;
        }
        return false;
    }


    /** Method checks, if the victory is possible by setting given token in one of the columns.
     * It returns the index of column, so that if the token is  placed into this column the victory can be achieved
     *
     * @param token - that might to be set
     * @return - column index for possible victory
     */
    private static int isVictoryPossibleForToken(char token) {
        for (int i = 0; i < COLUMNS; i++) {
            int col = i;
            int row = defineFirstFreeRowInColumn(col);
            if (checkInputForVictory(row, col, token)) {
                return col;
            }
        }
        return -1;
    }


    /** Calculates the best move for computer.
     *
     * It checks firstly, whether the victory can be achieved for 'O' Token
     *  - if true, than the token will be placed there.
     *  If not - it calculates if the partner can win in his next step, using 'X' token
     *  - if true - than computer occupies this field, to avoid  the losing.
     *
     *  If neither win nor lose is possible,  random move will be made
     *
     * @return -  new state of the game
     */
    private static char[][] makeComputedMove() {

        System.out.println("===========> COMPUTER MOVE: ===============");

        int winningColumn = isVictoryPossibleForToken(TOKEN_0);
        if (winningColumn != -1) {
            COMPUTER_WON = true;
            return insertToken(field, TOKEN_0, winningColumn);
        }

        winningColumn = isVictoryPossibleForToken(TOKEN_X);

        if (winningColumn != -1) {
            return insertToken(field, TOKEN_0, winningColumn);
        }

        do {
            int random = new Random().nextInt(0, COLUMNS);
            newFieldState = insertToken(field, TOKEN_0, random);
        } while (newFieldState == null);

        return newFieldState;
    }

    /**
     * Usage of goRecursive() with predefined offset.
     * It goes to the Neighbour from left side
     *
     * @param row
     * @param column
     */
    private static int goWest(int row, int column) {
        print("Moving LEFT from:(" + row + ", " + column + ")");
        return goRecursive(row, column, STAY, GO_WEST);

    }

    /**
     * Usage of goRecursive() with predefined offset.
     * It goes to the Neighbour from right side
     *
     * @param row
     * @param column
     */
    private static void goEast(int row, int column) {
        print("Moving RIGHT from:(" + row + ", " + column + ")");
        goRecursive(row, column, STAY, GO_EAST);
    }
    /**
     * Usage of goRecursive() with predefined offset.
     * It goes to the Neighbour bellow
     *
     * @param row
     * @param column
     */
    private static int goSouth(int row, int column) {
        print("Moving DOWN from:(" + row + ", " + column + ")");
        return goRecursive(row, column, GO_SOUTH, STAY);
    }

    // goNorth() - is obsoled, because there are no tokens upon the last set token

    private static void goNorthWest(int row, int column) {
        print("Moving NORTH-WEST from:(" + row + ", " + column + ")");
        goRecursive(row, column, GO_NORTH, GO_WEST);
    }

    private static void goNorthEast(int row, int column) {
        print("Moving NORTH-EAST from:(" + row + ", " + column + ")");
        goRecursive(row, column, GO_NORTH, GO_EAST);
    }


    private static void goSouthEast(int row, int column) {
        print("Moving  SOUTH-EAST from:(" + row + ", " + column + ")");
        goRecursive(row, column, GO_SOUTH, GO_EAST);
    }

    private static void goSouthWest(int row, int column) {
        print("Moving  SOUTH-WEST from:(" + row + ", " + column + ")");
        goRecursive(row, column, GO_SOUTH, GO_WEST);
    }


    /** Wrapper for standard Method System.out.println.
     * The String will be printed only if class variable DEBUG = true
     *
     * @param string
     */
    private static void print(String string) {
        if (DEBUG) {
            System.out.println(string);
        }
    }

    /**
     * Wrapper for Method print(String) to print empty String, if DEBUG is true
     *
     */
    private static void print() {
        print("");
    }

}
