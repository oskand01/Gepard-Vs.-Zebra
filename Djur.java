package company;

import java.awt.*;
import java.util.Random;
public class Djur {
    Random random = new Random();
    private char tag;
    private int xPos;
    private int yPos;
    private Point position;

    private boolean flytt;
    private int speed;



    public Djur() {
        tag = '0';
        xPos = -1;
        yPos = -1;
        position = new Point(getxPos(), getyPos());
        speed = 1;
    }



    public Djur(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.tag = tag;
        this.position = new Point(getxPos(),getyPos());
    }

    public Djur(int speed){

        getSpeed();

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

    /*public void setSpeed(int speed) {
        int max = 3;
        int min = 1;
        this.speed = random.nextInt(max-min+1) + min;

        }*/

        public int getSpeed(){
            int max = 3;
            int min = 1;
            this.speed = random.nextInt(max-min+1) + min;
            return this.speed;
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
