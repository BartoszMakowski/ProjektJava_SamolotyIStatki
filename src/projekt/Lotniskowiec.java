package projekt;

import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public class Lotniskowiec extends Statek {
    private Uzbrojenie uzbrojenie;

    public Lotniskowiec(Polozenie lokalizacja, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa, Uzbrojenie uzbrojenie) {
        super(lokalizacja, predkosc, najblizszyCel, trasa);
        this.uzbrojenie = uzbrojenie;
    }


//    public SamolotWojskowy produkujSamolot() {
//        SamolotWojskowy samolot = new SamolotWojskowy();
//        return samolot;
//    }

    public Uzbrojenie getUzbrojenie() {
        return uzbrojenie;
    }
}
