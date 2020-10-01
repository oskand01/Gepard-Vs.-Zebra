package company;

import java.util.Scanner;

public class Spelplan {

    public Spelplan() {

    }


    /**
     * Utskriften för hela spelplanen
     * Lång onödig förklaring på vad allt gör här tills senare
     * Enkelt förklarat kollar den raderna först och sen kolumnerna
     */
    static void printSpelplan() {
        if (Game.antalHits > 0) {
            skrivUtSkoj();
        }
        else {
            System.out.println();
        }

        System.out.println("**************************************************************"); //toppens gräns


        for (int i = 0; i < Game.spelplan.length; i++) {
            if(i >= 19){
                System.out.print("**************************************************************");
            }else{System.out.print("*"); } //vänster gräns

            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (Game.spelplan[i][j] == null) {
                    System.out.print(" ");  //Fyller i spelplanen
                } else{
                    System.out.print(Game.spelplan[i][j].getTag());   //symbolen för djuren på [x][y]
                }
            }
            if (i < 19) {
                System.out.print("*"); //Höger gräns
            }
            if (i < Game.spelplan.length) {
                System.out.println();
            }
        }

        System.out.println("Antal Geparder: " + Game.getAntalGeparder());
        System.out.println("Antal Zebror: " + Game.getAntalZebror());
        System.out.println("Uppätna zebror: " + Game.getZebraKills());
        System.out.println();
        System.out.println();

    }

    public static void skrivUtSkoj() {
        System.out.println("På " + Game.antalHits + " platser på spelplanen är en gepard precis bredvid en zebra");
    }

    public static void skrivUtIntro () {
        System.out.println("**************************************************************");
        System.out.println("********************* GEPARD vs ZEBRA ************************");
        System.out.println("**************************************************************");
    }

    public static void skrivUtOutro() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n**************************************************************");
        System.out.println("************************* GAME OVER **************************");
        System.out.println("**************************************************************\n");
        System.out.printf("Ingen uppges ha blivit skadad. %d zebror dog.\n\n\n\n\n\n\n\n\n\n\n\n", Game.getZebraKills());


    }
}


