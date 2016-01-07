package projekt;

import java.util.LinkedList;
import java.util.List;
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
    
//    public javafx.scene.image.ImageView getObrazek() {
//        return getObrazek();
//    }
}
