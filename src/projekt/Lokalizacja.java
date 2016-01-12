package projekt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 * Implementuje lokalizacje
 *
 */
public abstract class Lokalizacja {
    private int id;
    static private int ostatnieId;
    private String nazwa;
    private Polozenie polozenie;
    private List <Drogowskaz> odleglosci;
    /**
     * Tworzy lokalizacje
     * @param x wspolrzedna X lokalizacji
     * @param y wspolrzedna Y lokalizacji
     * @param nazwa nazwa lokalizacji
     */
    public Lokalizacja(int x, int y, String nazwa) {
        this.nazwa = nazwa;
        this.polozenie = new Polozenie(x,y);
        this.odleglosci = new LinkedList<Drogowskaz>();
        this.id = ostatnieId++;
    }

    /**
     * Zwraca liste drogowskazow
     * @return lista drogowskazow
     */
    public List<Drogowskaz> getOdleglosci() {
        return odleglosci;
    }

    /**
     * Zwraca polozenie lokalizacji
     * @return polozenie lokalizacji
     */
    public Polozenie getPolozenie() {
        return polozenie;
    }

    /**
     * Zwraca nazwe lokalizacji
     * @return nazwa lokalizacji
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Zwraca drogowskaz informujacy, jak dotrzec do docelowej lokalizacji.
     * @param lokalizacja docelowa lokalizacja
     * @return drogowskaz wskazujacy na docelowa lokalizacje
     */
    public Drogowskaz jakDojechac( Lokalizacja lokalizacja ){

        for(Drogowskaz d: this.odleglosci){
            if ( d.getDokad().getPolozenie().equals(lokalizacja.getPolozenie()) ){
                return d;
            }
        }
        return null;
    }

    /**
     * Umieszcza drogowskaz w lokalizacji
     * @param d drogowskaz do dodania
     * @return nowa liczba drogowskazow w lokalizacji
     */
    public int dodajDrogowskaz(Drogowskaz d){
        this.odleglosci.add(d);
        return odleglosci.size();
    }
    
    public void stopujPojazd(Pojazd pojazd){
        
    }
    
    public void startujPojazd(Pojazd pojazd){
        
    }

}
