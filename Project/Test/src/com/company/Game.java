package com.company;

import java.util.Scanner;

public class Game {
    private int counter; //Antal spelomgångar (Ex. för att se om geparder svälter ihjäl)??
    private Djur[] d = new Djur[50];
    private SpelPlan[][] spelPlan = new SpelPlan[20][60];
    private boolean krock = false;
    private int animals;


    public Game() {
        newGame();

    }

    public void newGame() {
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        setAnimals(); //Antal djur från användare
        getSpelPlan();
        printSpelplan();
        collition();

    }

    public void collition() {
        while (krock == false) {
            Zebra z = new Zebra();
            Gepard g = new Gepard();
            if (z.getX() == g.getX() && z.getY() == g.getY()) {
                System.out.printf("KROCK!%n(x: %d y: %d%n%nEfter %d gånger!%n%n%n)", z.getX(), z.getY(), counter);
                this.krock = true;

            } else {
                System.out.println("Zebra: " + z.getX() + " " + z.getY());
                System.out.println("Gepard: " + g.getX() + " " + g.getY());

            }
            this.counter++;

        }


    }

    public void setAnimals() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hur många geparder ska jaga zebrorna?");
        // try catch här
        this.animals = scan.nextInt();
    }

    public SpelPlan[][] getSpelPlan() {
        return spelPlan;

    }

    public void printSpelplan() {
        String s = " ";
        String b = "*";

        for (int x = 0; x < spelPlan.length; x++) {
            for (int y = 0; y < 60; y++) {
                if (x == 0 || x == 19 || y == 0 || y == 59) {
                    System.out.print(b);
                }
                else if (spelPlan[x][y] == null) {
                    System.out.print(s);
                } else {
                    System.out.print(spelPlan[x][y]);

                }
            }

            System.out.println();
        }

    }

}
