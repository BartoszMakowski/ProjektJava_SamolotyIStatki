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
        

        
        losujPlan(dom);
        Swiat.getPasazerowie().put("" + pesel, this); 
       
 
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

    private void losujPlan(Lokalizacja start){
        int i = (int) (Math.random() * Swiat.trasy.get("" + start.getPolozenie().getX() + "_" + start.getPolozenie().getY()).size());
        System.out.println("Wylosowano trasę: " + i);
        List<Lokalizacja> plan = new LinkedList<>();
        
        for (Lokalizacja l : Swiat.trasy.get( start.getPolozenie().getX() + "_" + start.getPolozenie().getY()).get(i)){
            if (l instanceof Pasazerski){
                plan.add(l);
            }
        }
        this.plan = plan;
        this.plan.remove(0);
        
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
