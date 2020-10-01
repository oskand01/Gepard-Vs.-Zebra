package com.company;

import java.awt.*;

public class Djur {

    private char tag;
    private int xPos;
    private int yPos;
    private Point position; //deklarerar en position som innehåller två värden, ex x och y

    private boolean flytt;


    public Djur() { //konstruktor
        tag = '0';
        xPos = -1;
        yPos = -1;
        position = new Point(getxPos(), getyPos()); //initierar en point och hämtar värdena för positionen på spelplanen x och y
    }

    public Djur(int x, int y) { //konstruktor
        this.xPos = x; //uppdaterar variablerna som deklarerats under public class
        this.yPos = y;
        this.tag = tag;
        this.position = new Point(getxPos(),getyPos());
    }


    public void setTag(char tag) {
        this.tag = tag;
    } //bekräftar djurets tag 'G' eller 'Z'. Identitetsmarkör.

    public char getTag() {
        return this.tag;
    } // hämtar djurets tag

    public int getxPos() {
        return this.xPos;
    } //hämtar positioner för x och y

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setFlytt(boolean flytt) {
        this.flytt = flytt;
    }

    public boolean harFlyttat() {
        return this.flytt;
    }

    public Point getPosition() {
        return position;
    } //point som inte användes i så stor utsträckning tyvärr, eftersom
                                                    //vi inte fick till olika hastigheter och de olika tillstånden hos djuren
                                                    // som vi hade tänkt implementera

    public void setPosition(int x, int y) {
        this.position = new Point(getxPos(), getyPos());
    }

    /**
     * Har använts vid debug för bl.a
     * att se djurens exakta x och y värden
     */
    @Override
    public String toString() {
        return "Djur{" +
                "tag=" + tag +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", position=" + position +
                ", flytt=" + flytt +
                '}';
    }
}
