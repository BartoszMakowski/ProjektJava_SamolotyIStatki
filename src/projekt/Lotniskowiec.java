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
public class Lotniskowiec extends Statek {
    private Uzbrojenie uzbrojenie;

    public Lotniskowiec(Polozenie lokalizacja, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa, Uzbrojenie uzbrojenie) {
        super(lokalizacja, predkosc, najblizszyCel, trasa);
        this.uzbrojenie = uzbrojenie;
    }
    
        
    public Lotniskowiec(PortMorski lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (3 + Math.random() * 10), lokalizacja, null);
        int i = (int)(Math.random() * 5);
        switch(i){
            case 0: uzbrojenie = Uzbrojenie.A; break;
            case 1: uzbrojenie = Uzbrojenie.B; break;
            case 2: uzbrojenie = Uzbrojenie.C; break;
            case 3: uzbrojenie = Uzbrojenie.D; break;
            case 4: uzbrojenie = Uzbrojenie.E; break;            
        }
        
        setObrazek(new ImageView(getClass().getResource("img/Lotniskowiec1.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
        znajdzTrase(lokalizacja);
        System.out.println(getTrasa());
        
}


//    public SamolotWojskowy produkujSamolot() {
//        SamolotWojskowy samolot = new SamolotWojskowy();
//        return samolot;
//    }

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
        
        ArrayList<PortMorski> listaPortow = new ArrayList<>();
        listaPortow.addAll(Swiat.getPortyMorskie().values());
                      
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
        
        l = skad;
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
    
    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {
                ruszaniePojazdu();
                przemieszczaniePojazdu();

                this.getNajblizszyCel().stopujPojazd(this);

                zwolnijPole();
                konczenieTrasyPojazdu();
                obslugaNaMiejscu();                                                       
            }        
    }
    
    @Override
    public void obslugaNaMiejscu(){


    if (getTrasa().get(0) instanceof PortMorski){
        tankuj();
    }


    if(getTrasa().size()> 1){            
        zmienCel(getTrasa().get(1));
    }
    else{
        System.out.println("Dotarłem do: " + getNajblizszyCel().getNazwa() + ".\nKONIEC TRASY.");
        znajdzTrase(getNajblizszyCel());
        zmienCel(this.getTrasa().get(1));
    }
    zwolnijPole();

    getTrasa().get(0).startujPojazd(this);
    getTrasa().remove(0);
    }

    @Override
    public String toString() {
        return "Lotniskowiec " + getId();
    }
       
}
