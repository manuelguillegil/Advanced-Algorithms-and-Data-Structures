/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;

public class USBDataFlow{
    
    /**
     * El metodo check es usado para revisar si el vertice existe en el grafo. Si no existe regresa false.
     * Parametros entrada:
     * @param grafo = Grafo de el problema
     * @param Vertice = Vertice a revisar si existe en el grafo
     * @return = Booleano true si existe, false si no.
     */
    public static Boolean check(GrafoDirigido grafo, String Vertice){
        Boolean R = false;
        for(int i=0; i<grafo.ListaVertices.size(); i++){
            if(grafo.ListaVertices.get(i).getId().equals(Vertice)){
                R = true;
            }
        }
        return R;
    }

    /**
     * Este metodo es utilizado para encontrar un ciclo en el grafo.
     * 
     * Parametros entrada:
     * @param Grado = Array con los grados de los vertices luego de correr Bellman V2
     * @param V = Vertice el cual revisaremos con dfs
     * @param grafo = Grafo del problema
     * @param Inicial = Vertice inicial del ciclo
     * @param cadena = String que contiene el camino
     * Parametros salida:
     * @return = null si no se encontro ciclo que empiece desde Inicial, String si se encontro ciclo.
     */
    public static String dfs_rec(int[] Grado, String V, GrafoDirigido grafo, String Inicial, String cadena){
        ArrayList<Arco> Sucesores = grafo.encontrarSucesores(V);                        
        String Camino = null;                                                    //Inicializamos la variable que contendra el camino a retornar
        if(V.equals(Inicial) && !cadena.equals("")){                             //Si, por dfs, volvimos a caer en el mismo vertice. Entonces encontramos el ciclo
            Camino = cadena;
            return Camino;
        }
        for(int i=0; i<Sucesores.size(); i++){                                   //Para todo sucesor de V
            int j=grafo.encontrarVertice(Sucesores.get(i).getExtremoFinal());    //Encuentro el Index del vertice en el Array Grado
            if(Grado[j]>0){                                                      //Si Grado[j]>0 entonces por este vertice podria encontrar un ciclo
                cadena = cadena + "->" + Sucesores.get(i).getExtremoFinal();     
                Camino = dfs_rec(Grado, Sucesores.get(i).getExtremoFinal(), grafo, Inicial, cadena);
                if(Camino!=null){                                                //Si ya encontramos el ciclo, lo retornamos hasta salir de la recursion
                    return Camino;
                }
                cadena = cadena.replace("->"+Sucesores.get(i).getExtremoFinal(), "");
            }
        }
        return null;                                                             //Se retorna null si no se encuentra un ciclo
    }


