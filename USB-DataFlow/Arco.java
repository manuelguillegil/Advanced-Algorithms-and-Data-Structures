/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

class Arco {

    /**Atributos de la clase Vertice:
     * vi: Identificador del vertice inicial del arco
     * vf: Identificador del vertice final del arco
     */
    String vi;
    String vf;

    /**
     * Constructor de clase Arco
     *
     * Parametros entrada:
     * @param Verticei = Identificador del vertice inicial del arco 
     * @param Verticef = Identificador del vertice final del arco
     */
    public Arco(String Verticei, String Verticef){
        this.vi = Verticei;
        this.vf = Verticef;
    }

    /**
     * Retorna el Extremo inicial de el arco
     *
     * Parametros salida:
     * @return = Identificador del vertice inicial del arco
     */
    public String getExtremoInicial(){
        return this.vi;
    }

    /**
     * Retorna el Extremo final de el arco
     *
     * Parametros salida:
     * @return = Identificador del vertice final del arco
     */
    public String getExtremoFinal(){
        return this.vf;
    }
    
}