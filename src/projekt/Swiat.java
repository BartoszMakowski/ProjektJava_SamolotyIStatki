/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.Collection;
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
 */
public class Swiat extends Application {
    private static HashMap<String, Lotnisko> lotniskaCywilne;
    private static HashMap<String, Lotnisko> lotniskaWojskowe;
    private static HashMap<String, PortMorski> portyMorskie;
    private static ArrayList<Lokalizacja> miasta;
    private static HashMap<String, Podrozny> pasazerowie;
    private static HashMap<String, Lokalizacja> lokalizacje;
    private static HashMap<String, Skrzyzowanie> skrzyzowania;
    private static HashMap<String, ArrayList<LinkedList<Lokalizacja>>> trasy;
    private static HashMap<String, LinkedList<Lokalizacja>> rozklady;
    private static HashMap<String, Samolot> samoloty;
    private static HashMap<String, Statek> statki;
//    public static SamolotPasazerski sp;
    
//    public static LinkedList<Lokalizacj> ptrasa;

    /**
     * @return the lotniska
     */
    public static HashMap<String, Lotnisko> getLotniska() {
        return lotniskaCywilne;
    }

    /**
     * @return the pojazdy
     */
    public static HashMap<String, Samolot> getSamoloty() {
        return samoloty;
    }

    /**
     * @return the pasazerowie
     */
    public static HashMap<String, Podrozny> getPasazerowie() {
        return pasazerowie;
    }

    /**
     * @return the portyMorskie
     */
    public static HashMap<String, PortMorski> getPortyMorskie() {
        return portyMorskie;
    }

    /**
     * @return the statki
     */
    public static HashMap<String, Statek> getStatki() {
        return statki;
    }

    /**
     * @return the lokalizacje
     */
    public static HashMap<String, Lokalizacja> getLokalizacje() {
        return lokalizacje;
    }

    /**
     * @return the lotniskaWojskowe
     */
    public static HashMap<String, Lotnisko> getLotniskaWojskowe() {
        return lotniskaWojskowe;
    }

    /**
     * @return the skrzyzowania
     */
    public static HashMap<String, Skrzyzowanie> getSkrzyzowania() {
        return skrzyzowania;
    }

