/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Nodo{
 
    /*Atributos:
    * id: Identificador del Vertice, sera un entero
    * ListaAdy: Lista de vertices adyacentes a este Vertice.
    */
    int id;
    ArrayList<Nodo> ListaAdy;
    int GradoInterior = 0;

    /*Constructor de la clase, inicializa los atributos
    */
    public Nodo(int identificador){
        this.id = identificador;                                           
        this.ListaAdy = new ArrayList<Nodo>();
    }

    /*Devuelve el identificador del vertice
    */
    public int getId(){
        return this.id;
    }

    /*Aumenta el Grado Interno de el Vertice
    */
    public void setGradoInterno(){
        this.GradoInterior++;
    }

    /*Devuelve el Grado Interno de el Vertice
    */
    public int getGradoInterno(){
        return this.GradoInterior;
    }
}