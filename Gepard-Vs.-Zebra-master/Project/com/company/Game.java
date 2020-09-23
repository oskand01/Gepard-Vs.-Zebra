package com.company;

import java.util.Scanner;

public class Game {

    private Djur[] d = new Djur[50];
    private int board[][] = new int[20][60];
    private boolean krock = false;
    private int animals;


    public Game() {
        System.out.println("Välkommen till den årliga Gepard vs. Zebra-tävlingen!");
        setAnimals(); //Antal djur från användare
        printSpelplan();
        printDjur();
    }

    public void setAnimals() {
        Scanner scan = new Scanner(System.in);
        do {
        System.out.println("Hur många geparder ska jaga zebrorna? Ange mellan 1-10");
            animals = scan.nextInt();
        } while (animals <= 0 || animals > 10);
        for (int i = 0; i < animals; i++) {
            Gepard gepard = new Gepard();
            setSpelPlan(gepard.getX(), gepard.getY());
            for (int j = 0; j < d.length; j++) {
                if(d[j] == null){
                    d[j] = gepard;
                    j = d.length;
                }
            }
        }
        for (int i = 0; i < animals*2; i++) {
            Zebra zebra = new Zebra();
            for (int j = 0; j < d.length; j++) {
                if(d[j] == null){
                    d[j] = zebra;
                    j = d.length;
                }
            }
        }
    }

    public void printDjur(){
        for (int i = 0; i < d.length; i++) {
            if(d[i] == null){
                i = d.length;
            }else System.out.println(d[i]);
        }
    }


    public void setSpelPlan(int x, int y) {
        board[x][y] = 1;
    }

    public void printSpelplan() {
        String s = " ";
        String b = "*";
        String g = "G";

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < 60; y++) {
                if (x == 0 || x == 19 || y == 0 || y == 59) {
                    System.out.print(b);
                } else if (board[x][y] == 0) {
                    System.out.print(s);
                } else{
                    System.out.print(board[x][y]);
                }

                }
            }

            System.out.println();
        }

    }




