import java.util.*;
/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

public interface Grafo<V,E>{


    /**Carga en un grafo la información almacenada en el archivo de texto cuya dirección, incluyendo el
    * nombre del archivo, viene dada por archivo. El archivo dado tiene un formato determinado que se
    * indicará más abajo. Se retorna true si los datos del archivo son cargados satisfactoriamente en el
    * grafo, y false en caso contrario. Este método debe manejar los casos en los que haya problemas al
    * abrir un archivo y el caso en el que el formato del archivo sea incorrecto.
    */
    public Boolean CargarGrafo(int n, int m, String Archivo, Transformer<String,V> TransformadorVertice, Transformer<String,E> TransformadorArista);


    /**Indica el número de vértices que posee el grafo. 
    */
    public int NumeroDeVertices();


    /**Indica el número de Lados que posee el grafo
    */
    public int NumeroDeLados();


    /**Agrega el vértice v previamente creado al grafo g previamente creado. Si en el grafo no hay vértice
    * con el mismo identificador que el vértice v, entonces lo agrega al grafo y retorna true, de lo
    * contrario retorna false. 
    */
    public Boolean AgregarVertice(Vertice<V> V);


    /** crea un vértice con las características dadas y las agrega al grafo g previamente creado. Si en el grafo
    * no hay vértice con el identificador id, entonces se crea un nuevo vértice y se agrega al grafo y se
    * retorna true, de lo contrario retorna false
    */
    public Boolean AgregarVertice(String Identificador, V Dato, Double peso);
    

    /**Retorna el vértice contenido en el grafo que posee el identificador id. En caso que en el grafo no
    * contenga ningún vértice con el identificador id, se lanza la excepción 
    */
    public Vertice<V> ObtenerVertice(String Identificador);


    /** Se indica si un vértice con el identificador id, se encuentra o no en el grafo. Retorna true en caso de
    * que el vértice pertenezca al grafo, false en caso contrario.
    */
    public Boolean EstaVertice(String Identificador);


    /**Determina si un lado pertenece a un grafo. La entrada son los identificadores de los vértices que son
    * los extremos del lado. En caso de ser aplicada esta función con un grafo dirigido, se tiene que u
    * corresponde al extermo inicial y v al extermo final.
    */
    public Boolean EstaLado(String Identificador1, String Identificador2);


    /**Elimina el vértice del grafo g. Si existe un vértice identificado con id y éste es eliminado exitosamente
    * del grafo se retorna true, en caso contrario false.
    */
    public Boolean EliminarVertice(String Identificador);


    /** Retorna una lista con los vértices del grafo g.
    */
    public ArrayList<Vertice<V>> Vertices();


    /** Retorna una lista con los lados del grafo g.
    */
    public ArrayList<Lado<E>> Lados();


    /** Calcula el grado del vértice identificado por id en el grafo g. En caso que en el grafo no contenga
    * ningún vértice con el identificador id, se lanza la excepción NoSuchElementException.
    */
    public int Grado(String Identificador);


    /**Obtiene los vértices adyacentes al vértice identicado por id en el grafo g y los retorna en una lista. En
    * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
    * NoSuchElementException.
    */
    public ArrayList<Vertice<V>> Adyacentes(String Identificador);


    /** Obtiene los lados incidentes al vértice identificado por id en el grafo g y los retorna en una lista. En
    * Página 4 de 6
    * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
    * NoSuchElementException.
    */
    public ArrayList<Lado<E>> Incidente(String Identificador);


    /**Retorna un nuevo grafo con la misma composición que el grafo de entrada. 
    */
    public ArrayList<Vertice<V>> Clone();           //CLONE


    /**Devuelve una representación del contenido del grafo como una cadena de caracteres. 
    */
    public String ToString();




    
}