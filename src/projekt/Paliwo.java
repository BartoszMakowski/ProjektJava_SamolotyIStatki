package projekt;

/**
 * Created by bartosz on 18.10.15.
 */
public class Paliwo {
    private int maksymalne;
    private int aktualne;

    public float getAktualne() {
        return aktualne;
    }

    public void setAktualne(int aktualne) {
        if (aktualne >= 0.0 && aktualne <= 1.0) {
            this.aktualne = aktualne;
        }
    }

    public int getMaksymalne() {
        return maksymalne;
    }

    public void setMaksymalne(int maksymalne) {
        if (maksymalne >= 25 && maksymalne <= 100) {
            this.maksymalne = maksymalne;
        }
    }
}
