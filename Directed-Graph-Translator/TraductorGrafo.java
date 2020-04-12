/**Almacenador de grafos para propósitos de traducción.
 * Esta es una clase padre para traductores de grafos.
 */
public abstract class TraductorGrafo{
	/**Almacena el grafo como una matriz de adyacencias
	 */
	protected int[][] grafo;
	
	/**Agrega un arco entre los vértices dados. Si el arco ya existe, el
	 * método no hace nada.
	 * 
	 * @param verticeInicial Vértice del cual sale el arco
	 * @param verticeFinal   Vérice al cual llega el arco
	 */
	public abstract void agregarArco(int verticeInicial, int verticeFinal);

	/**Imprime el grafo en el formato de destino.
	 * @return un <code>String</code> mostrando el grafo en la representación
	 *         deseada
	 */
	public abstract String imprimirGrafoTraducido();
}