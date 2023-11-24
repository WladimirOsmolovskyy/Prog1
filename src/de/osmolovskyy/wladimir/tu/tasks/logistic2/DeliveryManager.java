package de.osmolovskyy.wladimir.tu.tasks.logistic2;

public class DeliveryManager{
    public static void main(String[] args) {

        Location paris = Location.createLocationFrom("Paris", 301, 5592);
        Location kiew = Location.createLocationFrom("Kiew", 997, 8005);
        Location lissabon = Location.createLocationFrom("Lissabon", 301, 3712);
        Location chicago = Location.createLocationFrom("Chicago", 4, 12892);
        Location location = Location.createLocationFromUserInput();

        System.out.println(paris);
        System.out.println(kiew);
        System.out.println(lissabon);
        System.out.println(chicago);
        System.out.println(location);

        Delivery one = Delivery.createDeliveryUsing(paris, kiew, 1000);
        Delivery two = Delivery.createDeliveryUsing(kiew, paris, 1000);
        Delivery three = Delivery.createDeliveryUsing(lissabon, chicago, 755.5);

        Delivery four = Delivery.createDeliveryUsing(chicago, kiew);
        Delivery five = Delivery.createDeliveryUsing(paris, chicago);

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);
    }
}
