package company;

public class Gepard extends Djur {

    public Gepard() {
        super();
        this.setTag('G');
        setSpeed(1);
    }

    public Gepard(int x, int y) {
        super(x, y);
        this.setTag('G');
        this.setFlytt(false);
        this.setSpeed(1);
    }
    public Gepard(int x, int y, boolean flytt) {
        super(x, y);
        this.setTag('G');
        this.setFlytt(flytt);
        this.setSpeed(1);
    }

}
