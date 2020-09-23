package com.company;

public class Djur {

    private int x_pos;
    private int y_pos;
    //private int speed;
    private boolean alive;

    public Djur() {

        x_pos = 10;
        y_pos = 10;
        //speed = 1;
        alive = true;
    }

    public Djur(int speed) {
        this.x_pos = (int) (0 + Math.random() * 20);
        this.y_pos = (int) (0 + Math.random() * 60);
        //this.speed = speed;
        alive = true;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    @Override
    public String toString() {
        return "x_pos=" + x_pos +
                ", y_pos=" + y_pos +
                //", speed=" + speed +
                ", alive=" + alive;
    }
}
