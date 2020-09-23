package com.company;

public class Djur {

    private int x_pos;
    private int y_pos;
    private int id;
    //private int speed;
    private boolean alive;


    public Djur(){
        this.x_pos = (int) (0 + Math.random() * 20);
        this.y_pos = (int) (0 + Math.random() * 60);
        //this.speed = speed;
        alive = true;
    }

    @Override
    public String toString() {
        return "x_pos=" + x_pos +
                ", y_pos=" + y_pos +
                //", speed=" + speed +
                ", alive=" + alive;
    }
}
