import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.text.DecimalFormat;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

public class Mesero { 

	/*Este metodo es utilizado por el main para Cargar el Grafo con los Vertices y sus Aristas
    */
	static Grafo cargarGrafo(String nombreArchivo, Grafo grafo)
			throws IOException
	{
		int n;                           // Corresponde al número de nodos del grafo
        int m;                       	 // Corresponde a la cantidad de aristas en el grafo

		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
        String linea = Lector.readLine();
        n = Integer.parseInt(linea);

        /*Agregamos los vértices con las posiciones correspondientes*/
        for(int i = 0; i < n; i++){
        	linea = Lector.readLine();
        	String[] posiciones = linea.split(" ");
        	grafo.agregarVertice(i, Double.valueOf(posiciones[0]), Double.valueOf(posiciones[1]));
        }

        m = Integer.parseInt(Lector.readLine());      

        /*Ahora agregamos las aristas*/
        for(int i = 0; i < m; i++){
        	linea = Lector.readLine();
        	String[] vertices = linea.split(" ");
        	grafo.agregarArista(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[1]));

        }
        return grafo; 
    }

    /*Este metodo es utilizado por el algoritmo Dijkstra para encontrar el Vertice Minimo
    */ 
    static Nodo ExtraerMin(ArrayList<Nodo> cola, Double[] Costo){
        Double Min = 9999999999999999.9;
        int MinElem = -1;
        /*Se busca el Vertice con Costo minimo en el arreglo Costo*/
        for(int i=0; i<cola.size(); i++){
            if(Costo[cola.get(i).getId()]<Min){
                Min = Costo[i];
                MinElem = i;
            }
        }
        /*Guardamos el Vertice Minimo en R y luego lo eliminamos de la cola*/
        Nodo R = cola.get(MinElem);
        cola.remove(MinElem);
        return R;

    }

    /*Metodo que calcula la Distancia Euclideana entre dos Vertices en el plano
    */ 
    static Double Costoe(Nodo V, Nodo W){
        return Math.sqrt(Math.pow(W.getX() - V.getX(), 2) + Math.pow(W.getY() - V.getY(), 2)); 
    }


    /*Este metodo es utilizado por el main con el Cual tomamos como atributos el Vertice final del camino a buscar, el arreglo de predecesores, El Grafo y el arreglo
    * de Costos de cada vertice. Luego el metodo retornara un string con el camino creado desde este vertice final y el vertice inicial del camino mas el numero de lados
    * y la distancia entre ellos
    */
    public static String EncontrarCamino(int Inicio, int[] Predecesor, Grafo Grafo, Double[] Costo){
        /*Inicializa las variables necesarias para el metodo*/
        int Pred = Predecesor[Inicio];
        String Camino = String.valueOf(Grafo.ListaVertices.get(Inicio).getId());
        int NumLados = 1;
        DecimalFormat numberFormat = new DecimalFormat("0.0#");

        while(true){
            if(Pred!=Predecesor[Pred]){                                                              //Si el predecesor del vertice no es el mismo
                Camino = String.valueOf(Grafo.ListaVertices.get(Pred).getId())+ "->"+Camino;
                Pred = Predecesor[Pred];
                NumLados++;
            }
            else{                                                                                    //Si el predecesor del vertice es el mismo, entonces este vertice es
                break;                                                                               //el vertice inicial del camino
            }
        }
        Camino = String.valueOf(Grafo.ListaVertices.get(Pred).getId())+ "->"+Camino;                 //Agrega el Vertice inicial a el String con el camino.
        Camino = "Nodo "+String.valueOf(Inicio)+": "+Camino+"   "+"\t"+String.valueOf(NumLados)+" Lados"+" (Costo "+String.valueOf(numberFormat.format(Costo[Inicio]))+")";
        return Camino;
    }


	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{

		Grafo grafo = new Grafo();                                                     //Crea el objeto Grafo
        grafo = cargarGrafo(args[0], (Grafo)grafo);                                    //Carga el Grafo

        /*Inicializa todos los arreglos y variables necesarias para correr Dijkstra*/
        int[] P = new int[grafo.ListaVertices.size()];         
        Double[] Costo = new Double[grafo.ListaVertices.size()];
        ArrayList<Nodo> cola = new ArrayList<Nodo>();
        Nodo V;
        Nodo W;

        for(int i=0; i<grafo.ListaVertices.size(); i++){        
            P[i] = -1;                                                  //Se inicializa el arreglo en -1.
            cola.add(grafo.ListaVertices.get(i));
            Costo[i] = 99999999999.9;
        }
        Costo[Integer.parseInt(args[1])] = 0.0;
        P[Integer.parseInt(args[1])] = Integer.parseInt(args[1]);

        /*Aqui es donde comienza la ejecucion del Dijkstra*/
        while(cola.size()!=0){
            V = ExtraerMin(cola, Costo);                                                              //Vertice minimo en la cola
            for(int i=0; i<grafo.ListaVertices.get(V.getId()).ListaAdy.size(); i++){
                W = grafo.ListaVertices.get(V.getId()).ListaAdy.get(i);                               //Vertice adyacente a V
                /*Serian dos casos if ya que estamos en un grafo no dirigido por ende la arista funciona como un arco con doble sentido*/
                if(Costo[W.getId()] > Costo[V.getId()] + Costoe(V, W)){
                    Costo[W.getId()] = Costo[V.getId()] + Costoe(V, W);
                    P[W.getId()] = V.getId();
                }
                else if(Costo[V.getId()] > Costo[W.getId()] + Costoe(V, W)){
                    Costo[V.getId()] = Costo[W.getId()] + Costoe(V, W);
                    P[V.getId()] = W.getId();
                }
            }
        }

        /*Luego de correr Dijkstra y tener el arreglo de predecesores y Costos, pasamos a encontrar los caminos para luego imprimirlos*/
        for(int i=0; i<P.length; i++){
            if(P[i]!=i){
                System.out.println(EncontrarCamino(i, P, grafo, Costo));
            }
            /*Este else lo usamos cuando nos toca imprimir el camino hasta el Vertice S con el que corrimos Dijkstra
            * ya que en EncontrarCamino no tomamos en cuenta este caso*/
            else{
                System.out.println("Nodo "+String.valueOf(i)+": "+String.valueOf(i)+"   "+"\t"+String.valueOf(0)+" Lados"+" (Costo "+String.valueOf(Costo[i])+")");
            }
        }
	}
}