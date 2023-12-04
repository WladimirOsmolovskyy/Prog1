package de.osmolovskyy.wladimir.tu.tasks.task6;

public class DeliveryManager{
    public static void main(String[] args) {

        Location paris = Location.createLocationFrom("Paris", 301, 5592);
        Location kiew = Location.createLocationFrom("Kiew", 997, 8005);
        Location lissabon = Location.createLocationFrom("Lissabon", 301, 3712);
        Location chicago = Location.createLocationFrom("Chicago", 4, 12892);
        Location tokio = Location.createLocationFrom("Tokio", 444, 1292);
//        Location location = Location.createLocationFromUserInput();

        Delivery[] deliveries = new Delivery[8];

        deliveries[0] =  new Letter(paris, kiew, 0.15);
        deliveries[1] = new Letter(kiew, lissabon, 0.15, true);
        deliveries[2] = new Letter(lissabon, chicago, 0.6, false);
        deliveries[3] = Letter.createLetterFromUserInput();

        deliveries[4] = new Parcel(chicago, tokio, 5, new boolean []{false, true, false},  new int[]{10,10,10});
        deliveries[5] = new Parcel(tokio, paris, 0.4, new boolean []{true, true, true},  new int[]{10,10,10});
        deliveries[6] = new Parcel(paris, kiew, 0.4, new boolean []{false, false, false},  new int[]{100,100,100});

        for( Delivery delivery : deliveries){
            System.out.println(delivery);
        }


    }
}
