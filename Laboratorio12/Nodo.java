/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

public class Nodo{

    /*Identificador del vertice */
    int id;

    /**
     * Constructor de la clase Nodo
     * 
     * Parametros entrada:
     * @param id = Identificador del vertice.
     */
    public Nodo(int id){
        this.id = id;
    }

    /**
     * Retorna el Identificador del vertice
     * 
     * Parametros salida:
     * @return = Identificador del vertice.
     */
    public int getId(){
        return this.id;
    }
}