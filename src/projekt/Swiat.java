/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

/**
 *
 * @author bartosz
 * Implementuje świat.
 */
public class Swiat extends Application {
    private static HashMap<String, Lotnisko> lotniskaCywilne;
    private static HashMap<String, Lotnisko> lotniskaWojskowe;
    private static HashMap<String, PortMorski> portyMorskie;
    private static ArrayList<Lokalizacja> miasta;
    private static HashMap<String, Podrozny> pasazerowie;
    private static HashMap<String, Lokalizacja> lokalizacje;
    private static HashMap<String, Skrzyzowanie> skrzyzowania;
//    private static HashMap<String, ArrayList<LinkedList<Lokalizacja>>> trasy;
//    private static HashMap<String, LinkedList<Lokalizacja>> rozklady;
    private static HashMap<String, Samolot> samoloty;
    private static HashMap<String, Statek> statki;
//    private static Czolg czolg;

    /**
     * Zwraca listę lotnisk cywilnych.
     * @return lista lotnisk cywilnych
     * 
     */
    public static HashMap<String, Lotnisko> getLotniska() {
        return getLotniskaCywilne();
    }

    /**
     * Zwraca hashmapę samolotów.
     * @return hashmapa samolotów
     */
    public static HashMap<String, Samolot> getSamoloty() {
        return samoloty;
    }

    /**
     * Zwraca hashmapę pasażerów.
     * @return hashmapa pasażerów
     */
    public static HashMap<String, Podrozny> getPasazerowie() {
        return pasazerowie;
    }

    /**
     * Zwradca hashmapę portów morskich.
     * @return hashmapa portów morskich
     */
    public static HashMap<String, PortMorski> getPortyMorskie() {
        return portyMorskie;
    }

    /**
     * Zwraca hashmapę statków.
     * @return hashmapa statków
     */
    public static HashMap<String, Statek> getStatki() {
        return statki;
    }

    /**
     * Zwraca hashmapę wszystki lokalizacji.
     * @return hashmapa lokalizacji
     */
    public static HashMap<String, Lokalizacja> getLokalizacje() {
        return lokalizacje;
    }

    /**
     * Zwraca hashmapę lotnisk wojskowych.
     * @return hashmapa lotnisko wojskowych
     */
    public static HashMap<String, Lotnisko> getLotniskaWojskowe() {
        return lotniskaWojskowe;
    }

    /**
     * Zwraca hashmapę skrzyżowań.
     * @return hashmapa skrzyżowań
     */
    public static HashMap<String, Skrzyzowanie> getSkrzyzowania() {
        return skrzyzowania;
    }

//    /**
//     * Zwraca
//     * @return the trasy
//     */
//    public static HashMap<String, ArrayList<LinkedList<Lokalizacja>>> getTrasy() {
//        return trasy;
//    }

    /**
     * Zwraca tablicę lokalizacji dostępnych dla podróżnych.
     * @return tablica lokalizacji dostępnych dla podróżnych
     */
    public static ArrayList<Lokalizacja> getMiasta() {
        return miasta;
    }

