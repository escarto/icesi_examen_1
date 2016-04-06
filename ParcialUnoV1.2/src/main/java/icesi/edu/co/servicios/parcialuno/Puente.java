/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icesi.edu.co.servicios.parcialuno;

import com.sun.org.apache.xml.internal.resolver.Resolver;
import java.util.concurrent.Semaphore;
/*Version mostrar*/
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
        /*
        Este metodo le permite al generador de carros de la derecha hacer V()al semaforo de los recursos de los carros de la derecha
        */
        mutexCochesDerecha.acquire();
        cantidadCochesDerecha= cantidadCochesDerecha + 1;
        mutexCochesDerecha.release();    
        cochesDisponiblesDerecha.release();
        
        
        
    }
    
    public void ponerCochesIzquierda() throws InterruptedException{
/*        
    Este metodo le permite al generador de carros de la izquierda hacer V()al semaforo de los recursos de los carros de la izquierda
*/
        mutexCohesIzquierda.acquire();
        cantidadCochesIzquierda= cantidadCochesIzquierda + 1;
        mutexCohesIzquierda.release(); 
        cochesDisponiblesIzquierda.release();
    
        
        
        
    }
    public void darPaso() throws InterruptedException{
        /*
        El Guarda hace P(n) al semaforo de los recursos de los carros del lado correspondiente para reservarlos y poder mandarlos por el puente
        en caso de que tenga mas de 10 carros en la cola el manda 10 y le da paso al otro lado. Para hacer esto el tambien reserva P() al semaforo
        de los recursos de los espacios del puente para mandar los vehiculos.
        
        
        */
         
        if(ladoPuente==0){
           
            if(cantidadCochesDerecha==0){
                ladoPuente=1;
               System.out.println("cambie de lado porque no habia carros en la --------------****************** derecha" );

                
            }
            else if(cantidadCochesDerecha<10 && cantidadCochesDerecha>0){
                liberarEspaciosPuente();
                cochesDisponiblesDerecha.acquire(cantidadCochesDerecha);
                espaciosPuente.acquire(cantidadCochesDerecha);
                mutex.acquire();
                System.out.println("Mande carros a la derecha------------------" + cantidadCochesDerecha);

                espaciosDisponiblesPuente=espaciosDisponiblesPuente-cantidadCochesDerecha;
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
            if(cantidadCochesIzquierda==0){
                ladoPuente=0;
                System.out.println("cambie de lado porque no habia carros en la --------------****************** izquierda" );

            }
            else if(cantidadCochesIzquierda<10 && cantidadCochesIzquierda>0){
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
        /*
        
        En este metodo el Guarda hace V() al semaforo de los recursos de los espacios en el puente y hace P() al semaforo
        mutex de la variable espaciosPuente para reservarla y aumentarla y V() para liberarla
        
        */
            mutex.acquire();
            espaciosDisponiblesPuente=10-espaciosDisponiblesPuente;       
            espaciosPuente.release(10-espaciosDisponiblesPuente);
            System.out.println("Espacios Liberado ******************* "+ espaciosDisponiblesPuente);
            mutex.release();
        
            
       
    }
    
}
