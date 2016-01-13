package projekt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
        
        
        setObrazek(new ImageView(getClass().getResource("img/Wycieczkowiec1.png").toExternalForm()));
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
//                System.out.println("NIECH ODPOCZNIE");
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

    if (getTrasa().get(0) instanceof PortMorski){
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
//        System.out.println("Dotarłem do: " + getNajblizszyCel().getNazwa() + ".\nKONIEC TRASY.");
        znajdzTrase(getNajblizszyCel());
        zmienCel(this.getTrasa().get(1));
    }
    zwolnijPole();

//    System.out.println("PASAŻEROWIE, WSIADAJCIE!");
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
    
    @Override
    public void usun(){
        this.pasazerowie = new LinkedList<>();
        super.usun();
        
    }
    
    public void znajdzTrase(Lokalizacja skad){
        LinkedList<Lokalizacja> znalezionaTrasa = new LinkedList<>();
        HashMap<String, Lokalizacja> punkty = new HashMap<>();
        HashMap<String, Lokalizacja> poprzednik = new HashMap<>();
        HashMap<String, Integer> odleglosc = new HashMap<>();
        Lokalizacja l;
        
        ArrayList<PortMorski> listaPortow = new ArrayList<>();
        listaPortow.addAll(Swiat.getPortyMorskie().values());
               
        punkty.putAll(Swiat.getLokalizacje());
//        for (String s : Swiat.getLotniskaWojskowe().keySet()){
//            punkty.remove(s);
//        }
        
        for (String s : punkty.keySet()){
            odleglosc.put(s, 99999);
        }
//        System.out.println(odleglosc);
        
        PriorityQueue<String> kolejka = new PriorityQueue<String>(punkty.keySet().size(), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if ( odleglosc.get(o1) < odleglosc.get(o2) ){
                    return -1;
                }
                else if ( odleglosc.get(o1) > odleglosc.get(o2) ){
                    return 1;
                }
                return 0;                           
            }
        });
               
        
                
        odleglosc.replace(skad.getPolozenie().getX() + "_" + skad.getPolozenie().getY(), 0);
        
        kolejka.addAll(punkty.keySet());
        while (kolejka.peek() != null){
            l = punkty.get(kolejka.poll());
            int odlegloscTutaj = odleglosc.get(l.getPolozenie().getX() + "_" + l.getPolozenie().getY());
//            System.out.println(odlegloscTutaj);
            
//            System.out.println(l.getOdleglosci().size());
            for (Drogowskaz d : l.getOdleglosci()){
                if(odleglosc.get(d.getDokad().getPolozenie().getX() + "_" + d.getDokad().getPolozenie().getY()) >
                         odlegloscTutaj + d.getOdleglosc()){
//                    System.out.println("ZAMIANA!");
                    odleglosc.replace(d.getDokad().getPolozenie().getX() + "_" + d.getDokad().getPolozenie().getY(),
                            odlegloscTutaj + d.getOdleglosc());
                    kolejka.add(d.getDokad().getPolozenie().getX() + "_" + d.getDokad().getPolozenie().getY());
                    poprzednik.put(d.getDokad().getPolozenie().getX() + "_" + d.getDokad().getPolozenie().getY(), l);
//                    System.out.println(poprzednik.get(d.getDokad().getPolozenie().getX() + "_" + d.getDokad().getPolozenie().getY()).getNazwa());
                }
            }
            
        }
//        System.out.println(poprzednik.get("90_550").getNazwa());
        
        l = listaPortow.get((int)(listaPortow.size()*Math.random()));
        while(l.equals(skad)){
            l = listaPortow.get((int)(listaPortow.size()*Math.random()));
        }

        while(l != skad){
            znalezionaTrasa.addFirst(l);
            System.out.println(l);
            l = poprzednik.get(l.getPolozenie().getX() + "_" + l.getPolozenie().getY());
        }
        znalezionaTrasa.addFirst(l);
        
        getTrasa().clear();
        getTrasa().addAll(znalezionaTrasa);
                     
    }
}