    /**
     * Zwraca hashmapę lotnisk cywilnych.
     * @return hashmapa lotnisk cywilnych
     */
    public static HashMap<String, Lotnisko> getLotniskaCywilne() {
        return lotniskaCywilne;
    }

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Thread renderer = new Thread(){
            
            @Override
            public void run(){
                lotniskaCywilne = new HashMap<String, Lotnisko>();
                lotniskaWojskowe = new HashMap<String, Lotnisko>();
                portyMorskie = new HashMap<String, PortMorski>();
                lokalizacje = new HashMap<String, Lokalizacja>();
                skrzyzowania = new HashMap<String, Skrzyzowanie>();
                pasazerowie = new HashMap<String, Podrozny>();
                samoloty = new HashMap<String, Samolot>();
                statki = new HashMap<String, Statek>();
//                trasy = new HashMap<String, ArrayList<LinkedList<Lokalizacja>>>();
                miasta = new ArrayList<>();
                
//                TWORZENIE LOTNISK CYWILNYCH
                
                Lotnisko brzysko = new Lotnisko(550, 20, "Brzyskorzystewko", 3, TypPortu.CYWILNY);
                Lotnisko koty = new Lotnisko(550, 360, "Koty", 5, TypPortu.CYWILNY);
                Lotnisko samokleski = new Lotnisko(550, 550, "Samoklęski Duże", 2, TypPortu.CYWILNY);
                Lotnisko kity = new Lotnisko(365, 200, "Kity", 4, TypPortu.CYWILNY);
                Lotnisko leszcze = new Lotnisko(365, 550, "Leszcze", 1, TypPortu.CYWILNY);
                Lotnisko zlawies = new Lotnisko(90, 550, "Zławieś Wielka", 3, TypPortu.CYWILNY);
                
                getLotniskaCywilne().put("550_20", brzysko);
                getLotniskaCywilne().put("365_200", kity);
                getLotniskaCywilne().put("550_550", samokleski);
                getLotniskaCywilne().put("550_360", koty);
                getLotniskaCywilne().put("365_550", leszcze);
                getLotniskaCywilne().put("90_550", zlawies);
                
//                TWORZENIE LOTNISK WOJSKOWYCH
                
                Lotnisko alcatraz = new Lotnisko(30, 320, "Alcatraz", 1, TypPortu.WOJSKOWY);
                Lotnisko powidz = new Lotnisko(250, 20, "Powidz", 3, TypPortu.WOJSKOWY);
                Lotnisko murzynno = new Lotnisko(460, 400, "Murzynno", 2, TypPortu.WOJSKOWY);
                Lotnisko pasy = new Lotnisko(250, 460, "Pasy Krótkie", 2, TypPortu.WOJSKOWY);
                
                getLotniskaWojskowe().put("30_320", alcatraz);
                getLotniskaWojskowe().put("250_20", powidz);
                getLotniskaWojskowe().put("460_400", murzynno);
                getLotniskaWojskowe().put("250_460", pasy);
                
//                TWORZENIE PORTÓW MORSKICH
                
                PortMorski borowno = new PortMorski(115, 145, "Borówno");
                PortMorski pieczyska = new PortMorski(315, 145, "Pieczyska");
                PortMorski powidzPrzystan = new PortMorski(275, 20, "Powidz - Przystań");
                PortMorski samo = new PortMorski(430, 355, "Samociążek");
                PortMorski chmielniki = new PortMorski(140, 455, "Chmielniki");
                                
                getPortyMorskie().put("115_145", borowno);
                getPortyMorskie().put("315_145", pieczyska);
                getPortyMorskie().put("275_20", powidzPrzystan);
                getPortyMorskie().put("430_355", samo);
                getPortyMorskie().put("140_455", chmielniki);
                
//                TWORZENIE SKRZYŻOWAŃ MORSKICH
                
                Skrzyzowanie sm1 = new Skrzyzowanie(275, 145, "SM1");
                getSkrzyzowania().put("275_145", sm1);
                
                Skrzyzowanie sm2 = new Skrzyzowanie(140, 355, "SM2");
                getSkrzyzowania().put("140_355", sm2);
                                
                Skrzyzowanie sm3 = new Skrzyzowanie(275, 355, "SM3");
                getSkrzyzowania().put("275_355", sm3);
//                TWORZENIE SKRZYŻOWAŃ POWIETRZNYCH
                
                Skrzyzowanie s1 = new Skrzyzowanie(365, 20, "S1");
                getSkrzyzowania().put("365_20", s1);
                Skrzyzowanie s3 = new Skrzyzowanie(250, 200, "S3");
                getSkrzyzowania().put("250_200", s3);
                Skrzyzowanie s2 = new Skrzyzowanie(30, 200, "S2");
                getSkrzyzowania().put("30_200", s2);
                Skrzyzowanie s4 = new Skrzyzowanie(550, 200, "S4");
                getSkrzyzowania().put("550_200", s4);
                Skrzyzowanie s5 = new Skrzyzowanie(90, 320, "S5");
                getSkrzyzowania().put("90_320", s5);
                
                Skrzyzowanie s6 = new Skrzyzowanie(90, 400, "S6");
                getSkrzyzowania().put("90_400", s6);
                Skrzyzowanie s7 = new Skrzyzowanie(250, 400, "S7");
                getSkrzyzowania().put("250_400", s7);
                Skrzyzowanie s8 = new Skrzyzowanie(365, 400, "S8");
                getSkrzyzowania().put("365_400", s8);
                Skrzyzowanie s9 = new Skrzyzowanie(90, 460, "S9");
                getSkrzyzowania().put("90_460", s9);
                Skrzyzowanie s10 = new Skrzyzowanie(365, 460, "S10");
                getSkrzyzowania().put("365_460", s10);
                
                Skrzyzowanie s11 = new Skrzyzowanie(460, 460, "S11");
                getSkrzyzowania().put("460_460", s11);
                Skrzyzowanie s12 = new Skrzyzowanie(550, 460, "S12");
                getSkrzyzowania().put("550_460", s12);
                Skrzyzowanie s13 = new Skrzyzowanie(250, 550, "S13");
                getSkrzyzowania().put("250_550", s13);
                Skrzyzowanie s14 = new Skrzyzowanie(460, 550, "S14");
                getSkrzyzowania().put("460_550", s14);
                
                brzysko.dodajDrogowskaz(new Drogowskaz(brzysko, s1));
                brzysko.dodajDrogowskaz(new Drogowskaz(brzysko, s4));
                
                kity.dodajDrogowskaz(new Drogowskaz(180, s1, Kierunek.GORA));
                kity.dodajDrogowskaz(new Drogowskaz(kity, s3));
                kity.dodajDrogowskaz(new Drogowskaz(kity, s4));
                kity.dodajDrogowskaz(new Drogowskaz(kity, s8));
                
                koty.dodajDrogowskaz(new Drogowskaz(koty, s4));
                koty.dodajDrogowskaz(new Drogowskaz(koty, s12));
                
                zlawies.dodajDrogowskaz(new Drogowskaz(zlawies, s13));
                zlawies.dodajDrogowskaz(new Drogowskaz(zlawies, s9));
                
                leszcze.dodajDrogowskaz(new Drogowskaz(leszcze, s13));
                leszcze.dodajDrogowskaz(new Drogowskaz(leszcze, s14));
                leszcze.dodajDrogowskaz(new Drogowskaz(leszcze, s10));
                
                samokleski.dodajDrogowskaz(new Drogowskaz(samokleski, s14));
                samokleski.dodajDrogowskaz(new Drogowskaz(samokleski, s12));
                
                alcatraz.dodajDrogowskaz(new Drogowskaz(alcatraz, s2));
                alcatraz.dodajDrogowskaz(new Drogowskaz(alcatraz, s5));
                
                powidz.dodajDrogowskaz(new Drogowskaz(powidz, s1));
                powidz.dodajDrogowskaz(new Drogowskaz(powidz, s3));
                
                pasy.dodajDrogowskaz(new Drogowskaz(pasy, s7));
                pasy.dodajDrogowskaz(new Drogowskaz(pasy, s9));
                pasy.dodajDrogowskaz(new Drogowskaz(pasy, s10));
                pasy.dodajDrogowskaz(new Drogowskaz(pasy, s13));
                
                murzynno.dodajDrogowskaz(new Drogowskaz(murzynno, s8));
                murzynno.dodajDrogowskaz(new Drogowskaz(murzynno, s11));
                
                s1.dodajDrogowskaz(new Drogowskaz(s1, brzysko));
                s1.dodajDrogowskaz(new Drogowskaz(s1, kity));
                s1.dodajDrogowskaz(new Drogowskaz(s1, powidz));
                
                s2.dodajDrogowskaz(new Drogowskaz(s2, s3));
                s2.dodajDrogowskaz(new Drogowskaz(s2, alcatraz));
                
                s3.dodajDrogowskaz(new Drogowskaz(s3, kity));
                s3.dodajDrogowskaz(new Drogowskaz(s3, s2));
                s3.dodajDrogowskaz(new Drogowskaz(s3, powidz));
                
                s4.dodajDrogowskaz(new Drogowskaz(s4,brzysko));
                s4.dodajDrogowskaz(new Drogowskaz(s4, kity));
                s4.dodajDrogowskaz(new Drogowskaz(s4, koty));
                
                s5.dodajDrogowskaz(new Drogowskaz(s5, s6));
                s5.dodajDrogowskaz(new Drogowskaz(s5, alcatraz));
                
                s6.dodajDrogowskaz(new Drogowskaz(s6, s5));
                s6.dodajDrogowskaz(new Drogowskaz(s6, s7));
                s6.dodajDrogowskaz(new Drogowskaz(s6, s9));
                
                s7.dodajDrogowskaz(new Drogowskaz(s7, s6));
                s7.dodajDrogowskaz(new Drogowskaz(s7, s8));
                s7.dodajDrogowskaz(new Drogowskaz(s7, pasy));
                
                s8.dodajDrogowskaz(new Drogowskaz(s8, s7));
                s8.dodajDrogowskaz(new Drogowskaz(s8, s10));
                s8.dodajDrogowskaz(new Drogowskaz(s8, kity));
                s8.dodajDrogowskaz(new Drogowskaz(s8, murzynno));
                
                s9.dodajDrogowskaz(new Drogowskaz(s9, s6));
                s9.dodajDrogowskaz(new Drogowskaz(s9, zlawies));
                s9.dodajDrogowskaz(new Drogowskaz(s9, pasy));
                
                s10.dodajDrogowskaz(new Drogowskaz(s10, s8));
                s10.dodajDrogowskaz(new Drogowskaz(s10, s11));                
                s10.dodajDrogowskaz(new Drogowskaz(s10, leszcze));
                s10.dodajDrogowskaz(new Drogowskaz(s10, pasy));
                
                s11.dodajDrogowskaz(new Drogowskaz(s11, s10));
                s11.dodajDrogowskaz(new Drogowskaz(s11, s12));
                s11.dodajDrogowskaz(new Drogowskaz(s11, s14));
                s11.dodajDrogowskaz(new Drogowskaz(s11, murzynno));
                
                s12.dodajDrogowskaz(new Drogowskaz(s12, s11));
                s12.dodajDrogowskaz(new Drogowskaz(s12, koty));
                s12.dodajDrogowskaz(new Drogowskaz(s12, samokleski));
                
                s13.dodajDrogowskaz(new Drogowskaz(s13, zlawies));
                s13.dodajDrogowskaz(new Drogowskaz(s13, leszcze));
                s13.dodajDrogowskaz(new Drogowskaz(s13, pasy));
                
                s14.dodajDrogowskaz(new Drogowskaz(s14, s11));
                s14.dodajDrogowskaz(new Drogowskaz(s14, leszcze));
                s14.dodajDrogowskaz(new Drogowskaz(s14, samokleski));
                
//                TWORZENIE DROGOWSKAZÓW MORSKICH

                powidzPrzystan.dodajDrogowskaz(new Drogowskaz(powidzPrzystan, sm1));                
                borowno.dodajDrogowskaz(new Drogowskaz(borowno, sm1));                
                pieczyska.dodajDrogowskaz(new Drogowskaz(pieczyska, sm1));                
                chmielniki.dodajDrogowskaz(new Drogowskaz(chmielniki, sm2));
                samo.dodajDrogowskaz(new Drogowskaz(samo, sm3));
                
                sm1.dodajDrogowskaz(new Drogowskaz(sm1, borowno));
                sm1.dodajDrogowskaz(new Drogowskaz(sm1, powidzPrzystan));
                sm1.dodajDrogowskaz(new Drogowskaz(sm1, pieczyska));
                sm1.dodajDrogowskaz(new Drogowskaz(sm1, sm3));
                
                sm2.dodajDrogowskaz(new Drogowskaz(sm2, chmielniki));
                sm2.dodajDrogowskaz(new Drogowskaz(sm2, sm3));
                
                sm3.dodajDrogowskaz(new Drogowskaz(sm3, sm2));
                sm3.dodajDrogowskaz(new Drogowskaz(sm3, sm1));
                sm3.dodajDrogowskaz(new Drogowskaz(sm3, samo)); 
                
//              ROZWIĄZANIE POZWALAJĄCE PASAŻEROM LOSOWANIE TRAS SKŁADAJĄCYCH SIĘ Z LOTNISK I PORTÓW:

                pieczyska.dodajDrogowskaz(new Drogowskaz(pieczyska,kity));
                kity.dodajDrogowskaz(new Drogowskaz(kity,pieczyska));
                
                
//                niebieskie.dodajDrogowskaz(new Drogowskaz(40, s1, Kierunek.PRAWO));
//                s1.dodajDrogowskaz(new Drogowskaz(70, czerwone, Kierunek.DOL));
//                s1.dodajDrogowskaz(new Drogowskaz(40, niebieskie, Kierunek.LEWO));
//                czerwone.dodajDrogowskaz(new Drogowskaz(70, s1, Kierunek.GORA));
                
                getLokalizacje().putAll(getLotniskaCywilne());
                getLokalizacje().putAll(getLotniskaWojskowe());
                getLokalizacje().putAll(portyMorskie);
                getLokalizacje().putAll(getSkrzyzowania());
                getMiasta().addAll(getLotniskaCywilne().values());
                getMiasta().addAll(portyMorskie.values());

                System.out.println("Utworzone lokalizacje: ");
                for (Lokalizacja l : getLotniska().values()){
                    System.out.println(l.getNazwa());
                }

            }
        };
        
        renderer.setDaemon(true);
        renderer.start();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    
}
