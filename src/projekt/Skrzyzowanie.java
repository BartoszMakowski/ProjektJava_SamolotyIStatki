package projekt;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bartosz on 19.10.15.
 * Implementuje skrzyżowanie.
 */
public class Skrzyzowanie extends Lokalizacja {
    private Pojazd zajetePrzez;
    
    /**
     * Tworzy skrzyżowanie.
     * @param x współrzędna X skrzyżowania
     * @param y współrzędna Y skrzyżowania
     * @param nazwa nazwa skrzyżowania
     */
    public Skrzyzowanie(int x, int y, String nazwa) {
        super(x, y, nazwa);
        this.zajetePrzez = null;
    }
    
    /**
     * Zwraca pojazd, który zajmuje skrzyżowanie.
     * @return pojazd zajmujący skrzyżowanie
     */
    public Pojazd getZajetePrzez() {
        return zajetePrzez;
    }
    
    /**
     * Oznacza skrzyżowanie jako zajęte przez dany pojazd.
     * @param zajetePrzez pojazd zajmujący skrzyżowanie
     */
    public void setZajetePrzez(Pojazd zajetePrzez) {
        this.zajetePrzez = zajetePrzez;
    }
    
    @Override
    public void stopujPojazd(Pojazd p){
           
        while(!p.equals(this.zajetePrzez)){
            if(!p.isDzialaj()){
            return;
            }  
//            System.out.println("WSZEDŁEM DO WHILE'a");
            synchronized(this){
                if(this.zajetePrzez==null){
                    this.zajetePrzez=p;
                }
//                else System.out.println("Zajete przez: " + this.zajetePrzez.toString());
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(Skrzyzowanie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(25);
        } catch (InterruptedException ex) {
            Logger.getLogger(Skrzyzowanie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void startujPojazd(Pojazd p){
        synchronized(this){
            this.zajetePrzez = null;
        }
        
    }

}
