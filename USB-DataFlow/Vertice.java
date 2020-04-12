/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */


class Vertice{

    /**Atributos de la clase Vertice:
    * id: Identificador del vertice
    * expresion: Expresion aritmetica del vertice
    * valor: Valor numerico del vertice
    * grafoInt: Grado interior del vertice en el grafo
    */
    String id;
    String expresion;
    int valor;
    int gradoInt;

    /**
     * Constructor de la clase Vertice
     *
     * Parametro entrada:
     * @param identificador = Identificador del vertice
     * @param expresion = Expresion aritmetica del vertice
     */
    public Vertice(String identificador, String expresion){
        this.id = identificador;
        this.expresion = expresion;
        this.gradoInt = 0;
    }

    /**
     * Retorna la expresion aritmetica del vertice
     *
     * Parametro salida:
     * @return = Expresion aritmetica del vertice
     */
    public String getExpresion(){
        return this.expresion;
    }

    /**
     * Actualiza la expresion de el vertice
     * @param expresion = Expresion del vertice
     */
    public void setExpresion(String expresion){
        this.expresion = expresion;
    }

    /**
     * Retorna el Identificador del Vertice
     *
     * Parametro salida:
     * @return = Identificador del vertice
     */
    public String getId(){
        return this.id;
    }

    /**
     * Agrega el valor del Vertice
     *
     * Paramtero entrada:
     * @param Valor = Valor del vertice
     */
    public void setValor(int Valor){
        this.valor = Valor;
    }

    /**
     * Retorna el valor del Vertice
     *
     * Paramtero salida:
     * @return = Valor del vertice
     */
    public int getValor(){
        return this.valor;
    }

    /**
     * Este metodo Aumenta el grado interior del vertice en el grafo
     */
    public void setGradoInt(){
        this.gradoInt = this.gradoInt + 1;
    }

    /**
     * Este metodo Retorna el valor del grado interior del vertice en el grafo
     * @return = Grado interior del vertice
     */
    public int getGradoInt(){
        return this.gradoInt;
    }
}