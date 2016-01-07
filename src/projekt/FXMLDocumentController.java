/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author bartosz
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    private ImageView sp1;
    private ImageView sp2;
    private SamolotPasazerski sampas;
    private String coWyswietlane;
    
    @FXML
    private Button bStart;
    @FXML
    private Label lNazwa;
    private Label lY;
    private Label lX;
    @FXML
    private Label lCel;
    private Label LId;
    @FXML
    private SplitPane sp;
    @FXML
    private ImageView iv;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label lInfoLG;
    @FXML
    private Label lInfoLGW;
    @FXML
    private Label lNaglowek;
    @FXML
    private Button bTrasaZawartosc;
    @FXML
    private Button bUsunDodaj;
    @FXML
    private Button bZmien;
    @FXML
    private Button bAwaria;
    @FXML
    private Label lInfoPGW;
    @FXML
    private Label lInfoPG;
    @FXML
    private Label lInfoPD;
    @FXML
    private Label lInfoPDW;
    @FXML
    private Label lInfoLDW;
    @FXML
    private Label lInfoLD;
    
    
    @FXML
    private ListView<String> lvTrasa;
    
    private Lokalizacja wyswietlanaLokalizacja;
    private Pojazd wyswietlanyPojazd;
    private boolean czyPojazd;
    private boolean czyPasazerowie;
    @FXML
    private Circle s3;
    
    
    public void moveSp(int x, int y){
        sp1.setLayoutX(x*4);
        sp1.setLayoutY(y*4);
    }

    
    @FXML
    public void startC() throws InterruptedException {
//        for (int i = 0; i < 10; i++)
            Thread move = new Thread(){
                @Override
                public void run(){
                    
//                    Thread sam = new Thread(Projekt.sp);
//                    sam.setDaemon(true);
//                    sam.start();
//                    sp2 = Projekt.sp.getObrazek();
//                    sp.getChildren().add(sp2);
                    while(true){
                        try {
//                            if(Projekt.sp.getTrasa().size()>0){
//                                Projekt.sp.przemiescSie();
                                Thread.sleep(30);
//                            }
                        } catch (InterruptedException ex) {      
                            }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        switch(FXMLDocumentController.this.coWyswietlane){
                            case "pojazdCywilny":{

                                lInfoLG.setText("X:");                
                                lInfoLGW.setText(""  + wyswietlanyPojazd.getPolozenie().getX());
                //                        setText("" + nowySP.getPolozenie().getX());
                                lInfoPG.setText("Y:");
                                lInfoPGW.setText(""  + wyswietlanyPojazd.getPolozenie().getY());

                                lInfoLD.setText("V:");
                                lInfoLDW.setText("" + FXMLDocumentController.this.wyswietlanyPojazd.getPredkosc());

                                lInfoPD.setText("D:");
                                lInfoPDW.setText("" + FXMLDocumentController.this.wyswietlanyPojazd.getOdleglosc());

                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();
                                if(czyPasazerowie){
                                    if (((SamolotPasazerski)FXMLDocumentController.this.wyswietlanyPojazd).getPasazerowie().size() > 0)
                                        for (Podrozny p  : ((SamolotPasazerski)FXMLDocumentController.this.wyswietlanyPojazd).getPasazerowie()){
                                            olw.add(p.getImie() + " " + p.getNazwisko());
                                        }
                                }
                                else{
                                    if (FXMLDocumentController.this.wyswietlanyPojazd.getTrasa().size() > 0)
                                        for (Lokalizacja l  : FXMLDocumentController.this.wyswietlanyPojazd.getTrasa()){
                                            olw.add(l.getNazwa());
                                        }
                                }
                                lvTrasa.setItems(olw);
                            }
                            break;

                            case "skrzyzowanie":{
//                                    lInfoLG.setText("X:");                
//                                    lInfoLGW.setText(""  + FXMLDocumentController.this.wyswietlanaLokalizacja.getPolozenie().getX());
//                    //                        setText("" + nowySP.getPolozenie().getX());
//                                    lInfoPG.setText("Y:");
//                                    lInfoPGW.setText(""  + FXMLDocumentController.this.wyswietlanaLokalizacja.getPolozenie().getY());
//                                    
//                                    lNazwa.setText(FXMLDocumentController.this.wyswietlanaLokalizacja.toString());

//                                    
////                                    lvTrasa.getItems().clear();
//
//                                    if (FXMLDocumentController.this.wyswietlanaLokalizacja.getOdleglosci().size() > 0)
//                                        for (Drogowskaz d  : FXMLDocumentController.this.wyswietlanaLokalizacja.getOdleglosci()){
//                                            olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
//                                        }
//                                    lvTrasa.setItems(olw);
                                    
                                }
                            break;

                            case "lotniskoCywilne":
                            {
                                ObservableList<String> olw = FXCollections.observableArrayList();
                                if(czyPasazerowie){
                                    if (((Lotnisko)wyswietlanaLokalizacja).getOdwiedzajacy().size() > 0)
                                        for (Podrozny p  : ((Lotnisko)wyswietlanaLokalizacja).getOdwiedzajacy()){
                                            olw.add(p.getImie() + " " + p.getNazwisko());
                                        }
                                    else
                                        olw.clear();
                            }
                            else{
                                if (wyswietlanaLokalizacja.getOdleglosci().size() > 0)
                                    for (Drogowskaz d  : wyswietlanaLokalizacja.getOdleglosci()){
                                        olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
                                    }
//                                        lvTrasa.setItems(olw);
//                                        lvTrasa.setItems(null);
                            }
                            lvTrasa.setItems(olw);
                            }
                            break;
                            case "lotniskoWojskowe":{
                                
                            }
                            break;


                        }
//                                lvTrasa.setItems(olw);


                        }                    
                    

                });
            
            }
    }
                    };
            
            move.setDaemon(true);
            move.start();
                    }
    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dodajSP(){
        final SamolotPasazerski nowySP = new SamolotPasazerski((Lotnisko)wyswietlanaLokalizacja);
        Thread nowySPWatek = new Thread(nowySP);
        final ImageView ikonaSP = nowySP.getObrazek();
        this.ap.getChildren().add(ikonaSP);
        ikonaSP.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                FXMLDocumentController.this.czyPojazd = true;
                bTrasaZawartosc.setDisable(false);
                System.out.println("kliknieto" + event.getSource());
                coWyswietlane = "pojazdCywilny";
                lNazwa.setText(nowySP.toString());
                lNaglowek.setText("Trasa:");
                FXMLDocumentController.this.wyswietlanyPojazd = nowySP;
            }
            
        });
        nowySPWatek.setDaemon(true);
        nowySPWatek.start();
        
    }
    
        @FXML
    private void dodajSW(){
        final SamolotWojskowy nowySW = new SamolotWojskowy((Lotnisko)wyswietlanaLokalizacja);
        Thread nowySWWatek = new Thread(nowySW);
        final ImageView ikonaSW = nowySW.getObrazek();
        this.ap.getChildren().add(ikonaSW);
        ikonaSW.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                FXMLDocumentController.this.czyPojazd = true;
                bTrasaZawartosc.setDisable(false);
                System.out.println("kliknieto" + event.getSource());
                coWyswietlane = "pojazdCywilny";
                lNazwa.setText(nowySW.toString());
                lNaglowek.setText("Trasa:");
                FXMLDocumentController.this.wyswietlanyPojazd = nowySW;
            }
            
        });
        nowySWWatek.setDaemon(true);
        nowySWWatek.start();
        
    }
    


    @FXML
    private void skrzyzowanieInfo(MouseEvent event) {
        System.out.println("kliknieto " + event.getSource());
        System.out.println("kliknieto " + (int)((Circle)event.getSource()).centerXProperty().get());
        coWyswietlane = "skrzyzowanie";
        FXMLDocumentController.this.czyPojazd = false;
        FXMLDocumentController.this.wyswietlanaLokalizacja = 
                Projekt.lokalizacje.get(
                        (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                + (int)((Circle)event.getSource()).centerYProperty().get());
        System.out.println(FXMLDocumentController.this.wyswietlanaLokalizacja.getNazwa());
        lNazwa.setText(FXMLDocumentController.this.wyswietlanaLokalizacja.getNazwa());
        lInfoLG.setText("X:");
        lInfoLGW.setText(""  + FXMLDocumentController.this.wyswietlanaLokalizacja.getPolozenie().getX());                 
        lInfoPG.setText("Y:");
        lInfoPGW.setText(""  + FXMLDocumentController.this.wyswietlanaLokalizacja.getPolozenie().getY());
        lNaglowek.setText("Drogowskazy:");
        bTrasaZawartosc.setDisable(true);
        ObservableList<String> olw = FXCollections.observableArrayList();
////                                    lvTrasa.getItems().clear();
//
        if (FXMLDocumentController.this.wyswietlanaLokalizacja.getOdleglosci().size() > 0)
            for (Drogowskaz d  : FXMLDocumentController.this.wyswietlanaLokalizacja.getOdleglosci()){
                olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
            }
        lvTrasa.setItems(olw);
    }
    
    @FXML
    private void lotniskoCywilneInfo(MouseEvent event) {
        System.out.println("kliknieto " + event.getSource());
        System.out.println("kliknieto " + (int)((Circle)event.getSource()).centerXProperty().get());
        coWyswietlane = "lotniskoCywilne";
        bTrasaZawartosc.setDisable(false);
        bUsunDodaj.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                dodajSP();
            }
        });
        FXMLDocumentController.this.czyPojazd = false;
        FXMLDocumentController.this.wyswietlanaLokalizacja = 
                Projekt.lokalizacje.get(
                        (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                + (int)((Circle)event.getSource()).centerYProperty().get());
        System.out.println(FXMLDocumentController.this.wyswietlanaLokalizacja.getNazwa());
        
        lNazwa.setText(wyswietlanaLokalizacja.getNazwa());
        lInfoLG.setText("X:");
        lInfoLGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getX());                 
        lInfoPG.setText("Y:");
        lInfoPGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getY());
        lNaglowek.setText("Drogowskazy:");
        
        ObservableList<String> olw = FXCollections.observableArrayList();
        if (wyswietlanaLokalizacja.getOdleglosci().size() > 0)
            for (Drogowskaz d  : wyswietlanaLokalizacja.getOdleglosci()){
                olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
            }
        lvTrasa.setItems(olw);
        
    }
    
        @FXML
    private void lotniskoWojskoweInfo(MouseEvent event) {
        System.out.println("kliknieto " + event.getSource());
        System.out.println("kliknieto " + (int)((Circle)event.getSource()).centerXProperty().get());
        coWyswietlane = "lotniskoWojskowe";
        bTrasaZawartosc.setDisable(false);
        bUsunDodaj.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                dodajSW();
            }
        });
        FXMLDocumentController.this.czyPojazd = false;
        FXMLDocumentController.this.wyswietlanaLokalizacja = 
                Projekt.lokalizacje.get(
                        (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                + (int)((Circle)event.getSource()).centerYProperty().get());
        System.out.println(FXMLDocumentController.this.wyswietlanaLokalizacja.getNazwa());
        
        lNazwa.setText(wyswietlanaLokalizacja.getNazwa());
        lInfoLG.setText("X:");
        lInfoLGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getX());                 
        lInfoPG.setText("Y:");
        lInfoPGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getY());
        lNaglowek.setText("Drogowskazy:");
        
        ObservableList<String> olw = FXCollections.observableArrayList();
        if (wyswietlanaLokalizacja.getOdleglosci().size() > 0)
            for (Drogowskaz d  : wyswietlanaLokalizacja.getOdleglosci()){
                olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
            }
        lvTrasa.setItems(olw);
        
    }
    
    @FXML
    private void zmienCzyPasazerowie(){
        czyPasazerowie = !czyPasazerowie;
        if (czyPasazerowie){
                    lNaglowek.setText("Pasażerowie:");
        }
        else
        {
            switch(coWyswietlane){
                case "pojazdCywilny":
                    lNaglowek.setText("Trasa:");
                    break;
                case "lotniskoCywilne":
                    lNaglowek.setText("Drogowskazy:");
                    break;
                case "lotniskoWojskowe":
                    lNaglowek.setText("Drogowskazy:");
                    break;
                    
                    
            }

        }
    }

    @FXML
    private void lokInfo(MouseEvent event) {
    }
    
}
