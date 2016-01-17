package projekt;

/**
 * Created by bartosz on 18.10.15.
 * Implementuje położenie.
 */
public class Polozenie {
    private int x;
    private int y;
    
    /**
     * Tworzy położenie.
     * @param x współrzędna X położenia
     * @param y współrzędna Y położenia
     */
    public Polozenie(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    /**
     * Zwraca współrzędną X położenia.
     * @return współrzędna X położenia
     */
    public int getX() {
        return x;
    }
    
    /**
     * Zwraca współrzędną Y położenia.
     * @return współrzędna Y położenia
     */
    public int getY() {
        return y;
    }
    
    /**
     * Ustawia współrzędną X położenia
     * @param x nowa współrzędna X
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Ustawia współrzędną Y położenia.
     * @param y nowa współrzędna Y 
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

    /**
     * Porównuje dwa położenia.
     * @param polozenie porównywane położenie
     * @return prawdziwość równości
     */
    public boolean equals(Polozenie polozenie) {
        return (this.x == polozenie.getX() && this.y == polozenie.getY());
    }
}
