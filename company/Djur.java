package com.company;

import java.awt.*;

public class Djur {

    private char tag;
    private int xPos;
    private int yPos;
    private Point position;

    private boolean flytt;


    public Djur() {
        tag = '0';
        xPos = -1;
        yPos = -1;
        position = new Point(getxPos(), getyPos());
    }

    public Djur(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.tag = tag;
        this.position = new Point(getxPos(),getyPos());
    }


    public void setTag(char tag) {
        this.tag = tag;
    }

    public char getTag() {
        return this.tag;
    }

    public int getxPos() {
        return this.xPos;
    }

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
    }

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
