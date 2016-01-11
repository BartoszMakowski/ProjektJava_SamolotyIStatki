package projekt;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartosz on 18.10.15.
 */
public abstract class Samolot extends Pojazd {
//    private Paliwo paliwo;
    private int liczebnoscPersonelu;
    private boolean usterka;
    
    public Samolot(Polozenie polozenie, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
        this.usterka = false;
//        losujTrase(polozenie);
    }

    public void zglosUsterke(){
        this.usterka = true;
    }
    
        private boolean sprawdzPole(){
        boolean czy = true;
//        synchronized(Swiat.getSamoloty()){
            for(int i=1; i<15; i++){
                if(Swiat.getSamoloty().containsKey(( getPolozenie().getX() + getModyfikatorX() + i*getDeltaX())+"_" + (getPolozenie().getY()+ getModyfikatorY() + i * getDeltaY()))){
                    System.out.println(( getPolozenie().getX() +  i*getDeltaX())+"_" + (getPolozenie().getY()+ i * getDeltaY()));
                    czy = false;
                    break;
                }
            }
//        }
        return czy;
    }
    
    private void zajmijPole(){
        Swiat.getSamoloty().put((getPolozenie().getX() + getModyfikatorX() + getDeltaX() ) + "_" + (getPolozenie().getY() + getModyfikatorY() + getDeltaY()), (Samolot) this);
//        System.out.println((polozenie.getX() + deltaX) + "_" + (polozenie.getY() + deltaY));
        
    }
    
    private void zwolnijPole(){
        Swiat.getSamoloty().remove((getPolozenie().getX() + getModyfikatorX()) + "_" + (getPolozenie().getY() + getModyfikatorY()));        
    }
    
    private boolean czyMozna() throws InterruptedException{
        boolean czy = true;
        while (czy){
            
            synchronized(Swiat.getSamoloty()){
                if(sprawdzPole()){
                    zajmijPole();
                    zwolnijPole();
                    czy = false;
                }
            }
            if(czy){
                Thread.sleep(50);
                System.out.println("NIE MOZNA!");
            }
            
        }
        return true;
    }
    
}