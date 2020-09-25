package com.company;

import java.awt.*;
import java.util.Scanner;

/*
Lägg all logik här
som flyttar eller skapar objekt
*/

public class Game implements Interface {
/*
Tog bort objekten som skapades här
behöver bara skapas när vi ber dom
*/


    public Game() {

    }

    public void newGame() {
        menu(); //Antal djur från användare
        gepard.createGepards();
        zebra.createZebras();
        spelPlan.printSpelplan();
        gepard.printDjur(); //Skriver ut listan med alla djur
        /*
        borde anropa en metod här 
        som fortsätter köra igenom programmet 
        tills vi möter ett mål
        */
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




