/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.text.DecimalFormat;

public class Alumbrado{
    
    /**
     * Este metodo es utilizado por el main para cargar el Grafo del problema. Primero cargamos los vertices y luego de esto procedemos a cargar las
     * aristas con sus respectivos costos
     * 
     * Parametros entrada:
     * @param grafo = Grafo del problema
     * @param Lector = Lector de el archivo .txt
     * @param numVertices = Numero de vertices en el grafo
     * @param numAristas = Numero de aristas en el grafo
     * 
     * @throws IOException
     */
    public static void cargarGrafo(Grafo grafo, BufferedReader Lector, int numVertices, int numAristas)
            throws IOException
    {

        String linea;                                         //String que contendra la linea leida del archivo .txt
        String[] partes;                                      //Arreglo de las partes de la linea separadas por un espacio

        /*Agregamos los vértices del grafo*/
        for(int i=0; i<numVertices; i++){
            Nodo V = new Nodo(i);
        	grafo.agregarVertice(V);
        }

        /*Agregamos las aristas del grafo*/
        for(int i=0; i<numAristas; i++){
            linea = Lector.readLine();
            partes = linea.split(" ");

            /*Excepciones*/
            if(partes.length!=3){
                System.out.println("ERROR: No escribio el Input correctamente.");
                System.exit(1);
            }
            if(partes[0].equals(partes[1])){
                System.out.println("ERROR: En su archivo se encuentra un bucle y el problema nos indica que no pueden haber bucles.");
                System.exit(1);
            }
            if(Integer.parseInt(partes[0])>=grafo.ListaVertices.size() || Integer.parseInt(partes[0])>=grafo.ListaVertices.size() ||
                Integer.parseInt(partes[0])<0 || Integer.parseInt(partes[1])<0){

                System.out.println("ERROR: Una de las aristas contiene un vertice no existente en el grafo");
                System.exit(1);
            }

            /*Inserta la arista al grafo */
            Arista ar = new Arista(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
            grafo.agregarArista(ar);
        }
    }

    /**
     * Este metodo busca en la cola la arista de costo menor y la desencola 
     * 
     * Parametros entrada:
     * @param cola = Cola donde se encuentran las aristas.
     * 
     * Parametros salida:
     * @return = Arista de costo menor en la cola
     */
    public static Arista Desencolar(ArrayList<Arista> cola){
        int min = 999999999;
        Arista minElem = cola.get(0);
        int index = -1;

        for(int i=0; i<cola.size(); i++){
            if(min>cola.get(i).getCosto()){
                min = cola.get(i).getCosto();
                minElem = cola.get(i);
                index = i;
            }
        }
        cola.remove(index);
        return minElem;
    }

    /**
     * Este metodo Encuentra el representante de Vertice en el Disjoint set que se encuentra
     * 
     * Parametros entrada:
     * @param Vertice = Vertice del grafo
     * @param P = Arreglo de Representantes de los Dijoint sets.
     * Parametros salida:
     * @return = Representante del Disjoint set donde se encuentra Vertice
     */
    public static int Encontrar(int Vertice, int[] P){
        int y=Vertice;

        if(Vertice != P[Vertice]){
            y = Encontrar(P[Vertice], P);
            P[Vertice] = y;
        }

        return y;
    }

    /**
     * Main del programa, donde el codigo se ejecutara.
     * 
     * Parametros entrada:
     * @param args = Parametros ingresados por el usuario. args[0] = Nombre del archivo .txt
     * 
     * @throws IOException
     */
    public static void main(String[] args)
            throws IOException
    {
        try{
        BufferedReader Lector = new BufferedReader(                        //Inicializa el Lector del archivo .txt
                new FileReader(args[0]));

        /*El ciclo sigue mientras hayan casos de prueba*/
        while(true){
            String linea = Lector.readLine();
            if(linea == null){
                System.out.println("ERROR: No escribio el Input correctamente. ");
            }
            String[] partes = linea.split(" ");
            int numVertices = Integer.parseInt(partes[0]);
            int numAristas = Integer.parseInt(partes[1]);

            if(numVertices==0 && numAristas==0){                           //Si ya no hay mas casos de prueba, detiene la ejecucion
                System.exit(1);
            }
            /*Excepcion*/
            if(numVertices<0 || numAristas<0){                             
                System.out.println("ERROR: No se aceptan un numero negativo de aristas o vertices.");
                System.exit(1);
            }

            Grafo grafo = new Grafo();                                     //Crea el grafo del problema
            cargarGrafo(grafo, Lector, numVertices, numAristas);           //Carga el grafo con sus vertices y aristas


            /*Inicializamos las variables, cola, arreglo y lista que se usara para la ejecucion de Kruskal*/
            ArrayList<Arista> T = new ArrayList<Arista>();                 //Conjunto de aristas del arbol minimo cobertor
            ArrayList<Arista> cola = new ArrayList<Arista>(); 
            int numComp = numVertices;                                     //Arreglo de Representantes de los Disjoint Sets.
            int[] P = new int[grafo.ListaVertices.size()];

            /*KRUSKAL*/
            for(int i=0; i<grafo.ListaVertices.size(); i++){
                P[i] = i;                                                  //Inicializamos el arreglo de Representantes de los Disjoint sets
            }
            for(int i=0; i<grafo.ListaAristas.size(); i++){
                cola.add(grafo.ListaAristas.get(i));                       //Agregamos todos las aristas del grafo a la cola
            }
            while(numComp>1){
                Arista e = Desencolar(cola);                               //Desencolamos la arista con costo minimo de la cola

                int repreX = Encontrar(e.getExtremoIni(), P);              //Encontramos el representante del Disjoint set donde este el vertice inicial.
                int repreY = Encontrar(e.getExtremoFin(), P);              //Encontramos el representante del Disjoint set donde este el vertice Final.

                if(repreX != repreY){                                      //Si los representantes son iguales no agregamos la arista al arbol para no generar ciclos
                    P[repreX] = repreY;                                    //Une los Disjoint sets que contienen a los vertices inicial y final de la arista.
                    T.add(e);                                              //Agrega la arista al arbol cobertor minimo
                    numComp--;                                             //Decrementa el numero de Componentes conexas.
                }
            }


            /*Ahora calcularemos el costo total de las arstas del grafo y el costo total de las aristas del arbol minimo cobertor*/
            int costoGrafo = 0;
            int costoT = 0;
            for(int i=0; i<T.size(); i++){
                costoT += T.get(i).getCosto();
            }
            for(int i=0; i<grafo.ListaAristas.size(); i++){
                costoGrafo += grafo.ListaAristas.get(i).getCosto();
            }

            /*Imprimimos el resultado*/
            System.out.println(String.valueOf(costoGrafo-costoT)+" UT ahorradas");


        }

    }catch(IOException e1){
        System.out.println("ERROR: No se pudo encontrar el archivo .txt");
    }

    }
}