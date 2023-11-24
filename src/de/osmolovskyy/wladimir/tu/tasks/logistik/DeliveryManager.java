package de.osmolovskyy.wladimir.tu.tasks.logistik;

public class DeliveryManager {
    public static void main(String[] args) {
        Location berlin = Location.createLocationFrom("Berlin", 5250, 1309);
        Location hamburg = Location.createLocationFrom("Hamburg", 5355, 959);
        Location muenchen = Location.createLocationFrom("München",4815 , 1138);
        Location duesseldorf = Location.createLocationFrom("Düsseldorf", 5127, 673);


        // Location locationX = Location.createLocationFromUserInput(); // test ungültige eingaben

        System.out.println(berlin);
        System.out.println(hamburg);
        System.out.println(muenchen);
        System.out.println(duesseldorf);

        Delivery delivery1 = Delivery.createDeliveryUsing(berlin, hamburg);
        Delivery delivery2 = Delivery.createDeliveryUsing(hamburg, duesseldorf, 100);
        Delivery delivery3 = Delivery.createDeliveryUsing(duesseldorf, muenchen, 200);
        Delivery delivery4 = Delivery.createDeliveryUsing(muenchen, hamburg);
        Delivery delivery5 = Delivery.createDeliveryUsing(hamburg, berlin, 42.123);

        System.out.println("=========================================================================");

        System.out.println(delivery1);
        System.out.println(delivery2);
        System.out.println(delivery3);
        System.out.println(delivery4);
        System.out.println(delivery5);

    }
}
