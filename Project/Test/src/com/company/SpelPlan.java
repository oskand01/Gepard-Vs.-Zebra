package com.company;

public class SpelPlan extends Game {
    private int x;
    private int y;

    public SpelPlan() {
        x = 50;
        y = 50;

    }

    public void setX(int x) {
        if (x > 0) {
            this.x = x;
        }
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        if (y > 0) {
            this.y = y;
        }

    }

    public int getY() {
        return y;
    }



    /*





 */
}
