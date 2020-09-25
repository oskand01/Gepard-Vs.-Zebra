package com.company;

public class Zebra extends Djur {

    private boolean scared;  

    public Zebra(){
        scared = false;//avvakta med denna tills movement fungerar?
        setSpeed(2); //samma här?
        setDirectionRandom(directionRandom); //Borde ha all movement i game 
    }

    
    /*
   Borde också hanteras i Game, ska se om jag hittar en bättre lösning på detta kladdet   
*/
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
