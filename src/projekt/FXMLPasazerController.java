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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    /**
     * @return the pTrasa
     */
    public ListView<String> getpTrasa() {
        return pTrasa;
    }

    /**
     * @param pTrasa the pTrasa to set
     */
    public void setpTrasa(ListView<String> pTrasa) {
        this.pTrasa = pTrasa;
    }

    /**
     * @return the pImie
     */
    public Label getpImie() {
        return pImie;
    }

    /**
     * @param pImie the pImie to set
     */
    public void setpImie(Label pImie) {
        this.pImie = pImie;
    }

    /**
     * @return the pNazwisko
     */
    public Label getpNazwisko() {
        return pNazwisko;
    }

    /**
     * @param pNazwisko the pNazwisko to set
     */
    public void setpNazwisko(Label pNazwisko) {
        this.pNazwisko = pNazwisko;
    }

    /**
     * @return the pDom
     */
    public Label getpDom() {
        return pDom;
    }

    /**
     * @param pDom the pDom to set
     */
    public void setpDom(Label pDom) {
        this.pDom = pDom;
    }

    /**
     * @return the pPesel
     */
    public Label getpPesel() {
        return pPesel;
    }

    /**
     * @param pPesel the pPesel to set
     */
    public void setpPesel(Label pPesel) {
        this.pPesel = pPesel;
    }
    
}
