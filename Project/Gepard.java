package com.company;

import java.util.Random;

public class Gepard extends Djur  {
    private int antalGepard;

    public Gepard(){
        Random random = new Random(); //instansierar ett nytt objekt random

        Direction directionRandom;
        directionRandom = Direction.values()[random.nextInt(Direction.values().length)];
        //

        switch (directionRandom) {
            case up:

                break;
            case down:

                break;
            case left:

                break;
            case right:

                break;
            case upLeft:

                break;
            case downLeft:

                break;
            case upRight:

                break;
            case downRight:

                break;

            default:


        }


    }



}
