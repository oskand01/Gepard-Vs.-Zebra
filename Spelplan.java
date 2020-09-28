package com.company;

import java.util.Scanner;

public class Spelplan {

    public Spelplan(){
        printSpelplan();

    }


    /**
     *Utskriften för hela spelplanen
     * Lång onödig förklaring på vad allt gör här tills senare
     * Enkelt förklarat kollar den raderna först och sen kolumnerna
     *
     *
     */
    static void printSpelplan(){
        System.out.println("**************************************************************"); //toppens gräns

        for (int i = 0; i < Game.spelplan.length; i++) {
            System.out.print("*");  //vänster gräns
            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (Game.spelplan[i][j] == null) {
                    System.out.print(" ");  //Fyller i spelplanen
                } else {
                    System.out.print(Game.spelplan[i][j].getTag());   //symbolen för djuren på [x][y]
                }
            }
            System.out.print("*"); //Höger gräns
            if (i < Game.spelplan.length - 1) {    //Skippar att printa sista raden
                System.out.println();
            }
        }
        System.out.println("\n**************************************************************");

        System.out.println("Antal Geparder: " + Game.getAntalGeparder());
        System.out.println("Antal Zebror: " + Game.getAntalZebror());

    }
}
