package de.osmolovskyy.wladimir.tu.tasks.logistic2;

import java.util.Scanner;

public class Location {
    public static final String AUSSAGEKRAEFTIGE_FEHLERMELDUNG = "AUSSAGEKRÄFTIGE FEHLERMELDUNG";

    private String name;
    private int xPosition;
    private int yPosition;
    private int id = nextId++;
    private static int nextId = 1;

    public int getyPosition() {
        return this.yPosition;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getxPosition() {
        return this.xPosition;
    }



    public static Location createLocationFrom(String name, int xPosition, int yPosition){
        Location location = new Location();

        location.name = name;
        location.xPosition = xPosition;
        location.yPosition = yPosition;

        return location;
    }

    public static Location createLocationFromUserInput(){
        String name = readString("Geben Sie bitte den Namen der Location ein: ");
        int xPosition = readInteger("Geben Sie bitte die X-Koordinaten der Location ein: ");
        int yPosition = readInteger("Geben Sie bitte die Y-Koordinaten der Location ein: ");

        if(xPosition < 0 || yPosition < 0){
            System.out.println(AUSSAGEKRAEFTIGE_FEHLERMELDUNG);
            return null;
        }

        return createLocationFrom(name, xPosition, yPosition);
    }


    private static int readInteger(String s) {
        System.out.print(s);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", id=" + id +
                '}';
    }

    private static String readString(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }
}
