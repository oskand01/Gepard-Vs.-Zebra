package com.company;

public class Zebra extends Djur{
    private boolean scared;

    public Zebra(){
        super();
        this.setTag('Z');
        setSpeed(2);
        scared = false;
    }
    public Zebra(int x, int y){
        super(x, y);
        this.setTag('Z');
        setSpeed(2);
        setScared(scared);

    }

    public void setScared(boolean scared){
        if(scared){
            setSpeed(3);
        }
        else{
            setSpeed(2);

        }

    }



    }
