package projekt;

import java.util.List;

/**
 * Created by bartosz on 18.10.15.
 * Implementuje samolot.
 */
public abstract class Samolot extends Pojazd {
    private int liczebnoscPersonelu;
    private boolean usterka;

    /**
     * Tworzy samolot.
     * @param polozenie położenie samolotu
     * @param predkosc prędkość samolotu
     * @param najblizszyCel najbliższy cel samolotu
     * @param trasa trasa samolotu
     */
    public Samolot(Polozenie polozenie, int predkosc, Lotnisko najblizszyCel, List<Lokalizacja> trasa) {
        super(polozenie, predkosc, najblizszyCel, trasa);
        this.usterka = false;
//        losujTrase(polozenie);
    }
    
    /**
     * Tworzy samolot.
     * @param polozenie położenie samolotu
     * @param predkosc prędkość samolotu 
     */
    public Samolot(Polozenie polozenie, int predkosc) {
        super(polozenie, predkosc);
        this.usterka = false;
    }
    
    /**
     * Zgłasza usterkę samolotu. Powoduje zmianę trasy na prowadzącą do najbliższego dostępnego lotniska.
     */
    public void zglosUsterke(){
        this.setUsterka(true);
        znajdzTrase(getTrasa().get(0));
    }
    
    @Override
    public boolean sprawdzPole(){
        boolean czy = true;
//        synchronized(Swiat.getSamoloty()){;
            for(int i=1; i<15; i++){
                if(Swiat.getSamoloty().containsKey(( getPolozenie().getX() + getModyfikatorX() + i*getDeltaX())+"_" + (getPolozenie().getY()+ getModyfikatorY() + i * getDeltaY()))){
//                    System.out.println(( getPolozenie().getX() +  i*getDeltaX())+"_" + (getPolozenie().getY()+ i * getDeltaY()));
                    czy = false;
                    break;
                }
            }
//        }
        return czy;
    }
    
    /**
     * Oznacza pole jako zajęte przez wywołujący metodę samolot. Przydatne w unikaniu kolizji.
     */
    private void zajmijPole(){
        Swiat.getSamoloty().put((getPolozenie().getX() + getModyfikatorX() + getDeltaX() ) + "_" + (getPolozenie().getY() + getModyfikatorY() + getDeltaY()), (Samolot) this);
//        System.out.println((polozenie.getX() + deltaX) + "_" + (polozenie.getY() + deltaY));
        
    }
    
    /**
     * Zwalnia pole oznaczone wcześniej jako zajęte.
     */
    public void zwolnijPole(){
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
            }            
        }
        return true;
    }
    
    @Override
    public void run(){
        while(isDzialaj())
        {
            przemieszczaniePojazdu();
            this.getNajblizszyCel().stopujPojazd(this);
            zwolnijPole();
            if(!isDzialaj()) break;
            konczenieTrasyPojazdu();
            obslugaNaMiejscu();                                                       
        }        
    }

    /**
     * Zwraca liczebność personelu.
     * @return liczebność personelu
     */
    public int getLiczebnoscPersonelu() {
        return liczebnoscPersonelu;
    }

    /**
     * Zwraca prawdziwość awarii.
     * @return prawdziwość awarii
     */
    public boolean isUsterka() {
        return usterka;
    }

    /**
     * Ustawia prawdziwość awarii.
     * @param usterka nowa wartość prawdziwości awarii
     */
    public void setUsterka(boolean usterka) {
        this.usterka = usterka;
    }
    
    @Override
    public void usun(){
        super.usun();
        zwolnijPole();

    }
}
    