package com.company;

public class Gepard extends Djur {

    private boolean hungry;

    public Gepard() {
        setSpeed(1); //Bara tillfälliga värden
        hungry = true;
        setDirectionRandom(directionRandom);
        getDirectionRandom();


    }

    public void hunt() {
        setSpeed(3); //Går lätt att ändra med setSpeed
    }




    @Override
    public String toString() {

        return "Gepard\n" + super.toString() + "\n";
    }
}
