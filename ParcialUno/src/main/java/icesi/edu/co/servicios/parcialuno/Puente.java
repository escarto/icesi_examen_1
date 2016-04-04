/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icesi.edu.co.servicios.parcialuno;

import com.sun.org.apache.xml.internal.resolver.Resolver;
import java.util.concurrent.Semaphore;

/**
 *
 * @author distribuidos
 */
public class Puente {
    private int ladoPuente;
    private int espaciosDisponiblesPuente;
    private int cantidadCochesDerecha;
    private int cantidadCochesIzquierda;
    private Semaphore mutex;
    private Semaphore espaciosPuente;
    private Semaphore cochesDisponiblesDerecha;
    private Semaphore cochesDisponiblesIzquierda;    
    private Semaphore mutexCochesDerecha;
    private Semaphore mutexCohesIzquierda;
    
    
    public Puente(){
        espaciosDisponiblesPuente=10;
        /*
        la estructura que contiene la cantidad de autos disponible para mandar sobre el puente
        */
        ladoPuente=0; 
        /* si lado puente es 0 va tomar los coches del lado derecho y les va dar paso 
        en el puente para que crucen a la izquierda, en caso de que sea 1 va tomar 
        los coches del lado izquierdo y les va dar paso en el puente para que crucen a la derecha*/
        cantidadCochesDerecha=0;
        /*cantidadCochesDerecha es la variable que me permite controlar la llegada y salida desde el lado derecho de coches, antes de poner un coche
        en el lado derecho debo hacer cantidadCochesDerecha.v() y cuando tomo un coche para pasarlo por el puente cantidadCochesDerecha.P()
        */
        cantidadCochesIzquierda=0;
       /*cantidadCochesIzquierda es la variable que me permite controlar la llegada y salida desde el lado izquierdo de coches, antes de poner un coche
        en el lado izquierdo debo hacer cantidadCochesIzquierda.v() y cuando tomo un coche para pasarlo por el puente cantidadCochesIzquierda.P()
        */
        mutex = new Semaphore(1, true);
        espaciosPuente= new Semaphore(10);
        mutexCochesDerecha= new Semaphore(1);
        mutexCohesIzquierda= new Semaphore(1);
        cochesDisponiblesDerecha= new Semaphore(0, true);
        cochesDisponiblesIzquierda= new Semaphore(0, true);
    }
    
    public void ponerCochesDerecha() throws InterruptedException{
        mutexCochesDerecha.acquire();
        cantidadCochesDerecha= cantidadCochesDerecha + 1;
        mutexCochesDerecha.release();    
        cochesDisponiblesDerecha.release();
        
        
        
    }
    
    public void ponerCochesIzquierda() throws InterruptedException{
        mutexCohesIzquierda.acquire();
        cantidadCochesIzquierda= cantidadCochesIzquierda + 1;
        mutexCohesIzquierda.release(); 
        cochesDisponiblesIzquierda.release();
    
        
        
        
    }
    public void darPaso() throws InterruptedException{
         
        if(ladoPuente==0){
           
            if(cantidadCochesDerecha<10 && cantidadCochesDerecha>0){
                liberarEspaciosPuente();
                cochesDisponiblesDerecha.acquire(cantidadCochesDerecha);
                espaciosPuente.acquire(cantidadCochesDerecha);
                mutex.acquire();
                System.out.println("Mande carros a la derecha------------------" + cantidadCochesDerecha);

                espaciosDisponiblesPuente=espaciosDisponiblesPuente-cantidadCochesIzquierda;
                mutexCochesDerecha.acquire();
                cantidadCochesDerecha= cantidadCochesDerecha - cantidadCochesDerecha;
                mutexCochesDerecha.release();
                mutex.release();
                ladoPuente=1;  
            }
            else{
                liberarEspaciosPuente();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();
                cochesDisponiblesDerecha.acquire();

                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();        



                mutex.acquire();
                espaciosDisponiblesPuente=espaciosDisponiblesPuente-10;
                mutexCochesDerecha.acquire();
                System.out.println("Mande carros a la derecha------------------" + 10);
                cantidadCochesDerecha=cantidadCochesDerecha - 10;
                mutexCochesDerecha.release();
                mutex.release();
                ladoPuente=1;              
                
                
            }
        
           
            
      
                      
            
          
        }
        
        else{
            
            if(cantidadCochesIzquierda<10 && cantidadCochesIzquierda>0){
                liberarEspaciosPuente();
                cochesDisponiblesIzquierda.acquire(cantidadCochesIzquierda);
                espaciosPuente.acquire(cantidadCochesIzquierda);
                mutex.acquire();
                System.out.println("Mande carros a la izquierda------------------" + cantidadCochesIzquierda);

                espaciosDisponiblesPuente=espaciosDisponiblesPuente-cantidadCochesIzquierda;
                mutexCohesIzquierda.acquire();
                cantidadCochesIzquierda=cantidadCochesIzquierda - cantidadCochesIzquierda;
                mutexCohesIzquierda.release();
                mutex.release();
                ladoPuente=0;  
            }
            else{
                liberarEspaciosPuente();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                cochesDisponiblesIzquierda.acquire();
                
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire();
                espaciosPuente.acquire(); 
                
                mutex.acquire();
                espaciosDisponiblesPuente=espaciosDisponiblesPuente-10;
                mutexCohesIzquierda.acquire();
                System.out.println("Mande carros a la derecha------------------" + 10);

                cantidadCochesIzquierda=cantidadCochesIzquierda - 10;
                mutexCohesIzquierda.release();
                mutex.release();
                ladoPuente=0;                  
                
                
            }
            
        
            
                   
            
            
            
            
        }
    }
    
    public void liberarEspaciosPuente() throws InterruptedException{
            mutex.acquire();
            espaciosDisponiblesPuente=10-espaciosDisponiblesPuente;       
            espaciosPuente.release(10-espaciosDisponiblesPuente);
            System.out.println("Espacios Liberado ******************* "+ espaciosDisponiblesPuente);
            mutex.release();
        
            
       
    }
    
}
