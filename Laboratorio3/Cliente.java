import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

//Estudiantes:
//Pietro Iaia
//Manuel Guillermo Gil

/**<p>Programa para convertir archivos Lista de Adyacencaia a archivos Matriz de
 * Adyacencia.</p>
 * 
 * <p>Un archivo Lista de Adyacencia representa un grafo, donde cada
 * línea tiene el formato:</p>
 * <blockquote>
 * <code>v<sub>i</sub>: v<sub>1</sub> v<sub>2</sub> … v<sub>m</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>i</sub></code> es un número de un vértice, y 
 * <code>v<sub>1</sub> v<sub>2</sub> … v<sub>m</sub></code> son los números
 * de los vértices adyacentes a <code>v<sub>i</sub></code></p>
 * <p>mientras que un archivo Matriz de adyacencia representa un grafo
 * en el formato</p>
 * <blockquote>
 * <code> &nbsp; v<sub>1</sub> v<sub>2</sub> … v<sub>n</sub><br>
 * v<sub>1</sub>| a<sub>1</sub> a<sub>2</sub> … a<sub>n</sub><br>
 * v<sub>2</sub>| a<sub>1</sub> a<sub>2</sub> … a<sub>n</sub><br>
 * ⋮<br>
 * v<sub>n</sub>| a<sub>1</sub> a<sub>2</sub> … a<sub>n</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>1</sub> v<sub>2</sub> … v<sub>n</sub></code> son
 * los números que identifican a los vértices, y <code>a<sub>i</sub></code>
 * indica si existe un arco desde el vértice al inicio de esa fila, y el
 * vértice al que corresponde esa columna.</p>
 * <p>El programa {@link #main} lee un archivo, detecta (a través de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a través de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */
public class Cliente{
	/**Detecta el tipo de archivo basado en una muestra de una línea tomada del
	 * archivo.
	 * 
	 * @param  linea              La línea de muestra tomada del archivo
	 * @return <code>true</code>  si está en el formato de un archivo lista de
	 *                            adyacencias;<br></br>
	 *         <code>false</code> si está en el formato de un archivo Matriz de
	 *                            adyacencias.
	 * 
	 * @throws IllegalArgumentException si la línea no tiene ninguno de los dos
	 *                                  formatos
	 */
	static boolean esLista(String linea)
	{
		String[] partes = linea.split(" ");                                              //Divide el String linea en espacios delimitados por " "
		if (partes[0].equals("0:")){                                                    //Revisa si la linea tiene formato Lista de Adyacencia
			return true;
		}
		else if(partes[0].equals("") && !partes[3].equals("")){                                                  //Revisa si la linea tiene formato Matriz de Adyacencia
			return false;
		}
		else{
			throw new IllegalArgumentException("    **No es ni Lista ni Matriz**");              //Si no tiene ninguno, imprime un error
		}

	}
	
	/**Carga la <code>linea</code> de un archivo Lista de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La línea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual será cargada la línea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la línea no es válido
	 */
	private static void cargarLista(String linea, TraductorDesdeLista grafo)
			throws IllegalArgumentException
	{
		
		String[] partes = linea.split(" ");                                             //Separa la linea por espacios
		String[] num = partes[0].split(":");                                               //Separa Vi: en "Vi" y ":"
		if (!partes[0].equals(num[0])){
			throw new IllegalArgumentException("    **El formato de la linea no es valido**");
		}
		int verticeInicial = Integer.parseInt(num[0]);                                  //Toma Vi como Vertice inicial
		grafo.agregarVertice(verticeInicial);                                           //Agrega el vertice a la lista
		int verticeFinal;
		for(int i=1; i<partes.length; i++){
			verticeFinal = Integer.parseInt(partes[i]);
			grafo.agregarArco(verticeInicial, verticeFinal);
		}
	}
	
	/**Carga la <code>linea</code> de un archivo Matriz de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La línea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual será cargada la línea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la línea no es válido
	 */
	private static void cargarMatriz(String linea, TraductorDesdeMatriz grafo)
	{
		String[] partes = linea.split(" ");
		if(partes.length<2){                                                      //Si la linea son puros --------------

		}
		else{
			if(partes[0].equals("")){                                            //Si la linea es la primera linea de el archivo matriz adyacencia

			}
			else{                                  
				int VerticeInicial = Integer.parseInt(partes[0]);                 //Convierte Vi en entero
				for(int i=2;i<partes.length;i++){
					if(partes[i].equals("0")){                                    //Si no tiene arista, no hacer nada
						
					}
					else{
						int veces = Integer.parseInt(partes[i]);                  //Convierte la cantidad de arcos a entero
						for(int j=0;j<veces;j++){                                 //Si existe mas de un arco entre vertices, agrega la cantidad de arcos
							grafo.agregarArco(VerticeInicial, i-2);               //Agrega arco
						}
						 
					}

				}

			}

		}


	}
	
	/**Detecta el número de vértices en un archivo Matriz de Adyacencias 
	 * basado en una muestra de una línea tomada del archivo.
	 * 
	 * @param  linea  La línea del archivo que se desea cargar.
	 * @return        el número de vértices detectado
	 * @throws IllegalArgumentException si el formato de la línea no es válido
	 */
	private static int detectarVertices(String linea)
	{
		String[] partes = linea.split(" ");                                     //Divide el String linea en espacios delimitados por "·"
		System.out.println(partes[0]);
		if(partes[0].equals("")){                                                    //Revisa si el formato de linea es valido.
			int Contador = 0;                                                   //Contador utilizado para Contar Vertices
			for(int i=3; i<partes.length;i++){                                  //Se inicializa i=3 ya que los 3 primeros puestos de el arreglo partes son Strings vacios
				Contador++;                                                               
			}
			return Contador;
		}
		else{
			throw new IllegalArgumentException("La linea leida no es de un archivo Matriz de Adyacencia");
		}
		
		
	}
	
	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link TraductorGrafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>TraductorGrafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre algún error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es válido
	 */
	static TraductorGrafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		TraductorGrafo salida;
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		
		if(esLista(linea)){
			salida = new TraductorDesdeLista();
			do{
				cargarLista(linea, (TraductorDesdeLista)salida);
				linea=Lector.readLine();
			}while(linea != null);
		}else{
			salida = new TraductorDesdeMatriz(detectarVertices(linea));
			do{
				cargarMatriz(linea, (TraductorDesdeMatriz)salida);
				linea=Lector.readLine();
			}while(linea != null);
		}
		
		return salida;
	}
	
	/**Carga el grafo representado en el archivo dado y lo muestra en su
	 * representación alternativa.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo como único elemento
	 * 
	 * @throws IOException              si ocurre algún error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es válido
	 */
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 1){
			System.err.println("Uso: java Cliente <nombreArchivo>");
			return;
		}
		
		TraductorGrafo g = cargarGrafo(args[0]);
		
		System.out.println(g.imprimirGrafoTraducido());
	}
}