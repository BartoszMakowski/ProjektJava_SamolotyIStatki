package projekt;

import java.util.List;

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
        
        if(Math.random()<0.01){
            this.imie = "D";
            this.nazwisko = "B";
        }
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public losujCzlowieka()
//    {
//        this.imie = "Jan";
//        this.nazwisko = "Kowalski";
//        return this;
//    }

}
