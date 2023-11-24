package de.osmolovskyy.wladimir.tu.tasks.logistic2;

public class Delivery {
    private Location sender;
    private Location receiver;
    private long date;
    private double weight;

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
        if(isInputRight(receiver)) {
            this.receiver = receiver;
            }
    }

    void setWeight(double weight) {
        if(isInputRight(weight)) {
            this.weight = weight;
        }
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver, double weight) {

        if(isInputRight(sender) && isInputRight(receiver) && isInputRight(weight)){
            Delivery  delivery = new Delivery();
            delivery.sender = sender;
            delivery.setReceiver(receiver);
            delivery.setWeight(weight);
            delivery.date = System.currentTimeMillis();

            return delivery;
        }

        return null;
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver) {
        return createDeliveryUsing(sender, receiver, 0.5);
    }

    public double getDistance(){
        int deltaX = receiver.getxPosition() - sender.getxPosition();
        int deltaY = receiver.getyPosition() - sender.getyPosition();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    private static boolean isInputRight(Location location) {
        if(location == null) {
            System.out.println("Fehler. Location darf nicht null sein.");
            return false;
        } else {
            return true;
        }
    }

    private static boolean isInputRight(double weight) {
        if(weight < 0) {
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
                +", " + getWeight() + " kg, "
                + getDistance() + " km.";

    }
}