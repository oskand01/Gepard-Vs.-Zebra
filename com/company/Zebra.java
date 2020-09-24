package com.company;

public class Zebra extends Djur {

    private boolean scared;

    public Zebra(){
        scared = false;
        setSpeed(2);
        setDirectionRandom(directionRandom);
    }

    public void createZebras() {
        for (int i = 0; i < getAnimals() * 2; i++) { //Dubbelt så många zebror som geparder
            Zebra zebra = new Zebra();
            zebra.setId(i + 11); // Alla zebror får id 11-30
            for (int j = 0; j < getDjur().length; j++) {
                if (getDjur()[j] == null) {
                    getDjur()[j] = zebra;
                    j = getDjur().length;
                }
            }
        }
    }

    @Override
    public String toString() {

        return "Zebra\n" + super.toString() + "\n";
    }
}
