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
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bartosz
 */
public class Projekt extends Application {
    public static HashMap<String, Lotnisko> lotniska;
    public static HashMap<String, Lokalizacja> lokalizacje;
    public static HashMap<String, Skrzyzowanie> skrzyzowania;
    public static HashMap<String, ArrayList<LinkedList<Lokalizacja>>> trasy;
    public static HashMap<String, LinkedList<Lokalizacja>> rozklady;
    public static SamolotPasazerski sp;
//    public static LinkedList<Lokalizacj> ptrasa;

    /**
     * @return the lotniska
     */
    public static HashMap<String, Lotnisko> getLotniska() {
        return lotniska;
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
                lotniska = new HashMap<String, Lotnisko>();
                lokalizacje = new HashMap<String, Lokalizacja>();
                skrzyzowania = new HashMap<String, Skrzyzowanie>();
                trasy = new HashMap<String, ArrayList<LinkedList<Lokalizacja>>>();
//                Lotnisko niebieskie = new Lotnisko(10, 20, "Niebieskie", 5, TypPortu.CYWILNY);
//                Lotnisko czerwone = new Lotnisko(50, 90, "Czerwone", 3, TypPortu.CYWILNY);
//                Skrzyzowanie s1 = new Skrzyzowanie(50, 20, "Skrzyzowanie Jedyne");
//                lotniska.put("10_20", niebieskie);
//                lotniska.put("50_90", czerwone);
                
                
                Lotnisko brzysko = new Lotnisko(550, 20, "Brzyskorzystewko", 3, TypPortu.CYWILNY);
                Lotnisko koty = new Lotnisko(550, 360, "Koty", 5, TypPortu.CYWILNY);
                Lotnisko samokleski = new Lotnisko(550, 550, "Samoklęski Duże", 2, TypPortu.CYWILNY);
                Lotnisko kity = new Lotnisko(365, 200, "Kity", 4, TypPortu.CYWILNY);
                Lotnisko leszcze = new Lotnisko(365, 550, "Leszcze", 1, TypPortu.CYWILNY);
                Lotnisko zlawies = new Lotnisko(90, 550, "Zławieś Wielka", 3, TypPortu.CYWILNY);
                
                Lotnisko alcatraz = new Lotnisko(30, 320, "Alcatraz", 1, TypPortu.WOJSKOWY);
                Lotnisko powidz = new Lotnisko(250, 20, "Powidz", 3, TypPortu.WOJSKOWY);
                Lotnisko murzynno = new Lotnisko(460, 400, "Murzynno", 2, TypPortu.WOJSKOWY);
                Lotnisko pasy = new Lotnisko(250, 460, "Pasy Krótkie", 2, TypPortu.WOJSKOWY);
                
                lotniska.put("550_20", brzysko);
                lotniska.put("365_200", kity);
                lotniska.put("550_550", samokleski);
                lotniska.put("550_360", koty);
                lotniska.put("365_550", leszcze);
                lotniska.put("90_550", zlawies);
                
                Skrzyzowanie s1 = new Skrzyzowanie(365, 20, "S1");
                skrzyzowania.put("365_20", s1);
                Skrzyzowanie s3 = new Skrzyzowanie(250, 200, "S3");
                skrzyzowania.put("250_200", s3);
                Skrzyzowanie s2 = new Skrzyzowanie(30, 200, "S2");
                skrzyzowania.put("30_200", s2);
                Skrzyzowanie s4 = new Skrzyzowanie(550, 200, "S4");
                skrzyzowania.put("550_200", s4);
                Skrzyzowanie s5 = new Skrzyzowanie(90, 320, "S5");
                skrzyzowania.put("90_320", s5);
                
                Skrzyzowanie s6 = new Skrzyzowanie(90, 400, "S6");
                skrzyzowania.put("90_400", s6);
                Skrzyzowanie s7 = new Skrzyzowanie(250, 400, "S7");
                skrzyzowania.put("250_400", s7);
                Skrzyzowanie s8 = new Skrzyzowanie(365, 400, "S8");
                skrzyzowania.put("365_400", s8);
                Skrzyzowanie s9 = new Skrzyzowanie(90, 460, "S9");
                skrzyzowania.put("90_460", s9);
                Skrzyzowanie s10 = new Skrzyzowanie(365, 460, "S10");
                skrzyzowania.put("365_460", s10);
                
                Skrzyzowanie s11 = new Skrzyzowanie(460, 460, "S11");
                skrzyzowania.put("460_460", s11);
                Skrzyzowanie s12 = new Skrzyzowanie(550, 460, "S12");
                skrzyzowania.put("550_460", s12);
                Skrzyzowanie s13 = new Skrzyzowanie(250, 550, "S13");
                skrzyzowania.put("250_550", s13);
                Skrzyzowanie s14 = new Skrzyzowanie(460, 550, "S14");
                skrzyzowania.put("460_550", s14);
                
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
                
                s1.dodajDrogowskaz(new Drogowskaz(s1, brzysko));
                s1.dodajDrogowskaz(new Drogowskaz(s1,kity));
                
                s2.dodajDrogowskaz(new Drogowskaz(s2, s3));
                
                s3.dodajDrogowskaz(new Drogowskaz(s3, kity));
                s3.dodajDrogowskaz(new Drogowskaz(s3, s2));
                
                s4.dodajDrogowskaz(new Drogowskaz(s4,brzysko));
                s4.dodajDrogowskaz(new Drogowskaz(s4, kity));
                s4.dodajDrogowskaz(new Drogowskaz(s4, koty));
                
                s5.dodajDrogowskaz(new Drogowskaz(s5, s6));
                
                s6.dodajDrogowskaz(new Drogowskaz(s6, s5));
                s6.dodajDrogowskaz(new Drogowskaz(s6, s7));
                s6.dodajDrogowskaz(new Drogowskaz(s6, s9));
                
                s7.dodajDrogowskaz(new Drogowskaz(s7, s6));
                s7.dodajDrogowskaz(new Drogowskaz(s7, s8));
                
                s8.dodajDrogowskaz(new Drogowskaz(s8, s7));
                s8.dodajDrogowskaz(new Drogowskaz(s8, s10));
                s8.dodajDrogowskaz(new Drogowskaz(s8, kity));
                
                s9.dodajDrogowskaz(new Drogowskaz(s9, s6));
                s9.dodajDrogowskaz(new Drogowskaz(s9, zlawies));
                
                s10.dodajDrogowskaz(new Drogowskaz(s10, s8));
                s10.dodajDrogowskaz(new Drogowskaz(s10, s11));                
                s10.dodajDrogowskaz(new Drogowskaz(s10, leszcze));
                
                s11.dodajDrogowskaz(new Drogowskaz(s11, s10));
                s11.dodajDrogowskaz(new Drogowskaz(s11, s12));
                s11.dodajDrogowskaz(new Drogowskaz(s11, s14));
                
                s12.dodajDrogowskaz(new Drogowskaz(s12, s11));
                s12.dodajDrogowskaz(new Drogowskaz(s12, koty));
                s12.dodajDrogowskaz(new Drogowskaz(s12, samokleski));
                
                s13.dodajDrogowskaz(new Drogowskaz(s13, zlawies));
                s13.dodajDrogowskaz(new Drogowskaz(s13, leszcze));
                
                s14.dodajDrogowskaz(new Drogowskaz(s14, s11));
                s14.dodajDrogowskaz(new Drogowskaz(s14, leszcze));
                s14.dodajDrogowskaz(new Drogowskaz(s14, samokleski));
                
//                niebieskie.dodajDrogowskaz(new Drogowskaz(40, s1, Kierunek.PRAWO));
//                s1.dodajDrogowskaz(new Drogowskaz(70, czerwone, Kierunek.DOL));
//                s1.dodajDrogowskaz(new Drogowskaz(40, niebieskie, Kierunek.LEWO));
//                czerwone.dodajDrogowskaz(new Drogowskaz(70, s1, Kierunek.GORA));
                
                lokalizacje.putAll(lotniska);
                lokalizacje.putAll(skrzyzowania);

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
                
                trasa = new LinkedList<>(trasa);
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
                
                
                trasy.put("550_20", tr550_20);
                trasy.put("365_200", tr365_200);
                trasy.put("365_550", tr365_550);
                trasy.put("550_550", tr550_550);
                trasy.put("550_360", tr550_360);
                trasy.put("90_550", tr90_550);
                


//                sp = new SamolotPasazerski(new Polozenie(10, 0), 1, niebieskie, trasa, Kierunek.DOL);
//                while(true){
//                    FXMLDocumentController.se
//                    stage.show();
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Projekt.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
                
                
                
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
