/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author bartosz
 * Klasa - kontroler panelu "PasażerInfo©"
 */
public class FXMLPasazerController implements Initializable {

    @FXML
    private ListView<String> pTrasa;
    @FXML
    private Label pImie;
    @FXML
    private Label pNazwisko;
    @FXML
    private Label pDom;
    @FXML
    private  Label pPesel;

    /**
     * Inicjalizuje klasę kontrolera.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     * Zwraca listview z trasą.
     * @return listview z trasą
     */
    public ListView<String> getpTrasa() {
        return pTrasa;
    }

    /**
     * Ustawia listview z trasą.
     * @param pTrasa nowy listview
     */
    public void setpTrasa(ListView<String> pTrasa) {
        this.pTrasa = pTrasa;
    }

    /**
     * Zwraca label na imię pasażera.
     * @return the pImie label na imię pasażera
     */
    public Label getpImie() {
        return pImie;
    }

    /**
     * Ustawia label z imieniem pasażera.
     * @param pImie nowy label
     */
    public void setpImie(Label pImie) {
        this.pImie = pImie;
    }

    /**
     * Zwraca label na nazwisko pasażera.
     * @return the pNazwisko label na nazwisko pasażera
     */
    public Label getpNazwisko() {
        return pNazwisko;
    }

    /**
     * Ustawia label na nazwisko pasażera.
     * @param pNazwisko nowy label
     */
    public void setpNazwisko(Label pNazwisko) {
        this.pNazwisko = pNazwisko;
    }

    /**
     * Zwraca label na lokalizację domową pasażera.
     * @return the pDom label na lokalizację domową pasażera
     */
    public Label getpDom() {
        return pDom;
    }

    /**
     * Ustawia label na lokalizację domową pasażera.
     * @param pDom nowy label
     */
    public void setpDom(Label pDom) {
        this.pDom = pDom;
    }

    /**
     * Zwraca label na PESEL pasażera.
     * @return label na PESEL pasażera
     */
    public Label getpPesel() {
        return pPesel;
    }

    /**
     * Ustawia label na PESEL pasażera.
     * @param pPesel nowy label
     */
    public void setpPesel(Label pPesel) {
        this.pPesel = pPesel;
    }
    
}
