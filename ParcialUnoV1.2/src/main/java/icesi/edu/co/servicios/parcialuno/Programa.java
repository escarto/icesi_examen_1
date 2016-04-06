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
        
        /*
        Inicio los procesos de generar carros en la derecha y en la izquierda, ademas se inicializa el guarda que da paso en el puente
        */
        
        cochesDcha.start();
        cochesIzquda.start();
        guardaQueDaVia.start();
      

    }
    
}
