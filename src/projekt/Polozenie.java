package projekt;

/**
 * Created by bartosz on 18.10.15.
 */
public class Polozenie {
    private int x;
    private int y;

    public Polozenie(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

//    @Override
    public boolean equals(Polozenie polozenie) {
        return (this.x == polozenie.getX() && this.y == polozenie.getY());
    }
}