    /**
     * En el metodo cargarGrafo procedemos a cargar el grafo. Primero creamos el arreglo que contendra los identificadores de las columnas para luego utilizarlo
     * para ingresar los identificadores de los nodos del grafo de precedencia. Luego de esto agregamos los vertices al grafo y agregamos, para cada uno, Su
     * Identificador, su expresion y si es un valor numerico agregamos su Valor. Por ultimo agregamos los arcos al grafo de precedencia y para esto revisamos la
     * expresion de cada vertice, buscamos los operandos de el y si uno de los operandos es un identificador, agregamos un arco que vaya del nodo identificado en la
     * expresion hasta el nodo que tiene la expresion.
     * 
     * Parametros entrada:
     * @param grafo = grafo de el problema
     * @param fileName = nombre de el archivo.txt a abrir
     * @throws IOException
     */
    static void cargarGrafo(GrafoDirigido grafo, String fileName)
            throws IOException
    {
        try{
            int n;
            int m;
            BufferedReader Lector = new BufferedReader(
                new FileReader(fileName));
            
            String linea = Lector.readLine();
            String[] partes = linea.split(" ");
            n = Integer.parseInt(partes[0]);
            m = Integer.parseInt(partes[1]); 
            grafo.setFilas(n);
            grafo.setColumnas(m);

            /*Ahora procedemos a crear el arreglo de identificadores de columnas*/
            String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            String[] columnas = new String[m];
            int k=0;
            int l=0;
            for(int i=0; i<columnas.length; i++){
                if(i!=0){
                    if(i%26==0){
                        k++;
                    }
                    if(i%702==0){
                        l++;
                        k=0;
                    }
                }
                if(k==0 && l==0){                                     //Si k=0, entonces no hemos terminado de identificar de la A a la Z
                    columnas[i] = alfabeto[i]; 
                }
                else if(k!=0 && l==0){                                //Si no, entonces debemos empezar a identificar con mas de 1 letra
                    columnas[i] = alfabeto[k-1]+alfabeto[i-k*26];
                }
                else{
                    columnas[i] = alfabeto[l-1]+alfabeto[k]+alfabeto[i-l*702-k*26];
                }
            }
            
            /*Ahora agregaremos los Vertices del grafo*/
            for(int i=0; i<n; i++){
                linea = Lector.readLine();
                partes = linea.split(" ");
                if(partes.length>m){
                    System.out.println("ERROR: Se ingresaron mas columnas de las indicadas.");
                    System.exit(1);
                }
                for(int j=0; j<partes.length; j++){
                    Vertice V = new Vertice(String.valueOf(columnas[j])+String.valueOf(i+1), partes[j].toUpperCase());
                    if(partes[j].charAt(0) != '='){
                        V.setValor(Integer.parseInt(partes[j]));
                    }
                    grafo.agregarVertice(V);
                }
            }
            linea = Lector.readLine();
            if(linea != null){
                System.out.println("ERROR: Se ingresaron mas filas de las indicadas");
                System.exit(1);
            }

            /*Ahora agregaremos los Arcos al grafo */
            for(int i=0; i<grafo.ListaVertices.size(); i++){
                String expresion = grafo.ListaVertices.get(i).getExpresion();
                if(expresion.charAt(0) == '='){
                    /*Aqui eliminamos todo de la expresion excepto los operandos, para asi buscar los operandos dependientes de otras casillas
                    * y crear un arco desde esa casilla (vertice) a la casilla actual.
                    */
                    expresion = expresion.replace("=", " ");                                              
                    expresion = expresion.replace("MAX("," ");                                            
                    expresion = expresion.replace("MIN("," ");                                            
                    expresion = expresion.replace("SUM(", " ");                                           
                    expresion = expresion.replace(")"," ");                             
                    expresion = expresion.replace("+"," ");
                    expresion = expresion.replace("-"," ");
                    expresion = expresion.replace("*"," ");
                    expresion = expresion.replace(","," ");

                    partes = expresion.split(" ");
                    for(int j=0; j<partes.length; j++){
                        if(!partes[j].equals("")){
                            if(Character.isLetter(partes[j].charAt(0))){
                                if(!check(grafo, partes[j])){
                                    System.out.println("ERROR: Una de las casillas es dependiente de una casilla inexistente en el archivo .txt");
                                    System.exit(1);
                                }
                                /*Si el arco ya existe, no lo agrega */
                                if(!grafo.existeArco(partes[j], grafo.ListaVertices.get(i).getId())){
                                    partes[j].toUpperCase();
                                    grafo.ListaVertices.get(i).setGradoInt();
                                    Arco Ar = new Arco(partes[j], grafo.ListaVertices.get(i).getId());
                                    grafo.agregarArco(Ar);
                                }
                            }
                        }
                    }
                }
            }
        }catch(IOException e1){
            System.out.println("ERROR: El archivo .txt no existe");
            System.exit(1);
        }catch(NumberFormatException e2){
            System.out.println("ERROR: Hubo un error en la sintaxis del archivo .txt");
            System.exit(1);
        }
    }


