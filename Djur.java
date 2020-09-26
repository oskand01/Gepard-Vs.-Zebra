package com.company;

public class Djur {

    private char tag;
    private int xPos;
    private int yPos;
    private int speed;

    public Djur(){
        tag = '0';
        xPos = -1;
        yPos = -1;
        speed = 1;
    }

    public Djur(int x, int y){
        this.xPos = x;
        this.yPos = y;
        this.tag = tag;
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

    public void setSpeed(int speed){
        this.speed = speed;

    }

    public int getSpeed(){
        return speed;

    }




}
