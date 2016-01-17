/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 *
 * @author bartosz
 * Implementuje czołg.
 */
public class Czolg extends Pojazd implements Pasazerski{
    private final int miejsca;
    private List <Podrozny> pasazerowie;
    private static Skrzyzowanie s_pom;
    private static Lokalizacja kity;
    private static Lokalizacja pieczyska;
    private static Pasazerski cel;
    
    /**
     * Tworzy czołg.
     */
    public Czolg(){
        super(Swiat.getLotniskaCywilne().get("365_200").getPolozenie(),-150);
//        System.out.println("AAA: " + Swiat.getLotniskaCywilne().get("365_200"));
        this.miejsca = 7;
        this.pasazerowie = new LinkedList<>();
        //        System.out.println("WSZYSTKO OK");

        
        setObrazek(new ImageView(getClass().getResource("img/Czolg.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        System.out.println("" + getPolozenie().getX() + "   " + getPolozenie().getY());
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
        setModyfikatorX(0);
        setModyfikatorY(0);
//        s_pom = new Skrzyzowanie(365, 145, "S NA WYSPIE");
        kity = Swiat.getLokalizacje().get("365_200");
        pieczyska = Swiat.getLokalizacje().get("315_145");
        getTrasa().add(pieczyska);        
    }

    /**
     * Przenosi pasażerów z czołgu do lokalizacji, jeśli jest ona w ich planie."
     * @param dokad cel przesiadki
     */
    @Override
    public void przesiadkaPasazera(Pasazerski dokad) {
        LinkedList<Podrozny> doUsuniecia = new LinkedList<>();
        for (Podrozny p : this.getPasazerowie()){
            synchronized(dokad){
                if (p.czyWysiasc(dokad)){
                    doUsuniecia.add(p);
                    dokad.dodajPasazera(p);
                }
            }
            p.setOdpoczywa(true);
        }
        for(Podrozny p : doUsuniecia){
            this.usunPasazera(p);
        }
    }
    
    /**
     * Bezwarunkowo dodaje pasażera do listy pasażerów czołgu.
     * @param pasazer pasażer do dodania
     */
    @Override
    public void dodajPasazera(Podrozny pasazer) {
        this.getPasazerowie().add(pasazer);
    }
    /**
     * Usuwa pasażera z listy pasażerów czołgu.
     * @param pasazer pasażer do usunięcia
     */
    @Override
    public void usunPasazera(Podrozny pasazer) {
        getPasazerowie().remove(pasazer);
    }
    /**
     * Sprawdza, czy są wolne miejsca dla pasażerów.
     * @return istnienie wolnych miejsc
     */
    @Override
    public boolean czyJestMiejsce() {
        if (this.getPasazerowie().size() < this.getMiejsca()){
            return true;
        }
        return false;
    }

    /**
     * Zwraca liczbę wolnych miejsc.
     * @return liczba wolnych miejsc
     */
    public int getMiejsca() {
        return miejsca;
    }

    /**Zwraca listę pasażerów w czołgu.
     * @return lista pasażerów
     */
    public List <Podrozny> getPasazerowie() {
        return pasazerowie;
    }

    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {                   
                try {
                    przejazdCzolgu();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Czolg.class.getName()).log(Level.SEVERE, null, ex);
                }
                obslugaNaMiejscu(); 
                
            }        
    }
    /**
     * Implementuje obsługę w miejscach docelowych.
     */
//    @Override
    public void obslugaNaMiejscu(){
        przesiadkaPasazera(cel);
        int sen;
        sen = 7500;
        tankuj(999);

            Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        getObrazek().setX(-30);
                        getObrazek().setY(-30);
                    }
                }); 
        try {
            Thread.sleep(sen);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
        }
        cel.przesiadkaPasazera(this);

    }
    
    private void przejazdCzolgu() throws InterruptedException{
        if(getPolozenie().getX() == 365){
            cel = (Pasazerski)(pieczyska);
            setDeltaX(0);
            setDeltaY(-1);
            while(getPolozenie().getY() != 145){
                przemieszczanieCzolgu();
            }
            setDeltaX(-1);
            setDeltaY(0);
            while(getPolozenie().getX() != 315){
                przemieszczanieCzolgu();
            }
            getTrasa().remove(0);
            getTrasa().add(kity);
        }
        else{
            cel = (Pasazerski)(kity);
            setDeltaX(1);
            setDeltaY(0);
            while(getPolozenie().getX() != 365){
                przemieszczanieCzolgu();
            }
            setDeltaX(0);
            setDeltaY(1);
            while(getPolozenie().getY() != 200){
                przemieszczanieCzolgu();
            }
            getTrasa().remove(0);
            getTrasa().add(pieczyska);
            
        }
    }
    
    private void przemieszczanieCzolgu() throws InterruptedException{
        przemiescSie();
        Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    getObrazek().setX(getPolozenie().getX()-8 + getModyfikatorX());
                    getObrazek().setY(getPolozenie().getY()-8 + getModyfikatorY());
                }
            });
    }
    
    @Override
    public String toString() {
        return "Rudy 102";
    }
    
    @Override
    public int getPredkosc(){
        return 1;
    }
    
    
    
}
