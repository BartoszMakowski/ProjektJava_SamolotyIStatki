package projekt;

import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public class Wycieczkowiec extends Statek{
    private int miejsca;
    private int zajeteMiejsca;
    private String firma;
    private List<Podrozny> pasazerowie;

    public Wycieczkowiec(Polozenie polozenie, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
    }


    public int getMiejsca() {
        return miejsca;
    }

    public int getZajeteMiejsca() {
        return zajeteMiejsca;
    }

    public void setZajeteMiejsca(int zajeteMiejsca) {
        this.zajeteMiejsca = zajeteMiejsca;
    }

    public String getFirma() {
        return firma;
    }

    public List<Podrozny> getPasazerowie() {
        return pasazerowie;
    }

    public void zmienPasazerow(){}
}
