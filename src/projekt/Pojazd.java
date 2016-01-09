package projekt;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * Created by bartosz on 18.10.15.
 */
public abstract class Pojazd implements Runnable {
    private Polozenie polozenie;
    private int predkosc;
    private int odleglosc;
    private static int ostatnieId;
    private int id;
    private Lokalizacja najblizszyCel;
    private List <Lokalizacja> trasa;
    private Kierunek kierunek;
    private javafx.scene.image.ImageView obrazek;
    private int modyfikatorX;
    private int modyfikatorY;
    private int deltaX;
    private int deltaY;

    public Pojazd(Polozenie polozenie, int predkosc, List<Lokalizacja> trasa) {
        this.polozenie = new Polozenie(polozenie.getX(),polozenie.getY());
        this.predkosc = predkosc;
        this.trasa = trasa;
        this.id = ostatnieId++;
        this.kierunek = Kierunek.ZADEN;
        //        this.zmienCel(trasa.listIterator().next());
    }

    public Pojazd(Polozenie polozenie, int predkosc, Lokalizacja najblizszyCel, List<Lokalizacja> trasa){
        this(polozenie, predkosc, trasa);
        this.najblizszyCel = najblizszyCel;
    }

    public int getPredkosc() {
        return predkosc;
    }

    public List<Lokalizacja> getTrasa() {
        return trasa;
    }

    public void setTrasa(List<Lokalizacja> trasa) {
        this.trasa = trasa;
    }

    public int getId() {
        return id;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public Lokalizacja getNajblizszyCel() {
        return najblizszyCel;
    }

    public void setNajblizszyCel(Lokalizacja najblizszyCel) {
        this.najblizszyCel = najblizszyCel;
//        this.kierunek = Kierunek.LEWO;
    }

    public void usun(){}
    
    private void zmienCel(Lokalizacja lok){
        this.najblizszyCel = lok;
        System.out.println( this.toString() +  ": Ustalono nowy cel: " + this.najblizszyCel.getNazwa() + this.najblizszyCel.getPolozenie() );
        this.setKierunek(this.trasa.get(0).jakDojechac( this.najblizszyCel ).getKierunek());
        
         switch (this.getKierunek())
        {
            case LEWO:
                this.modyfikatorX = 0;
                this.modyfikatorY = -5;
                break;
            case PRAWO:
                this.modyfikatorX = 0;
                this.modyfikatorY = 5;
                break;
            case DOL:
                this.modyfikatorX = -5;
                this.modyfikatorY = 0;
                break;
            case GORA:
                this.modyfikatorX = 5;
                this.modyfikatorY = 0;
                break;
        }
        
        this.odleglosc = this.trasa.get(0).jakDojechac( this.najblizszyCel ).getOdleglosc();
        System.out.println("ODLEGŁOŚĆ: " + this.odleglosc);
    }

    public void przemiescSie() throws InterruptedException {
        
        switch (this.getKierunek())
        {
            case LEWO:
                deltaX=-1;
                deltaY=0;
                break;
            case PRAWO:
                deltaX=1;
                deltaY=0;
                break;
            case DOL:
                deltaY=1;
                deltaX=0;
                break;
            case GORA:
                deltaY=-1;
                deltaX=0;
                break;
            default:
                break;
        }
        
        this.polozenie.setY(this.polozenie.getY()+deltaY);
        this.polozenie.setX(this.polozenie.getX()+deltaX);
        
        this.odleglosc--;
        try {                    
            Thread.sleep(50-this.predkosc);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("Pojazd " + this.id + ": " + this.polozenie.toString() + " | " + this.najblizszyCel.getPolozenie() + "." + " Kierunek: " + this.getKierunek().toString());


    }

    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    /**
     * @param kierunek the kierunek to set
     */
    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }
    
    public void run(){
//        Image gpojazd;
            while(true)
            {
                
                while (this.getOdleglosc()>15)
                {
 
                    try {
                        this.przemiescSie();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            getObrazek().setX(getPolozenie().getX()-8 + modyfikatorX);
                            getObrazek().setY(getPolozenie().getY()-8 + modyfikatorY);
                        }
                    });
                    
//                    try {                    
//                        Thread.sleep(100);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }      
                
//                if( this.polozenie.equals( this.najblizszyCel.getPolozenie() ) ){
                    this.najblizszyCel.stopujPojazd(this);
                    
                    while (this.getOdleglosc()>0)
                    {
                    try {
                        this.przemiescSie();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            getObrazek().setX(getPolozenie().getX()-8 + modyfikatorX);
                            getObrazek().setY(getPolozenie().getY()-8 + modyfikatorY);
                        }
                    });
                    }
                    
                    if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski))
                    {
                        System.out.println("        " + this.trasa.get(0).getNazwa());
                        ((Pasazerski)this).przesiadkaPasazera((Pasazerski)this.trasa.get(0));
                    }
                    
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if(this.trasa.size()> 1){            
                        this.zmienCel(this.trasa.get(1));
                    }
                    else{
                        System.out.println("Dotarłem do: " + this.najblizszyCel.getNazwa() + ".\nKONIEC TRASY.");
                        this.trasa = new LinkedList<>(Swiat.trasy.get(this.polozenie.getX() +"_" + this.polozenie.getY())
                                .get((int)Math.random() * Swiat.trasy.
                                        get(this.polozenie.getX() + "_" + this.polozenie.getY()).size()));
//                        this.trasa.add(0, null);
//                        this.setTrasa(
                          this.zmienCel(this.trasa.get(1));
                    }
                    
                    System.out.println("PASAŻEROWIE, WSIADAJCIE!");
                    if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski))
                    {
                        ((Pasazerski)this.trasa.get(0)).przesiadkaPasazera((Pasazerski)this);
                    }
                    
                    this.trasa.get(0).startujPojazd(this);
                    this.trasa.remove(0);
             //   }
             
                                       
            }
        
    }

    /**
     * @return the odleglosc
     */
    public int getOdleglosc() {
        return odleglosc;
    }

    /**
     * @return the obrazek
     */
    public javafx.scene.image.ImageView getObrazek() {
        return obrazek;
    }

    /**
     * @param obrazek the obrazek to set
     */
    public void setObrazek(javafx.scene.image.ImageView obrazek) {
        this.obrazek = obrazek;
    }
}
