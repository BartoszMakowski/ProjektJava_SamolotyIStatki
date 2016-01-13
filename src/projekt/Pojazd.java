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
    private int paliwo;
    private static int ostatnieId=1;
    private int id;
    private Lokalizacja najblizszyCel;
    private List <Lokalizacja> trasa;
    private Kierunek kierunek;
    private javafx.scene.image.ImageView obrazek;
    private int modyfikatorX;
    private int modyfikatorY;
    private int deltaX;
    private int deltaY;
    private boolean dzialaj;

    public Pojazd(Polozenie polozenie, int predkosc, List<Lokalizacja> trasa) {
        this.polozenie = new Polozenie(polozenie.getX(),polozenie.getY());
        this.predkosc = predkosc;
        this.trasa = trasa;
        this.id = ostatnieId++;
        this.kierunek = Kierunek.ZADEN;
        this.dzialaj = true;
//        losujTrase(polozenie);
        //        this.zmienCel(trasa.listIterator().next());
    }

    public Pojazd(Polozenie polozenie, int predkosc, Lokalizacja najblizszyCel, List<Lokalizacja> trasa){
        this(polozenie, predkosc, trasa);
        this.najblizszyCel = najblizszyCel;
        this.paliwo = 999;
        this.trasa = new LinkedList<>();
        znajdzTrase(najblizszyCel);
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

    public void usun(){
          
//        Thread.currentThread().interrupt();
        getObrazek().setX(-100);
        System.out.println("TERAZ USUNĘ");
        synchronized(Swiat.getSamoloty()){
            zwolnijPole(); 
        }        
        dzialaj = false;
        
//        this.setObrazek(null);
               
    }
    
    public void zmienCel(Lokalizacja lok){
        this.najblizszyCel = lok;
        System.out.println( this.toString() +  ": Ustalono nowy cel: " + this.najblizszyCel.getNazwa() + this.najblizszyCel.getPolozenie() );
        System.out.println("ZEROWY INDEKS TRASY: " + this.trasa.get(0).getNazwa());
        System.out.println("NAJBLIŻSZY CEL: " + this.najblizszyCel.getNazwa());
        this.setKierunek(this.trasa.get(0).jakDojechac( this.najblizszyCel ).getKierunek());
        
         switch (this.getKierunek())
        {
            case LEWO:
                this.modyfikatorX = 0;
                this.modyfikatorY = -8;
                this.getObrazek().setRotate(225);
                break;
            case PRAWO:
                this.modyfikatorX = 0;
                this.modyfikatorY = 8;
                this.getObrazek().setRotate(45);
                break;
            case DOL:
                this.modyfikatorX = -8;
                this.modyfikatorY = 0;
                this.getObrazek().setRotate(135);
                break;
            case GORA:
                this.modyfikatorX = 8;
                this.modyfikatorY = 0;
                this.getObrazek().setRotate(315);
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
//        if (!dzialaj){
//            System.out.println("TERAZ WYJDĘ");
//            return;
//        }
         
        this.polozenie.setY(this.polozenie.getY()+getDeltaY());
        this.polozenie.setX(this.polozenie.getX()+getDeltaX());
        paliwo -= 2;
        
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
            for(int i=1; i<20; i++){
                if(Swiat.getSamoloty().containsKey(( polozenie.getX() + getModyfikatorX() + i*getDeltaX())+"_" + (polozenie.getY()+ getModyfikatorY() + i * getDeltaY()))){
                    System.out.println(( polozenie.getX() +  i*getDeltaX())+"_" + (polozenie.getY()+ i * getDeltaY()));
                    czy = false;
                    break;
                }
            }
//        }
        return czy;
    }
    
    private void zajmijPole(){
        Swiat.getSamoloty().put((polozenie.getX() + getModyfikatorX() + getDeltaX() ) + "_" + (polozenie.getY() + getModyfikatorY() + getDeltaY()), (Samolot) this);
//        System.out.println((polozenie.getX() + deltaX) + "_" + (polozenie.getY() + deltaY));
        
    }
    
    private void zwolnijPole(){
        Swiat.getSamoloty().remove((polozenie.getX() + getModyfikatorX()) + "_" + (polozenie.getY() + getModyfikatorY()));        
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

    /**
     * @return the paliwo
     */
    public int getPaliwo() {
        return paliwo;
    }
    
    protected void losujTrase(Polozenie p){
        int i = Swiat.getTrasy().get(p.getX() + "_" + p.getY()).size();
        this.trasa = new LinkedList<>(Swiat.getTrasy().get(p.getX() +"_" + p.getY())
                                .get((int)(Math.random() * i)));
        System.out.println("MOŻLIWOŚCI: "+ i);
//        if(this instanceof SamolotPasazerski){
            trasa.removeAll(trasa);
            znajdzTrase(Swiat.getLokalizacje().get(p.getX() + "_" + p.getY()));
//        }
    }
    
    public void zmienTrase(){
        LinkedList<Lokalizacja> trasaStartowa = new LinkedList<>();
        for(Lokalizacja l : trasa){
            if (!(l instanceof Skrzyzowanie)){
                losujTrase(l.getPolozenie());
                break;
            }            
            else{
                trasaStartowa.add(l);
            }
        }
        trasa.addAll(0, trasaStartowa);
                
    }
    
    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {                
                ruszaniePojazdu();
                przemieszczaniePojazdu();
                this.najblizszyCel.stopujPojazd(this);
                zwolnijPole();
                konczenieTrasyPojazdu();
                obslugaNaMiejscu(); 
            }        
    }

    /**
     * @return the modyfikatorX
     */
    public int getModyfikatorX() {
        return modyfikatorX;
    }

    /**
     * @return the modyfikatorY
     */
    public int getModyfikatorY() {
        return modyfikatorY;
    }

    /**
     * @return the deltaX
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * @return the deltaY
     */
    public int getDeltaY() {
        return deltaY;
    }
    
    public void ruszaniePojazdu(){
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
    }
    
    public void przemieszczaniePojazdu(){
        while (this.getOdleglosc()>20){
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
                    getObrazek().setX(getPolozenie().getX()-8 + getModyfikatorX());
                    getObrazek().setY(getPolozenie().getY()-8 + getModyfikatorY());
                }
            });

        }  
    }
    
    public void konczenieTrasyPojazdu(){
        while (this.getOdleglosc()>0){
        System.out.println("WOLNE");
        try {
            this.przemiescSie();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                getObrazek().setX(getPolozenie().getX()-8 + getModyfikatorX());
                getObrazek().setY(getPolozenie().getY()-8 + getModyfikatorY());
            }
        });
}
    }
    
    public void znajdzTrase(Lokalizacja skad){}
    
    public void obslugaNaMiejscu(){
        int sen;
        if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski)){
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
        
        if (this.trasa.get(0) instanceof Lotnisko){
            paliwo = 999;
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
            while(trasa.size() <2){
                znajdzTrase(najblizszyCel);
            }            
            System.out.println(trasa.get(1).getNazwa());
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
    }

    /**
     * @return the dzialaj
     */
    public boolean isDzialaj() {
        return dzialaj;
    }
    
    public void tankuj(){
        paliwo = 999;
    }
    
}
