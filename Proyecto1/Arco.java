/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

class Arco<E> extends Lado<E>{

    /*Arco tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * Vi que seria el Vertice inicial de el arco y Vf el Vertice final de el arco*/
    Vertice Vi;
    Vertice Vf;

    /*Constructor de clase Arco*/
    public void CrearArco(String Identificador, E Dato, Double peso, Vertice Verticei, Vertice Verticef){
        this.Vi = Verticei;
        this.Vf = Verticef;
        this.id = Identificador;
        this.Dato = Dato;
        this.p = peso;
    }

    /*Retorna el Extremo inicial de el arco*/
    public Vertice GetExtremoInicial(){
        return this.Vi;
    }

    /*Retorna el Extremo final de el arco*/
    public Vertice GetExtremoFinal(){
        return this.Vf;
    }

    /*Regresa un String con la informacion de el Arco*/
    public String ToString(){
        String Result;
        return Result = "Arco: "+this.id+"  Dato: "+String.valueOf(this.Dato)+"  Peso: "+String.valueOf(this.p)+"  Extremo Inicial: "+this.Vi.id+"  Extremo Final: "+this.Vf.id;
    }
    
}