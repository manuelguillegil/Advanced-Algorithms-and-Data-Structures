import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

class GrafoDirigido<V,E> implements Grafo<V,E>{

    /*Atributos de Clase GrafoNoDirigido
    * Grafo: Sera el ArrayList de Vertices que contendra cada Vertice del Grafo
    */
    ArrayList<Vertice<V>> Grafo;

    /*Constructor de la clase GrafoDirigido*/
    public void CrearGrafoDirigido(){
        Grafo = new ArrayList<Vertice<V>>();
    }

    /*Calcula el grado exterior del vértice identificado por Identificador en el grafo. En caso de que no exista ningún
    * vertice con ese identificador, se lanza la excepción NoSuchElementException
    */
    public int GradoExterior(String Identificador){
        Boolean Existe = false;
        int Contador = 0;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){
                Existe = true;
                for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){               //Cuenta todos los lados donde el Vertice sea el Vertice Inicial
                    Contador++;
                }
            }
        }
        if(Existe){
            return Contador;
        }
        else{
            throw new NoSuchElementException("Este Vertice no existe en el grafo");
        }
    }

    /*Calcula el grado interior del vértice identificado por Identificador en el grafo. En caso de que no exista ningún
    * vértice con ese identificador, se lanza la excepción NoSuchElementException.
    */
    public int GradoInterior(String Identificador){
        int Contador = 0;
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){                           //Revisa si el vertice existe en el Grafo
                Existe = true;
            }
        }
        if(Existe){
            for(int i=0; i<this.Grafo.size(); i++){
                if(this.Grafo.get(i).GetId().equals(Identificador)){                
    
                }
                else{
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){               //Cuenta todos los lados donde el Vertice sea el Vertice Final
                        if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(Identificador)){
                            Contador++;
                        }
                    }
                }
            }
            return Contador;
        }
        else{
            throw new NoSuchElementException("Este Vertice no existe en el grafo");
        }   
    }

    /*Agrega un nuevo arco al grafo si el identificador del arco no lo posee ningún arco en el grafo.
    * Retorna true en caso en que la inserción se lleva a cabo, false en caso contrario.
    */
    public Boolean AgregarArco(Arco<E> a){
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                if(this.Grafo.get(i).ListaSucesores.get(j).GetId().equals(a.GetId())){       //Revisa si el Arco esta en el Grafo
                    return false;
                }
            }
        }

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(a.GetExtremoInicial().GetId())){             //Revisa donde colocarlo (debe colocarlo en la Lista de sucesores de su Vertice Inicial)
                this.Grafo.get(i).ListaSucesores.add(a);
                break;
            }
        }
        return true;
    }

    /*Si el identificador id no lo posee ningún arco en el grafo, crea un nuevo arco y lo agrega en el grafo.
    * Retorna true en caso en que la inserción se lleva a cabo, false en contrario.
    */ 
    public Boolean AgregarArco(String id, E dato, Double p, String IdV1, String IdV2){
        Arco<E> Arista = new Arco<E>();
        Vertice<V> Vertice1 = new Vertice<V>();
        Vertice<V> Vertice2 = new Vertice<V>();

        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                if(this.Grafo.get(i).ListaSucesores.get(j).GetId().equals(id)){         //Revisa si ya existe el Arco en el grafo
                    return false;
                }
            }
        }

        for(int i=0; i<this.Grafo.size(); i++){ 
            if(this.Grafo.get(i).GetId().equals(IdV1)){                                 //Encuentra el Vertice en el extremo Inicial de la arista
                Vertice1 = this.Grafo.get(i);
            }
            else if(this.Grafo.get(i).GetId().equals(IdV2)){                            //Encuentra el vertice en el extremo Final de la arista
                Vertice2 = this.Grafo.get(i);
            }
        }

        Arista.CrearArco(id, dato, p, Vertice1, Vertice2);                              //Crea el arco

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Arista.GetExtremoInicial().GetId())){              //Agrega el arco a la lista de sucesores del Vertice Inicial
                this.Grafo.get(i).ListaSucesores.add(Arista);
                break;
            }
        }
        return true;
    }

    /*Si el identificador id no lo posee ningún arco en el grafo, crea un nuevo arco y lo agrega en el grafo.
    * Retorna true en caso en que la inserción se lleva a cabo, false en contrario.
    */
    public Boolean EliminarArco(String id){
        Boolean Existe = false;
        
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                if(this.Grafo.get(i).ListaSucesores.get(j).GetId().equals(id)){                  //Busca donde se encuentra el Arco en la Lista y lo elimina
                    this.Grafo.get(i).ListaSucesores.remove(j);     
                    Existe = true;
                }
            }
        }
        if(Existe){
            return true;
        }
        return false;                                                                            //Si no existe, retorna False
    }

    /*Devuelve el arco que tiene como identificador id. En caso de que no exista ningún arco con ese
    * identificador, se lanza la excepción NoSuchElementException.
    */
    public Arco<E> ObtenerArco(String id){
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                if(this.Grafo.get(i).ListaSucesores.get(j).GetId().equals(id)){      
                    return this.Grafo.get(i).ListaSucesores.get(j);
                }
            }
        }
        throw new NoSuchElementException("Este Arco no existe en el grafo");
    }

    /*Devuelve una lista con los vértices sucesores del vértice con identificador id. En caso de que no
    * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
    */
    public ArrayList<Vertice<V>> Sucesores(String id){
        ArrayList<Vertice<V>> Result = new ArrayList<Vertice<V>>();
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(id)){                           //Revisa si el vertice existe en el Grafo
                Existe = true;
            }
        }
        if(Existe){
            for(int i=0; i<this.Grafo.size(); i++){
                if(this.Grafo.get(i).GetId().equals(id)){
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){                    //Si existe, busca los Sucesores de el Vertice y los agrega a Result
                        Result.add(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal());       
                    }
                }
            }
            return Result;
        }
        else{
            throw new NoSuchElementException("Este Vertice no existe en el grafo");
        }        
    }

    /*Devuelve una lista con los vértices predecesores del vértice con identificador id. En caso de que no
    * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
    */
    public ArrayList<Vertice<V>> Predecesores(String id){
        ArrayList<Vertice<V>> Result = new ArrayList<Vertice<V>>();
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(id)){                           //Revisa si el vertice existe en el Grafo
                Existe = true;
            }
        }
        if(Existe){
            for(int i=0; i<this.Grafo.size(); i++){
                for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                    if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(id)){       //Si existe, bsuca los predecesores de el Vertice y los agrega
                        Result.add(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoInicial());            //a Result.
                    }
    
                }
            }
            return Result;
        }
        else{
            throw new NoSuchElementException("Ese Vertice no se encuentra en el Grafo");
        }
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
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                Contador++;
            }
        }

        return Contador;
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
            if(this.Grafo.get(i).GetId().equals(Identificador1)){                          //Busca el Vertice inicial de el Arco, entra a su lista de sucesores y...
                for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){              //...busca el vertice final de el Arco. Si lo encuentra retorna el Arco.
                    if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(Identificador2)){
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
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(Identificador)){
                    this.Grafo.get(i).ListaSucesores.remove(j);                             //Busca en la listaAdy de cada vertice y si encuentra el Vertice a eliminar
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

        for(int i=0; i<this.Grafo.size(); i++){                                            //Agrega a Result todos los vertices de el Grafo
            Result.add(this.Grafo.get(i));
        }
        return Result;
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Lado<E>> Lados(){
        ArrayList<Lado<E>> Result = new ArrayList<Lado<E>>();

        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){                  //Agrega a Result todos los Arcos de el grafo
                Result.add(this.Grafo.get(i).ListaSucesores.get(j));              
            }
        }
        return Result;
    }

    /*Descrito en la interfaz Grafo.java*/
    public int Grado(String Identificador){
        int Contador = 0;                                                            

        Contador = Contador + GradoInterior(Identificador);                           //La suma de Grado Interior y Grado Exterior de un Vertice es el grado del Vertice
        Contador = Contador + GradoExterior(Identificador);

        return Contador;
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Vertice<V>> Adyacentes(String Identificador){
        ArrayList<Vertice<V>> Result = new ArrayList<Vertice<V>>();
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){
                Existe = true;
            }
        }
        if(Existe){
            for(int i=0; i<this.Grafo.size(); i++){
                if(this.Grafo.get(i).GetId().equals(Identificador)){
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                        Result.add(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal());        
                    }
                }
                else{
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){                        
                        if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(Identificador)){  
                            Result.add(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoInicial());      
                        }
                    }
                }
            }
            return Result;

        }
        else{
            throw new NoSuchElementException("Este Vertice no existe en el grafo");
        }
    }

    /*Descrito en la interfaz Grafo.java*/
    public ArrayList<Lado<E>> Incidente(String Identificador){
        ArrayList<Lado<E>> Result = new ArrayList<Lado<E>>();
        Boolean Existe = false;

        for(int i=0; i<this.Grafo.size(); i++){
            if(this.Grafo.get(i).GetId().equals(Identificador)){
                Existe = true;
            }
        }
        if(Existe){
            for(int i=0; i<this.Grafo.size(); i++){
                if(this.Grafo.get(i).GetId().equals(Identificador)){ 
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){                        //Agrega los Arcos que tengan al vertice como Extremo Inicial
                        Result.add(this.Grafo.get(i).ListaSucesores.get(j));        
                    }
                }
                else{
                    for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){                        //Busca los Arcos que tengan al vertice como Extremo Final
                        if(this.Grafo.get(i).ListaSucesores.get(j).GetExtremoFinal().GetId().equals(Identificador)){  
                            Result.add(this.Grafo.get(i).ListaSucesores.get(j));      
                        }
                    }
                }
            }
            return Result;
            
        }
        else{
            throw new NoSuchElementException("Este Vertice no existe en el grafo");
        }
    }

    /*Descrito en la interfaz Grafo.java*/
    public String ToString(){
        String Result = "\nRepresentacion del Grafo Dirigido:\n";

        for(int i=0; i<this.Grafo.size(); i++){
            Result = Result + String.valueOf(this.Grafo.get(i).GetId() + ":");
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                Result = Result + "  " + String.valueOf(this.Grafo.get(i).ListaSucesores.get(j).GetId());
            }
            Result = Result + "\n";
        }

        Result = Result + "\nVertices del Grafo:\n";
        for(int i=0; i<this.Grafo.size(); i++){
            Result = Result + this.Grafo.get(i).ToString() + "\n";
        }

        Result = Result + "\nArco del Grafo:\n";
        for(int i=0; i<this.Grafo.size(); i++){
            for(int j=0; j<this.Grafo.get(i).ListaSucesores.size(); j++){
                Result = Result + this.Grafo.get(i).ListaSucesores.get(j).ToString() + "\n";
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
    public Boolean CargarGrafo(int n, int m, String Archivo, Transformer<String,V> TransformadorVertice, Transformer<String,E> TransformadorArco){

        try{
            BufferedReader Lector = new BufferedReader(                           //Crea el lector del archivo .txt
                    new FileReader(Archivo));
        
            String linea = Lector.readLine();                                     //
            linea = Lector.readLine();                                            //
            linea = Lector.readLine();                                            //  Aqui se omitiran las lineas que ya se leyeron en Cliente.java
            linea = Lector.readLine();                                            //
            linea = Lector.readLine();                                            // 

            String[] partes;
            for(int i=0; i<n; i++){
                linea = Lector.readLine();
                partes = linea.split(" ");                                        //Corta por los espacios a el String leido 
                V Dato = TransformadorVertice.Transformar(partes[1]);             //Transforma del String Dato del vertice en su tipo generico
                AgregarVertice(partes[0], Dato, Double.parseDouble(partes[2]));
            }

            for(int i=0; i<m; i++){
                linea = Lector.readLine();
                partes = linea.split(" ");                                        //Corta por los espacios a el String leido 
                E Dato = TransformadorArco.Transformar(partes[1]);                //Transforma del String Dato del Arco en su tipo generico
                AgregarArco(partes[0], Dato, Double.parseDouble(partes[2]), partes[3], partes[4]);
            }

            return true;  

        }catch(IOException e) {
			return false;
		} 
    }
    
}