package company;

public class Zebra extends Djur {

    public Zebra() {
        super();
        this.setTag('Z');
    }

    public Zebra(int x, int y) {
        super(x, y);
        this.setTag('Z');
        this.setFlytt(false);
    }
    public Zebra(int x, int y, boolean flytt) {
        super(x, y);
        this.setTag('Z');
        this.setFlytt(flytt);
    }

}
