import javax.swing.JSpinner.DateEditor;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

public abstract class Lado<E>{

    /*Atributos de la Clase Lado:
    * id: Identificador de Lado
    * Dato: Dato de el Lado
    * p: Peso de el lado
    */
    String id;
    E Dato;
    Double p;

    /*Constructor de la clase Lado*/
    public void CrearLado(String identificador, E Dato, Double peso){
        this.id = identificador;
        this.Dato = Dato;                                                                       
        this.p = peso;
    }

    /*Retorna el peso de el Lado*/
    public Double GetPeso(){
        return this.p;
    }

    /*Retorna el Identificador de el Lado*/
    public String GetId(){
        return this.id;
    }

    /*Retorna el Dato de el Lado*/
    public E GetDato(){                                                                     
        return this.Dato;

    }

    /*Retorna un String con la informacion de el Lado*/
    public String ToString(){
        String Result;

        return Result = "Lado "+this.id+":  Dato: "+String.valueOf(this.Dato)+"  Peso: "+String.valueOf(this.p);
    }

}