package projekt;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 * Created by bartosz on 19.10.15.
 */
public class SamolotPasazerski extends Samolot implements Pasazerski{
    private final int miejsca;
    private List <Podrozny> pasazerowie;
    
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
        getObrazek().xProperty().set(this.getPolozenie().getX());
        getObrazek().yProperty().set(this.getPolozenie().getY());
        getObrazek().setId("" + this.getId());
    }
  

    public int getMiejsca() {
        return miejsca;
    }

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
                System.out.println("NIECH ODPOCZNIE");
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
   
       
}
