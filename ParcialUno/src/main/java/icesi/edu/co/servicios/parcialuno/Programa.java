/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icesi.edu.co.servicios.parcialuno;

/**
 *
 * @author distribuidos
 */
public class Programa {
    public static void main(String main[]) {

        Puente puente= new Puente();

        CochesDcha cochesDcha= new CochesDcha(puente);
        CochesIzquda cochesIzquda= new CochesIzquda(puente);
        GuardaQueDaVia guardaQueDaVia= new GuardaQueDaVia(puente);
        
        cochesDcha.start();
        cochesIzquda.start();
        guardaQueDaVia.start();
      

    }
    
}
