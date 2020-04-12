import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */


class GrafoNoDirigido<V,E> implements Grafo<V,E>{

    /*Atributos de Clase GrafoNoDirigido
    * Grafo: Sera el ArrayList de Vertices que contendra cada Vertice del Grafo
    */
    ArrayList<Vertice<V>> Grafo;

    /*Constructor de la clase GrafoNoDirigido*/
    public void CrearGrafoNoDirigido(){
        Grafo = new ArrayList<Vertice<V>>();
    }

    /*Agrega una nueva arista al grafo si el identificador de la arista no existe en ninguna Arista
    * En el grafo
    */
    public Boolean AgregarArista(Arista<E> a){

        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                if(this.Grafo.get(i).ListaAdy.get(j).GetId().equals(a.GetId())){             //Busca si el Identificador de la Arista ya existe en el Grafo.
                    return false;
                }
            }
        }

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(a.GetExtremo1().GetId())){                   //Agrega la Arista a la lista de Adyacencia de el Vertice extremo 1
                this.Grafo.get(i).ListaAdy.add(a);
                break;
            }
        }
        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(a.GetExtremo2().GetId())){                   //Agrega la Arista a la lista de Adyacencia de el Vertice extremo 2
                this.Grafo.get(i).ListaAdy.add(a);
                break;
            }
        }
        return true;
    }

    /*Si el identificador id no lo posee ninguna arista en el grafo, crea una nueva arista y la agrega en el
    * grafo. Retorna true en caso en que la inserción se lleve a cabo, false en contrario.
    */
    public Boolean AgregarArista(String id, E dato, Double p, String IdV1, String IdV2){
        Arista<E> Arista = new Arista<E>();
        Vertice<V> Vertice1 = new Vertice<V>();
        Vertice<V> Vertice2 = new Vertice<V>();

        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                if(this.Grafo.get(i).ListaAdy.get(j).GetId().equals(id)){                    //Revisa si ya existe la arista en el Grafo
                    return false;
                }
            }
        }

        for(int i=0; i<this.Grafo.size(); i++){ 
            if(this.Grafo.get(i).GetId().equals(IdV1)){                                      //Encuentra el Vertice en el extremo 1 de la arista
                Vertice1 = this.Grafo.get(i);
            }
            else if(this.Grafo.get(i).GetId().equals(IdV2)){                                 //Encuentra el vertice en el extremo 2 de la arista
                Vertice2 = this.Grafo.get(i);
            }
        }

        Arista.CrearArista(id, dato, p, Vertice1, Vertice2);                                 //Crea la Arista

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Arista.GetExtremo1().GetId())){              //Agrega la Arista a uno de los Vertices extremos
                this.Grafo.get(i).ListaAdy.add(Arista);
                break;
            }
        }
        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Arista.GetExtremo2().GetId())){              //Agrega la Arista a el otro de los Vertices extremos
                this.Grafo.get(i).ListaAdy.add(Arista);
                break;
            }
        }
        return true;
    }

    /*Elimina la arista en el grafo que esté identificada con id. Se retorna true en caso que se haya
    * eliminado la arista del grafo y false en caso de que no exista una arista con ese identificador en el
    * grafo*/
    public Boolean EliminarArista(String id){
        Boolean Existe = false;
        
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                if(this.Grafo.get(i).ListaAdy.get(j).GetId().equals(id)){
                    this.Grafo.get(i).ListaAdy.remove(j);
                    Existe = true;
                }
            }
        }
        if(Existe){
            return true;
        }
        return false;
    }

    /*Devuelve la arista que tiene como identificador id. En caso de que no exista ninguna arista con ese
    * identificador, se lanza la excepción NoSuchElementException.
    */
    public Arista<E> ObtenerArista(String id){

        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                if(this.Grafo.get(i).ListaAdy.get(j).GetId().equals(id)){
                    return this.Grafo.get(i).ListaAdy.get(j);
                }
            }
        }
        throw new NoSuchElementException("Esta Arista no existe en el grafo");
    }


   //####################################### IMPLEMENTACION INTERFAZ GRAFO ##########################################################################


    /*Descrito en la interfaz Grafo.java*/
    public int NumeroDeVertices(){
        int Contador = 0;
        for(int i=0; i<this.Grafo.size(); i++){
            Contador++;
        }
        return Contador;
    }

    /*Descrito en la interfaz Grafo.java*/
    public int NumeroDeLados(){
        int Contador = 0;
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                Contador++;
            }
        }

        return Contador/2;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean AgregarVertice(Vertice<V> V){
        for(int i=0; i<this.Grafo.size(); i++){                      //Revisa todo los vertices del grafo y si no encuentra el vertice repetido, lo agrega.
            if(this.Grafo.get(i).GetId().equals(V.GetId())){
                return false;
            }
        }
        this.Grafo.add(V);
        return true;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean AgregarVertice(String Identificador, V Dato, Double peso){
        Vertice<V> Ve = new Vertice<V>();
        Ve.CrearVertice(Identificador, Dato, peso);
        for(int i=0; i<this.Grafo.size(); i++){                      //Revisa todo los vertices del grafo y si no encuentra el vertice repetido, lo agrega.
            if(this.Grafo.get(i).GetId().equals(Ve.GetId())){
                return false;
            }
        }
        this.Grafo.add(Ve);
        return true;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Vertice<V> ObtenerVertice(String Identificador){                    
        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){                       //Trata de encontrar el vertice en el grafo, si no lo encuentra
                return this.Grafo.get(i);                                              //lanza una excepcion 
            }
        }
        throw new NoSuchElementException("Este Vertice no existe en el grafo");
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean EstaVertice(String Identificador){
        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){                       
                return true;                                              
            }
        }
        return false;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean EstaLado(String Identificador1, String Identificador2){
        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador1)){                         //Busca el Vertice Extremo 1 de la Arista, entra a su lista de sucesores y...
                for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){                   //...busca el Vertice Extremo 2 de la Arista. Si lo encuentra retorna la Arista.
                    if(this.Grafo.get(i).ListaAdy.get(j).GetExtremo2().GetId().equals(Identificador2)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean EliminarVertice(String Identificador){
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){

            if(this.Grafo.get(i).GetId().equals(Identificador)){                             //Busca el vertice en la ListaInicial y lo borra
                this.Grafo.remove(i);
                Existe = true;
            }
        }
        
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                if(this.Grafo.get(i).ListaAdy.get(j).GetExtremo2().GetId().equals(Identificador) || 
                this.Grafo.get(i).ListaAdy.get(j).GetExtremo1().GetId().equals(Identificador)){
                    this.Grafo.get(i).ListaAdy.remove(j);                                    //Busca en la lsitaAdy de cada vertice y si encuentra el Vertice a eliminar
                                                                                             //Entonces borra este lado.
                }
            }
        }
        if(Existe){
            return true;
        }
        return false;
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Vertice<V>> Vertices(){
        ArrayList<Vertice<V>> Result = new ArrayList<Vertice<V>>();

        for(int i=0; i<this.Grafo.size(); i++){                                      //Agrega a Result todos los vertices de el Grafo
            Result.add(this.Grafo.get(i));
        }
        return Result;
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Lado<E>> Lados(){
        ArrayList<Lado<E>> Result = new ArrayList<Lado<E>>();                    
     
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){                  //Agrega todas las Aristas del grafo a Result
                Result.add(this.Grafo.get(i).ListaAdy.get(j));
            }
        }
        return Result;
    }

    /*Descrito en la interfaz Grafo.java*/
    public int Grado(String Identificador){
        Boolean LoEncontro = false;
        int Contador = 0;                                                            //Inicializamos el contador

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){                     //Encuentra el Vertice que tiene como Id: Identificador
                for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){      
                    Contador++;                                                      //Cuenta la cantidad de Aristas que tiene
                }
                LoEncontro = true;
                break;
            }
        }
        if(LoEncontro){
            return Contador;
        }
        else{
            throw new NoSuchElementException("El Vertice ingresado no existe en el Grafo");
        }
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Vertice<V>> Adyacentes(String Identificador){
        Boolean LoEncontro = false;
        ArrayList<Vertice<V>> Result = new ArrayList<Vertice<V>>();

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){
                LoEncontro = true;
                for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                    Result.add(this.Grafo.get(i).ListaAdy.get(j).GetExtremo2());
                }
            }
        }
        if(LoEncontro){
            return Result;
        }
        else{
            throw new NoSuchElementException("El Vertice ingresado no existe en el Grafo");
        }
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Lado<E>> Incidente(String Identificador){
        Boolean LoEncontro = false;
        ArrayList<Lado<E>> Result = new ArrayList<Lado<E>>();

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){
                LoEncontro = true;
                for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){                           //Agrega las Aristas incidentes en el vertice dado
                    Result.add(this.Grafo.get(i).ListaAdy.get(j));
                }
            }
        }
        if(LoEncontro){
            return Result;
        }
        else{
            throw new NoSuchElementException("El Vertice ingresado no existe en el Grafo");
        }
    }

    /*Descrito en la interfaz Grafo.java*/
    public String ToString(){
        String Result = "\nRepresentacion del Grafo No Dirigido:\n";

        for(int i=0; i<this.Grafo.size(); i++){
            Result = Result + String.valueOf(this.Grafo.get(i).GetId() + ":");
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                Result = Result + "  " + String.valueOf(this.Grafo.get(i).ListaAdy.get(j).GetId());
            }
            Result = Result + "\n";
        }

        Result = Result + "\nVertices del Grafo:\n";
        for(int i=0; i<this.Grafo.size(); i++){
            Result = Result + this.Grafo.get(i).ToString() + "\n";
        }

        Result = Result + "\nAristas del Grafo:\n";
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaAdy.size(); j++){
                Result = Result + this.Grafo.get(i).ListaAdy.get(j).ToString() + "\n";
            }
        }

        return Result;
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Vertice<V>> Clone(){
        ArrayList<Vertice<V>> GrafoClone = new ArrayList<Vertice<V>>();
        GrafoClone = Grafo;

        return GrafoClone;
    }

    /*Descrito en la interfaz Grafo.java*/
    public Boolean CargarGrafo(int n, int m, String Archivo, Transformer<String,V> TransformadorVertice, Transformer<String,E> TransformadorArista){
        try{
            BufferedReader Lector = new BufferedReader(                                       //Crea el lector del archivo .txt
                    new FileReader(Archivo));
        
            String linea = Lector.readLine();                                                 //
            linea = Lector.readLine();                                                        //
            linea = Lector.readLine();                                                        //  Aqui se omitiran las lineas que ya se leyeron en Cliente.java
            linea = Lector.readLine();                                                        //
            linea = Lector.readLine();                                                        // 

            String[] partes;
            for(int i=0; i<n; i++){
                linea = Lector.readLine();
                partes = linea.split(" ");
                V Dato = TransformadorVertice.Transformar(partes[1]);                         //Corta por los espacios a el String leido 
                AgregarVertice(partes[0], Dato, Double.parseDouble(partes[2]));               //Transforma del String Dato del vertice en su tipo generico
            }

            for(int i=0; i<m; i++){
                linea = Lector.readLine();                        
                partes = linea.split(" ");                                                    //Corta por los espacios a el String leido
                E Dato = TransformadorArista.Transformar(partes[1]);                          //Transforma del String Dato de la Arista en su tipo generico
                AgregarArista(partes[0], Dato, Double.parseDouble(partes[2]), partes[3], partes[4]);   
            }

            return true;  

        }catch(IOException e) {
			return false;
		}
    }
}