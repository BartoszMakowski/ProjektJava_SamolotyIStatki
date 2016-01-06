package projekt;

import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public abstract class Statek extends Pojazd {

    public Statek(Polozenie polozenie, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
    }

    public void plyn(){}
}
