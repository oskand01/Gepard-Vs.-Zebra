package com.company;


import java.util.Random;
import java.util.Scanner;

public class Game {


    /**
     * En salig blandning av svenska och engelska
     * försökt städa lite
     */
    protected static Djur[][] spelplan = new Djur[20][60];
    Random random = new Random();
    private static int antalGeparder;
    private static int antalZebror;

    protected enum Flytta {up, down, left, right}

    /**
     * Agerar default
     */
    public Game(int geparder, int zebror) {
        antalGeparder = geparder;
        antalZebror = zebror;
        skapaAntalDjur(antalGeparder, antalZebror);
    }

    /**
     * Konstruktorn agerar som själva menyn för "spelet"
     * <p>
     * Skickar vidare till metoder som:
     * Tar emot inmatning av antal djur, som skickar vidare och
     * Placerar djuren på spelplanen
     * Tar hand om game-loopen
     */
    public Game() {
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");

        System.out.println("Hur många geparder ska jaga hur många zebror?");
        mataInDjur();
        runGame();
    }

    public void mataInDjur() {
        Scanner scan = new Scanner(System.in);
        int gepard;
        int zebra;

        gepard = scan.nextInt();
        do {
            zebra = scan.nextInt();
            System.out.println("Antalet Zebror måste vara minst lika många som Geparder");
        } while (gepard > zebra);

        skapaAntalDjur(gepard, zebra);
    }


    /**
     * En game-loop
     * kör Spelplan() och Move()
     * fortsätter tills spelplanen är tom
     * Villkoren kan vi ändra sen
     * <p>
     * thread.sleep(3000)
     * innebär att programmet pausar i 3000 millisekunder
     * inte optimalt att köra i större program,
     * endast för redovisningens skull
     */
    public void runGame() {
        while (antalZebror != 0 && antalGeparder != 0) {
            clearScreen();
            Spelplan.printSpelplan();
            kontroll();
            resetFlytt();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("mög");
            }
        }
    }

    /**
     * Skapar antalet djur och skickar vidare dom till placera-metoden
     *
     * @param gepard antalet geparder som skapas
     * @param zebra  antalet zebror som skapas
     */
    public void skapaAntalDjur(int gepard, int zebra) {
        for (int i = 0; i < gepard; i++) {
            placeraDjur(new Gepard());
            antalGeparder++;
        }
        for (int i = 0; i < zebra; i++) {
            placeraDjur(new Zebra());
            antalZebror++;
        }
    }


    /**
     * Används för att placera ut Djuren
     * Genererar en random position inom gränsterna vi väljer
     * x representerar rad
     * y representerar kolumn
     * <p>
     * kallar på metoden upptagen() innan den placerar ut något djur
     *
     * @param djur Parametern säger vilket djur som ska placeras ut på spelplanen
     *             och jämförs med hjälp av instanceof för att skapa och placera ut rätt djur.
     */
    public void placeraDjur(Djur djur) {
        int x;
        int y;

        do {
            x = random.nextInt(19);
            y = random.nextInt(59);
        } while (upptagen(x, y)); //något som testar om rutan är upptagen)

        if (djur instanceof Gepard) {
            spelplan[x][y] = new Gepard(x, y);
        } else if (djur instanceof Zebra) {
            spelplan[x][y] = new Zebra(x, y);
        } else {
            System.out.println("Kunde inte placera fler djur");
        }
    }

    /**
     * Testar spelplanens x och y
     * om de redan används returnerar den true
     * om de inte används returnerar den false
     */

    public static boolean upptagen(int x, int y) {
        if (Game.spelplan[x][y] != null) {
            return true;
        } else {
            return false;
        }
    }


    public static Flytta randomFlytt() {
        Flytta[] values = Flytta.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }

    public static int getAntalGeparder() {
        return antalGeparder;
    }

    public static int getAntalZebror() {
        return antalZebror;
    }

    public static void kontroll() {
        for (int i = 0; i < spelplan.length - 1; i++) {
            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    kontrollDjur(i, j);
                }
            }
        }
    }

    public static void kontrollDjur(int i, int j) {
        if (spelplan[i][j].harFlyttat() == false) {
            flytta(i, j, randomFlytt());
        }
    }

    public static void resetFlytt() {
        for (int i = 0; i < spelplan.length - 1; i++) {
            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    spelplan[i][j].setFlytt(false);
                }
            }
        }
    }

    /**
     * La in Eli's enum och switch igen
     * <p>
     * måste kolla om rutan är upptagen.....
     *
     * @param dir använder vi för att bestämma vilket håll
     */


    /*en metod för att hämta djurets riktning ur enumtypen
      Flytta dir.
     */
    public static void flytta(int x, int y, Game.Flytta dir) {

        switch (dir) {
            case up:
                //ett villkor för att kontrollera om djurets position ligger på spelplanens gräns
                //djuret "Rör" sig då i motsatt riktning
                if (spelplan[x][y].getxPos() == 0) {
                    moveDjur(x+1, y, spelplan[x][y].getTag()); //anropar metod moveDjur för att genomföra rörelsen
                    resetDjur(x, y); //anropar en metod som nollställer spelplanen
                break;
                }
                    moveDjur(x-1, y, spelplan[x][y].getTag());
                    resetDjur(x, y);
                break;

            case down:

                if (spelplan[x][y].getxPos() == 19) {
                    moveDjur(x-1, y, spelplan[x][y].getTag());
                    resetDjur(x, y);

                break;
                }

                    moveDjur(x+1, y, spelplan[x][y].getTag());
                    resetDjur(x, y);

                break;

            case left:
                if (spelplan[x][y].getyPos() == 1) {
                    moveDjur(x, y+1, spelplan[x][y].getTag());
                    resetDjur(x, y);
                break;
                }

                    moveDjur(x, y-1, spelplan[x][y].getTag());
                    resetDjur(x, y);

                break;

            case right:

                if (spelplan[x][y].getyPos() == 59) {
                    moveDjur(x, y-1, spelplan[x][y].getTag());
                    resetDjur(x, y);
                break;
                }

                moveDjur(x, y+1, spelplan[x][y].getTag());
                resetDjur(x, y);

                break;


        }

    }

    public static void moveDjur(int x, int y, char tag){
        if(tag == 'G') {
            spelplan[x][y] = new Gepard(x, y, true); //instanserar ett nytt objekt av Gepard på spelplanen
        }
        else {
            spelplan[x][y] = new Zebra(x, y, true); //instanserar ett nytt objekt av Zebra på spelplanen

        }
    }

    public static void resetDjur(int x, int y){ //en metod för att reseta spelplanen innan denya positionerna
        spelplan[x][y] = null;                  //för djur skrivits in på spelplanen

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
