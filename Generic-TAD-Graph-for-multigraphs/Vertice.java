/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

class Vertice<V> {

    /*Atributos de la clase Vertice:
    * id: Identificador del vertice
    * Dato: Dato de el vertice
    * p: Peso de el Vertice
    * ListaAdy: ArrayList con las Aristas que inciden en el vertice (utilizado en Grafos no Dirigidos)
    * ListaSucesores: ArrayList con los Arco que inciden en el vertice (utilizado en Grafos Dirigidos)
    */
    String id;
    V Dato;
    Double p;
    ArrayList<Arista> ListaAdy;                      //Si el vertice esta en un grafo no dirigido
    ArrayList<Arco> ListaSucesores;                  //Si el vertice esta en un grafo dirigido

    /*Constructor de la clase Vertice*/
    public void CrearVertice(String identificador, V Dato, Double peso){
        this.id = identificador;
        this.Dato = Dato;                                                                         
        this.p = peso;
        this.ListaAdy = new ArrayList<Arista>();
        this.ListaSucesores = new  ArrayList<Arco>();
    }

    /*Retorna el peso del Vertice*/
    public Double GetPeso(){
        return this.p;
    }

    /*Retorna el Identificador del Vertice*/
    public String GetId(){
        return this.id;
    }

    /*Retorna el Dato del Vertice*/
    public V GetDato(){
        return this.Dato;
    }

    /*Retorna un String con la informacion del Vertice*/
    public String ToString(){
        String Result;
        return Result = "Vertice "+this.id+":  Dato: "+String.valueOf(this.Dato)+"  Peso: "+String.valueOf(this.p);
    }
}