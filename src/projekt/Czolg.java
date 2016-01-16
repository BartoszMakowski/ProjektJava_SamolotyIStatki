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
 */
public class Czolg extends Pojazd implements Pasazerski{
    private final int miejsca;
    private List <Podrozny> pasazerowie;
    private static Skrzyzowanie s_pom;
    private static Lokalizacja kity;
    private static Lokalizacja pieczyska;
    private static Pasazerski cel;
    
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
                
//        s_pom.dodajDrogowskaz(new Drogowskaz(s_pom, kity));
//        s_pom.dodajDrogowskaz(new Drogowskaz(s_pom, pieczyska));
//        kity.dodajDrogowskaz(new Drogowskaz(kity, s_pom));
//        pieczyska.dodajDrogowskaz(new Drogowskaz(pieczyska, s_pom));
//        znajdzTrase(kity);
        
    }

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
//            System.out.println("NIECH ODPOCZNIE");
            p.setOdpoczywa(true);
        }
        for(Podrozny p : doUsuniecia){
            this.usunPasazera(p);
        }
    }

    @Override
    public void dodajPasazera(Podrozny pasazer) {
        this.getPasazerowie().add(pasazer);
    }

    @Override
    public void usunPasazera(Podrozny pasazer) {
        getPasazerowie().remove(pasazer);
    }

    @Override
    public boolean czyJestMiejsce() {
        if (this.getPasazerowie().size() < this.getMiejsca()){
            return true;
        }
        return false;
    }

    /**
     * @return the miejsca
     */
    public int getMiejsca() {
        return miejsca;
    }

    /**
     * @return the pasazerowie
     */
    public List <Podrozny> getPasazerowie() {
        return pasazerowie;
    }
//    
//    @Override
//    public void znajdzTrase(Lokalizacja skad){
//        System.out.println("DOBRA METODA");
//        if(skad.equals(pieczyska)){
//            getTrasa().clear();
//            getTrasa().add(pieczyska);
//            getTrasa().add(s_pom);
//            getTrasa().add(kity);            
//        }
//        else{
//            getTrasa().clear();
//            getTrasa().add(kity);
//            getTrasa().add(s_pom);
//            getTrasa().add(pieczyska);
//        }    
//        System.out.println("TRASA: " + getTrasa());       
//    }
    
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
    
    @Override
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
    
    
    
}
