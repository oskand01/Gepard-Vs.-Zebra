package com.company;

public class Gepard extends Djur {

    private boolean hungry;

    public Gepard() {
        setSpeed(1); //Bara tillfälliga värden
        hungry = true;

    }

    public void hunt() {
        setSpeed(3); //Går lätt att ändra med setSpeed
    }

    public void createGepards() {
        for (int i = 0; i < getAnimals(); i++) { //animals = användarens val av antal geparder
            Gepard gepard = new Gepard();
            gepard.setId(i + 1); //Nytt! Sätter den nya gepardens id till index + 1 (Alla geparder får id 1 - 10
            for (int j = 0; j < getDjur().length; j++) {
                if (getDjur()[j] == null) {
                    getDjur()[j] = gepard;
                    j = getDjur().length;
                }
            }
        }
    }


    @Override
    public String toString() {

        return "Gepard\n" + super.toString() + "\n";
    }
}
