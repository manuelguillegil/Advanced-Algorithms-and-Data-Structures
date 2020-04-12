/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Hamilton {

	private static void cargarAristas(String linea, Grafo grafo)
			throws IllegalArgumentException
	{
		String[] vertices = linea.split(" ");                          //Separa la linea por espacios, para tener en vertices: vertice incial en vertices[0] y vertice final en vertices[1]
		int vertice1 = Integer.parseInt(vertices[0]);                                  
		int vertice2 =  Integer.parseInt(vertices[1]);	
		grafo.AgregarArista(vertice1, vertice2);
	}

	static Grafo cargarGrafo(String nombreArchivo, Grafo grafo)
			throws IOException
	{
		int n;                                                  // Corresponde a la cantidad de vértices en el grafo
		int m;                                                  // Corresponde a la cantidad de aristas en el grafo
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine(); 						// Lee la primera línea que corresponde a la cantidad de vertices del grafo en el archivo txt
		n =  Integer.parseInt(linea);   
		linea = Lector.readLine(); 								// Lee la segunda línea que corresponde a la cantidad de aristas del grafo en el archivo txt
		m =  Integer.parseInt(linea);

		for(int i = 0; i < n; i++){
			grafo.AgregarVertice();                   			//Agrega los vertices al grafo 
		}

		linea = Lector.readLine();			                    // Tercera línea que corresponde al primer elemento del grafo en el archivo txt
		
		do{
			cargarAristas(linea, (Grafo)grafo);                 // Aquí añadimos las aristas que nos indica el archivo txt
			linea=Lector.readLine();
		}while(linea != null);
		
		return grafo;
	}


	/**Este metodo Revisa si existe un Camino Hamiltoniano dado el arreglo 
	 * de visita generado por DFS o BFS 
	 * 
	*/
	private static Boolean ExisteHamiltoniano(ArrayList<Integer> Camino, int VerticeInicial, int[] Arr){

		Boolean Existe = true;          

		if(Camino.size()!=Arr.length){
			Existe = false;                                                          
		}

		return Existe;
	}


	/**Este metodo encuentra un camino dado un arreglo de visita y el vertice 
	 * donde inicia este camino 
	 * 
	*/
	private static ArrayList<Integer> SearchPath(int[] Arr, int Vinicial, ArrayList<Integer> Camino){

		Camino.add(Vinicial);
		int Posicion = Vinicial;

		for(int j=0; j<Arr.length; j++){
			for(int i=0; i<Arr.length; i++){
				if(Posicion!=i && Arr[i]==Camino.get(Camino.size()-1)){                                   
					Camino.add(i);                                                                        
				}
				
			}

		}

		return Camino;

	}


	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		int VerticeInicial;
		Boolean Existe;
		ArrayList<Integer> Camino = new ArrayList<Integer>();     
		Grafo grafo = new Grafo();                                         //Crea el objeto Grafo
		grafo = cargarGrafo(args[0], (Grafo)grafo);                        //Carga el Grafo con sus vertices y lados
		         
	
		if(args[1].equals("BFS")){
			BFS bfs = new BFS();                                           //Crea el objeto BFS
			int[] ArrBFS = new int[grafo.ListaAdy.size()];                 //Arreglo que tendra el resultado de BFS

			for(int v=0; v<grafo.ListaAdy.size(); v++){
				for(int i=0; i<grafo.ListaAdy.size(); i++){        
					ArrBFS[i] = -1;                                                //Inicializo todos los elementos de este arreglo en -1.
				}

				System.out.println("Recorrido desde: "+Integer.toString(v));
				VerticeInicial = v;                                                    //Vertice donde se inicia el DFS
				ArrBFS = bfs.Inicio(v, grafo);
				Camino = SearchPath(ArrBFS, VerticeInicial, Camino);                   //Crea el camino que inicia en v

				for(int i=0; i<Camino.size()-1; i++){
					System.out.println(" "+Integer.toString(Camino.get(i))+"-"+Integer.toString(i+1));  //Imprime el camino
				}
				Existe = ExisteHamiltoniano(Camino, VerticeInicial, ArrBFS);
				Camino.clear();                                                        //Limpia el camino

				if(!Existe){
					System.out.println("No se encontro camino Hamiltoniano por aqui");
				}
				else{
					System.out.println("Se encontro un camino Hamiltoniano!");
					break;
				}
			}

		}
		else if(args[1].equals("DFS")){
			
			DFS dfs = new DFS();
			int[] ArrDFS = new int[grafo.ListaAdy.size()];
			
			for(int v=0; v<grafo.ListaAdy.size(); v++){
				for(int i=0; i<grafo.ListaAdy.size(); i++){        
					ArrDFS[i] = -1;                                                 
				}

				System.out.println("Recorrido desde: "+Integer.toString(v));
				VerticeInicial = v;                                                    //Vertice donde se inicia el DFS
				ArrDFS = dfs.Inicio(v, grafo);
				Camino = SearchPath(ArrDFS, VerticeInicial, Camino);                   //Crea el camino que inicia en v

				for(int i=0; i<Camino.size()-1; i++){
					System.out.println(" "+Integer.toString(Camino.get(i))+"-"+Integer.toString(Camino.get(i+1)));  //Imprime el camino
				}
				Existe = ExisteHamiltoniano(Camino, VerticeInicial, ArrDFS);           //Revisa si existe un camino hamiltoniano
				Camino.clear();                                                        //Limpia el camino para otra iteracion

				if(!Existe){
					System.out.println("No se encontro camino Hamiltoniano por aqui");
				}
				else{
					System.out.println("Se encontro un camino Hamiltoniano!");
					break;
				}	
			}

		}
	}
}