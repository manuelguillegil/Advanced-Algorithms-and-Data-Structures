/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

public class Arista{
    
    /*costo, Vertice inicial y vertice final de la arista */
    int costo;
    int vInicial;
    int vFinal;

    /**
     * Constructor de la clase Arista
     * 
     * Parametros entrada:
     * @param v1 = Vertice inicial de la arista
     * @param v2 = Vertice final de la arista
     * @param c = costo de la arista
     */
    public Arista(int v1, int v2, int c){
        this.vInicial = v1;
        this.vFinal = v2;
        this.costo = c;
    }

    /**
     * Retorna el vertice extremo inicial de la arista
     * 
     * Parametros salida:
     * @return = Vertice extremo inicial de la arista
     */
    public int getExtremoIni(){
        return this.vInicial;
    }

    /**
     * Retorna el vertice extremo final de la arista
     * 
     * Parametros salida:
     * @return = Vertice extremo final de la arista
     */
    public int getExtremoFin(){
        return this.vFinal;
    }

    /**
     * Retorna el costo de la arista
     * 
     * Parametros salida:
     * @return = Costo de la arista
     */
    public int getCosto(){
        return this.costo;
    }
    


}