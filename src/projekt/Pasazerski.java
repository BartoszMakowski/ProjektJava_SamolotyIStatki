/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author bartosz
 * Obiecuje metody, jakie muszą oferować pojazdy i lokalizacje pasażerskie.
 */
public interface Pasazerski {
    
    /**
     * Przenosi pasażerów do pojazdu lub lokalizacji, jeśli jest ona w ich planie.
     * @param dokad cel przesiadki
     */
    public void przesiadkaPasazera(Pasazerski dokad);   
    
    /**
     * Bezwarunkowo dodaje pasażera do listy pasażerów.
     * @param pasazer pasażer do dodania
     */
    public void dodajPasazera(Podrozny pasazer);
    
    /**
     * Usuwa pasażera z listy pasażerów.
     * @param pasazer pasażer do usunięcia
     */
    public void usunPasazera(Podrozny pasazer);
    
    /**
     * Sprawdza, czy są wolne miejsca dla pasażerów.
     * @return istnienie wolnych miejsc
     */
    public boolean czyJestMiejsce();
}
