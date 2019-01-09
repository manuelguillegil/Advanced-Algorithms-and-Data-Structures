/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

public class Nodo{
 
    /*Atributos:
    * id: Identificador del Vertice, sera un entero
    * x: Posicion en el plano x del vertice
    * y: Posicion en el plano y del vertice
    * ListaAdy: Lista de vertices adyacentes a este Vertice.
    */
    int id;
    Double x;
    Double y;
    ArrayList<Nodo> ListaAdy;

    /*Constructor de la clase, inicializa los atributos
    */
    public Nodo(int identificador, double x, double y){
        this.id = identificador;                                           
        this.x = x;
        this.y = y;
        this.ListaAdy = new ArrayList<Nodo>();
    }

    /*Devuelve el identificador del vertice
    */
     public int getId(){
        return this.id;
    }

    /*Devuelve la posicion en el plano x del vertice
    */
    public Double getX(){
      return this.x;
    }

    /*Devuelve la posicion en el plano y del vertice
    */
    public Double getY(){
       return this.y;
    }

}