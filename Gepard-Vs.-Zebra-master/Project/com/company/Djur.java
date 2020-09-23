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
        this.id = (int) (0 + Math.random() * 10);
        //this.speed = speed;
        alive = true;
    }

    public int getX() {
        return x_pos;
    }

    public int getY() {
        return y_pos;
    }

    @Override
    public String toString() {
        return "id= "+ id + " x_pos=" + x_pos +
                ", y_pos=" + y_pos +
                //", speed=" + speed +
                ", alive=" + alive;
    }
}
