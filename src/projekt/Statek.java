package projekt;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * Created by bartosz on 19.10.15.
 * Implementuje statek.
 */
public abstract class Statek extends Pojazd {
    
    /**
     * Tworzy statek.
     * @param polozenie położenie startowe
     * @param predkosc prędkość
     * @param najblizszyCel najbliższy cel
     * @param trasa trasa
     */
    public Statek(Polozenie polozenie, int predkosc, PortMorski najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
    }

    @Override
    public boolean sprawdzPole(){
        boolean czy = true;
//        synchronized(Swiat.getStatki()){
            for(int i=1; i<15; i++){
                if(Swiat.getStatki().containsKey(( getPolozenie().getX() + getModyfikatorX() + i*getDeltaX())+"_" + (getPolozenie().getY()+ getModyfikatorY() + i * getDeltaY()))){
//                    System.out.println(( getPolozenie().getX() +  i*getDeltaX())+"_" + (getPolozenie().getY()+ i * getDeltaY()));
                    czy = false;
                    break;
                }
            }
//        }
        return czy;
    }
    
    /**
     * Oznacza pole jako zajęte przez wywołujący metodę statek. Przydatne w unikaniu kolizji.
     */
    public void zajmijPole(){
        Swiat.getStatki().put((getPolozenie().getX() + getModyfikatorX() + getDeltaX() ) + "_" + (getPolozenie().getY() + getModyfikatorY() + getDeltaY()), this);
//        System.out.println((polozenie.getX() + deltaX) + "_" + (polozenie.getY() + deltaY));
        
    }
    
    /**
     * Zwalnie pole oznaczone wcześniej jako zajęte.
     */
    public void zwolnijPole(){
//        System.out.println("JEDNAK JEST LEPIEJ");
        Swiat.getStatki().remove((getPolozenie().getX() + getModyfikatorX()) + "_" + (getPolozenie().getY() + getModyfikatorY()));        
    }
    
    @Override
    public boolean czyMozna() throws InterruptedException{
        boolean czy = true;
        while (czy){
            
            synchronized(Swiat.getStatki()){
                if(sprawdzPole()){
                    zajmijPole();
                    zwolnijPole();
                    czy = false;
                }
            }
            if(czy){
                Thread.sleep(50);
//                System.out.println("NIE MOZNA!");
            }
            
        }
        return true;
    }
    
    @Override
    public void przemieszczaniePojazdu(){
        while (this.getOdleglosc()>20){
            try {
                czyMozna();
                przemiescSie();
                if(!isDzialaj()){
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
            }
    //                    zwolnijPole();
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    getObrazek().setX(getPolozenie().getX()-8 + getModyfikatorX());
                    getObrazek().setY(getPolozenie().getY()-8 + getModyfikatorY());
                }
            });

        }  
    }
    
    @Override
    public void tankuj(){
        tankuj(5000);
        
    }  

    
}
