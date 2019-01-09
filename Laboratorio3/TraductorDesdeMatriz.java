//Estudiantes:
//Pietro Iaia
//Manuel Guillermo Gil

/**Almacena un grafo que puede crecer dinámicamente para propósitos
 * de tradución a Matriz de Adyacencias. Esta clase está pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeMatriz extends TraductorGrafo{
	
	//ToDo: Debe colocar aquí estructuras de java.util.collections apropiadas
	int[][] MatrizAdyacencia;
	
	/**Crea un grafo con el número de vértices dado
	 * 
	 * @param vertices El número de vértices del grafo
	 */
	TraductorDesdeMatriz(int vertices){
		this.MatrizAdyacencia = new int[vertices][vertices];                                        //Inicializa la matriz de Adyacencia con numero de vertices dado
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		this.MatrizAdyacencia[verticeInicial][verticeFinal] = this.MatrizAdyacencia[verticeInicial][verticeFinal]+1;  //Agrega la Arista a la matriz de adyacencia 
	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		String GrafoTraducido="";
		for(int i=0; i<this.MatrizAdyacencia.length;i++){
            GrafoTraducido = GrafoTraducido+"v"+i+":";
			for(int j=0;j<this.MatrizAdyacencia[i].length;j++){
				if (this.MatrizAdyacencia[i][j]!=0){
					GrafoTraducido = GrafoTraducido+" v"+j;
				}
			}
			GrafoTraducido = GrafoTraducido+"\n";
		}
		return GrafoTraducido;	  
	}
}