    public static void main(String[] args)
            throws IOException
    {
        if(args.length==0){
            System.out.println("ERROR: No se indico el archivo .txt de carga.");
            System.exit(1);
        }
        GrafoDirigido grafo = new GrafoDirigido();                                    //Creamos el grafo
        cargarGrafo(grafo, args[0]);                                                  //Cargamos el grafo
        /*
            Descripcion del Grafo:

            G = (V,E) Dirigido
            V = {x | x es una casilla}
            E = {(x,y) in VxV | el valor de la casilla x es utilizado por la casilla y}
        */

        /*Inicializa los arreglos y Listas necesarias para la corrida del algoritmo de Bellman*/
        ArrayList<String> T = new ArrayList<String>();
        int[] Costo = new int[grafo.ListaVertices.size()];
        int[] Grado = new int[grafo.ListaVertices.size()];
        int[] Pred = new int[grafo.ListaVertices.size()];
        Evaluador evaluador = new Evaluador();                                 //Inicializamos el Evaluador
        for(int i=0; i<grafo.ListaVertices.size(); i++){
            /*Si el vertice Tiene Grado Interno = 0. Este sera un vertice Fuente del grafo y se agrega de una vez en la Lista T*/
            if(grafo.ListaVertices.get(i).getGradoInt()==0){
                T.add(grafo.ListaVertices.get(i).getId());
                Costo[i] = 0;
                Grado[i] = 0;
            }
            else{
                Costo[i] = 999999999;
                Grado[i] = grafo.ListaVertices.get(i).getGradoInt();
            }
            Pred[i] = -1;
        }


        /*Antes de empezar, tomamos todos los vertices con grado 0 y si su expresion es una funcion, evaluamos la funcion para agregar su valor.
        * Esto lo hacemos ya que como son de grado interior = 0, su expresion no depende de una casilla y nunca sera evaluada dentro
        * de Bellman V2 y tendremos que hacerlo aqui*/
        for(int i=0; i<Grado.length; i++){ 
            if(Grado[i]==0){ 
                String cadena = grafo.ListaVertices.get(i).getExpresion();                  //Tomamos la expresion del vertice
                if(cadena.charAt(0) == '='){                                                //Si la expresion es una funcion
                    cadena = cadena.replace("=", "");                                                                   
                    grafo.ListaVertices.get(i).setValor(evaluador.evaluar(cadena));         //Evaluamos la expresion y su resultado lo agregamos a el atributo valor del vertice
                }                                                                           
            }
        }

        /*Una vez inicializadas, corremos Bellman para grafos aciclicos () */
        while(T.size()!=0){
            String V = T.get(0);                                       //Se toma un vertice cualquiera de la lista T (Nosotros tomamos el primero, como una cola).
            int indexI = grafo.encontrarVertice(V);                    //Busca la posicion de el Vertice V en el grafo (ArrayList)
            T.remove(0);                                               //Lo eliminamos de la Lista para no tomarlo denuevo
            ArrayList<Arco> sucesores = grafo.encontrarSucesores(V);   //ArrayList que contienen todos los arcos con vertice inicial en V

            /*Para todos los sucesores de V*/
            for(int i=0; i<sucesores.size(); i++){
                int indexF = grafo.encontrarVertice(sucesores.get(i).getExtremoFinal());                //Busca la posicion de el sucesor de V en el grafo (ArrayList)
                String cadena = grafo.ListaVertices.get(indexF).getExpresion();                         //Obtenemos la expresion de este sucesor

                cadena = cadena.replace(V, String.valueOf(grafo.ListaVertices.get(indexI).getValor())); //Actualizamos la expresion del sucesor con el valor de V
                grafo.ListaVertices.get(indexF).setExpresion(cadena);                                   //

                Grado[indexF]--;                                                             
                /*Si todos los predecesores del Vertice ya han sido recorridos*/
                if(Grado[indexF] == 0){                                                   
                    T.add(grafo.ListaVertices.get(indexF).getId());                        //Agregamos el identificador del sucesor a T
                    cadena = cadena.replace("=", "");                                      //Elimina de la expresion el caracter "=" ya que no lo necesitaremos
                    grafo.ListaVertices.get(indexF).setValor(evaluador.evaluar(cadena));   //Evaluamos la Expresion aritmetica y guardamos el resultado en el atributo..
                                                                                           //..valor de el vertice
                }
                /*Si se encontro un camino mas corto a un Vertice, se actualiza su costo y su predecesor*/
                if(Costo[indexF] > Costo[indexI] + 1){
                    Costo[indexF] = Costo[indexI] + 1;
                    Pred[indexF] = indexI;
                }
            }
        }
        /*Luego de correr Bellman revisaremos el arreglo de Grado ya que, si no existe ciclos en el grafo, el arreglo de Grado debe estar
        * lleno de ceros. Si no es asi la configuracion nos genera un ciclo*/
        Boolean Ciclo = false;
        for(int i=0; i<Grado.length; i++){
            if(Grado[i]!=0){
                Ciclo = true;
            }
        }
        if(Ciclo){
            System.out.println("Su configuracion contiene un ciclo:");
            /*Ahora encontramos el ciclo */
            for(int i=0; i<Grado.length; i++){
                if(Grado[i]>0){                                   //Para todo vertice no tomado en cuenta en Bellman V2
                    String V = grafo.ListaVertices.get(i).getId();
                    String camino = "";
                    camino = dfs_rec(Grado, V, grafo, V, camino); //Correr dfs para encontrar un ciclo que inicie en el vertice V
                    if(camino!=null){                             //Si encontro un ciclo, lo imprime
                        System.out.println(V+camino);
                        break;
                    }
                }
            }
        }
        else{
            /*Si no existe un ciclo, se imprime el resultado de la forma indicada*/
            int n = grafo.getFilas();                           //Numero de filas 
            int m = grafo.getColumnas();                        //Numero de columnas
            int k=0;                                            //Contador para imprimir lo elementos del grafo
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    System.out.print(grafo.ListaVertices.get(k).getValor()+" ");
                    k++;
                }
                System.out.println();                           //Saltamos de linea
            }
        }
    }
}