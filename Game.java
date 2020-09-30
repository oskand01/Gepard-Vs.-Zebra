package company;


import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class Game {

    /**
     * En salig blandning av svenska och engelska
     * försökt städa lite
     */
    protected static Djur[][] spelplan = new Djur[20][60];
    private Random random = new Random();
    private static int antalGeparder;
    private static int antalZebror;
    private static int zebror;

    protected static int antalHits; //OBS! Tillfällig för att se om metoden getDistance() fungerar

    protected enum Flytta {up, down, left, right}

    private boolean dennaFlyttKlar = false;

    /**
     * Konstruktorn agerar som själva menyn för "spelet"
     * <p>
     * Skickar vidare till metoder som:
     * Tar emot inmatning av antal djur, som skickar vidare och
     * Placerar djuren på spelplanen
     * Tar hand om game-loopen
     */
    public Game() {
        Spelplan.skrivUtIntro();
        mataInDjur();
        getDjur();
        runGame();
    }

    public void mataInDjur() {
        Scanner scan = new Scanner(System.in);
        int gepard = 0;
        int zebra = 0;
        try {
            System.out.print("\nHur många geparder och zebror ska delta?\nGeparder: ");
            gepard = scan.nextInt();
            while (gepard > zebra || zebra < 1) {
                System.out.print("Zebrorna måste vara minst lika många som geparderna.\nZebror: ");
                zebra = scan.nextInt();
            }
        } catch ( Exception e) {
            System.out.println("Du måste skriva in en siffra!");
            mataInDjur();
        }
        this.zebror = zebra;
        skapaAntalDjur(gepard, zebra);

    }


    /**
     * En game-loop
     * <p>
     * fortsätter tills spelplanen uppfyller villkoret vi satt.
     *
     * <p>
     * thread.sleep(3000)
     * innebär att programmet pausar i 3000 millisekunder
     * inte optimalt att köra i större program,
     * endast för redovisningens skull
     */
    public void runGame() {
        while (Game.getZebraKills() != 50) {
            clearScreen();
            Spelplan.printSpelplan();
            kontroll();
            resetFlytt();
            getDjur();
            getZebraKills();

            //debug(); Användes för att få exakta koordinater och antal djur, lättare att felsöka

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("mög");
            }
        }
        Spelplan.skrivUtOutro();
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
            x = random.nextInt(17) + 1;
            y = random.nextInt(58) + 1;
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
     * Testar koordinatens x och y
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

    /**
     * Loopar igenom spelplanen och spar alla djuren i en enkel array som skickas vidare
     * till metoden getDistance
     */
    public void getDjur() {
        int x = 0;
        Djur[] djurPos = new Djur[antalGeparder + antalZebror];
        for (int i = 0; i < spelplan.length; i++) {
            for (int j = 0; j < spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    djurPos[x] = spelplan[i][j];
                    x++;
                }
            }
        }
        getAntalDjur(djurPos);
        nyaZebror();
        getDistance(djurPos);

    }

    public void getAntalDjur(Djur[] djurPos) {
        int zebror = 0;
        int geparder = 0;
        for (int i = 0; i < djurPos.length; i++) {
            if (djurPos[i] instanceof Zebra) {
                zebror++;
            } else if (djurPos[i] instanceof Gepard) {
                geparder++;
            }
        }
        setAntalGeparder(geparder);
        setAntalZebror(zebror);
    }

    public static int getZebraKills() {

        int zebraKills = 0;
        if (zebror > getAntalZebror()) {
            zebraKills += zebror - getAntalZebror();
        }
        return zebraKills;
    }

    public void nyaZebror() {
        int total;
        if (getAntalZebror() < getAntalGeparder()) {
            total = (int)(getAntalGeparder() - getAntalZebror() + Math.random() * 20);
            skapaAntalDjur(0, total);
            //placeraDjur(new Zebra());
            setAntalZebror(getAntalZebror() + 1);
            zebror += total;
        }
    }


    /**
     * Metoden mäter avståndet varje enskilt djur har till alla andra djur
     * Just nu är villkoret att om något objekt har avståndet < 2 till ett annat objekt
     * och om det ena är gepard och det andra zebra så ska något hända.
     * <p>
     * Här kan vi ha med saker som att en zebra ska bli rädd och få högre
     * hastighet/byta riktning om den befinner sig inom ett visst avstånd från en gepard t.ex.
     */
    public void getDistance(Djur[] djurPos) {
        antalHits = 0; //OBS! Tillfällig för att se om den funkar
        int avstånd;
        for (int i = 0; i < djurPos.length; i++) {
            for (int j = i + 1; j < djurPos.length; j++) {
                if (djurPos[i] != null && djurPos[j] != null) {
                    avstånd = (int) djurPos[i].getPosition().distance(djurPos[j].getPosition());
                    if (avstånd < 2 && djurPos[i].getTag() != djurPos[j].getTag()) {
                        antalHits++; //Tillfällig
                    } else if (avstånd < 2 && djurPos[i].getTag() == djurPos[j].getTag()) {
                    }
                }
            }
        }
    }

    public void kontroll() {
        for (int i = 0; i < spelplan.length - 1; i++) {
            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    kontrollDjur(i, j);
                }
            }
        }
    }

    /*
     * Se om det går att titta på alla koordinater runt om, och gå in i en zebra om den finns där.
     * Annars random move
     *
     *
     */
    public void kontrollDjur(int i, int j) {
        if (spelplan[i][j].harFlyttat() == false) {
            if (spelplan[i][j] instanceof Gepard) {
                flytta(i, j, checkRuntOm(i, j));
            } else {
                flytta(i, j, randomFlytt());
            }
            setDennaFlyttKlar(false);
        }
    }

    public void resetFlytt() {
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
    public void flytta(int x, int y, Game.Flytta dir) {

        switch (dir) {
            case up:

                if (spelplan[x][y].getxPos() == 0) {
                    moveDjur(x + 1, y, spelplan[x][y].getTag()); //anropar metod moveDjur för att genomföra rörelsen
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                } else {
                    moveDjur(x - 1, y, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                }

            case down:

                if (spelplan[x][y].getxPos() == 18) {
                    moveDjur(x - 1, y, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                } else {
                    moveDjur(x + 1, y, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }

                    break;
                }

            case left:
                if (spelplan[x][y].getyPos() == 0) {
                    moveDjur(x, y + 1, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                } else {
                    moveDjur(x, y - 1, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }

                    break;
                }

            case right:

                if (spelplan[x][y].getyPos() == 59) {
                    moveDjur(x, y - 1, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                } else {
                    moveDjur(x, y + 1, spelplan[x][y].getTag());
                    if (dennaFlyttKlar == true) {
                        resetDjur(x, y);
                    }
                    break;
                }
        }
    }

    public void moveDjur(int x, int y, char tag) {
        if (tag == 'G') {
            moveGepard(x, y);
        } else if (tag == 'Z') {
            moveZebra(x, y);
        }
    }

    public void moveGepard(int x, int y) {
        if (upptagen(x, y)) {
            if (spelplan[x][y] instanceof Zebra) {
                spelplan[x][y] = new Gepard(x, y, true);
                setDennaFlyttKlar(true);

            } else if (spelplan[x][y].getTag() == 'G') {
                return;
            }
        } else if (upptagen(x, y) == false) {
            spelplan[x][y] = new Gepard(x, y, true);
            setDennaFlyttKlar(true);
        }
    }

    public void moveZebra(int x, int y) {
        if (upptagen(x, y)) {
            return;
        } else if (upptagen(x, y) == false) {
            spelplan[x][y] = new Zebra(x, y, true);
            setDennaFlyttKlar(true);
        }
    }

    /**
     * Metod för att sätta den angivna positionen till null efter en flytt
     */
    public void resetDjur(int x, int y) {
        spelplan[x][y] = null;

    }

    /**
     * Tillfällig metod för att testa getDistance()
     */



    /**
     * Samlat upp get-set- och diverse return metoder
     */
    public boolean isDennaFlyttKlar() {
        return dennaFlyttKlar;
    }

    public void setDennaFlyttKlar(boolean dennaFlyttKlar) {
        this.dennaFlyttKlar = dennaFlyttKlar;
    }

    public static void setAntalGeparder(int antalGeparder) {
        Game.antalGeparder = antalGeparder;
    }

    public static void setAntalZebror(int antalZebror) {
        Game.antalZebror = antalZebror;
    }

    public static int getAntalGeparder() {
        return antalGeparder;
    }

    public static int getAntalZebror() {
        return antalZebror;
    }

    public void setZebror(int zebror) {
        this.zebror = zebror;
    }

    public int getZebror() {
        return zebror;
    }

    /**
     * Bonus,
     * Meningslösa metoder för programmets funktion
     * men fancy
     */
    public void debug() {
        for (int i = 0; i < spelplan.length; i++) {
            for (int j = 0; j < spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    System.out.println(spelplan[i][j].toString());
                }
            }
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Flytta checkRuntOm(int x, int y) {
        if (outOfBounds(x, y) == false) {
            if (spelplan[x - 1][y] instanceof Zebra) {
                return Flytta.up;
            } else if (spelplan[x + 1][y] instanceof Zebra) {
                return Flytta.down;
            } else if (spelplan[x][y - 1] instanceof Zebra) {
                return Flytta.left;
            } else if (spelplan[x][y + 1] instanceof Zebra) {
                return Flytta.right;
            } else {
                return randomFlytt();
            }
        } else {
            return randomFlytt();
        }
    }

    public boolean outOfBounds(int x, int y) {
        if (spelplan[x][y].getxPos() <= 0) {
            return true;
        } else if (spelplan[x][y].getxPos() >= 18) {
            return true;
        } else if (spelplan[x][y].getyPos() <= 0) {
            return true;
        } else if (spelplan[x][y].getyPos() >= 58) {
            return true;
        } else
            return false;
    }

}
