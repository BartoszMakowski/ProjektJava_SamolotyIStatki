package projekt;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 * Created by bartosz on 19.10.15.
 */
public class Wycieczkowiec extends Statek implements Pasazerski{
    private int miejsca;
    private int zajeteMiejsca;
    private String firma;
    private List<Podrozny> pasazerowie;

    public Wycieczkowiec(Polozenie polozenie, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
    }
    
    public Wycieczkowiec(PortMorski lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (3 + Math.random() * 10), lokalizacja, null);
        this.miejsca = 2 + (int) (Math.random()*18);
        this.pasazerowie = new LinkedList<>();
        System.out.println("WSZYSTKO OK");
        
        for (int i=0; i<this.miejsca/2; i++){
            Podrozny p = new Podrozny(lokalizacja);
//            this.pasazerowie.add(p);
            ((PortMorski)this.getNajblizszyCel()).getOdwiedzajacy().add(p);
            Thread t = new Thread(p);
            t.setDaemon(true);
            t.start();
        }
        
        
        setObrazek(new ImageView(getClass().getResource("img/Samolot2.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
    }


    public int getMiejsca() {
        return miejsca;
    }

    public int getZajeteMiejsca() {
        return zajeteMiejsca;
    }

    public void setZajeteMiejsca(int zajeteMiejsca) {
        this.zajeteMiejsca = zajeteMiejsca;
    }

    public String getFirma() {
        return firma;
    }

    public List<Podrozny> getPasazerowie() {
        return pasazerowie;
    }
  

    @Override
    public void przesiadkaPasazera(Pasazerski dokad) {
        LinkedList<Podrozny> doUsuniecia = new LinkedList<>();
        for (Podrozny p : this.pasazerowie){
            synchronized(dokad){
                if (p.czyWysiasc(dokad)){
                    doUsuniecia.add(p);
                    dokad.dodajPasazera(p);
                }
            }
                System.out.println("NIECH ODPOCZNIE");
                p.setOdpoczywa(true);
        }
        for(Podrozny p : doUsuniecia){
            this.usunPasazera(p);
        }
    }

    @Override
    public void dodajPasazera(Podrozny pasazer) {
        pasazerowie.add(pasazer);
    }

    @Override
    public void usunPasazera(Podrozny pasazer) {
        pasazerowie.remove(pasazer);
    }

    @Override
    public boolean czyJestMiejsce() {
        if (getPasazerowie().size() < getMiejsca()){
            return true;
        }
        return false;
    }
    
    public void obslugaNaMiejscu(){
    int sen;
    if (getTrasa().get(0) instanceof Pasazerski){
        System.out.println("        " + this.getTrasa().get(0).getNazwa());
        przesiadkaPasazera((Pasazerski)getTrasa().get(0));
        sen = 1500 +(int)Math.random() * 3500;

        Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    getObrazek().setX(-30);
                    getObrazek().setY(-30);
                }
            });
    }
    else{
        sen = 150;
    }

    if (getTrasa().get(0) instanceof Lotnisko){
        tankuj();
    }

    try {
        Thread.sleep(sen);
    } catch (InterruptedException ex) {
        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
    }

    if(getTrasa().size()> 1){            
        zmienCel(getTrasa().get(1));
    }
    else{
        System.out.println("Dotarłem do: " + getNajblizszyCel().getNazwa() + ".\nKONIEC TRASY.");
        losujTrase(getPolozenie());
        zmienCel(this.getTrasa().get(1));
    }
    zwolnijPole();

    System.out.println("PASAŻEROWIE, WSIADAJCIE!");
    if (getTrasa().get(0) instanceof Pasazerski)
    {
        ((Pasazerski)getTrasa().get(0)).przesiadkaPasazera((Pasazerski)this);
    }

    getTrasa().get(0).startujPojazd(this);
    getTrasa().remove(0);
    }
    
    @Override
    public void run(){
//        Image gpojazd;
        while(isDzialaj())
        {
            ruszaniePojazdu();
            przemieszczaniePojazdu();
            getNajblizszyCel().stopujPojazd(this);
            zwolnijPole();
            konczenieTrasyPojazdu();
            obslugaNaMiejscu();                                                       
        }        
    }
}
