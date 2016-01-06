package projekt;

import java.util.LinkedList;
import java.util.List;
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

    public Podrozny(String imie, String nazwisko, long pesel, Lokalizacja dom) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dom = dom;
    }
    
    public Podrozny(Lokalizacja dom){
        this.imie=imiona[(int) (Math.random() * (imiona.length -1))];
        this.nazwisko=nazwiska[(int) (Math.random() * (nazwiska.length -1))];
        this.pesel = (long) (50000000000L + Math.random()*65011000000L) % 50000000000L;
        this.dom = dom;
        this.odpoczywa = false;
        
        if(Math.random()<0.01){
            this.imie = "D";
            this.nazwisko = "B";
        }
        
        int i = (int) (Math.random() * Projekt.trasy.get("" + dom.getPolozenie().getX() + "_" + dom.getPolozenie().getY()).size());
        System.out.println("Wylosowano trasę: " + i);
        List<Lokalizacja> plan = new LinkedList<>();
        plan.addAll(Projekt.trasy.get( dom.getPolozenie().getX() + "_" + dom.getPolozenie().getY()).get(i));
        this.plan = plan;
        System.out.println(this.plan);
        
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

    public void losujPlan(){}

    public Object getGdzieAktualnie() {
        return gdzieAktualnie;
    }

    public void setGdzieAktualnie(Object gdzieAktualnie) {
        this.gdzieAktualnie = gdzieAktualnie;
    }
    
    public boolean czyWsiasc(Pasazerski pojazd){
        for (Lokalizacja l : ((Pojazd)pojazd).getTrasa()){
            System.out.println("SPRAWDZAM, CZY WSIADAĆ");
            if (l.equals(this.plan.get(0))){
                return true;
            }
        }
        System.out.println("NIE WSIADAM");
        return false;
    }
    
    public boolean czyWysiasc(Pasazerski l){
        if (l.equals(plan.get(0))){
                return true;
        }        
        return false;
    }
    
    public void odpocznij() throws InterruptedException{
        if (this.rodzajPodrozy.equals(RodzajPodrozy.PRYWATNA)){
            this.odpoczywa = true;
            Thread.sleep(15000);
        }
        else{
            this.odpoczywa = true;
            Thread.sleep(25000);
        }
        this.odpoczywa = false;
    }
    
//    public void wsiadzWysiadz()
    
        

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
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

}
