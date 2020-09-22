package com.company;

import java.util.Random;

public class Zebra extends Djur {
    private int antalZebra;

    public Zebra(){
        Random random = new Random(); //instansierar ett nytt objekt random

        Direction directionRandom; //deklarerar enumtypen directionRandom
        directionRandom = Direction.values()[random.nextInt(Direction.values().length)];
        /*initierar enumtypen directRandom.
         values() hämtar alla värden inuti enumtypen
         det är av referenstyp så värdena kopieras
         random.nextInt tar slumpmässigt fram värdenas placering genom enumtypemns längd(.length)
         */

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
