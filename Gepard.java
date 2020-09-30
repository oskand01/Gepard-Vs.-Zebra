package company;

public class Gepard extends Djur {
public boolean hunt;
    public Gepard() {
        super();
        this.setTag('G');

    }

    public Gepard(int speed){
        super(speed);
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
