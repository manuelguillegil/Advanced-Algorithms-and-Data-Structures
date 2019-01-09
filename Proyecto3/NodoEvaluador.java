public class NodoEvaluador{
    String id;
    NodoEvaluador padre;
    NodoEvaluador hijo1;
    NodoEvaluador hijo2;

    /**
     * En este metodo agregamos el Identificador de nodo
     * 
     * Parametro entrada:
     * @param id = Identificador del nodo
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * En este metodo retornamos el Identificador del nodo
     * 
     * Paramtero salida:
     * @return = Identificador del nodo
     */
    public String getId(){
        return this.id;
    }

    /**
     * En este metodo agregamos el padre del nodo
     * 
     * Parametro entrada:
     * @param Padre = Nodo padre del nodo
     */
    public void setPadre(NodoEvaluador Padre){
        this.padre = Padre;
    }

    /**
     * En este metodo agregamos el Hijo #1 del nodo
     * 
     * Parametro entrada:
     * @param Hijo1 = Nodo hijo #1 del nodo
     */
    public void setHijo1(NodoEvaluador Hijo1){
        this.hijo1 = Hijo1;
    }

    /**
     * En este metodo agregamos el Hijo #2 del nodo
     * 
     * Parametro entrada:
     * @param Hijo2 = Nodo hijo #2 del nodo
     */
    public void setHijo2(NodoEvaluador Hijo2){
        this.hijo2 = Hijo2;
    }

    /**
     * En este metodo retornamos el Nodo padre del nodo
     * 
     * Parametro salida:
     * @return = Nodo padre del nodo
     */
    public NodoEvaluador getPadre(){
        return this.padre;
    }

    /**
     * En este metodo retornamos el Nodo Hijo #1 del nodo
     * 
     * Paramtero salida: 
     * @return = Nodo Hijo #1 del nodo
     */
    public NodoEvaluador getHijo1(){
        return this.hijo1;
    }

    /**
     * En este metodo retornamos el Nodo Hijo #2 del nodo
     * 
     * Paramtero salida:
     * @return = Nodo Hijo #2 del nodo
     */
    public NodoEvaluador getHijo2(){
        return this.hijo2;
    }
}