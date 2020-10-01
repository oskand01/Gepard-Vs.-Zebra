package com.company;



public class Spelplan {

    public Spelplan() {

    }


    /**
     * Utskriften för hela spelplanen och djuren,
     * representerade av sin tillhörande tag.
     */
    public void printSpelplan() {
        System.out.println("**************************************************************"); //toppens psuedo-gräns


        for (int i = 0; i < Game.spelplan.length; i++) {
            if(i >= 19){
                System.out.print("**************************************************************"); //Bottens Gräns
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

    public static void skrivUtIntro () {
        System.out.println("**************************************************************");
        System.out.println("********************* GEPARD vs ZEBRA ************************");
        System.out.println("**************************************************************");
    }

    public void skrivUtOutro() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n**************************************************************");
        System.out.println("************************* GAME OVER **************************");
        System.out.println("**************************************************************\n");
        System.out.printf("Ingen uppges ha blivit skadad. %d zebror dog.\n\n\n\n\n\n\n\n\n\n\n\n", Game.getZebraKills());
    }
}


