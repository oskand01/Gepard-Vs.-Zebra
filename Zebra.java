package company;

public class Zebra extends Djur {
    public boolean scared;


    public Zebra() {
        super();
        this.setTag('Z');
    }
    public Zebra(int speed){
        super(speed);

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
