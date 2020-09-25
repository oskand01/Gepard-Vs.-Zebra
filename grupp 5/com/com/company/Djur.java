package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Scanner;

public abstract class Djur implements Interface {
    /*
    Detta borde flyttas till Game
    Random random = new Random(); 
    
    enum Direction{up, down, left, right}   //initierar en enumtyp för de olika riktningarna som djuren ska kunna röra sig i
    
    Direction directionRandom;
    //deklarerar directionRandom av enumtypen Direction, ska nedan används för att slumpa fram en label/riktning för djuret
    */


    private int x;
    private int y;
    private Point pos; //Nytt! Point anger en position och innehåller ett X-värde och ett Y-värde: Point(int x, int y)
    //Skitbra för att ta reda på och jämföra positioner

    private int id; //Tänkte byta ut detta emot char tag; istället, om nin skriver nya metoder, då slipper vi loopa massa vektorer
    // private int speed;   kommenterar ut detta tills vi får basen att funka
    private boolean alive;
    private int animals;

    
    /*
    Tilldelar en random position till djuret
    Behöver en metod för att se om cellen är upptagen
    */
    public Djur(){
        this.x = (int) (1 + Math.random() * 19);
        this.y = (int) (1 + Math.random() * 29);
        this.pos = new Point(getX(), getY());
        alive = true;
        //speed = 1; //Sätter en grundspeed för alla djur som sen ändras i gepard- och zebra-klasserna, utkommenterad tills vidare
    }
    /*
    Metod till senare, kan anropas genom att ge parametern Gepard eller Zebra specifikt när dom ska flyttas
    public Djur(Djur d){
    this.setPos(a.getPos()
    }
    */

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

    
    /*
    set getID bytas antagligen ut till char tag
    */
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

    /*
    Skitbra, men borde nog ligga i game :)
    */
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











  /*  
  kommenterar ut denna sålänge, kan vara överflödig längre fram, men osäker
  public void printDjur() {
        for (int i = 0; i < djur.length; i++) {
            if (djur[i] == null) {
                i = djur.length;
            } else System.out.println(djur[i]);
        }
    }
    */

    @Override
    public String toString() {
        return "id= "+ id + " pos: " + pos +
                ", alive=" + alive;
    }
}
