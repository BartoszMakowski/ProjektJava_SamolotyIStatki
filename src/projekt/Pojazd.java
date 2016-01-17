package projekt;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * Created by bartosz on 18.10.15.
 * Implementuje pojazd.
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
    
    /**
     * Tworzy pojazd.
     * @param polozenie położenie startowe tworzonego pojazdu
     * @param predkosc prędkość tworzonego pojazdu
     * @param trasa trasa tworzonego pojazdu
     */
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
    
    /**
     * Tworzy pojazd.
     * @param polozenie położenie startowe tworzonego pojazdu
     * @param predkosc prędkość tworzonego pojazdu
     * @param najblizszyCel najbliższy Cel tworzonego pojazdu
     * @param trasa tasa tworzonego pojazdu
     */
    public Pojazd(Polozenie polozenie, int predkosc, Lokalizacja najblizszyCel, List<Lokalizacja> trasa){
        this(polozenie, predkosc, trasa);
        this.najblizszyCel = najblizszyCel;
        this.paliwo = 999;
        this.trasa = new LinkedList<>();
        znajdzTrase(najblizszyCel);
    }
    
    /**
     * Tworzy pojazd.
     * @param polozenie położenie startowe tworzonego pojazdu
     * @param predkosc prędkość tworzonego pojazdu
     */
    public Pojazd(Polozenie polozenie, int predkosc){
        this(polozenie, predkosc, null);
//        this.najblizszyCel = najblizszyCel;
        this.paliwo = 999;
        this.trasa = new LinkedList<>();
//        znajdzTrase(najblizszyCel);
    }
    
    /**
     * Zwraca prędkosć pojazdu.
     * @return prędkość pojazdu
     */
    public int getPredkosc() {
        return predkosc;
    }
    
    /**
     * Zwraca trasę pojazdu.
     * @return trasa pojazdu
     */
    public List<Lokalizacja> getTrasa() {
        return trasa;
    }
    
    /**
     * Ustawia trasę pojazdu.
     * @param trasa nowa trasa pojazdu
     */
    public void setTrasa(List<Lokalizacja> trasa) {
        this.trasa = trasa;
    }
    
    /**
     * Zwraca ID pojazdu.
     * @return ID pojazdu
     */
    public int getId() {
        return id;
    }
    
    /**
     * Zwraca aktualne położenie pojazdu.
     * @return aktualne położenie pojazdu
     */
    public Polozenie getPolozenie() {
        return polozenie;
    }
    
    /**
     * Zwraca najbliższy cel pojazdu.
     * @return najbliższy cel pojazdu
     */
    public Lokalizacja getNajblizszyCel() {
        return najblizszyCel;
    }
    /**
     * Ustawia najbliższy cel pojazdu.
     * @param najblizszyCel nowy najbliższy cel pojazdu
     */
    public void setNajblizszyCel(Lokalizacja najblizszyCel) {
        this.najblizszyCel = najblizszyCel;
    }
    
    /**
     * Usuwa pojazd.
     */
    public void usun(){
        dzialaj = false;
//        Thread.currentThread().interrupt();
//        zwolnijPole();
    }
    
    /**
     * Ustawia parametry lotu dla nowego celu.
     * @param lok nowy cel
     */
    public void zmienCel(Lokalizacja lok){
        this.najblizszyCel = lok;
        this.setKierunek(this.trasa.get(0).jakDojechac( this.najblizszyCel ).getKierunek());
        
         switch (this.getKierunek())
        {
            case LEWO:
                this.setModyfikatorX(0);
                this.setModyfikatorY(-8);
                this.getObrazek().setRotate(225);
                break;
            case PRAWO:
                this.setModyfikatorX(0);
                this.setModyfikatorY(8);
                this.getObrazek().setRotate(45);
                break;
            case DOL:
                this.setModyfikatorX(-8);
                this.setModyfikatorY(0);
                this.getObrazek().setRotate(135);
                break;
            case GORA:
                this.setModyfikatorX(8);
                this.setModyfikatorY(0);
                this.getObrazek().setRotate(315);
                break;
        }
         
               switch (this.getKierunek())
        {
            case LEWO:
                setDeltaX(-1);
                setDeltaY(0);
                break;
            case PRAWO:
                setDeltaX(1);
                setDeltaY(0);
                break;
            case DOL:
                setDeltaY(1);
                setDeltaX(0);
                break;
            case GORA:
                setDeltaY(-1);
                setDeltaX(0);
                break;
            default:
                break;
        }
        
        this.odleglosc = this.trasa.get(0).jakDojechac( this.najblizszyCel ).getOdleglosc();
//        System.out.println("ODLEGŁOŚĆ: " + this.odleglosc);
    }
    
    /**
     * Przemieszcza pojazd o 1 piksel.
     * @throws InterruptedException 
     */
    public void przemiescSie() throws InterruptedException {
        if (!dzialaj){
            return;
        }
         
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
    
    /**
     * Ustawia położenie pojazdu.
     * @param polozenie nowe położenie pojazdu.
     */
    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }
    
    /**
     * Zwraca kierunek pojazdu.
     * @return kierunek pojazdu
     */
    public Kierunek getKierunek() {
        return kierunek;
    }

    /**
     * Ustawia kierunek pojazdu.
     * @param kierunek nowy kierunek pojazdu
     */
    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }
    
    

    /**
     * Zwraca odległość od najbliższego celu.
     * @return odległość od najbliższego celu
     */
    public int getOdleglosc() {
        return odleglosc;
    }

    /**
     * Zwraca ikonę pojazdu.
     * @return ikona pojazdu
     */
    public javafx.scene.image.ImageView getObrazek() {
        return obrazek;
    }

    /**
     * Ustawia ikonę pojazdu.
     * @param obrazek nowa ikona pojazdu
     */
    public void setObrazek(javafx.scene.image.ImageView obrazek) {
        this.obrazek = obrazek;
    }
    
    /**
     * Wykrywa kolizje z pojazdami poprzedzającymi.
     * @return zaistnienie kolizji
     */
    public boolean sprawdzPole(){
        boolean czy = true;
            for(int i=1; i<20; i++){
                if(Swiat.getSamoloty().containsKey(( polozenie.getX() + getModyfikatorX() + i*getDeltaX())+"_" + (polozenie.getY()+ getModyfikatorY() + i * getDeltaY()))){
//                    System.out.println(( polozenie.getX() +  i*getDeltaX())+"_" + (polozenie.getY()+ i * getDeltaY()));
                    czy = false;
                    break;
                }
            }
        return czy;
    }
    
 
    private void zajmijPole(){}

    private void zwolnijPole(){}
    
    /**
     * * Sprawdz możliwość wykonania ruchu. Metoda implementowana przez klasy dziedziczące.
     * @return możliwość wykonania ruchu
     * @throws InterruptedException 
     */
    public boolean czyMozna() throws InterruptedException{
        return true;
    }

    /**
     * Zwraca aktualną ilość paliwa.
     * @return aktualna ilość paliwa
     */
    public int getPaliwo() {
        return paliwo;
    }
    
    protected void losujTrase(Polozenie p){
            znajdzTrase(Swiat.getLokalizacje().get(p.getX() + "_" + p.getY()));
//        }
    }
    
    /**
     * Losuje nową trasę pojazdu.
     */
    public void zmienTrase(){
//        Lokalizacja l = trasa.get(0);
        znajdzTrase(trasa.get(0));             
                
    }
    
    @Override
    public void run(){
//        Image gpojazd;
            while(isDzialaj())
            {                
                ruszaniePojazdu();
                przemieszczaniePojazdu();
                if(!dzialaj){
                    zwolnijPole();
                    break;
                }
                this.najblizszyCel.stopujPojazd(this);
                zwolnijPole();
                konczenieTrasyPojazdu();
                obslugaNaMiejscu(); 
            }        
    }

    /**
     * Zwraca przesunięcie ikony pojazdu w osi X.
     * @return przesunięcie ikony pojazdu w osi X
     */
    public int getModyfikatorX() {
        return modyfikatorX;
    }

    /**
     * Zwraca przesunięcie ikony pojazdu w osi Y.
     * @return przesunięcie ikony pojazdu w osi Y
     */
    public int getModyfikatorY() {
        return modyfikatorY;
    }

    /**
     * Zwraca wartość składowej X wektora prędkości pojazdu.
     * @return składowej X wektora prędkości pojazdu
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * * Zwraca wartość składowej Y wektora prędkości pojazdu.
     * @return składowa Y wektora prędkości pojazdu
     */
    public int getDeltaY() {
        return deltaY;
    }
    
    /**
     * Start pojazdu z lokalizacji.
     */
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
    
    /**
     * Przemieszcza pojazd w kierunku celu.
     */
    public void przemieszczaniePojazdu(){
        while (this.getOdleglosc()>20){
             try {
                czyMozna();
                this.przemiescSie();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pojazd.class.getName()).log(Level.SEVERE, null, ex);
            }
             if(!dzialaj){
                 break;
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
    
    /**
     * Przemieszcza pojazd do celu, gdy pojazd ten zarezerwował już w nim miejsce.
     */
    public void konczenieTrasyPojazdu(){
        int predkoscOryginalna = predkosc;
        predkosc = 8;
        while (this.getOdleglosc()>0){
//            if(!dzialaj){
//                break;
//            }
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
        predkosc = predkoscOryginalna;
    }
    
    /**
     * Losuje nową trasę pojazdu zaczynającą się od podanej lokalizacji
     * @param skad lokalizacja początkowa
     */
    public void znajdzTrase(Lokalizacja skad){}
    
    /**
     * Implemntuje czynności wykonywane w lokaliacjach, w których zatrzymuje się pojazd.
     */
    public void obslugaNaMiejscu(){
//        if(!dzialaj){            
//            return;
//        }
        int sen;
        if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski)){
//            System.out.println("        " + this.trasa.get(0).getNazwa());
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
//            System.out.println("Dotarłem do: " + this.najblizszyCel.getNazwa() + ".\nKONIEC TRASY.");
            while(trasa.size() <2){
                znajdzTrase(najblizszyCel);
            }            
            System.out.println(trasa.get(1).getNazwa());
            this.zmienCel(this.trasa.get(1));
        }
        zwolnijPole();

//        System.out.println("PASAŻEROWIE, WSIADAJCIE!");
        if ((this instanceof Pasazerski) && (this.trasa.get(0) instanceof Pasazerski))
        {
            ((Pasazerski)this.trasa.get(0)).przesiadkaPasazera((Pasazerski)this);
        }

        this.trasa.get(0).startujPojazd(this);
        this.trasa.remove(0);
    }

    /**
     * Zwraca stan pojazdu (aktywny/do usunięcia).
     * @return prawdziwość stanu aktywnego pojazdu
     */
    public boolean isDzialaj() {
        return dzialaj;
    }
    
    /**
     * Uzupełnia zapas paliwa pojazdu.
     */
    public void tankuj(){
        paliwo = 999;
    }
    
    /**
     * Uzupełnia zapas paliwa pojazdu do podanej wartości.
     * @param f nowa ilość paliwa
     */
    public void tankuj(int f){
        if(f<10000 && f>0){
            paliwo = f;
        }
        else{
            tankuj();
        }
        
    }

    /** Ustawia wartość przesunięcia ikony pojazdu w osi X.
     * @param modyfikatorX nowa wartość przesunięcia ikony pojazdu w osi X
     */
    public void setModyfikatorX(int modyfikatorX) {
        this.modyfikatorX = modyfikatorX;
    }

    /** Ustawia wartość przesunięcia ikony pojazdu w osi Y.
     * @param modyfikatorY nowa wartość przesunięcia ikony pojazdu w osi Y
     */
    public void setModyfikatorY(int modyfikatorY) {
        this.modyfikatorY = modyfikatorY;
    }

    /** Ustawia wartość składowej X wektora prędkości pojazdu.
     * @param deltaX nowa wartość składowej X wektora prędkości pojazdu
     */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /** Ustawia wartość składowej Y wektora prędkości pojazdu.
     * @param deltaY nowa wartość składowej Y wektora prędkości pojazdu
     */
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    /**
     * Ustawia stan pojazdu (aktywny/do usunięcia).
     * @param dzialaj nowa wartość dla prawdziwości działania
     */
    public void setDzialaj(boolean dzialaj) {
        this.dzialaj = dzialaj;
    }
    
}
