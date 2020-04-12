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

public class Mikado{

    /*Este metodo se utiliza para cargar el Grafo con los datos extraidos del archivo .txt
    */
    static Grafo cargarGrafo(BufferedReader Lector, Grafo grafo, int NumVertices, int NumArcos)
			throws IOException
	{	
        /*Agregamos los vértices con las posiciones correspondientes*/
        for(int i=1; i<=NumVertices; i++){
            Nodo V = new Nodo(i);
        	grafo.agregarVertice(V);
        }

        /*Agregamos los arcos del grafo*/
        for(int i=0; i<NumArcos; i++){
            String linea = Lector.readLine();
            String[] vertices = linea.split(" ");
            grafo.agregarArco(Integer.parseInt(vertices[0])-1, Integer.parseInt(vertices[1])-1);
        }

        return grafo; 
    }

    public static void main(String args[])
        throws IOException
    {

        BufferedReader Lector = new BufferedReader(
                new FileReader(args[0]));
        while(true){
            /*Inicializa variables para la carga del grafo*/
            String linea = Lector.readLine();
            String[] Partes = linea.split(" ");
            int NumVertices = Integer.parseInt(Partes[0]);
            int NumArcos = Integer.parseInt(Partes[1]);

            /*Si llegamos al final del Archivo .txt termina la ejecucion*/
            if(NumVertices== 0 && NumArcos== 0){
                System.exit(1);
            }

            /*Se crea el grafo y lo cargamos con los detalles de cada caso*/
            Grafo grafo = new Grafo();
            grafo = cargarGrafo(Lector, grafo, NumVertices, NumArcos);

            /*Inicializamos los arreglos y Listas para la corrida de Bellman V2*/
            ArrayList<Nodo> T = new ArrayList<Nodo>();
            ArrayList<Nodo> Tomados = new ArrayList<Nodo>();
            int[] Costo = new int[grafo.ListaVertices.size()];
            int[] Grado = new int[grafo.ListaVertices.size()];
            int[] Pred = new int[grafo.ListaVertices.size()];

            /*Bellman para grafos sin circuitos (V2). Se utiliza M.getId()-1 ya que las listas y arreglos cuentan desde el 0. pero el archivo cuenta los
            * vertices desde el 1. Por ende la posicion 0 en un arreglo\Lista es el correspondiente al vertice con identificador 1 en el Grafo*/
            for(int i=0; i<grafo.ListaVertices.size(); i++){
                /*Si el vertice Tiene Grado Interno = 0. Este sera un vertice Fuente del grafo y se agrega de una vez en la Lista T*/
                if(grafo.ListaVertices.get(i).getGradoInterno()==0){
                    T.add(grafo.ListaVertices.get(i));
                    Costo[i] = 0;
                    Grado[i] = 0;
                }
                else{
                    Costo[i] = 999999999;
                    Grado[i] = grafo.ListaVertices.get(i).getGradoInterno();
                }
                Pred[i] = -1;
            }
            while(T.size()!=0){
                Nodo V = T.get(0);                                         //Se toma un vertice cualquiera de la lista T (Nosotros tomamos el primero, como una cola).
                Tomados.add(V);                                            //Agregamos el Vertice (Palillo) A la Lista de Tomados. Lo usaremos mas adelante para imprimirlos en orden
                T.remove(0);                                               //Lo eliminamos de la Lista para no tomarlo denuevo
                /*Para todos los sucesores de V*/
                for(int i=0; i<V.ListaAdy.size(); i++){
                    Nodo M = V.ListaAdy.get(i);
                    Grado[M.getId()-1]--;
                    if(Grado[M.getId()-1] == 0){
                        T.add(M);
                    }
                    /*Si se encontro un camino mas corto a un Vertice, se actualiza su costo y su predecesor*/
                    if(Costo[M.getId()-1] > Costo[V.getId()-1] + 1){
                        Costo[M.getId()-1] = Costo[V.getId()-1] + 1;
                        Pred[M.getId()-1] = V.getId()-1;
                    }
                }
            }

            /*Luego de correr Bellman V2, ahora revisamos si existe un circuito, si existe uno el problema no tiene solucion y se imprime IMPOSSIBLE. Si no
            * Imprimimos en que orden los palillos fueron tomados*/
            Boolean Ciclo = false;
            for(int i=0; i<Grado.length; i++){
                if(Grado[i]!=0){
                    Ciclo = true;
                }
            }
            if(Ciclo){
                System.out.println("IMPOSSIBLE");
            }
            else{
                /*Imprimimos el orden con el cual se retiraran los palillo. Este viene dado por su posicion en la Lista Tomados*/
                for(int j=0; j<Tomados.size(); j++){
                    System.out.println(Tomados.get(j).getId());                                   //Imprimimos el palillo tomado.
                }
            }
        }
    }
}
