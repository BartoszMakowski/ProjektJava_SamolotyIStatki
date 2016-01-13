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
 */
enum Uzbrojenie{
    A,B,C,D,E};

public class SamolotWojskowy extends Samolot {
    private Uzbrojenie uzbrojenie;

    public SamolotWojskowy(Polozenie lokalizacja, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(lokalizacja, predkosc, najblizszyCel, trasa);
    }
    
    public SamolotWojskowy(Lotnisko lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (5 + Math.random() * 25), lokalizacja, null);
        this.uzbrojenie = Uzbrojenie.A;        
        System.out.println("WSZYSTKO OK");
        
        
        setObrazek(new ImageView(getClass().getResource("img/SamolotWojskowy4.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
    }

    public Uzbrojenie getUzbrojenie() {
        return uzbrojenie;
    }
    
    @Override
    public void znajdzTrase(Lokalizacja skad){
        LinkedList<Lokalizacja> znalezionaTrasa = new LinkedList<>();
        HashMap<String, Lokalizacja> punkty;
        HashMap<String, Lokalizacja> poprzednik = new HashMap<>();
        HashMap<String, Integer> odleglosc = new HashMap<>();
        Lokalizacja l;
        
        ArrayList<Lotnisko> listaLotnisk = new ArrayList<>();
        listaLotnisk.addAll(Swiat.getLotniskaWojskowe().values());
               
        punkty = Swiat.getLokalizacje();
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
        
        l = listaLotnisk.get((int)(listaLotnisk.size()*Math.random()));
        while(l.equals(skad)){
            l = listaLotnisk.get((int)(listaLotnisk.size()*Math.random()));
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
    
    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {
                ruszaniePojazdu();
                przemieszczaniePojazdu();
                if (getNajblizszyCel() instanceof Lotnisko){
                    if (((Lotnisko)getNajblizszyCel()).getRodzaj().equals(TypPortu.WOJSKOWY)){
                        this.getNajblizszyCel().stopujPojazd(this);
                    }
                }
                zwolnijPole();
                konczenieTrasyPojazdu();
                obslugaNaMiejscu();                                                       
            }        
    }
}
    
//    public javafx.scene.image.ImageView getObrazek() {
//        return getObrazek();
//    }

