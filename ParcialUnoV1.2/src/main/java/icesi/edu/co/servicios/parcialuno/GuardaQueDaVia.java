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
public class GuardaQueDaVia extends Thread{
    private Puente puente;
    
    public GuardaQueDaVia(Puente puente){
        this.puente=puente;
    }
    
    public void run() {

        while (true) {

            int tiempoDandoVia= (int) (Math.random() * 10);
            System.out.println("Dando via" + tiempoDandoVia + " segundos");

            try {
                sleep(tiempoDandoVia * 1000);
            } catch (InterruptedException e) {
            }

            System.out.println("dandoVia");
            
            try {
                puente.darPaso();
            } catch (InterruptedException ex) {
                Logger.getLogger(CochesDcha.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
