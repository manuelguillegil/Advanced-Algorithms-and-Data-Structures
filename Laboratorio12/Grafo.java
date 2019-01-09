/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Grafo {

    /*Lista de Vertices y Aristas del grafo */
    ArrayList<Nodo> ListaVertices;
    ArrayList<Arista> ListaAristas;

    /**
     * Constructor de la clase Grafo.
     */
    public Grafo(){

        this.ListaVertices = new ArrayList<Nodo>();
        this.ListaAristas = new ArrayList<Arista>();
    }
    
    /**
     * Agrega un vertice al grafo
     * 
     * Parametros entrada:
     * @param V = Nodo del grafo
     */
    public void agregarVertice(Nodo V){

        ListaVertices.add(V);                                                       
    }

    /**
     * Agrega una arista al grafo
     * 
     * Parametros entrada:
     * @param ar = Arista del grafo
     */
    public void agregarArista(Arista ar){

        this.ListaAristas.add(ar);
    }


}