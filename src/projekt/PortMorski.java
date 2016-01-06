package projekt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public class PortMorski extends Lokalizacja {
    private List<Podrozny> odwiedzajacy;
    private List<Wycieczkowiec> zajetyPrzez;

    public PortMorski(int x, int y, String nazwa) {
        super(x, y, nazwa);
        this.odwiedzajacy = null;
        this.zajetyPrzez = null;
    }

    public List<Wycieczkowiec> getZajetyPrzez() {
        return zajetyPrzez;
    }

    public void setZajetyPrzez(List<Wycieczkowiec> zajetyPrzez) {
        this.zajetyPrzez = zajetyPrzez;
    }

    public List<Podrozny> getOdwiedzajacy() {
        return odwiedzajacy;
    }

    public void setOdwiedzajacy(List<Podrozny> odwiedzajacy) {
        this.odwiedzajacy = odwiedzajacy;
    }
}
