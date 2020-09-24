package com.company;

import java.awt.*;

public class SpelPlan {
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

    public void setPoint(Point point) {
        this.point = point;
    }

    public void printSpelplan() {
        String s = "  ";
        String b = "- ";
        String h = "|";
        String g = "G ";
        String z = "Z ";
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                //if ()
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


}