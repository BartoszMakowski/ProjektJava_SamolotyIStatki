package projekt;

import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
enum Uzbrojenie{
    A,B,C,D,E};

public class SamolotWojskowy extends Samolot {
    private Uzbrojenie uzbrojenie;

    public SamolotWojskowy(Polozenie lokalizacja, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(lokalizacja, predkosc, najblizszyCel, trasa);
    }

    public Uzbrojenie getUzbrojenie() {
        return uzbrojenie;
    }
}
