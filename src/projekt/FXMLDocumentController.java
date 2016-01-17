/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author bartosz
 * Klasa - kontroler
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    private String coWyswietlane;
    private double czasSymulacji;
    private MediaPlayer mediaPlayer;
    Media utwor;
    
    @FXML
    private Label lNazwa;
    private Label lY;
    private Label lX;
    private Label LId;
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
    private SplitPane sp;
    @FXML
    private ImageView iv;
    @FXML
    private Circle s3;
    @FXML
    private Label lCel;
    @FXML
    private Button bZmien;
    @FXML
    private Button bAwaria;
    @FXML
    private Label lCzasSym;
   
    
    private void startC() throws InterruptedException {
        
//        for (int i = 0; i < 10; i++)
            Thread move = new Thread(){
                @Override
                public void run(){
//                    while{
                        try {
                            Thread.sleep(3000);                            
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }                     
                        
                        
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                dodajCzolg();
                            }
                        });
//                    }
                    
                    while(true){
                        try {
                                Thread.sleep(30);
                                czasSymulacji += 0.03;
                                //                            }
                        } catch (InterruptedException ex) {      
                            }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        
                        lCzasSym.setText( (double)Math.round(czasSymulacji * 10 )/10 + "s");
                        switch(coWyswietlane){
                            case "samolotPasazerski":{
                                wyswietlStanPojazdu();
                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();
                                if(czyPasazerowie){
                                    if (((SamolotPasazerski)FXMLDocumentController.this.wyswietlanyPojazd).getPasazerowie().size() > 0)
                                        for (Podrozny p  : ((SamolotPasazerski)FXMLDocumentController.this.wyswietlanyPojazd).getPasazerowie()){
                                            olw.add("" + p.getPesel() + ". " + p.getImie() + " " + p.getNazwisko());
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
                            
                            case "samolotWojskowy":{

                                lInfoLG.setText("X:");                
                                lInfoLGW.setText(""  + wyswietlanyPojazd.getPolozenie().getX());
                //                        setText("" + nowySP.getPolozenie().getX());
                                lInfoPG.setText("Y:");
                                lInfoPGW.setText(""  + wyswietlanyPojazd.getPolozenie().getY());

                                lInfoLD.setText("V:");
                                lInfoLDW.setText("" + FXMLDocumentController.this.wyswietlanyPojazd.getPredkosc());

                                lInfoPD.setText("F:");
                                lInfoPDW.setText("" + wyswietlanyPojazd.getPaliwo());

                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();
                                
                                
                                if (FXMLDocumentController.this.wyswietlanyPojazd.getTrasa().size() > 0){
                                    for (Lokalizacja l  : FXMLDocumentController.this.wyswietlanyPojazd.getTrasa()){
                                        olw.add(l.getNazwa());
                                    }
                                }
                                
                                lvTrasa.setItems(olw);
                            }
                            break;
                            
                            case "wycieczkowiec":{

                                wyswietlStanPojazdu();
                                
                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();
                                if(czyPasazerowie){
                                    if (((Wycieczkowiec)wyswietlanyPojazd).getPasazerowie().size() > 0)
                                        for (Podrozny p  : ((Wycieczkowiec)wyswietlanyPojazd).getPasazerowie()){
                                            olw.add("" + p.getPesel() + ". " + p.getImie() + " " + p.getNazwisko());
                                        }
                                }
                                else{
                                    if (wyswietlanyPojazd.getTrasa().size() > 0)
                                        for (Lokalizacja l  : FXMLDocumentController.this.wyswietlanyPojazd.getTrasa()){
                                            olw.add(l.getNazwa());
                                        }
                                }
                                lvTrasa.setItems(olw);
                            }
                            break;
                            
                            case "lotniskowiec":{
                                wyswietlStanPojazdu();

                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();                                
                                
                                if (wyswietlanyPojazd.getTrasa().size() > 0){
                                    for (Lokalizacja l  : FXMLDocumentController.this.wyswietlanyPojazd.getTrasa()){
                                        olw.add(l.getNazwa());
                                    }
                                }                                
                                
                                lvTrasa.setItems(olw);
                            }
                            break;


                            case "skrzyzowanie":{
                                    wyswietlajSkrzyzowanie();
                                    
                                }
                            break;

                            case "lotniskoCywilne":
                            {
                                lInfoLD.setText("Max:");
                                lInfoLDW.setText("" + ((Lotnisko)wyswietlanaLokalizacja).getPojemnosc());

                                lInfoPD.setText("Teraz:");
                                lInfoPDW.setText("" + ((Lotnisko)wyswietlanaLokalizacja).getZajetePrzez().size());
                                
                                ObservableList<String> olw = FXCollections.observableArrayList();
                                if(czyPasazerowie){
                                    if (((Lotnisko)wyswietlanaLokalizacja).getOdwiedzajacy().size() > 0)
                                        for (Podrozny p  : ((Lotnisko)wyswietlanaLokalizacja).getOdwiedzajacy()){
                                            olw.add("" + p.getPesel() + ". " + p.getImie() + " " + p.getNazwisko());
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
                                lInfoLD.setText("Max:");
                                lInfoLDW.setText("" + ((Lotnisko)wyswietlanaLokalizacja).getPojemnosc());

                                lInfoPD.setText("Teraz:");
                                lInfoPDW.setText("" + ((Lotnisko)wyswietlanaLokalizacja).getZajetePrzez().size());
                                
                                
                            }
                            break;
                            
                            case "portMorski":
                            {
                                lInfoLD.setText("Max:");
                                lInfoLDW.setText("" + ((PortMorski)wyswietlanaLokalizacja).getPojemnosc());

                                lInfoPD.setText("Aktualnie:");
                                lInfoPDW.setText("" + ((PortMorski)wyswietlanaLokalizacja).getZajetyPrzez().size());
                                
                                ObservableList<String> olw = FXCollections.observableArrayList();
                                if(czyPasazerowie){
                                    if (((PortMorski)wyswietlanaLokalizacja).getOdwiedzajacy().size() > 0)
                                        for (Podrozny p  : ((PortMorski)wyswietlanaLokalizacja).getOdwiedzajacy()){
                                            olw.add("" + p.getPesel() + ". " + p.getImie() + " " + p.getNazwisko());
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
                            
                            case "czolg":{
                                wyswietlStanPojazdu();
                                ObservableList<String> olw = FXCollections.observableArrayList();
    //                                    lvTrasa.getItems().clear();
                                if(czyPasazerowie){
                                    if (((Czolg)wyswietlanyPojazd).getPasazerowie().size() > 0)
                                        for (Podrozny p  : ((Czolg)wyswietlanyPojazd).getPasazerowie()){
                                            olw.add("" + p.getPesel() + ". " + p.getImie() + " " + p.getNazwisko());
                                        }
                                }
                                else{
                                    if (wyswietlanyPojazd.getTrasa().size() > 0)
                                        for (Lokalizacja l  : wyswietlanyPojazd.getTrasa()){
                                            olw.add(l.getNazwa());
                                        }
                                }
                                lvTrasa.setItems(olw);
                            }
                        }
                    } 

                });            
            }
        }
        };
            
        move.setDaemon(true);
        move.start();
    }
    
    /**
     * Wykonuje czynności startowe.
     * @param url
     * @param rb 
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            coWyswietlane = "nic";
            czasSymulacji = 0;
            utwor = new Media(getClass().getResource("audio/Czolg2.mp3").toString());
            mediaPlayer = new MediaPlayer(utwor);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    CzyscGUI();
                }
            });

            startC();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void dodajSP(){
        SamolotPasazerski nowySP = new SamolotPasazerski((Lotnisko)wyswietlanaLokalizacja);
        Thread nowySPWatek = new Thread(nowySP);
        ImageView ikonaSP = nowySP.getObrazek();
        this.ap.getChildren().add(ikonaSP);
        ikonaSP.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                coWyswietlane = "samolotPasazerski";
                wyswietlSamolot(nowySP, ikonaSP);                
                bTrasaZawartosc.setDisable(false);
                bAwaria.setText("Zgłoś awarię");
//              System.out.println("kliknieto" + event.getSource());                                               
            }
            
        });
        nowySPWatek.setDaemon(true);
        nowySPWatek.start();
        
    }
    
    private void dodajSW(){
        SamolotWojskowy nowySW = new SamolotWojskowy((Lotniskowiec)wyswietlanyPojazd);
        Thread nowySWWatek = new Thread(nowySW);
        ImageView ikonaSW = nowySW.getObrazek();
        ap.getChildren().add(ikonaSW);
        ikonaSW.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                coWyswietlane = "samolotWojskowy";
                wyswietlSamolot(nowySW, ikonaSW);
//                czyPojazd = true;
                bTrasaZawartosc.setDisable(true);                                
            } 
        });
        
        nowySWWatek.setDaemon(true);
        nowySWWatek.start();
        
    }
    
    private void dodajWycieczkowiec(){
        Wycieczkowiec nowyWyc = new Wycieczkowiec((PortMorski)wyswietlanaLokalizacja);
        Thread nowyWycWatek = new Thread(nowyWyc);
        ImageView ikonaWyc = nowyWyc.getObrazek();
        ap.getChildren().add(ikonaWyc);
        ikonaWyc.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
//                czyPojazd = true;
                bTrasaZawartosc.setDisable(false);
//                System.out.println("kliknieto" + event.getSource());
                coWyswietlane = "wycieczkowiec";
                wyswietlStatek(nowyWyc, ikonaWyc);
                
            }
        });       
        
        nowyWycWatek.setDaemon(true);
        nowyWycWatek.start();
        
    }
    
    private void dodajLotniskowiec(){
        Lotniskowiec nowyLot = new Lotniskowiec((PortMorski)wyswietlanaLokalizacja);
        Thread nowyLotWatek = new Thread(nowyLot);
        ImageView ikonaLot = nowyLot.getObrazek();
        ap.getChildren().add(ikonaLot);
        ikonaLot.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                coWyswietlane = "lotniskowiec";
                wyswietlStatek(nowyLot, ikonaLot);
//                  bTrasaZawartosc.setDisable(false);
//                System.out.println("kliknieto" + event.getSource());                
//                lNazwa.setText(nowyLot.toString());
//                lNaglowek.setText("Trasa:");
//                wyswietlanyPojazd = nowyLot;
                
                bUsunDodaj.setText("Usuń statek");
                bUsunDodaj.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        nowyLot.usun();
                        ap.getChildren().remove(ikonaLot);
                        coWyswietlane = "nic";
                    }
                });
                
                bAwaria.setDisable(false);
                bAwaria.setText("Dodaj samolot wojskowy");
                bAwaria.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        dodajSW();
                    }
                });
            }
            
            
        });
        
        
        nowyLotWatek.setDaemon(true);
        nowyLotWatek.start();
        
    }
    
    @FXML
    private void skrzyzowanieInfo(MouseEvent event) {
        coWyswietlane = "skrzyzowanie";
        if(event.getSource() instanceof Circle){
            wyswietlanaLokalizacja = 
                    Swiat.getLokalizacje().get(
                            (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                    + (int)((Circle)event.getSource()).centerYProperty().get());
        }
        else{
            wyswietlanaLokalizacja = 
                Swiat.getLokalizacje().get(
                        (int)((Rectangle)event.getSource()).getX() + "_" 
                                + (int)((Rectangle)event.getSource()).getY());
            
        }
        wyswietlLokalizacje();
        bTrasaZawartosc.setDisable(true);

    }
    
    @FXML
    private void lotniskoCywilneInfo(MouseEvent event) {
        coWyswietlane = "lotniskoCywilne";
        wyswietlanaLokalizacja = 
                Swiat.getLokalizacje().get(
                        (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                + (int)((Circle)event.getSource()).centerYProperty().get());
        wyswietlLokalizacje();
        bTrasaZawartosc.setDisable(false);
        
        bUsunDodaj.setDisable(false);
        bUsunDodaj.setText("Dodaj samolot");
        bUsunDodaj.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                dodajSP();
            }
        });
        
    }
    
    @FXML
    private void lotniskoWojskoweInfo(MouseEvent event) {
        coWyswietlane = "lotniskoWojskowe";
        wyswietlanaLokalizacja = 
                Swiat.getLokalizacje().get(
                        (int)((Circle)event.getSource()).centerXProperty().get() + "_" 
                                + (int)((Circle)event.getSource()).centerYProperty().get());
        wyswietlLokalizacje();
        bTrasaZawartosc.setDisable(false);
        bUsunDodaj.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                dodajSW();
            }
        });               
    }
    
    @FXML
    private void pasazerInfo(MouseEvent event) throws IOException {

        Podrozny p = Swiat.getPasazerowie().get(lvTrasa.getSelectionModel().getSelectedItem().substring(0, lvTrasa.getSelectionModel().getSelectedItem().indexOf(".")));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPasazer.fxml"));
        Parent root = (Parent)loader.load();
        FXMLPasazerController controller = (FXMLPasazerController)loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        Thread pasInfo = new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }   

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            controller.getpImie().setText(p.getImie());
                            controller.getpNazwisko().setText(p.getNazwisko());
                            controller.getpPesel().setText("" + p.getPesel());
                            controller.getpDom().setText(p.getDom().getNazwa());                
                            ObservableList<String> olw =  FXCollections.observableArrayList();
                            for (Lokalizacja l  : p.getPlan()){
                                olw.add(l.getNazwa());
                            }
                            controller.getpTrasa().setItems(olw);
                        }
                    });      
                }               
            }
        };
        pasInfo.setDaemon(true);
        pasInfo.start();
    }

    @FXML
    private void lokInfo(MouseEvent event) {
    }
    
    @FXML
    private void portInfo(MouseEvent event) {
        coWyswietlane = "portMorski";
        wyswietlanaLokalizacja = 
                Swiat.getLokalizacje().get(
                        (int)((Rectangle)event.getSource()).getX() + "_" 
                                + (int)((Rectangle)event.getSource()).getY());
        wyswietlLokalizacje();
        bTrasaZawartosc.setDisable(false);
        
        bZmien.setText("Dodaj lotniskowiec");
        bZmien.setDisable(false);
        bZmien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dodajLotniskowiec();
            }
        });
        
        bUsunDodaj.setDisable(false);
        bUsunDodaj.setText("Dodaj wycieczkowiec");
        bUsunDodaj.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                dodajWycieczkowiec();
            }
        });
    }
    
    private void wyswietlSamolot(Samolot s, ImageView ikona){
        wyswietlPojazd(s, ikona);
        bZmien.setDisable(false);
        bZmien.setText("Zmień trasę");
        
        bAwaria.setDisable(false);
        bAwaria.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s.zglosUsterke();
            }
        });
        bUsunDodaj.setText("Usuń ten samolot");
        
    }
    
    private void wyswietlStatek(Statek s, ImageView ikona){
        wyswietlPojazd(s, ikona);
        bUsunDodaj.setText("Usuń ten statek");       
    }
    
    private void wyswietlPojazd(Pojazd p, ImageView ikona){
        grajMuzyke();
        lNazwa.setText(p.toString());
        if (czyPasazerowie){
            lNaglowek.setText("Pasażerowie:");
        }
        else{
            lNaglowek.setText("Trasa:");
        }
        wyswietlanyPojazd = p;
        lInfoLG.setText("X:");                
        lInfoPG.setText("Y:");
        lInfoLD.setText("V:");
        lInfoPD.setText("F:");
        bTrasaZawartosc.setDisable(false);
        bUsunDodaj.setDisable(false);
        bZmien.setDisable(false);
                        
                
        bUsunDodaj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                p.usun();
                ap.getChildren().remove(ikona);
                coWyswietlane = "nic";
            }
        });
        
        bZmien.setText("Zmień trasę");
                
        bZmien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                p.zmienTrase();
            }
        });               
        
    }
    
    private void dodajCzolg(){
        Czolg czolg = new Czolg();
        Thread nowyCWatek = new Thread(czolg);
        ImageView ikonaC = czolg.getObrazek();
        this.ap.getChildren().add(ikonaC);
        ikonaC.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                coWyswietlane = "czolg";
                wyswietlPojazd(czolg, ikonaC);                
                bTrasaZawartosc.setDisable(false); 
                bAwaria.setDisable(true);
                bUsunDodaj.setDisable(true);
                bZmien.setDisable(true);
//              System.out.println("kliknieto" + event.getSource());                                                
            }
            
        });
        nowyCWatek.setDaemon(true);
        nowyCWatek.start();
    }
    
    private synchronized void grajMuzyke() {      
               
        mediaPlayer.stop();
        switch(coWyswietlane){
            case "czolg":{
                utwor = new Media(getClass().getResource("audio/Czolg2.mp3").toString());
                break;
            }
            case "samolotWojskowy":
//                {
//                utwor = new Media(Paths.get("src/projekt/audio/Samolot.mp3").toUri().toString());
//                break;
//            }
            case "samolotPasazerski":
            {
                utwor = new Media(getClass().getResource("audio/Samolot2.mp3").toString());
                break;
            }
            case "lotniskowiec":
            case "wycieczkowiec":
            {
                utwor = new Media(getClass().getResource("audio/Statek2.mp3").toString());
                break;
            }
        }
        mediaPlayer = new MediaPlayer(utwor);
        mediaPlayer.play();
    }
    
    private void CzyscGUI(){
        lCel.setText("");
        lInfoLD.setText("");
        lInfoLDW.setText("");
        lInfoLG.setText("");
        lInfoLGW.setText("");
        lInfoPD.setText("");
        lInfoPDW.setText("");
        lInfoPG.setText("");
        lInfoPGW.setText("");
        lNaglowek.setText("");
        lNazwa.setText("");
        bAwaria.setDisable(true);
        bTrasaZawartosc.setDisable(true);
        bUsunDodaj.setDisable(true);
        bZmien.setDisable(true);
    }
    
    private void wyswietlStanPojazdu(){
        lInfoLGW.setText(""  + wyswietlanyPojazd.getPolozenie().getX());
        lInfoPGW.setText(""  + wyswietlanyPojazd.getPolozenie().getY());
        lInfoLDW.setText("" + wyswietlanyPojazd.getPredkosc());
        lInfoPDW.setText("" + wyswietlanyPojazd.getPaliwo());        
    }
    
    @FXML
    private void zmienCzyPasazerowie(){
        czyPasazerowie = !czyPasazerowie;
        if (czyPasazerowie){
                    lNaglowek.setText("Pasażerowie:");
                    lvTrasa.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                pasazerInfo(event);
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
        }
        else
        {
            lvTrasa.setOnMouseClicked(null);
            switch(coWyswietlane){
                case "wycieczkowiec":
                case "lotniskowiec":
                case "samolotPasazerski":
                case "samolotWojskowy":
                case "czolg":
                    lNaglowek.setText("Trasa:");
                    break;
                case "lotniskoCywilne":
                case "lotniskoWojskowe":
                case "portMorski":
                case "skrzyzowanie":
                    lNaglowek.setText("Drogowskazy:");
                    break;
                    
            }

        }
    }
    
    private void wyswietlLokalizacje(){
        CzyscGUI();
        czyPojazd = false;       
        lNazwa.setText(wyswietlanaLokalizacja.getNazwa());
        lInfoLG.setText("X:");
        lInfoLGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getX());                 
        lInfoPG.setText("Y:");
        lInfoPGW.setText("" + wyswietlanaLokalizacja.getPolozenie().getY());
        czyPasazerowie = false;
        lNaglowek.setText("Drogowskazy:");
        ObservableList<String> olw = FXCollections.observableArrayList();
        if (wyswietlanaLokalizacja.getOdleglosci().size() > 0)
            for (Drogowskaz d  : wyswietlanaLokalizacja.getOdleglosci()){
                olw.add(d.getDokad().getNazwa() + "   " + d.getKierunek() + "   " + d.getOdleglosc() );
            }
        lvTrasa.setItems(olw);
        bAwaria.setDisable(true);
        bZmien.setDisable(true);
        bUsunDodaj.setDisable(true);
    }
    
    private void wyswietlajSkrzyzowanie(){
        lInfoLD.setText("Zajęte:");
        String odp;
        if(((Skrzyzowanie)wyswietlanaLokalizacja).getZajetePrzez() == null){
            lInfoLDW.setText("NIE");                           
            }
        else{
            lInfoLDW.setText("TAK");
        }
        
        
    }
    
}
