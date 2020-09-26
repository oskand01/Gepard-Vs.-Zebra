package com.company;
import java.util.Random;
import java.util.Scanner;

public class Game {


    /**
     * En salig blandning av svenska och engelska
     * försökt städa lite
     */
    private int x;
    private int y;
    static Djur[][] spelplan = new Djur[20][60];
    Random random = new Random();
    private static int antalGeparder;
    private static int antalZebror;
    private enum Flytta {up, down, left, right}




    /**
     * Konstruktorn för game agerar som själva menyn för "spelet"
     * <p>
     * Ber om antalet djur,
     * ser till att antalet Zebror inte är färre än Geparder
     * <p>
     * Placerar djuren på spelplanen
     * Startar runGame, som tar hand om game-loopen
     */
    public Game() {
        Scanner scan = new Scanner(System.in);
        int gepard;
        int zebra;

        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        System.out.println("Hur många geparder ska jaga hur många zebror?");
        gepard = scan.nextInt();

        do {
            zebra = scan.nextInt();
            System.out.println("Antalet Zebror måste vara minst lika många som Geparder");
        } while (gepard > zebra);

        valAntalDjur(gepard, zebra);
        runGame();
        flyttaDjur(new Gepard(), Flytta.values()[random.nextInt(Flytta.values().length)]);
        //kallar på flyttaDjur, placerar den här tillfälligt pga osäker

    }

    /**
     * Helt meningslös för tillfället
     * tänkte att vi kanske behöver uppdatera
     * antalet zebror och geparder
     * efter eventuell "död" på spelplanen
     */
    public Game(int geparder, int zebror) {
        antalGeparder = geparder;
        antalZebror = zebror;
        valAntalDjur(antalGeparder, antalZebror);
        


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
            new Spelplan();

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("mög");
            }

        }
    }

    /**
     * Skickar hit antalet djur som användaren angett i Game-konstruktorn
     * loopar igenom respektive antal
     * <p>
     * Skapar nya Geparder och Zebror
     * och kallar på placeraDjur()
     * <p>
     * ändrar våra antalGeparder/antalZebror
     */
    public void valAntalDjur(int gepard, int zebra) {
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

    public boolean upptagen(int x, int y) {
        if (Game.spelplan[x][y] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * La in Eli's enum och switch igen
     *
     * @param djur bestämmer vilket djur som ska röra sig
     * @param dir  använder vi för att bestämma vilket håll
     */
    public void flyttaDjur(Djur djur, Flytta dir) {
        int x = djur.getxPos();
        int y = djur.getyPos();


        if (djur.getTag() == 'G') {
            switch (dir) {
                case up:
                    y -= djur.getSpeed(); //hämtar djurets hastighet i Objekt djur
                    djur.setyPos(y); // bestämmer det nya y-värdet för djurets position

                    break;
                case down:
                    y += djur.getSpeed();
                    djur.setyPos(y);

                    break;
                case left:
                    x += djur.getSpeed();
                    djur.setxPos(x);

                    break;
                case right:
                    x -= djur.getSpeed();
                    djur.setxPos(x);

                    break;


            }
            spelplan[x][y] = new Gepard(x, y);

        } else if (djur.getTag() == 'Z') {
            switch (dir) {
                case up:
                    y -= djur.getSpeed();
                    this.y = y;

                    break;
                case down:
                    y += djur.getSpeed();
                    this.y = y;

                    break;
                case left:
                    x += djur.getSpeed();
                    this.x = x;

                    break;
                case right:
                    x -= djur.getSpeed();
                    this.x = x;

                    break;

            }
            spelplan[x][y] = new Gepard(x, y);

        }
    }

    public static Flytta generateRandomFlytt() {
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
}
