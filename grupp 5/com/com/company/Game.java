package com.company;

import java.awt.*;
import java.util.Scanner;

public class Game implements Interface {
    private Zebra zebra = new Zebra(); // Aggregat
    private Gepard gepard = new Gepard(); // Aggregat
    private SpelPlan spelPlan = new SpelPlan(); //Aggregat och SpelPlan tillbaka som egen klass, bra för att kunna använda metoder på objektet tänkte jag


    public Game() {

    }

    public void newGame() {
        menu(); //Antal djur från användare
        gepard.createGepards();
        zebra.createZebras();
        spelPlan.printSpelplan();
        gepard.printDjur(); //Skriver ut listan med alla djur
    }

    public void menu() { //Hette tidigare setAnimals
        int animals;
        Scanner scan = new Scanner(System.in);
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        do {
            System.out.println("Hur många geparder ska jaga zebrorna? Ange mellan 1-10");
            animals = scan.nextInt();
            gepard.setAnimals(animals);
            zebra.setAnimals(animals);
            //Try catch här för att göra idiotsäker?
        } while (animals <= 0 || animals > 10);
    }

}



