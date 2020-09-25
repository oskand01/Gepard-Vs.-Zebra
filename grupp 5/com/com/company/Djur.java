package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Scanner;

public abstract class Djur implements Interface {
    Random random = new Random(); // instansierar ett nytt objekt random
    enum Direction{up, down, left, right}
    //initierar en enumtyp för de olika riktningarna som djuren ska kunna röra sig i
    Direction directionRandom;
    //deklarerar directionRandom av enumtypen Direction, ska nedan används för att slumpa fram en label/riktning för djuret


    private int x;
    private int y;
    private Point pos; //Nytt! Point anger en position och innehåller ett X-värde och ett Y-värde: Point(int x, int y)
    //Skitbra för att ta reda på och jämföra positioner

    private int id; //Geparder får id 1-10, zebror får id 11 och uppåt
    private int speed;
    private boolean alive;
    private int animals;

    public Djur(){
        this.x = (int) (1 + Math.random() * 19);
        this.y = (int) (1 + Math.random() * 29);
        this.pos = new Point(getX(), getY()); //Här får "Point pos" sitt värde
        alive = true;
        speed = 1; //Sätter en grundspeed för alla djur som sen ändras i gepard- och zebra-klasserna
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getPos() { //Hämtar positionen på djuret

        return pos;
    }

    public void setPos(int x, int y) {
        this.pos = new Point(getX(), getY());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) { //Metod för att
        this.speed = speed;
    }

    public int getAnimals() {
        return animals;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }

    public Djur[] getDjur() {
        return djur;
    }

    public void setDirectionRandom(Direction directionRandom) {

        directionRandom = Direction.values()[random.nextInt(Direction.values().length)];

        switch (directionRandom) {
            case up:
                this.y = getY()-getSpeed();
                setPos(x, y);
                break;
            case down:
                this.y = getY()+getSpeed();
                setPos(x, y);
                break;

            case left:
                this.x = getX()+getSpeed();
                setPos(x, y);
                break;
            case right:
                this.x = getX()-getSpeed();
                setPos(x, y);
                break;

            default:
        }

        this.directionRandom = directionRandom;

    }
    public Direction getDirectionRandom(){return directionRandom;}











    public void printDjur() {
        for (int i = 0; i < djur.length; i++) {
            if (djur[i] == null) {
                i = djur.length;
            } else System.out.println(djur[i]);
        }
    }

    @Override
    public String toString() {
        return "id= "+ id + " pos: " + pos +
                ", alive=" + alive;
    }
}
