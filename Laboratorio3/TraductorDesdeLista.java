//Estudiantes:
//Pietro Iaia
//Manuel Guillermo Gil

/**Almacena un grafo que puede crecer dinámicamente para propósitos
 * de tradución a Matriz de Adyacencias. Esta clase está pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
import java.util.*;
public class TraductorDesdeLista extends TraductorGrafo{

	//ToDo: Debe colocar aquí estructuras de java.util.collections apropiadas
	ArrayList<ArrayList<Integer>> ListaInicial;
	
	/**Crea un grafo minimal*/
	TraductorDesdeLista(){
		this.ListaInicial  = new ArrayList<ArrayList<Integer>>();                                 //Inicia la Lista de Adyacencia 
	}
	
	/**Agrega un vértice al grafo. Si el vértice ya existe, el método no hace
	 * nada.
	 * 
	 * @param id El número del vértice que se desea agregar
	 */
	public void agregarVertice(int id){
		for(int i=0; i<this.ListaInicial.size(); i++){
			if(this.ListaInicial.get(i).get(0)==id){                                                                            //Revisa si el vertice ya existe, y si lo hace entonces no hace nada.
				return;
			}
		}
		ArrayList<Integer> ListaLados = new ArrayList<Integer>();                                 //Crea la lista de adyacencia de este vertice
		ListaLados.add(id);
		this.ListaInicial.add(ListaLados);                                                        //Agrega la Lista de adyacencia a la Lista Inicial
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		for(int i=0; i<this.ListaInicial.size(); i++){                                            //Busca donde esta el vertice Inicial
			if (this.ListaInicial.get(i).get(0)==verticeInicial){                                 //Si lo encuentra, le agrega el lado
				this.ListaInicial.get(i).add(verticeFinal);
			}

		}
		
	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		

		int[] OrdenVertices = new int[this.ListaInicial.size()];
		String GrafoTraducido = "   ";
		Boolean ExisteLado;
		for(int i=0; i<this.ListaInicial.size(); i++){
			GrafoTraducido = GrafoTraducido + Integer.toString(this.ListaInicial.get(i).get(0)) + " ";             //Agrega la primera linea de la matriz
			OrdenVertices[i] = this.ListaInicial.get(i).get(0);                                                    //Orden en el que van los vertices (dado por la lista)

		}
		GrafoTraducido = GrafoTraducido + "\n";                                                                    //Salta de linea

		for(int i=0; i<this.ListaInicial.size(); i++){
			GrafoTraducido = GrafoTraducido + "--";                                                                //Agrega los 2n --
		}
		GrafoTraducido = GrafoTraducido +"-- \n";                                                                  //Agrega los ultimos 2 -- y salta de linea

		for(int i=0; i<this.ListaInicial.size(); i++){
			GrafoTraducido = GrafoTraducido + Integer.toString(this.ListaInicial.get(i).get(0)) + " | ";           //Agrega el vertice Inicial de la forma "vi | "

			for(int k=0; k<OrdenVertices.length; k++){                                                             
				ExisteLado = false;
				for(int j=1; j<this.ListaInicial.get(i).size(); j++){

					if(this.ListaInicial.get(i).get(j)==OrdenVertices[k]){                                         //Revisa en orden (dado por la lista) si existe el lado..
						ExisteLado = true;                                                                         //..(verticeInicial,VerticeFinal)
					}
				}
				if(ExisteLado==true){                                                                              //Si existe el lado, pone 1 en la posicion..
					GrafoTraducido = GrafoTraducido + "1 ";                                                        //.."verticeInicial,VerticeFinal" en la matriz
				}
				else{
					GrafoTraducido = GrafoTraducido + "0 ";                                                        //Si no existe el lado, pone 0 en la posicion.. 
				}                                                                                                  //.."verticeInicial,VerticeFinal" en la matriz

			}			
			GrafoTraducido = GrafoTraducido + "\n";
		}
		return GrafoTraducido;
	}
}