package company;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Game {

    /**
     * En salig blandning Svengelska
     */
    protected static Djur[][] spelplan = new Djur[20][60];
    private Spelplan board = new Spelplan(); //instansierar ett objekt av Spelplan
    private Random random = new Random(); //instansierar ett objekt av Random typ
    private static int antalGeparder;
    private static int antalZebror;
    private static int diffZebror;

    private enum Flytta {up, down, left, right} //initierar en enum med våra olika riktningar

    private boolean dennaFlyttKlar = false;

    /**
     * Konstruktorn agerar som själva menyn för "spelet"
     * <p>
     * Skickar vidare till metoder som:
     * Tar emot inmatning av antal djur, som skickar vidare och
     * Placerar djuren på spelplanen
     * Startar runGame som behandlar vår Game-loop
     */
    public Game() { //konstruktor
        board.skrivUtIntro();
        mataInDjur();
        getDjur();
        runGame();
    }

    /**metod för att mata in antalet djur som användaren vill ha
     *innehåller också en try-catch funktion för att fånga upp felaktigheter
     * såsom om en skriver ut fler geparder än zebror eller använder fel format**/
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
        } catch (Exception e) {
            System.out.println("Du måste skriva in en siffra!");
            mataInDjur();
        }
        diffZebror = zebra;
        skapaAntalDjur(gepard, zebra);

    }


    /**
     * En game-loop
     * som ska rensa, skriva ut spelplanen, flytta alla djur,
     * ställer tillbaka en boolean i djuren om dom flyttat sig eller inte till false inför nästa runda
     * och sist visar hur många djur som finns på spelplanen.
     * <p>
     * fortsätter tills spelplanen uppfyller villkoret vi satt.
     * <p>
     * När villkoret uppfylls skrivs vår Game-over skärm ut
     * <p>
     * thread.sleep(500)
     * innebär att programmet pausar i 500 millisekunder (halv sekund)
     * inte optimalt att köra i större program,
     * endast för redovisningens skull
     */
    public void runGame() {
        while (getZebraKills() != 50) {
            clearScreen();
            board.printSpelplan();
            vadFinns();
            resetFlytt();
            getDjur();
            getZebraKills();

            debug(); //Användes för att få exakta koordinater och antal djur, lättare att felsöka

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("mög"); // ja!
            }
        }
        board.skrivUtOutro(); //outrot skrivs ut
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
        } while (upptagen(x, y)); //något som testar om rutan är upptagen

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


    /**
     * Returnerar ett random värde från vår enum Flytta
     * så up, down, left eller right
     */
    public Flytta randomFlytt() {
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
    }

    /**
     * @param djurPos håller koll på antalet zebror och geparder
     * hämtar antal djur i sina respektive instancer, Zebra och Gepard
     */
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


    /**
     * Håller koll på antalet uppätna zebror
     *
     * @return
     */
    public static int getZebraKills() {
        int zebraKills = 0;
        if (diffZebror > getAntalZebror()) {
            zebraKills += diffZebror - getAntalZebror();
        }
        return zebraKills;
    }


    /**
     * Skapar upp till 20 nya zebror om antalet zebror går under antalet geparder
     */
    public void nyaZebror() {
        int total;
        if (getAntalZebror() < getAntalGeparder()) {
            total = (int) (getAntalGeparder() - getAntalZebror() + Math.random() * 20);
            skapaAntalDjur(0, total);

            diffZebror += total;
        }
    }


    /**
     * Metoden mäter avståndet varje enskilt djur har till alla andra djur
     * Var tänkt att användas för att styra djurens beteende men hann aldrig komma så långt
     */
