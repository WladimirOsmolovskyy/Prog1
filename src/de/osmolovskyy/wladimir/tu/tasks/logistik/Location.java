package de.osmolovskyy.wladimir.tu.tasks.logistik;

import de.osmolovskyy.wladimir.tu.tasks.memory.MemoryGame;

import java.util.Scanner;

public class Location {
    public static final String AUSSAGEKRAEFTIGE_FEHLERMELDUNG = "AUSSAGEKRÄFTIGE FEHLERMELDUNG";
    public static final String TO_STRING_PATTERN = "Location( id: %d, name: %s, coordinates: [%d, %d]}";
    private String name;
    private int xPosition;
    private int yPosition;

    private int id = nextId++;

    private static int nextId;


    public String getName() {
        return name;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getId() {
        return id;
    }

    public int getNextId() {
        return nextId;
    }


    public static Location createLocationFrom(String name, int xPosition, int yPosition) {
        Location location = new Location();
        location.name = name;
        location.xPosition = xPosition;
        location.yPosition = yPosition;

        return location;
    }

    public static Location createLocationFromUserInput() {
        String name = readString("Geben Sie den Location Namen ein: ");
        int xPosition = readInteger("Geben Sie die X-Position ein: ");
        int yPosition = readInteger("Geben Sie die Y-Position ein:");

        if (isInputIntOK(xPosition) && isInputIntOK(yPosition)) {
            return createLocationFrom(name, xPosition, yPosition);
        } else {
            System.out.println(AUSSAGEKRAEFTIGE_FEHLERMELDUNG);
        }

        return null;
    }


    private static int readInteger(String s) {
        System.out.print(s);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

    private static String readString(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }

    private static boolean isInputIntOK(int input) {
        return input >= 0;
    }
    @Override
    public String toString() {
//        return "Location{" +
//                "name='" + name + '\'' +
//                ", xPosition=" + xPosition +
//                ", yPosition=" + yPosition +
//                ", id=" + id +
//                '}';

        return String.format(TO_STRING_PATTERN, id,name, xPosition, yPosition);
    }

}
