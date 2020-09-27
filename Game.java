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
            new Spelplan();
            kontroll();
            resetFlytt();

            try {
                Thread.sleep(3000);
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
            x = random.nextInt(20);
            y = random.nextInt(60);
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
    public static void flytta(int x, int y, Game.Flytta dir) {

        switch (dir) {
            case up:
                if (spelplan[x][y].getxPos() == 0) {
                    break;
                }
                if (spelplan[x][y].getTag() == 'G') {
                    spelplan[x - 1][y] = new Gepard(x - 1, y);
                } else if (spelplan[x][y].getTag() == 'Z') {
                    spelplan[x - 1][y] = new Zebra(x - 1, y, true);
                }
                spelplan[x][y] = null;
                break;

            case down:
                if (spelplan[x][y].getxPos() == 20) {
                    break;
                }
                if (spelplan[x][y].getTag() == 'G') {
                    spelplan[x + 1][y] = new Gepard(x + 1, y);
                } else if (spelplan[x][y].getTag() == 'Z') {
                    spelplan[x + 1][y] = new Zebra(x + 1, y, true);
                }
                spelplan[x][y] = null;

                break;
            case left:
                if (spelplan[x][y].getyPos() == 1) {
                    break;
                }
                if (spelplan[x][y].getTag() == 'G') {
                    spelplan[x][y - 1] = new Gepard(x, y - 1, true);
                } else if (spelplan[x][y].getTag() == 'Z') {
                    spelplan[x][y - 1] = new Zebra(x, y - 1, true);
                }
                spelplan[x][y] = null;

                break;
            case right:
                if (spelplan[x][y].getyPos() == 59) {
                    break;
                }

                if (spelplan[x][y].getTag() == 'G') {
                    spelplan[x][y + 1] = new Gepard(x, y + 1, true);
                } else if (spelplan[x][y].getTag() == 'Z') {
                    spelplan[x][y + 1] = new Zebra(x, y + 1, true);
                }
                spelplan[x][y] = null;

                break;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
