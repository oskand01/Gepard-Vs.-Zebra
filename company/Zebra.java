package com.company;

public class Zebra extends Djur {

    private boolean scared;

    public Zebra(){
        scared = false;
        setSpeed(2);
    }


    @Override
    public String toString() {

        return "Zebra\n" + super.toString() + "\n";
    }
}
