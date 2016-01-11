package projekt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 19.10.15.
 */
public class Wycieczkowiec extends Statek{
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

    public void zmienPasazerow(){}
}
