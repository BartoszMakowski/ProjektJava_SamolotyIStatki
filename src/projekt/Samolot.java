package projekt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 18.10.15.
 */
public abstract class Samolot extends Pojazd {
    private Paliwo paliwo;
    private int liczebnoscPersonelu;
    private boolean usterka;
    private boolean czekanie;


    public Samolot(Polozenie polozenie, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
        this.usterka = false;
        int i = (int) (Math.random() * Swiat.trasy.get("" + polozenie.getX() + "_" + polozenie.getY()).size());
        List<Lokalizacja> mojaTrasa = new LinkedList<>();
        mojaTrasa.addAll(Swiat.trasy.get("" + polozenie.getX() + "_" + polozenie.getY()).get(i));
        this.setTrasa(mojaTrasa);
    }

    public void tankuj(){}

    public void zglosUsterke(){
        this.usterka = true;
    }

    public void lec(){}
}