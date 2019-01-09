/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

class Arista<E> extends Lado<E>{

    /*Arco tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * V1 que seria el primer Vertice de la arista y V2 el segundo Vertice de la arista*/
    Vertice V1;
    Vertice V2;

    /*Constructor de clase Arista*/
    public void CrearArista(String Identificador, E Dato, Double peso, Vertice Verticei, Vertice Verticef){
        this.V1 = Verticei;
        this.V2 = Verticef;
        this.id = Identificador;
        this.Dato = Dato;
        this.p = peso;
    }

    /*Retorna el primer Vertice de la arista*/
    public Vertice GetExtremo1(){
        return this.V1;
    }

    /*Retorna el segundo Vertice de la arista*/
    public Vertice GetExtremo2(){
        return this.V2;
    }

    /*Regresa un String con la informacion de la arista*/
    public String ToString(){
        String Result;
        
        return Result = "Arista "+this.id+":  Dato: "+String.valueOf(this.Dato)+"  Peso: "+String.valueOf(this.p)+"  Extremo 1: "+this.V1.id+"  Extremo 2: "+this.V2.id;
    }
    
}