/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Grafo {

    // El grafo que vamos a implementar va a ser representada como una lista de adyacencias 
    ArrayList<ArrayList<Integer>> ListaAdy;

    public Grafo(){
        this.ListaAdy = new ArrayList<ArrayList<Integer>>();
    }
    
    /**Agrega un vértice al grafo. Si el vértice ya existe, el método no hace
     * nada.
     * 
     * 
     */
    public void AgregarVertice(){
        ArrayList<Integer> ListaLados = new ArrayList<Integer>();                               //Crea la lista de adyacencia de este vertice
        this.ListaAdy.add(ListaLados);                                                        //Agrega la Lista de adyacencia a la Lista Inicial
    }

    /**{@inheritDoc}**/
    public void AgregarArista(int Vertice1, int Vertice2){
        ListaAdy.get(Vertice1).add(Vertice2);                                                 //Se agrega en los dos porque es un grafo no dirigido
        ListaAdy.get(Vertice2).add(Vertice1);
        
    }

    

}