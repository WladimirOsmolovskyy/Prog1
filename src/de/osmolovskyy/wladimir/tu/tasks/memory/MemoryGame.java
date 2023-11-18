package de.osmolovskyy.wladimir.tu.tasks.memory;

import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MemoryGame {
    private static final int ROWS = 3;
    private static final int COLUMNS = 4;

    static boolean[][] exposed = new boolean[ROWS][COLUMNS];
    static int[][] position = new int[ROWS][COLUMNS];
    static char[][] game = new char[ROWS][COLUMNS];


    private static int pointsPlayer1 = 0;
    private static int pointsPlayer2 = 0;

    private static int PLAYER_1 = 1;
    private static int PLAYER_2 = 2;

    private static int ACTIVE = PLAYER_1;

    public static void main(String[] args) {

        System.out.println("==================== S T A R T ====================");
        initPosition(position);
        initGame(game);

        do {
            renderPlayground();
            int firstMove = makeMove();
            char firstChar = exposeField(firstMove);
            renderPlayground();
            int secondMove = makeMove();
            char secondChar = exposeField(secondMove);
            renderPlayground();

            evaluateMoveResults(firstChar, secondChar, firstMove, secondMove);
            printGameStandings();

        } while (!isGameOver());

        System.out.println("-=[ Game Over]=-");
        System.out.println(winner());

    }

    private static void evaluateMoveResults(char firstChar, char secondChar, int firstMove, int secondMove) {
        if (isTheSameCharacterFound(firstChar, secondChar)) {
            increasePointsOfActivePlayer();
        } else {
            hideFields(firstMove, secondMove);
            toggleActivePlayer();
        }
        ;
    }

    private static String winner() {
        if (pointsPlayer2 > pointsPlayer1) {
            return "PLAYER_1 WON!";
        }
        if (pointsPlayer1 > pointsPlayer2) {
            return "PLAYER 2 WON!";
        } else {
            return "DRAW!";
        }
    }


    private static void toggleActivePlayer() {
        if (ACTIVE == PLAYER_1) {
            ACTIVE = PLAYER_2;
        } else {
            ACTIVE = PLAYER_1;
        }
    }

    private static void increasePointsOfActivePlayer() {
        if (ACTIVE == PLAYER_1) {
            pointsPlayer1++;
        } else {
            pointsPlayer2++;
        }
    }

    private static boolean isTheSameCharacterFound(char firstChar, char secondChar) {
        return firstChar == secondChar;
    }

    private static void hideFields(int firstMove, int secondMove) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (position[i][j] == firstMove || position[i][j] == secondMove) {
                    exposed[i][j] = false;
                }
            }
        }
    }

    private static char exposeField(int move) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (position[i][j] == move) {
                    exposed[i][j] = true;
                    return game[i][j];
                }
            }
        }
        return ' ';
    }


    private static void initGame(char[][] game) {
        int[] values = new int[COLUMNS * ROWS / 2];

        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                boolean repeat;
                do {
                    int randomInt = random.nextInt(0, COLUMNS * ROWS / 2);
                    if (values[randomInt] < 2) {
                        values[randomInt]++;
                        game[i][j] = (char) ('A' + randomInt);
                        repeat = false;
                    } else {
                        repeat = true;
                    }
                } while (repeat);

            }
        }
    }

    private static void initPosition(int[][] position) {
        int counter = 1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                position[i][j] = counter++;
            }
        }
    }

    private static void renderPlayground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (exposed[i][j] == false) {
                    System.out.print("[" + position[i][j] + "]");
                } else {
                    System.out.print("[" + game[i][j] + "]");
                }
            }
            System.out.println();
        }
    }

    private static boolean isGameOver() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (exposed[i][j] == false) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int makeMove() {
        String str = String.format("Player %d, your move: ", ACTIVE);
        return readInteger(str);

    }

    private static int readInteger(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

    private static void printGameStandings() {
        System.out.format("Player 1 has %d point(s).\n", pointsPlayer1);
        System.out.format("Player 2 has %d point(s).\n", pointsPlayer2);
    }
}