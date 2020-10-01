package com.company;

public class Gepard extends Djur {

    public Gepard() {
        super();
        this.setTag('G');
    }

    public Gepard(int x, int y) {
        super(x, y);
        this.setTag('G');
        this.setFlytt(false);
    }
    public Gepard(int x, int y, boolean flytt) {
        super(x, y);
        this.setTag('G');
        this.setFlytt(flytt);
    }

}
