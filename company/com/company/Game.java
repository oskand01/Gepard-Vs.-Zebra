package com.company;

import java.awt.*;
import java.util.Scanner;

public class Game implements Interface {
    private Djur[] djur = new Djur[50];
    private SpelPlan spelPlan = new SpelPlan(); //SpelPlan tillbaka som egen klass, bra för att kunna använda metoder på objektet tänkte jag
    private int animals;

    public Game() {
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        setAnimals(); //Antal djur från användare
        createGepards();
        createZebras();
        getAnimalPositions();
        spelPlan.printSpelplan();
        printDjur();
    }

    public void setAnimals() { //Delar upp metoden i tre; setAnimals, createGepards, createZebras
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Hur många geparder ska jaga zebrorna? Ange mellan 1-10");
            animals = scan.nextInt();
            //Try catch här för att göra idiotsäker?
        } while (animals <= 0 || animals > 10);
    }

    public void createGepards() {
        for (int i = 0; i < animals; i++) { //animals = användarens val av antal geparder
            Gepard gepard = new Gepard();
            gepard.setId(i + 1); //Nytt! Sätter den nya gepardens id till index + 1 (Alla geparder får id 1 - 10
            for (int j = 0; j < djur.length; j++) {
                if (djur[j] == null) {
                    djur[j] = gepard;
                    j = djur.length;
                }
            }
        }
    }

    public void createZebras() {
        for (int i = 0; i < animals * 2; i++) { //Dubbelt så många zebror som geparder
            Zebra zebra = new Zebra();
            zebra.setId(i + 11); // Alla zebror får id 11-40
            for (int j = 0; j < djur.length; j++) {
                if (djur[j] == null) {
                    djur[j] = zebra;
                    j = djur.length;
                }
            }
        }
    }

    public void printDjur() {
        for (int i = 0; i < djur.length; i++) {
            if (djur[i] == null) {
                i = djur.length;
            } else System.out.println(djur[i]);
        }
    }


    //Gör ingenting än så länge men tänkte bara
    public void getAnimalPositions() {
        Point animalPosition = new Point();
        for (int i = 0; i < djur.length; i++) {
            if (djur[i] != null) {
                animalPosition = djur[i].getPos();
                spelPlan.setPoint(animalPosition);
            }
        }
    }

    public int getAnimals() {
        return animals;
    }

    @Override
    public Point getPosition() {
        Point position = spelPlan.getPoint();
        return position;
    }

}




