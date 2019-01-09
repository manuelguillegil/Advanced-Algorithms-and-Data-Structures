/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;

class DFS{

	int[] P;

	public int[] Inicio(int v, Grafo grafo){
		P = new int[grafo.ListaAdy.size()];

		for(int i=0; i<grafo.ListaAdy.size(); i++){
			P[i] = -1;
		}
		
		P[v] = v;
		DFS_rec(v, grafo);
		return P;
	}

	private void DFS_rec(int v, Grafo grafo){

		for(int w=0; w<grafo.ListaAdy.get(v).size(); w++){
			if(P[grafo.ListaAdy.get(v).get(w)]==-1){
				P[grafo.ListaAdy.get(v).get(w)] = v;
				DFS_rec(grafo.ListaAdy.get(v).get(w), grafo);
			}
		}
	}

}