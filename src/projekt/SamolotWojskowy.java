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
 * enum z typami uzbrojenia
 */
enum Uzbrojenie{
    A,B,C,D,E};

/**
 * Implementuje samolot wojskowy.
 * @author bartosz
 */
public class SamolotWojskowy extends Samolot {
    private Uzbrojenie uzbrojenie;

    /**
     * Tworzy samolot wojskowy.
     * @param lokalizacja położenie startowe
     * @param predkosc prędkość
     * @param najblizszyCel najbliższy cel
     * @param trasa trasa
     */
    public SamolotWojskowy(Polozenie lokalizacja, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(lokalizacja, predkosc, najblizszyCel, trasa);
    }
    
    /**
     * Tworzy samolot wojskowy.
     * @param lokalizacja lokalizacja startowa
     */
    public SamolotWojskowy(Lotnisko lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (5 + Math.random() * 25), lokalizacja, null);
//        System.out.println("WSZYSTKO OK");
        
        
        setObrazek(new ImageView(getClass().getResource("img/SamolotWojskowy4.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
    }
    
    /**
     * Tworzy samolot wojskowy
     * @param polozenie położenie startowe
     * @param uzbrojenie rodzaj uzbrojenia
     */
    public SamolotWojskowy(Polozenie polozenie, Uzbrojenie uzbrojenie){
        super(polozenie, (int) (5 + Math.random() * 25));
        this.uzbrojenie = uzbrojenie;        
//        System.out.println("WSZYSTKO OK");
        
        
        setObrazek(new ImageView(getClass().getResource("img/SamolotWojskowy4.png").toExternalForm()));
        getObrazek().fitHeightProperty().set(25);
        getObrazek().fitWidthProperty().set(25);
        getObrazek().xProperty().set(-30);
        getObrazek().yProperty().set(-30);
        getObrazek().setId("" + this.getId());
    }
    
    /**
     * Tworzy samolot wojskowy.
     * @param l lotniskowiec produkujący samolot
     */
    public SamolotWojskowy(Lotniskowiec l){
         this(l.getPolozenie(), l.getUzbrojenie());
    }
    
    /**
     * Zwraca rodzaj uzbrojenia.
     * @return rodzaj uzbrojenia
     */
    public Uzbrojenie getUzbrojenie() {
        return uzbrojenie;
    }
    
    @Override
    public boolean sprawdzPole(){
//        System.out.println("JEDNAK JEST DOBRZE");
        boolean czy = true;
//        synchronized(Swiat.getSamoloty()){
            for(int i=1; i<20; i++){
                for(int j=-10; j<11; j++){
                    if(getDeltaX() != 0){
                        if(Swiat.getSamoloty().containsKey(( getPolozenie().getX() + getModyfikatorX() + i*getDeltaX())+"_" + (getPolozenie().getY()+ getModyfikatorY() + j))){
//                            System.out.println(( getPolozenie().getX() +  i*getDeltaX())+"_" + (getPolozenie().getY()+ i * getDeltaY()));
                            czy = false;
                            break;
                        }
                    }
                    else{
                        if(Swiat.getSamoloty().containsKey(( getPolozenie().getX() + getModyfikatorX() + j)+"_" + (getPolozenie().getY()+ getModyfikatorY() + i*getDeltaY()))){
//                            System.out.println(( getPolozenie().getX() +  i*getDeltaX())+"_" + (getPolozenie().getY()+ i * getDeltaY()));
                            czy = false;
                            break;
                        }
                    }
                }
            }
//        }

//        System.out.println(toString() + ": jestem blokowany w pozycji " + getPolozenie().getX() + "  " + getPolozenie().getY() );
        return czy;        
    }
    
    /**
     * Tworzy i ustala tymczasową trasę, która umożliwia włączenie się do ruchu powietrznego.
     */
    private void startuj(){
        Skrzyzowanie s1= new Skrzyzowanie(getPolozenie().getX(), getPolozenie().getY(), "S-TEMP-1");
        Skrzyzowanie s2= new Skrzyzowanie(30, getPolozenie().getY(), "S-TEMP-2");
        s1.dodajDrogowskaz(new Drogowskaz(s1, s2));
        s2.dodajDrogowskaz(new Drogowskaz(s2, Swiat.getLotniskaWojskowe().get("30_320")));
        LinkedList<Lokalizacja> nowaTrasa = new LinkedList<>();
        nowaTrasa.add(s1);
        nowaTrasa.add(s2);
        nowaTrasa.add(Swiat.getLotniskaWojskowe().get("30_320"));
        setTrasa(nowaTrasa);
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
//            System.out.println(l);
            l = poprzednik.get(l.getPolozenie().getX() + "_" + l.getPolozenie().getY());
        }
        znalezionaTrasa.addFirst(l);
        
        getTrasa().clear();
        getTrasa().addAll(znalezionaTrasa);
                     
    }
    
    @Override
    public void run(){
//        Image gpojazd;
            startuj();
            while(isDzialaj())
            {
                ruszaniePojazdu();
                przemieszczaniePojazdu();
                if (getNajblizszyCel() instanceof Lotnisko){
                    if (((Lotnisko)getNajblizszyCel()).getRodzaj().equals(TypPortu.CYWILNY)){                        
                    }
                    else{
                        getNajblizszyCel().stopujPojazd(this);
                    }
                }
                else if(getNajblizszyCel() instanceof Skrzyzowanie){
                    getNajblizszyCel().stopujPojazd(this);
                }
                zwolnijPole();                
//                if(!isDzialaj()) break;
                konczenieTrasyPojazdu();
                obslugaNaMiejscu();                                                       
            }
//            zwolnijPole();
    }

    @Override
    public String toString() {
        return "Samolot wojskowy " + getId();
    }
//    
//    @Override
//    public void usun(){
////        System.out.println("USUWAM");
////        Thread.currentThread().interrupt();
//        super.usun();
//        
//    }
    
}
    
//    public javafx.scene.image.ImageView getObrazek() {
//        return getObrazek();
//    }

