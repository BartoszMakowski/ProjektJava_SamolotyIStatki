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
        
        this.odleglosc = this.trasa.get(0).jakDojechac( this.najblizszyCel ).getOdleglosc();
        System.out.println("ODLEGŁOŚĆ: " + this.odleglosc);
    }

    public void przemiescSie() throws InterruptedException {
//        czyMozna();
        
         
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
                if(odleglosc>0){
                    
                    Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    getObrazek().setX(-30);
                                    getObrazek().setY(-30);
                                }
                    });
                    
                    for(int i=0; i<5; i++){
                            try {
                                    this.przemiescSie();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
                                }

                        }
                }
                
                
                while (this.getOdleglosc()>20)
                {
//                    try {
//                        czyMozna();
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    try {
                        czyMozna();
                        this.przemiescSie();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    zwolnijPole();
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            getObrazek().setX(getPolozenie().getX()-8 + modyfikatorX);
                            getObrazek().setY(getPolozenie().getY()-8 + modyfikatorY);
                        }
                    });
                    
                }      
                
//                if( this.polozenie.equals( this.najblizszyCel.getPolozenie() ) ){
                    this.najblizszyCel.stopujPojazd(this);
                    zwolnijPole();
                    
                   
                    
                    while (this.getOdleglosc()>0){
//                        zwolnijPole();
                    
//                        if (sprawdzPole()){
                            
                            System.out.println("WOLNE");
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
                        //}
                    }
                    int sen;
                    
                    if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski))
                    {
                        System.out.println("        " + this.trasa.get(0).getNazwa());
                        ((Pasazerski)this).przesiadkaPasazera((Pasazerski)this.trasa.get(0));
                        sen = 1500 +(int)Math.random() * 3500;
                        
                        Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    getObrazek().setX(-30);
                                    getObrazek().setY(-30);
                                }
                            });
                    }
                    else{
                        sen = 150;
                    }
                    
                    try {
                        Thread.sleep(sen);
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
                    zwolnijPole();
                    
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
    
    private boolean sprawdzPole(){
        boolean czy = true;
//        synchronized(Swiat.getSamoloty()){
            for(int i=1; i<15; i++){
                if(Swiat.getSamoloty().containsKey(( polozenie.getX() + modyfikatorX + i*deltaX)+"_" + (polozenie.getY()+ modyfikatorY + i * deltaY))){
                    System.out.println(( polozenie.getX() +  i*deltaX)+"_" + (polozenie.getY()+ i * deltaY));
                    czy = false;
                    break;
                }
            }
//        }
        return czy;
    }
    
    private void zajmijPole(){
        Swiat.getSamoloty().put((polozenie.getX() + modyfikatorX + deltaX ) + "_" + (polozenie.getY() + modyfikatorY + deltaY), (Samolot) this);
//        System.out.println((polozenie.getX() + deltaX) + "_" + (polozenie.getY() + deltaY));
        
    }
    
    private void zwolnijPole(){
        Swiat.getSamoloty().remove((polozenie.getX() + modyfikatorX) + "_" + (polozenie.getY() + modyfikatorY));        
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