/*    public void getDistance(Djur[] djurPos) {
        int distance;
        for (int i = 0; i < djurPos.length; i++) {
            for (int j = i + 1; j < djurPos.length; j++) {
                if (djurPos[i] != null && djurPos[j] != null) {
                    distance = (int) djurPos[i].getPosition().distance(djurPos[j].getPosition());
                    if (distance < 2 && djurPos[i].getTag() != djurPos[j].getTag()) {
                    } else if (distance < 2 && djurPos[i].getTag() == djurPos[j].getTag()) {
                    }
                }
            }
        }
    }*/

    /**
     * Söker igenom hela arrayen efter Djur
     * skickar vidare till kontroll för vilket djur koordinaten innehåller
     * för att bestämma hur det ska beté sig
     */
    public void vadFinns() {
        for (int i = 0; i < spelplan.length - 1; i++) {
            for (int j = 0; j < Game.spelplan[i].length; j++) {
                if (spelplan[i][j] != null) {
                    kontrollDjur(i, j);
                }
            }
        }
    }

    /**
     * Kontrollerar djuret
     * Om det redan fått flytta sig en gång hoppas de över.
     * Är det en Gepard, skickar den vidare och letar först efter en Zebra, hittar den ingen
     * får den en random flytt.
     * <p>
     * Zebra för tillfället flyttar sig endast random
     * <p>
     * Den sista metoden är en återställning av en kontroll
     * som görs så inte djuret råkar bli felaktigt borttaget
     * under de andra metodernas gång
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

    /**
     * Metod som stället om boolean flytt i varje enskilt djur
     * så dom är redo för att flytta sig nästa omgång.
     * <p>
     * utan en sån boolean kunde samma djur flytta sig ett okontrollerat antal gånger
     * mellan 2 olika utskrifter av spelplanen.
     */
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
     * En switch som tagit emot Djurets koordinater och ett håll den ska flytta sig
     *
     * @param x   Djurets x-koordinat
     * @param y   Djurets y-koordinat
     * @param dir bestäms av random eller om Geparden hittat en Zebra
     */
    public void flytta(int x, int y, Game.Flytta dir) {
        int speed = (int) (1 + Math.random() * 3);
        char djurTag = spelplan[x][y].getTag();
        Point djurPosition = new Point();
        djurPosition.setLocation(x,y);

        for (int i = 0; i < speed; i++) {

            int a = (int) djurPosition.getX();
            int b = (int) djurPosition.getY();
            resetDjur(a,b);

            switch (dir) {
                case up:

                    if (a == 0) {
                        moveDjur((int) (a + 1), b, djurTag); //anropar metod moveDjur för att genomföra rörelsen
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a + 1, b);
                        }
                        break;
                    } else {
                        moveDjur(a - 1, b, djurTag);
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a - 1, b);
                        }
                        break;
                    }

                case down:

                    if (a == 18) {
                        moveDjur(a - 1, b, djurTag);

                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a - 1, b);
                        }
                        break;
                    } else {
                        moveDjur(a + 1, b, djurTag);
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a + 1, b);
                        }
                        break;
                    }

                case left:
                    if (b == 0) {
                        moveDjur(a, b + 1, djurTag);
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a, b + 1);
                        }
                        break;
                    } else {
                        moveDjur(a, b - 1, djurTag);
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a, b - 1);
                        }
                        break;
                    }


                case right:

                    if (b == 58) {
                        moveDjur(a, b - 1, djurTag);
                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a, b - 1);


                        }
                        break;
                    } else {
                        moveDjur(a, b + 1, djurTag);

                        if (dennaFlyttKlar == true) {
                            djurPosition.setLocation(a, b + 1);


                        }
                        break;
                    }
            }
            setDennaFlyttKlar(false);


        }
    }


    /**
     * Kollar vilket djur som ska röra sig.
     * Valde att använda Tag istället för instanceof
     * för att slippa skriva spelplan hit och dit
     * <p>
     * Djuren får sina egna flytt-metoder.
     * Zebror rör sig bara om rutan är tom,
     * Geparder rör sig över allt utom andra Geparder
     */
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
     * Används i kontrollDjur() om det är en Gepard
     * för att söka runt sig efter en Zebra,
     * om det finns returneras den riktning som Zebran är i.
     * annars returneras randomFlytt.
     * <p>
     * outOfBounds() används för att undvika exception
     * om Geparden skulle stå emot en kant vid söktillfället.
     */
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
    /**en metod för att kontrollera i vilka positioner som vår spelplan tar slut
     * returnerar ett värde true/false till checkRuntOm så att geparden inte ser till en omgivning som är out of bounds**/
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

    /**
     * Ställer den gamla positionen till null
     * efter en lyckad flytt.
     */
    public void resetDjur(int x, int y) {
        spelplan[x][y] = null;

    }

    /**
     * Samlat upp get-set- och diverse
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
        this.diffZebror = zebror;
    }

    public int getZebror() {
        return diffZebror;
    }

    /**
     * Bonus,
     * Meningslösa metoder för programmets funktion
     * men fancy
     * <p>
     * debug har används för att visa exakta värden på djuren mellan varje print
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

}
