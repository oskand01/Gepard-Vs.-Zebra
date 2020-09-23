package com.company;

import java.util.Scanner;

public class Game {
    private int counter; //Antal spelomgångar (Ex. för att se om geparder svälter ihjäl)??
    private Djur[] d = new Djur[50];
    private boolean krock = false;
    private int animals;
    private int board[][] = new int[20][60];


    public Game() {
        newGame();

    }

    public void newGame() {
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        setAnimals(); //Antal djur från användare
        printBoard();
        //collition();

    }

    public void collition() {
        while (krock == false) {
            Zebra z = new Zebra(); //Bara så länge
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



public void printBoard() {
    String s = " ";
    String b = "*";

    for (int x = 0; x < board.length; x++) {
        for (int y = 0; y < 60; y++) {
            if (x == 0 || x == 19 || y == 0 || y == 59) {
                System.out.print(b);
            }
            else if (board[x][y] == 0) {
                System.out.print(s);
            } else {
                System.out.print(board[x][y]);

            }
        }

        System.out.println();
    }

}

}