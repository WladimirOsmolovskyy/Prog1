package de.osmolovskyy.wladimir.tu.tasks.task6;

public class Letter extends Delivery {

    private final boolean registeredLetter;


    public Letter(Location sender, Location receiver, double weight, boolean registeredLetter) {
        super(sender,receiver, weight);

        this.registeredLetter = registeredLetter;
        this.date = System.currentTimeMillis();
        calculatePostage(weight, registeredLetter);
    }

    public Letter(Location sender, Location receiver, double weight) {
        super(sender,receiver, weight);

        this.registeredLetter = false;
        this.date = System.currentTimeMillis();
        calculatePostage(weight);
    }

    private void calculatePostage(double weight, boolean registeredLetter) {
       calculatePostage(weight);

        if(registeredLetter){
            this.postage += 300;
        }
    }

    private void calculatePostage(double weight) {
        if( weight <= 0.2) {
            this.postage = 70;
        } else if ( weight > 0.2 && weight <= 0.5) {
            this.postage = 200;
        } else {
            this.postage = 400;
        }
    }
    public boolean isRegisteredLetter() {
        return registeredLetter;
    }

    public static Letter createLetterFromUserInput(){
        // readInputs instead of this gard coded locations
        var sender = Location.createLocationFrom("Wladimir", 1, 1);
        var receiver = Location.createLocationFrom("Alexey", 2,2);
        var registered = true;
        // checkInputs
        return new Letter(sender, receiver, 0.6, registered);
    }

    @Override
    public String toString() {
        return String.format("%sBrief von: %s an: %s  [Gewicht: %4.2f, Porto: %d Ct]",
                registeredLetter ? "Einschreiben " : "",
                sender.getName(),
                receiver.getName(),
                weight,
                postage);
    }
}
