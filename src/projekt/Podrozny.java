package projekt;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bartosz on 19.10.15.
 */
enum RodzajPodrozy{
    PRYWATNA, SLUZBOWA}

public class Podrozny implements Runnable{
    private static String[] imiona={"Jan","Anna","Krzysztof","Maria","Dominika","Mariusz", "Janusz", "Hiacynta", "Leokadia", "Krystyna"};
    private static String[] nazwiska={"Jurek","Nowak","Krzak","Błękit","Jawa","Komar", "Łużyn", "Messi", "Ronaldo",};
    private String imie;
    private String nazwisko;
    private long pesel;
    private Lokalizacja dom;
    private List<Lokalizacja> plan;
    private Lokalizacja cel;
    private RodzajPodrozy rodzajPodrozy;
    private Object gdzieAktualnie;
    private boolean odpoczywa;
    private static int bazaPesel = 1;

    public Podrozny(String imie, String nazwisko, long pesel, Lokalizacja dom) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dom = dom;
    }
    
    public Podrozny(Lokalizacja dom){
        this.imie=imiona[(int) (Math.random() * (imiona.length -1))];
        this.nazwisko=nazwiska[(int) (Math.random() * (nazwiska.length -1))];
//        this.pesel = (long) (50000000000L + Math.random()*65011000000L) % 100000000000L;
        this.pesel = bazaPesel++;
        this.dom = dom;
        this.odpoczywa = false;
        this.rodzajPodrozy = Math.random() > 0.5 ? RodzajPodrozy.PRYWATNA : RodzajPodrozy.SLUZBOWA;
//        System.out.println(znajdzTrase(dom, Swiat.getLokalizacje().get("90_550")));
        
        Lokalizacja cel = Swiat.getMiasta().get((int)(Swiat.getMiasta().size() * Math.random()));
        System.out.println( this + ": MOIM CELEM JEST: " + cel.getNazwa());

        this.plan = znajdzTrase(dom, cel);
        Lokalizacja cel2 = Swiat.getMiasta().get((int)(Swiat.getMiasta().size() * Math.random()));
        this.plan.addAll(znajdzTrase(cel, cel2));
        this.plan.addAll(znajdzTrase(cel2, dom));
        
//        losujPlan(dom);
        Swiat.getPasazerowie().put("" + pesel, this); 
       
 
//        System.out.println(this.plan);
        
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public long getPesel() {
        return pesel;
    }

    public Lokalizacja getDom() {
        return dom;
    }

    public List<Lokalizacja> getPlan() {
        return plan;
    }

    public Lokalizacja getCel() {
        return cel;
    }

    public RodzajPodrozy getRodzajPodrozy() {
        return rodzajPodrozy;
    }

    private void losujPlan(Lokalizacja start){
        Lokalizacja dokad = start;
        while(dokad == start){
            int i = (int) (Math.random() * Swiat.getMiasta().size());
            dokad = Swiat.getMiasta().get(i);
            System.out.println("Wylosowano trasę: " + i);
        }
        
        this.plan = znajdzTrase(start, dokad);
//        this.plan.remove(0);       
    }
    
    private LinkedList<Lokalizacja> znajdzTrase(Lokalizacja skad, Lokalizacja dokad){
        LinkedList<Lokalizacja> znalezionaTrasa = new LinkedList<>();
        HashMap<String, Lokalizacja> punkty = new HashMap<>();
        HashMap<String, Lokalizacja> poprzednik = new HashMap<>();
        HashMap<String, Integer> odleglosc = new HashMap<>();
        Lokalizacja l;
        
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
        
        l = dokad;
        while(l != skad){
            if (l instanceof Pasazerski){
                znalezionaTrasa.addFirst(l);
            }
            System.out.println(l);
            l = poprzednik.get(l.getPolozenie().getX() + "_" + l.getPolozenie().getY());
        }
//        znalezionaTrasa.addFirst(l);
        
        return znalezionaTrasa;       
    }

    public Object getGdzieAktualnie() {
        return gdzieAktualnie;
    }

    public void setGdzieAktualnie(Object gdzieAktualnie) {
        this.gdzieAktualnie = gdzieAktualnie;
    }
    
    public boolean czyWsiasc(Pasazerski pojazd){
        if (this.plan.size()<1){
            losujPlan(((Pojazd)pojazd).getTrasa().get(0));
        }
//        System.out.println("jestem w: " + ((Pojazd)pojazd).getTrasa().get(0).getNazwa() + "   chcę do: " + this.plan.get(0).getNazwa() );
        for (Lokalizacja l : ((Pojazd)pojazd).getTrasa()){
//            System.out.println("SPRAWDZAM, CZY WSIADAĆ");
            if (l.equals(this.plan.get(0))){
                return true;
            }
        }
//        System.out.println("NIE WSIADAM");
        return false;
    }
    
    public boolean czyWysiasc(Pasazerski l){
//        System.out.println("jestem w: " + ((Lokalizacja)l).getNazwa() + "   chcę do: " + this.plan.get(0).getNazwa() );
        if (((Lokalizacja)l).equals(this.plan.get(0))){
//            System.out.println("TAK, WYSIADAM");
//            this.plan.remove(0);
            return true;
        }        
        return false;
    }
    
    public void odpocznij() throws InterruptedException{
        if (this.rodzajPodrozy.equals(RodzajPodrozy.PRYWATNA)){
            this.setOdpoczywa(true);
            Thread.sleep(15000);
        }
        else{
            this.setOdpoczywa(true);
            Thread.sleep(25000);
        }
        this.setOdpoczywa(false);
    }
    
//    public void wsiadzWysiadz()
    
        

    @Override
    public void run() {
        while(true){
            if(this.odpoczywa){
                try {
                    this.odpocznij();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Podrozny.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Podrozny.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public losujCzlowieka()
//    {
//        this.imie = "Jan";
//        this.nazwisko = "Kowalski";
//        return this;
//    }

    /**
     * @return the czyOdpoczywa
     */
    public boolean isOdpoczywa() {
        return odpoczywa;
    }

    /**
     * @param odpoczywa the odpoczywa to set
     */
    public void setOdpoczywa(boolean odpoczywa) {
        this.odpoczywa = odpoczywa;
    }
    
    @Override
    public String toString(){
        return(""+pesel);
    }

}
