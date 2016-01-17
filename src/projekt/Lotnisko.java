package projekt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class Lotnisko extends Lokalizacja implements Pasazerski{
    private int pojemnosc;
    private int zajetosc;
    private TypPortu rodzaj;
    private List<Podrozny> odwiedzajacy;
    private List<Samolot> zajetePrzez;
    private boolean aktywne;

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
        this.aktywne = false;
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
    /**
     * Pozwala samolotowi wylądować dopiero wtedy, gdy będzie dla niego miejsce na lotnisku.
     * @param samolot samolot, który chce wylądować
     */
//    @Override
    public void stopujPojazd(Pojazd samolot){
//        System.out.println("COS EWIDENTNIE NIE DZIAŁA. NAPRAW TO!");
        while(!zajetePrzez.contains(samolot)){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lotnisko.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized(this){
//                System.out.println("CHCE DODAC SAMOLOT");
                if (this.zajetePrzez.size()<this.pojemnosc && !aktywne){
//                    System.out.println("DODAJE SAMOLOT");
                    this.zajetePrzez.add((Samolot)samolot);
                    aktywne = true;
                }
            }
            
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Lotnisko.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    aktywne = false;
                }
            });
            t.setDaemon(true);
            t.start();
            }
            

    }
    /**
     * Dodaje samolot do listy samolotów na lotnisku.
     * @param samolot samolot do dodania
     */   
    @Override
    public void startujPojazd(Pojazd samolot){
        synchronized(this){
            this.zajetePrzez.remove((Samolot)samolot);
        }         
    }
    /**
     * Usuwa samolot z listy samolotów na lotnisku.
     * @param samolot samolot do usunięcia z listy
     */
    public void zaklepMiejsce(Pojazd samolot){
        synchronized(this){
            if(this.zajetosc<this.pojemnosc)
                this.zajetosc++;
        }  
    }
    
    /**
     * Przenosi pasażera z lotniska do samolotu, jeśli samolot leci w pożądanym przez pasażera kierunku.
     * @param dokad samolot, do którego może odbyć się przsiadka
     */
    @Override
    public void przesiadkaPasazera(Pasazerski dokad) {
        LinkedList<Podrozny> doUsuniecia = new LinkedList<>();
        for (Podrozny pasazer : this.odwiedzajacy){
            if (!pasazer.isOdpoczywa()){
//                System.out.println("NIE ODPOCZYWAM");
                synchronized(dokad){
                    if ( (pasazer.czyWsiasc(dokad)) && (dokad.czyJestMiejsce()) ) {
                        doUsuniecia.add(pasazer);
                        dokad.dodajPasazera(pasazer);
//                        pasazer.getPlan().remove(0);
                    }
                }
            }
        }
        
        for (Podrozny p : doUsuniecia){
            this.usunPasazera(p);
        }
    }
    
    /**
     * Dodaje pasażera do odwiedzających lotnisko.
     * @param pasazer pasażer do dodania
     */
    @Override
    public void dodajPasazera(Podrozny pasazer) {
        pasazer.getPlan().remove(0);
        this.odwiedzajacy.add(pasazer);
    }
    
    /**
     * Usuwa pasażera z odwiedzających lotnisko.
     * @param pasazer pasażer do usunięcia
     */
    @Override
    public void usunPasazera(Podrozny pasazer) {
        this.odwiedzajacy.remove(pasazer);
    }
    
    /**
     * Odpowiada, czy na lotnisku jest miejsce dla pasażera.
     * @return dostępność wolnych miejsc
     */
    @Override
    public boolean czyJestMiejsce() {
        if (this.getRodzaj().equals(getRodzaj().CYWILNY)){
            return true;
        }
        return false;
    }
    
}