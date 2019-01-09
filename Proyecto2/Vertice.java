/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

class Vertice {

    /*Atributos de la clase Vertice:
    * id: Identificador del vertice
    * Capacidad: Capacidad de gente que puede ir al baño en este vertice.
    * p: Peso de el Vertice, en este problema muestra en que piso esta el baño.
    * HayAgua: Booleano que nos señala si en este vertice hay agua, es decir, los baños funcionan.
    */
    Boolean HayAgua;
    String id;
    int Capacidad;
    Double p;                    

    /*Constructor de la clase Vertice*/
    public void CrearVertice(String identificador, int Capacidad, Double peso){
        this.id = identificador;
        this.Capacidad = Capacidad;                                                                         
        this.p = peso;
        this.HayAgua = false;
    }

    /*Retorna el peso del Vertice*/
    public Double GetPeso(){
        return this.p;
    }

    public void SetPeso(Double Pnew){
        this.p = Pnew;
    }

    public void SetAgua(){
        this.HayAgua = true;
    }

    public Boolean GetAgua(){
        return this.HayAgua;
    }

    /*Retorna el Identificador del Vertice*/
    public String GetId(){
        return this.id;
    }

    /*Retorna la Capacidad del Vertice*/
    public int GetCapacidad(){
        return this.Capacidad;
    }

    /*Reduce la Capacidad del Vertice, si la capacidad llega a 0 hacemos que el vertice ya no tenga agua*/
    public void ReduceCapacidad(int i){
        if(this.Capacidad>i){
            this.Capacidad = this.Capacidad - i;
        }
        else{
            this.Capacidad = 0;
            this.HayAgua = false;
        }
    }

}