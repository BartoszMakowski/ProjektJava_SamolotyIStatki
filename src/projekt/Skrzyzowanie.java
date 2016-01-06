package projekt;

import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public class Skrzyzowanie extends Lokalizacja {
    private Pojazd zajetePrzez;

    public Skrzyzowanie(int x, int y, String nazwa) {
        super(x, y, nazwa);
    }

    public Pojazd getZajetePrzez() {
        return zajetePrzez;
    }

    public void setZajetePrzez(Pojazd zajetePrzez) {
        this.zajetePrzez = zajetePrzez;
    }

}
