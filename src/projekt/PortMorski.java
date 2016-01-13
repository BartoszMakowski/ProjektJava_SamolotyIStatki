package projekt;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bartosz on 19.10.15.
 */
public class PortMorski extends Lokalizacja implements Pasazerski{
    private List<Podrozny> odwiedzajacy;
    private List<Wycieczkowiec> zajetyPrzez;
    private int pojemnosc;
    private boolean aktywny;

    public PortMorski(int x, int y, String nazwa) {
        super(x, y, nazwa);
        this.odwiedzajacy = new LinkedList<>();
        this.zajetyPrzez = new LinkedList<>();
        this.aktywny = false;
        this.pojemnosc = 5;
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
    
    @Override
    public void stopujPojazd(Pojazd statek){
        if (statek instanceof Lotniskowiec) return;
        while(!zajetyPrzez.contains(statek)){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lotnisko.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized(this){
                System.out.println("CHCE DODAC STATEK");
                if (zajetyPrzez.size()<getPojemnosc() && !aktywny){
                    System.out.println("DODAJE STATEK");
                    this.zajetyPrzez.add((Wycieczkowiec)statek);
                    aktywny = true;
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
                    aktywny = false;
                }
            });
            t.setDaemon(true);
            t.start();
            }
}

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
                    }
                }
            }
        }
        
        for (Podrozny p : doUsuniecia){
            this.usunPasazera(p);
        }
    }

    @Override
    public void dodajPasazera(Podrozny pasazer) {
        pasazer.getPlan().remove(0);
        odwiedzajacy.add(pasazer);
    }

    @Override
    public void usunPasazera(Podrozny pasazer) {
        odwiedzajacy.remove(pasazer);
    }

    @Override
    public boolean czyJestMiejsce() {
        return true;
    }

    /**
     * @return the pojemnosc
     */
    public int getPojemnosc() {
        return pojemnosc;
    }
    
    /**
     *
     * @param statek
     */
    public void startujPojazd(Pojazd statek){
        synchronized(this){
            zajetyPrzez.remove(statek);
        }         
    }
}
