/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Grafo {

    /*El grafo que vamos a implementar va a ser representada como una lista de adyacencias
    */ 
    ArrayList<Nodo> ListaVertices;

    /*Constructor de la clase
    */
    public Grafo(){
        this.ListaVertices = new ArrayList<Nodo>();
    }
    
    /*Agrega un vértice al grafo.
    */
    public void agregarVertice(int id, Double x, Double y){
        Nodo nodo = new Nodo(id,x,y);
        ListaVertices.add(nodo);                                                       
    }

    /*Agrega una arista al grafo\
    */
    public void agregarArista(int v1, int v2){

        this.ListaVertices.get(v1).ListaAdy.add(ListaVertices.get(v2));
        this.ListaVertices.get(v2).ListaAdy.add(ListaVertices.get(v1));
    }


}