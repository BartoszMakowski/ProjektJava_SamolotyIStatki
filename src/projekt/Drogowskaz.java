package projekt;

/**
 * Created by bartosz on 19.10.15.
 * Implementuje drogowskaz.
 */
public class Drogowskaz {
    private int odleglosc;
    private Lokalizacja dokad;
    private Kierunek kierunek;
    
    /**
     * Tworzy drogowskaz.
     * @param skad miejsce lokalizacji drogowskazu
     * @param dokad miejsce docelowe
     */
    public Drogowskaz(Lokalizacja skad, Lokalizacja dokad){
        this.dokad = dokad;
        this.odleglosc = dokad.getPolozenie().getX() - skad.getPolozenie().getX() + dokad.getPolozenie().getY() - skad.getPolozenie().getY();
        this.odleglosc *= Integer.signum(this.odleglosc);
        if(dokad.getPolozenie().getX() - skad.getPolozenie().getX() > 0)
            this.kierunek = Kierunek.PRAWO;
        else if (dokad.getPolozenie().getX() - skad.getPolozenie().getX() < 0)
            this.kierunek = Kierunek.LEWO;
        else if(dokad.getPolozenie().getY() - skad.getPolozenie().getY() > 0)
            this.kierunek = Kierunek.DOL;
        else
            this.kierunek = Kierunek.GORA;
        
//        dokad.getPolozenie().getX() - skad.getPolozenie().getX()
    }
    
    /**
     * Tworzy drogowskaz
     * @param odleglosc odleglosc do celu
     * @param dokad cel
     * @param kierunek kierunek celu
     */
    public Drogowskaz(int odleglosc, Lokalizacja dokad, Kierunek kierunek) {
        this.odleglosc = odleglosc;
        this.dokad = dokad;
        this.kierunek = kierunek;
    }
    
    /**
     * Tworzy drogowskaz.
     * @param odleglosc odleglosc do celu
     * @param x wspolrzedna X celu
     * @param y wspolrzedna Y celu
     * @param kierunek kierunek celu
     */
    public Drogowskaz(int odleglosc, int x, int y, Kierunek kierunek){
        this.odleglosc = odleglosc;
        this.kierunek = kierunek;
//        System.out.println( "Ile lokalizacji?: " + Projekt.getLotniska().size() );
        this.dokad = Swiat.getLotniska().get( (x + "_" + y).toString() );

    }
    /**
     * Zwraca odleglosc do celu.
     * @return odleglosc do celu
     */
    public int getOdleglosc() {
        return odleglosc;
    }
    
    /**
     * Zwraca cel.
     * @return cel
     */
    public Lokalizacja getDokad() {
        return dokad;
    }
    
    /**
     * Zwraca kierunek celu
     * @return kierunek celu
     */
    public Kierunek getKierunek() {
        return kierunek;
    }

}
/**
 * enum z 5 kierunkami
 * @author bartosz
 */
enum Kierunek {
    GORA, DOL, LEWO, PRAWO, ZADEN

}
