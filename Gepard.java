package com.company;

public class Gepard extends Djur{
    boolean hungry;
    public Gepard(){
        super();
        this.setTag('G');
        setSpeed(1);
        hungry = false;

    }

    public Gepard(int x, int y){
        super(x, y);
        this.setTag('G');
        setSpeed(1);
        setHungry(hungry);

    }

    public void setHungry(boolean hungry){
        if(hungry){
            setSpeed(2);
        }
        else{
            setSpeed(1);
        }

    }


    }

