package de.osmolovskyy.wladimir.tu.tasks.logistik;

public class Delivery {
    private final Location sender;
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
        // TODO: checkLocation()
        this.receiver = receiver;
    }

     void setWeight(double weight) {
        // TODO: checkDouble()
        this.weight = weight;
    }

    public Delivery(Location sender, long date) {
        this.sender = sender;
        this.date = date;
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver, double weight){
        Delivery delivery = new Delivery(sender, System.currentTimeMillis());
        delivery.setReceiver(receiver);
        delivery.setWeight(weight);

        return delivery;
    }

    public static Delivery createDeliveryUsing(Location sender, Location receiver){
        Delivery delivery = new Delivery(sender, System.currentTimeMillis());
        delivery.setReceiver(receiver);
        delivery.setWeight(0.5);

        return delivery;
    }

    public double getDistance(){
        int deltaX = getReceiver().getxPosition() - getSender().getxPosition();
        int deltaY = getReceiver().getyPosition() - getSender().getyPosition();

        return Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
    }

    @Override
    public String toString() {
        return String.format("Lieferung von %s nach %s, %3.2f kg, %3.2f km",
                getSender().getName(),
                getReceiver().getName(),
                getWeight(),
                getDistance());
    }
}
