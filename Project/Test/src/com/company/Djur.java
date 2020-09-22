package com.company;

import java.util.Scanner;

public abstract class Djur {

    //Positionen i Array
    private int x;
    private int y;

    public Djur() {
        x = (int) (0 + Math.random() * 50); //random X
        y = (int) (0 + Math.random() * 50); //random Y

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




    public void Collision(Djur d) {

    }
}
