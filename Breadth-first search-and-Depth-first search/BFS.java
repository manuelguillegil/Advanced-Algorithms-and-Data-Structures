/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

class BFS{

	public int[] Inicio(int v, Grafo grafo){

		int[] P = new int[grafo.ListaAdy.size()];                                //Crea el arreglo que devolvera DFS
		LinkedList<Integer> cola = new LinkedList<Integer>();           //Crea la cola que tendra el orden con que se tomaran los vertices

		for(int i=0; i<grafo.ListaAdy.size(); i++){        
			P[i] = -1;                                                  //Se inicializa el arreglo en -1.
		}
		P[v] = v;                       
		cola.offer(v); 

		while(cola.size() != 0){
			
			v = cola.poll();
			for(int w=0; w<grafo.ListaAdy.get(v).size(); w++){
				if(P[grafo.ListaAdy.get(v).get(w)]==-1){
					P[grafo.ListaAdy.get(v).get(w)] = v;
					cola.offer(grafo.ListaAdy.get(v).get(w));
				}
			}

		}
		return P;
	}

}