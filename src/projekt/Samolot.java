package projekt;

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
    }

    public void tankuj(){}

    public void zglosUsterke(){
        this.usterka = true;
    }

    public void lec(){}
}