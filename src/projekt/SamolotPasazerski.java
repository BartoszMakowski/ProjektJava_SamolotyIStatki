package projekt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import javafx.scene.image.ImageView;

/**
 * Created by bartosz on 19.10.15.
 * Implementuje samolot pasażerski.
 */
public class SamolotPasazerski extends Samolot implements Pasazerski{
    private final int miejsca;
    private List <Podrozny> pasazerowie;
    
    /**
     * Tworzy samolot pasażerski
     * @param lokalizacja lokalizacja startowa
     */
    public SamolotPasazerski(Lotnisko lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (5 + Math.random() * 25), lokalizacja, null);
        this.miejsca = 2 + (int) (Math.random()*8);
        this.pasazerowie = new LinkedList<>();
        System.out.println("WSZYSTKO OK");
        
        for (int i=0; i<this.miejsca/2; i++){
            Podrozny p = new Podrozny(lokalizacja);
//            this.pasazerowie.add(p);
            ((Lotnisko)this.getNajblizszyCel()).getOdwiedzajacy().add(p);
            Thread t = new Thread(p);
            t.setDaemon(true);
            t.start();
        }
        
        setObrazek(new ImageView(getClass().getResource("img/Samolot2.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(-100);
        getObrazek().yProperty().set(-100);
        getObrazek().setId("" + this.getId());
    }
  
    /**
     * Zwraca liczbę miejsc w samolocie.
     * @return liczba miejsc w samolocie
     */
    public int getMiejsca() {
        return miejsca;
    }
    /**
     * Zwraca listę pasażerów w samolocie.
     * @return lista pasażerów w samolocie
     */
    public List<Podrozny> getPasazerowie() {
        return pasazerowie;
    }


    @Override
    public String toString() {
        return "Samolot Pasażerski " + this.getId(); //To change body of generated methods, choose Tools | Templates.
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
        this.pasazerowie.add(pasazer);       
    }

    @Override
    public void usunPasazera(Podrozny pasazer) {
        this.getPasazerowie().remove(pasazer);
    }

    @Override
    public boolean czyJestMiejsce() {
        if (this.getPasazerowie().size() < this.getMiejsca()){
            return true;
        }
        return false;
    }
    
    @Override
    public void usun(){
        this.pasazerowie = new LinkedList<>();
        super.usun();        
        
    }
    
    @Override
    public void znajdzTrase(Lokalizacja skad){
        LinkedList<Lokalizacja> znalezionaTrasa = new LinkedList<>();
        HashMap<String, Lokalizacja> punkty = new HashMap<>();
        HashMap<String, Lokalizacja> poprzednik = new HashMap<>();
        HashMap<String, Integer> odleglosc = new HashMap<>();
        Lokalizacja l;
        
        ArrayList<Lotnisko> listaLotnisk = new ArrayList<>();
        listaLotnisk.addAll(Swiat.getLotniskaCywilne().values());
               
        punkty.putAll(Swiat.getLokalizacje());
        
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
        l = listaLotnisk.get((int)(listaLotnisk.size()*Math.random()));
        if(isUsterka()){
            System.out.println("WARIANT Z USTERKĄ");
            int min = 9999;
            String tmp;
            for(Lokalizacja lotnisko : listaLotnisk){
                tmp = lotnisko.getPolozenie().getX() + "_" + lotnisko.getPolozenie().getY();
                if(odleglosc.get(tmp) < min){
                    l = lotnisko;
                    min = odleglosc.get(tmp);
                }                
            }           
        }
        else{            
            while(l.equals(skad)){
                l = listaLotnisk.get((int)(listaLotnisk.size()*Math.random()));
            }
        }
        setUsterka(false);

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
