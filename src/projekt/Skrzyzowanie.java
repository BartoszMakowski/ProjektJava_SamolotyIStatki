package projekt;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bartosz on 19.10.15.
 */
public class Skrzyzowanie extends Lokalizacja {
    private Pojazd zajetePrzez;

    public Skrzyzowanie(int x, int y, String nazwa) {
        super(x, y, nazwa);
        this.zajetePrzez = null;
    }

    public Pojazd getZajetePrzez() {
        return zajetePrzez;
    }

    public void setZajetePrzez(Pojazd zajetePrzez) {
        this.zajetePrzez = zajetePrzez;
    }
    
    @Override
    public void stopujPojazd(Pojazd p){
        System.out.println("WTF");
        
        while(!p.equals(this.zajetePrzez)){
            System.out.println("WSZEDŁEM DO WHILE'a");
            synchronized(this){
                if(this.zajetePrzez==null){
                    this.zajetePrzez=p;
                }
                else System.out.println("Zajete przez: " + this.zajetePrzez.toString());
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
