/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

class Arista{

    /*Atributos de la clase Arista:
    * id: Identificador de la arista
    * V1: Identificador del vertice extremo 1 de la arista
    * V2: Identificador del vertice extremo 2 de la arista
    * Capacidad: Numero de personas maxima que puede pasar por esta arista, se reduce siempre que pase una persona
    * p: peso de la arista, en este problema se refiere a la distancia de el camino de un edificio a otro.
    */
    int id;
    String V1;
    String V2;
    int Capacidad;
    Double p;


    /*Constructor de clase Arista*/
    public void CrearArista(int id, int Capacidad, Double peso, String Verticei, String Verticef){
        this.V1 = Verticei;
        this.V2 = Verticef;
        this.Capacidad = Capacidad;
        this.p = peso;
        this.id = id;
    }

    /*Retorna el primer Vertice de la arista*/
    public String GetExtremo1(){
        return this.V1;
    }

    /*Retorna el segundo Vertice de la arista*/
    public String GetExtremo2(){
        return this.V2;
    }

    /*Retorna la capacidad de la arista*/
    public int GetCapacidad(){
        return this.Capacidad;
    }

    /*Reduce la capacidad de la arista*/
    public void ReduceCapacidad(int Num){
        if(this.Capacidad>Num){
            this.Capacidad = this.Capacidad - Num;
        }
        else{
            this.Capacidad = 0;
        }
    }

    /*Retorna el peso de la arista*/
    public Double GetPeso(){
        return this.p;
    }

    /*Retorna el identificador de la arista*/
    public int GetId(){
        return this.id;
    }
    
}