    /**
     * @return the trasy
     */
    public static HashMap<String, ArrayList<LinkedList<Lokalizacja>>> getTrasy() {
        return trasy;
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
                trasy = new HashMap<String, ArrayList<LinkedList<Lokalizacja>>>();
                
//                TWORZENIE LOTNISK CYWILNYCH
                
                Lotnisko brzysko = new Lotnisko(550, 20, "Brzyskorzystewko", 3, TypPortu.CYWILNY);
                Lotnisko koty = new Lotnisko(550, 360, "Koty", 5, TypPortu.CYWILNY);
                Lotnisko samokleski = new Lotnisko(550, 550, "Samoklęski Duże", 2, TypPortu.CYWILNY);
                Lotnisko kity = new Lotnisko(365, 200, "Kity", 4, TypPortu.CYWILNY);
                Lotnisko leszcze = new Lotnisko(365, 550, "Leszcze", 1, TypPortu.CYWILNY);
                Lotnisko zlawies = new Lotnisko(90, 550, "Zławieś Wielka", 3, TypPortu.CYWILNY);
                
                lotniskaCywilne.put("550_20", brzysko);
                lotniskaCywilne.put("365_200", kity);
                lotniskaCywilne.put("550_550", samokleski);
                lotniskaCywilne.put("550_360", koty);
                lotniskaCywilne.put("365_550", leszcze);
                lotniskaCywilne.put("90_550", zlawies);
                
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

                int n = powidzPrzystan.dodajDrogowskaz(new Drogowskaz(powidzPrzystan, sm1));
                System.out.println("NNNNNNNNNNNNNNNN: " + n);
                sm1.dodajDrogowskaz(new Drogowskaz(sm1, borowno));
                
                
//                niebieskie.dodajDrogowskaz(new Drogowskaz(40, s1, Kierunek.PRAWO));
//                s1.dodajDrogowskaz(new Drogowskaz(70, czerwone, Kierunek.DOL));
//                s1.dodajDrogowskaz(new Drogowskaz(40, niebieskie, Kierunek.LEWO));
//                czerwone.dodajDrogowskaz(new Drogowskaz(70, s1, Kierunek.GORA));
                
                getLokalizacje().putAll(lotniskaCywilne);
                getLokalizacje().putAll(getLotniskaWojskowe());
                getLokalizacje().putAll(portyMorskie);
                getLokalizacje().putAll(getSkrzyzowania());
//                miasta.addAll( lotniskaCywilne);
//                miasta.addAll((Collection<? extends Lokalizacja>) portyMorskie);

                System.out.println("Utworzone lokalizacje: ");
                for (Lokalizacja l : getLotniska().values()){
                    System.out.println(l.getNazwa());
                }

                LinkedList<Lokalizacja> trasa; //= new LinkedList<>();
                LinkedList<Lokalizacja> trasa2;
//                trasa.push(brzysko);
//                trasa.push(s1);
//                trasa.push(kity);
//                trasa.push(s1);
//                trasa.push(brzysko);
//                trasy.put("550_20",trasa);
//                trasy.put("550_20", null);
//                System.out.println(trasy.get("550_20").size());
//                ptrasa = trasa;
                
                ArrayList<LinkedList<Lokalizacja>> tr550_20 = new ArrayList<>();
                ArrayList<LinkedList<Lokalizacja>> tr365_200 = new ArrayList<>();
                ArrayList<LinkedList<Lokalizacja>> tr550_550 = new ArrayList<>();
                ArrayList<LinkedList<Lokalizacja>> tr550_360 = new ArrayList<>();
                ArrayList<LinkedList<Lokalizacja>> tr365_550 = new ArrayList<>();
                ArrayList<LinkedList<Lokalizacja>> tr90_550 = new ArrayList<>();
                
                ArrayList<LinkedList<Lokalizacja>> tr275_20 = new ArrayList<>();
                
                trasa = new LinkedList<>();
                trasa.add(brzysko);
                trasa.add(s1);
                trasa.add(kity);
                trasa.add(s8);
                trasa.add(s10);
                trasa.add(leszcze);
                tr550_20.add(trasa);
                
                trasa2 = new LinkedList<>(trasa);
                trasa2.remove(0);
                trasa2.remove(0);
                trasa2.add(s13);
                trasa2.add(zlawies);
                
                tr365_200.add(trasa2);
                
                trasa2 = new LinkedList<>(trasa);
                trasa2.add(s14);
                trasa2.add(samokleski);
                trasa2.add(s12);
                trasa2.add(koty);
                trasa2.add(s4);
                trasa2.add(brzysko);
                
                tr550_20.add(trasa2);
                
                
                trasa = new LinkedList<>();
                trasa.add(brzysko);
                trasa.add(s4);
                trasa.add(kity);
                
                tr550_20.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(brzysko);
                trasa.add(s4);
                trasa.add(koty);
                trasa.add(s12);
                trasa.add(samokleski);                
                tr550_20.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(leszcze);
                trasa.add(s10);
                trasa.add(s8);
                trasa.add(kity);                
                tr365_550.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(leszcze);
                trasa.add(s10);
                trasa.add(s8);
                trasa.add(kity); 
                trasa.add(s4);
                trasa.add(brzysko);               
                tr365_550.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(samokleski);
                trasa.add(s12);
                trasa.add(s11);
                trasa.add(s10);
                trasa.add(s8);
                trasa.add(kity);
                trasa.add(s1);
                trasa.add(brzysko);
                tr550_550.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(zlawies);
                trasa.add(s13);
                trasa.add(leszcze);                
                tr90_550.add(trasa);
                
                trasa = new LinkedList<>();
                trasa.add(koty);
                trasa.add(s4);
                trasa.add(kity);                
                tr550_360.add(trasa);
                
                
                
                getTrasy().put("550_20", tr550_20);
                getTrasy().put("365_200", tr365_200);
                getTrasy().put("365_550", tr365_550);
                getTrasy().put("550_550", tr550_550);
                getTrasy().put("550_360", tr550_360);
                getTrasy().put("90_550", tr90_550);
                
                
                trasa = new LinkedList<>();
                trasa.add(powidzPrzystan);
                trasa.add(sm1);
                trasa.add(borowno);
                tr275_20.add(trasa);
                
                getTrasy().put("275_20", tr275_20);
                
                
                
                
                ArrayList<LinkedList<Lokalizacja>> tr250_20 = new ArrayList<>();
                
                trasa = new LinkedList<>();
                trasa.add(powidz);
                trasa.add(s3);
                trasa.add(s2);
                trasa.add(alcatraz);
                tr250_20.add(trasa);
                
                getTrasy().put("250_20", tr250_20);       
                
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
