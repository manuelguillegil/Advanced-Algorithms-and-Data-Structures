/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */
import java.util.*;

public class GrafoDirigido{
    ArrayList<Vertice> ListaVertices;
    ArrayList<Arco> ListaArcos;
    int NumColumnas;
    int NumFilas;
    
    /**
     * Constructor de la clase GrafoDirigido 
     */
    public GrafoDirigido(){
        this.ListaVertices = new ArrayList<Vertice>();
        this.ListaArcos = new ArrayList<Arco>();
    }

    /**
     * Este metodo agrega el numero de filas que contiene el recuadro representado por el grafo
     * parametros entrada:
     * @param filas = Numero de filas del recuadro
     */
    public void setFilas(int filas){
        this.NumFilas = filas;
    }

    /**
     * Este metodo agrega el numero de columnas que contiene el recuadro representado por el grafo
     * parametros entrada:
     * @param columnas = Numero de columnas del recuadro
     */
    public void setColumnas(int columnas){
        this.NumColumnas = columnas;
    }

    /**
     * Este metodo retorna el numero de filas que contiene el recuadro representado por el grafo
     * parametros entrada:
     * @return = Numero de filas del recuadro
     */
    public int getFilas(){
        return this.NumFilas;
    }

    /**
     * Este metodo retorna el numero de columnas que contiene el recuadro representado por el grafo
     * parametros entrada:
     * @return = Numero de columnas del recuadro
     */
    public int getColumnas(){
        return this.NumColumnas;
    }

    /**
     * Agrega un vertice al grafo
     * 
     * Parametro entrada:
     * @param V = Vertice del grafo
     */
    public void agregarVertice(Vertice V){
        this.ListaVertices.add(V);
    }

    /**
     * Agrega un arco al grafo
     * 
     * Parametro entrada:
     * @param Ar = Arco del grafo
     */
    public void agregarArco(Arco Ar){
        this.ListaArcos.add(Ar);
    }

    /**
     * Este metodo encuentra la posicion del vertice en el grafo (ArrayList) dado su identificador
     * 
     * Parametro entrada:
     * @param idVertice = Identificador del vertice
     * Parametro salida:
     * @return = Posicion del Vertice en el grafo
     */
    public int encontrarVertice(String idVertice){
        for(int i=0; i<this.ListaVertices.size(); i++){
            if(this.ListaVertices.get(i).getId().equals(idVertice)){
                return i;
            }
        }
        return -1;                                                                    //Este caso nunca sucedera
    }

    /**
     * Este metodo encuentra todos los arco en el grafo  que tienen a idVertice como vertice extremo inicial
     * 
     * Parametro entrada:
     * @param idVertice = Identificador del vertice extremo final
     * Parametro salida:
     * @return = Arco del grafo
     */
    public ArrayList<Arco> encontrarSucesores(String idVertice){
        ArrayList<Arco> R = new ArrayList<Arco>();
        for(int i=0; i<this.ListaArcos.size(); i++){
            if(this.ListaArcos.get(i).getExtremoInicial().equals(idVertice)){
                R.add(this.ListaArcos.get(i));
            }
        }
        return R;
    }

    /**
     * Este metodo revisa los arcos en el grafo y devuelve true si un arco, con los vertices iniciales y finales obtenidos, ya existe.
     * 
     * Parametros entrada:
     * @param idVertice1 = Identificador del vertice extremo inicial del arco
     * @param idVertice2 = Identificador del vertice extremo final del arco
     * Parametros salida:
     * @return = true si existe, false si no existe.
     */
    public Boolean existeArco(String idVertice1, String idVertice2){
        Boolean R = false;
        for(int i=0; i<this.ListaArcos.size(); i++){
            if(this.ListaArcos.get(i).getExtremoInicial().equals(idVertice1) && this.ListaArcos.get(i).getExtremoFinal().equals(idVertice2)){
                R = true;
            }
        }
        return R;
    }
}