package com.company;

import java.awt.*;

/*
Tänker om vi har som tumregel
att spelplan visar alla förändringar, 
men alla flytt sker i game? 

Då är det bättre att vi hanterar x och y i game
*/

public class SpelPlan implements Interface {
    private int x;
    private int y;
    private Point point; //Här är också en point


    public SpelPlan() {
        this.x = 20; //Tillfälliga världen
        this.y = 30;
        this.point = new Point(x, y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point pos) {
        pos.setLocation(getX(), getY());
        this.point = pos;
    }

    
    /*
    Denna metoden ska bytas ut
    Filar på ett mer effektivt sätt att visa djur,
    som inte kräver id
    */
    public void printSpelplan() {
        String s = "  ";
        String b = "_ ";
        String h = "|";
        //String g = "G ";
        //String z = "Z ";
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                point.setLocation(i, j); //Points position får värdet av index genom hela loopandet,
                // tänkte att det skulle kunna användas för att skriva ut var djuren är på planen, på något sätt
                getAnimalPositions(point);
                if (i == 0 || i == getX() - 1) {
                    System.out.print(b);
                } else if (j == 0 || j == getY() - 1) {
                    System.out.print(h);
                } else {
                    System.out.print(s);
                }
            }
            System.out.println();


        }
    }

    public void getAnimalPositions(Point point) {

        for (int i = 0; i < djur.length; i++) {
            if (djur[i].getPos().equals(point.getLocation()) && djur[i].getId() <= 10) {
                System.out.print("G ");
            } else if (djur[i].getPos().equals(point.getLocation()) && djur[i].getId() > 10) {
                System.out.print("Z ");
            }
            else {
                i = djur.length;
            }
        }
    }


}
