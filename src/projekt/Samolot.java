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
    
    public Samolot(Polozenie polozenie, int predkosc) {
        super(polozenie, predkosc);
        this.usterka = false;
//        losujTrase(polozenie);
    }

    public void zglosUsterke(){
        this.setUsterka(true);
    }
    
    @Override
    public boolean sprawdzPole(){
        boolean czy = true;
//        synchronized(Swiat.getSamoloty()){;
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
    
    public void zwolnijPole(){
//        System.out.println("JEDNAK JEST LEPIEJ");
        Swiat.getSamoloty().remove((getPolozenie().getX() + getModyfikatorX()) + "_" + (getPolozenie().getY() + getModyfikatorY()));        
    }
    
    @Override
    public boolean czyMozna() throws InterruptedException{
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
    
    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {
                ruszaniePojazdu();
                przemieszczaniePojazdu();
                this.getNajblizszyCel().stopujPojazd(this);
                zwolnijPole();
                konczenieTrasyPojazdu();
                obslugaNaMiejscu();                                                       
            }        
    }

    /**
     * @return the liczebnoscPersonelu
     */
    public int getLiczebnoscPersonelu() {
        return liczebnoscPersonelu;
    }

    /**
     * @return the usterka
     */
    public boolean isUsterka() {
        return usterka;
    }

    /**
     * @param usterka the usterka to set
     */
    public void setUsterka(boolean usterka) {
        this.usterka = usterka;
    }
}
    