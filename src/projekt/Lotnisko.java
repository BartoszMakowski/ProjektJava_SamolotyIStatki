package projekt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
enum TypPortu {
    WOJSKOWY, CYWILNY
}
/**
 * Implementuje lotnisko.
 * @author bartosz
 */
public class Lotnisko extends Lokalizacja {
    private int pojemnosc;
    private int zajetosc;
    private TypPortu rodzaj;
    private List<Podrozny> odwiedzajacy;
    private List<Samolot> zajetePrzez;

    /**
     * Tworzy lotnisko.
     * @param x wspolrzedna X lotniska
     * @param y wspolrzedna Y lotniska
     * @param nazwa nazwa lotniska
     * @param pojemnosc pojemnosc lotniska
     * @param rodzaj rodzaj lotniska (cywilne/wojskowe)
     */
    public Lotnisko(int x, int y, String nazwa, int pojemnosc, TypPortu rodzaj) {
        super(x, y, nazwa);
        this.pojemnosc = pojemnosc;
        this.zajetosc = 0;
        this.rodzaj = rodzaj;
        this.odwiedzajacy = new LinkedList<>();
        this.zajetePrzez = new ArrayList<>();
    }


    /**
     * Zwraca pojemnosc lotniska.
     * @return pojemnosc lotniska
     */
    public int getPojemnosc() {
        return pojemnosc;
    }
    /**
     * Zwraca aktualna liczbe samolotow przebywajacych na lotnisku.
     * @return aktualna liczba samolotow na lotnisku
     */
    public int getZajetosc() {
        return zajetosc;
    }
    /**
     * Zmienia aktualna liczbe samolotow przebywajacych na lotnisku.
     * @param zajetosc aktualna liczba samolotow na lotnisku
     */
    public void setZajetosc(int zajetosc) {
        this.zajetosc = zajetosc;
    }
    /**
     * Zwraca rodzaj lotniska (cywilne/wojskowe).
     * @return rodzaj lotniska
     */
    public TypPortu getRodzaj() {
        return rodzaj;
    }
    
    /**
     * Zwraca liste podroznych znajdujacych sie na lotnisku.
     * @return  lsita podroznych obecnych na lotnisku
     */
    public List<Podrozny> getOdwiedzajacy() {
        return odwiedzajacy;
    }
    
    /**
     * Ustawia liste podroznych znajdujacych sie na lotnisku.
     * @param odwiedzajacy lista odwiedzajacych
     */
    public void setOdwiedzajacy(List<Podrozny> odwiedzajacy) {
        this.odwiedzajacy = odwiedzajacy;
    }

    /**
     * Zwraca liste samolotow znajdujacych sie na lotnisku.
     * @return lista samolotow na lotnisku
     */
    public List<Samolot> getZajetePrzez() {
        return zajetePrzez;
    }
    
    /**
     * Ustawia liste samolotow znajdujacych sie na lotnisku.
     * @param zajetePrzez lista samolotow na lotnisku 
     */
    public void setZajetePrzez(List<Samolot> zajetePrzez) {
        this.zajetePrzez = zajetePrzez;
    }
    
    @Override
    public void stopujPojazd(Pojazd samolot){
        synchronized(this.zajetePrzez){
            if(this.zajetePrzez.size()<this.pojemnosc){
                this.zajetePrzez.add((Samolot)samolot);
//                System.out.println("\n\n\n\n" + this.zajetePrzez.size());
//                return true;
                
            }                
        }
//        return false; 
    }
    
    @Override
    public void startujPojazd(Pojazd samolot){
        synchronized(this.zajetePrzez){
            this.zajetePrzez.remove((Samolot)samolot);
//            this.zajetosc--;
            System.out.println(this.zajetePrzez.size());
        }         
    }
    
    public void zaklepMiejsce(Pojazd samolot){
        synchronized(this){
            if(this.zajetosc<this.pojemnosc)
                this.zajetosc++;
        }  
    }
}