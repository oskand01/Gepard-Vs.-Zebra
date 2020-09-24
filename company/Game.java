package com.company;

import java.awt.*;
import java.util.Scanner;

public class Game implements Interface {
    private Zebra zebra = new Zebra();
    private Gepard gepard = new Gepard();
    private SpelPlan spelPlan = new SpelPlan(); //SpelPlan tillbaka som egen klass, bra för att kunna använda metoder på objektet tänkte jag


    public Game() {

    }

    public void newGame() {
        menu(); //Antal djur från användare
        gepard.createGepards();
        zebra.createZebras();
        spelPlan.printSpelplan();

    }

    public void menu() { //Delar upp metoden i tre; setAnimals, createGepards, createZebras
        int animals;
        Scanner scan = new Scanner(System.in);
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        do {
            System.out.println("Hur många geparder ska jaga zebrorna? Ange mellan 1-10");
            animals = scan.nextInt();
            //Try catch här för att göra idiotsäker?
        } while (animals <= 0 || animals > 10);
    }



/*
    @Override
    public Point getPosition() {
        Point position = spelPlan.getPoint();
        return position;
    }

 */

}




