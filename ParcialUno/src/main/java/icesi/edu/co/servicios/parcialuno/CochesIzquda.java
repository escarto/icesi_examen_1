/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icesi.edu.co.servicios.parcialuno;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author distribuidos
 */
public class CochesIzquda extends Thread{
    private Puente puente;
    
    public CochesIzquda(Puente puente){
        this.puente=puente;
    }
    
    public void run() {

        while (true) {

            int tiempoPoniendoCoches= (int) (Math.random() * 10);
            System.out.println("Poniendo coches en la izquierda a" + tiempoPoniendoCoches + " segundos");

            try {
                sleep(tiempoPoniendoCoches * 1000);
            } catch (InterruptedException e) {
            }

            System.out.println("Llegaron coches");
            
            try {
                puente.ponerCochesIzquierda();
            } catch (InterruptedException ex) {
                Logger.getLogger(CochesDcha.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
