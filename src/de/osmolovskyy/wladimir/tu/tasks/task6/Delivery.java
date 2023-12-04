package de.osmolovskyy.wladimir.tu.tasks.task6;

public class Delivery {
    Location sender;
    Location receiver;
    long date;
    double weight;
    int postage;

    public Delivery(Location sender, Location receiver, double weight) {
        if (isInputRight(sender) && isInputRight(receiver) && isInputRight(weight)) {
            this.sender = sender;
            this.receiver = receiver;
            this.weight = weight;
            this.date = System.currentTimeMillis();
        }
    }

    public Delivery() {

    }

    public int getPostage() {
        return postage;
    }

    public Location getSender() {
        return sender;
    }

    public Location getReceiver() {
        return receiver;
    }

    public long getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    void setReceiver(Location receiver) {
        if (isInputRight(receiver)) {
            this.receiver = receiver;
        }
    }

    void setWeight(double weight) {
        if (isInputRight(weight)) {
            this.weight = weight;
        }
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver, double weight) {

        if (isInputRight(sender) && isInputRight(receiver) && isInputRight(weight)) {
            Delivery delivery = new Delivery();
            delivery.sender = sender;
            delivery.receiver = receiver;
            delivery.weight = weight;
            delivery.date = System.currentTimeMillis();

            return delivery;
        }

        return null;
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver) {
        return createDeliveryUsing(sender, receiver, 0.5);
    }

    public double getDistance() {
        int deltaX = receiver.getxPosition() - sender.getxPosition();
        int deltaY = receiver.getyPosition() - sender.getyPosition();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    static boolean isInputRight(Location location) {
        if (location == null) {
            System.out.println("Fehler. Location darf nicht null sein.");
            return false;
        } else {
            return true;
        }
    }

    static boolean isInputRight(double weight) {
        if (weight < 0) {
            System.out.println("Fehler. Weight darf nicht negativ sein.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Lieferung von "
                + sender.getName()
                + " nach " + receiver.getName()
                + ", " + getWeight() + " kg, "
                + getDistance() + " km.";
    }
}