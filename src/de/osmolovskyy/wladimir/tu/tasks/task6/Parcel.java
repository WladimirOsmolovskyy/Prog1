package de.osmolovskyy.wladimir.tu.tasks.task6;

public class Parcel extends Delivery {

    private boolean expressDelivery;
    private boolean transportInsurence;
    private boolean redirection;

    private char category;

    public Parcel(Location sender, Location receiver, double weight,boolean[] extraServices, int[] dimensions) {
        super(sender, receiver, weight);
        this.expressDelivery = extraServices[0];
        this.transportInsurence = extraServices[1];
        this.redirection = extraServices[2];
        this.category = defineCategory(dimensions, weight);
        switch (this.category){
            case 'S': this.postage = 300; break;
            case 'M': this.postage = 500; break;
            case 'L': this.postage = 700; break;
            default:
        }

        addExtraServicesFees();

    }

    private void addExtraServicesFees() {
        if(expressDelivery){
            this.postage += 500; // Ct
        }
        if(transportInsurence){
            this.postage += 600; // Ct
        }
        if(redirection){
            this.postage += 700; // Ct
        }

        if( expressDelivery && transportInsurence && redirection){
            this.postage *= 0.8;  // 20% rabbat
        }
    }

    private char defineCategory(int[] dimensions, double weight) {
        if(dimensions.length != 3 || ! isInputRight(weight)){
            // ERROR HANDLING
        }

        if (weight < 1 && lengthOfBorders(dimensions) <= 80) {  // cm
            return 'S';
        }
        if (weight <= 4 && calculateVolume(dimensions) < 64_000) {    // cm³
            return 'M';
        }

        return 'L';
    }

    private int calculateVolume(int[] dimensions) {
        return dimensions[0] * dimensions[1] * dimensions[2];
    }

    private int lengthOfBorders(int[] dimensions) {
        return dimensions[0] + dimensions[1] + dimensions[2];

    }

    @Override
    public String toString() {
        return String.format("Parcel Category '%c' von: %s nach: %s  [Gewicht: %4.2f, Porto: %d Ct, Zusatzleisungen:{%s, %s, %s}]",
                this.category,
                sender.getName(),
                receiver.getName(),
                weight,
                postage,
                expressDelivery ? "Express " : "-",
                transportInsurence ? "Versicherung " : "-",
                redirection ? "Redirection" : "-"
                );
    }

    @Override
    public void setReceiver(Location location){
        if (redirection){
            this.receiver = location;
        } else {
            System.out.println("Redirection ist nicht vorgemerkt");
        }
    }

    public Parcel createParcelFromUserInput(){
        Location sender = Location.createLocationFrom("Papa", 1, 1);
        Location receiver = Location.createLocationFrom("Sohn", 2,2);
        double weight = 3;
        boolean[] extras = {true, false, false};
        int[] dims = {55,35, 15};
         // check and return null in error case
        return  new Parcel(sender, receiver, weight, extras, dims);

    }
}
