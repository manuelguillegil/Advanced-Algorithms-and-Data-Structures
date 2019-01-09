/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Grafo {

    // El grafo que vamos a implementar va a ser representada como una lista de adyacencias 
    ArrayList<ArrayList<Integer>> ListaAdy;
    ArrayList<ArrayList<Integer>> Acciones;

    public Grafo(){
        this.ListaAdy = new ArrayList<ArrayList<Integer>>();
        this.Acciones = new ArrayList<ArrayList<Integer>>();
    }
    
    /**Agrega un vértice al grafo. Si el vértice ya existe, el método no hace
     * nada.
     * 
     * 
     */
    public void AgregarVertice(){
        ArrayList<Integer> ListaLados = new ArrayList<Integer>();                             //Crea la lista de adyacencia de este vertice
        ArrayList<Integer> AccionesPorCuarto = new ArrayList<Integer>();
        this.ListaAdy.add(ListaLados);                                                        //Agrega la Lista de adyacencia a la Lista Inicial
        this.Acciones.add(AccionesPorCuarto);
    }

    /**{@inheritDoc}**/
    public void AgregarArista(int Vertice1, int Vertice2){
        this.ListaAdy.get(Vertice1-1).add(Vertice2-1);
        this.ListaAdy.get(Vertice2-1).add(Vertice1-1);                                                  
    }

    public void AgregarAcciones(int Vertice1, int Vertice2){
        this.Acciones.get(Vertice1-1).add(Vertice2-1);                                   
    }

    

}