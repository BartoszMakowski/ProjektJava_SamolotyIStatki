package projekt;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 * Created by bartosz on 19.10.15.
 */
public class SamolotPasazerski extends Samolot {
    private int zajeteMiejsca;
    private int miejsca;
    private List <Podrozny> pasazerowie;
    
    public SamolotPasazerski(Lotnisko lokalizacja){
        super(lokalizacja.getPolozenie(), (int) (5 + Math.random() * 25), lokalizacja, null);
        this.miejsca = 2 + (int) (Math.random()+8);
        int i = (int) (Math.random() * Projekt.trasy.get("" + lokalizacja.getPolozenie().getX() + "_" + lokalizacja.getPolozenie().getY()).size());
        List<Lokalizacja> mojaTrasa = new LinkedList<>();
        mojaTrasa.addAll(Projekt.trasy.get("" + lokalizacja.getPolozenie().getX() + "_" + lokalizacja.getPolozenie().getY()).get(i));
        this.setTrasa(mojaTrasa);
        this.pasazerowie = new LinkedList<>();
        System.out.println("WSZYSTKO OK");
        
        for (i=0; i<this.miejsca/2; i++){
            Podrozny p = new Podrozny(lokalizacja);
            this.pasazerowie.add(p);
            Thread t = new Thread(p);
            t.setDaemon(true);
            t.start();
        }
        
        this.obrazek = new ImageView(getClass().getResource("img/Samolot2.png").toExternalForm());
        this.obrazek.fitHeightProperty().set(25);
        this.obrazek.fitWidthProperty().set(25);
        this.obrazek.xProperty().set(this.getPolozenie().getX());
        this.obrazek.yProperty().set(this.getPolozenie().getY());
        this.obrazek.setId("" + this.getId());
    }
    
    public SamolotPasazerski(Polozenie polozenie, Lotnisko najblizszyCel, List<Lokalizacja> trasa){
        super(polozenie, (int) (5 + Math.random() * 25), najblizszyCel, trasa);
        this.miejsca = 2 + (int) (Math.random()+8);
        this.pasazerowie = new LinkedList<>();
        for (int i=0; i<this.miejsca/2; i++){
            Podrozny p = new Podrozny(najblizszyCel);
            this.pasazerowie.add(p);
            Thread t = new Thread(p);
            t.setDaemon(true);
            t.start();
        }
        
        this.obrazek = new ImageView(getClass().getResource("img/Samolot.png").toExternalForm());
        this.obrazek.fitHeightProperty().set(25);
        this.obrazek.fitWidthProperty().set(25);
//        this.obrazek.xProperty().set(this.getPolozenie().getX()*4);
//        this.obrazek.yProperty().set(this.getPolozenie().getY()*4);
        this.obrazek.setId("" + this.getId());

        
    }
    
    

    public SamolotPasazerski(Polozenie polozenie, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
        this.obrazek = new ImageView(getClass().getResource("img/Samolot.png").toExternalForm());
        this.obrazek.fitHeightProperty().set(15);
        this.obrazek.fitWidthProperty().set(15 + this.getId());
        this.obrazek.xProperty().set(this.getPolozenie().getX()*4);
        this.obrazek.yProperty().set(this.getPolozenie().getY()*4);
        this.obrazek.setId("" + this.getId());
//        this.obrazek.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("mouse click detected! ");
//                
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
//        });
    }
    
    public SamolotPasazerski(Polozenie polozenie, Lotnisko najblizszyCel, List<Lokalizacja> trasa, Kierunek kierunek){
        this(polozenie,najblizszyCel,trasa);
        this.setKierunek(kierunek);
    }
      
    public SamolotPasazerski(Polozenie polozenie, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa, Kierunek kierunek){
        this(polozenie,predkosc,najblizszyCel,trasa);
        this.setKierunek(kierunek);
    }

    public void zmienPasazerow(){}

    public int getMiejsca() {
        return miejsca;
    }

    public int getZajeteMiejsca() {
        return zajeteMiejsca;
    }

    public List<Podrozny> getPasazerowie() {
        return pasazerowie;
    }

    /**
     * @return the obrazek
     */
    public javafx.scene.image.ImageView getObrazek() {
        return obrazek;
    }

    @Override
    public String toString() {
        return "Samolot Pasażerski " + this.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
