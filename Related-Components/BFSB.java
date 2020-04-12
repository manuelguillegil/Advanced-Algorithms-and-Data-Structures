//Estudiantes:
//Pietro Iaia 15-10718
//Manuel Guillermo Gil 14-10397

import java.util.*;

class BFSB{

    int[] P;                                                                     //Areglo de Predecesores
    int[] D;                                                                     //Arreglo de Ordinales

    /*Este codigo es Utilizado cuando el Usuario ingrese como --ord o --pred
    * int V0: El vertice inicial del recorrido
    * String arg: Indica que funcion debe ejecutarse
    * int Num: Cota de profundidad de recorrido
    * Grafo grafo: contiene la informacion del grafo
    */
    public int[] Inicio(int V0, String arg, int Num, Grafo grafo){
        LinkedList<Integer> cola = new LinkedList<Integer>();                    // Cola usada por BFS

        if(arg.equals("pred")){

            int Time = 0;
            P = new int[grafo.ListaAdy.size()];                                  // Inicializa cada arreglo                            
            D = new int[grafo.ListaAdy.size()];                                  //
            
            for(int i=0; i<grafo.ListaAdy.size(); i++){
				P[i] = -1;
				D[i] = -1;
            }
            
            P[V0]=V0;                                                            //El predecesor de el vertice inicial es el mismo
            cola.offer(V0);                                                      //Agrega el vertice inicial en la cola usada por BFS
            BFS(cola, Num, Time, grafo); 
            return P;                                                            //Restorna el arreglo de los predecesores
        }

        else{

            int Time = 0;
            P = new int[grafo.ListaAdy.size()];                                  // Inicializa cada arreglo                               
            D = new int[grafo.ListaAdy.size()];                                  //
            
            for(int i=0; i<grafo.ListaAdy.size(); i++){
				P[i] = -1;
				D[i] = -1;
            }
            
            P[V0]=V0;                                                            //El predecesor de el vertice inicial es el mismo
            cola.offer(V0);                                                      //Agrega el vertice inicial en la cola usada por BFS
            BFS(cola, Num, Time, grafo);
            return D;                                                            //Restorna el arreglo de los Ordinales
        }
    }


    /*Este codigo es Utilizado cuando el Usuario ingrese como --arb y quiera imprimir el arbol
    * int V0: El vertice inicial del recorrido
    * String arg: Indica que funcion debe ejecutarse
    * int Num: Cota de profundidad de recorrido
    * Grafo grafo: contiene la informacion del grafo
    */
    public void InicioPrint(int V0, String arg, int Num, Grafo grafo){
        LinkedList<Integer> cola = new LinkedList<Integer>();                    // Cola usada por BFS
                             
		P = new int[grafo.ListaAdy.size()];                                      // Inicializa el Arreglo de Colores

        for(int i=0; i<grafo.ListaAdy.size(); i++){
            P[i] = -1;
        }

        String Espacio = "";                                                     //String que contiene la identacion necesaria para cada nivel
        cola.offer(V0);                                                          //Agrega el vertice inicial en la cola usada por BFS
        P[V0]=V0;                                                                //El predecesor de el vertice inicial es el mismo
        System.out.println(String.valueOf(0)+" - "+String.valueOf(0)+" (Raiz)");
        BFSPrint(cola, Espacio, Num, grafo);
    }

    /*Este metodo es BFS para cuando no necesitemos imprimir el recorrido
    */
    private int BFS(LinkedList<Integer> cola, int Num, int Time, Grafo grafo){
        int ContadorNiv = 0;                                                     //Este sera el contador de Niveles de profundidad
        int NumVPorNiv = 1;                                                      //Numero de vertices por nivel
        int NumVPorNivSig = 0;                                                   //Numero de vertices del siguiente nivel
        int v;                                                                   //Variable que contendra un vertice

        while(cola.size() != 0){
            v = cola.poll();
            D[v] = Time;                                                         //Este sera el arreglo donde tendremos el tiempo en el cual fue descubierto un vertice
            Time = Time + 1;
            NumVPorNiv--;                                                        //Falta 1 vertice menos por revsar en el nivel dado                            
            if(ContadorNiv<Num){                                                 //Si el recorrido llega a la profundidad cota, no sigue el recorrido
                for(int w=0; w<grafo.ListaAdy.get(v).size(); w++){
                    if(P[grafo.ListaAdy.get(v).get(w)]==-1){
                        P[grafo.ListaAdy.get(v).get(w)] = v;
                        cola.offer(grafo.ListaAdy.get(v).get(w));                //Ingresa en la cola cada vertice de la capa actual
                        NumVPorNivSig++;                                         //Se encontro un nuevo vertice en el nivel siguiente
                    }
                }
                if(NumVPorNiv==0){                                               //Si el numero de vertices que faltan por revisar en el nivel es igual a 0, se salta al proximo nivel
                    ContadorNiv++;                                               //Luego de que se abren todos los vertices de una capa, se aumenta el numero de profundidad
                    NumVPorNiv = NumVPorNivSig;
                    NumVPorNivSig = 0;
                }
            }
		}
		return Time;
    }

    /*Este metodo es BFS para cuando necesitemos imprimir el recorrido
    */
    private void BFSPrint(LinkedList<Integer> cola, String Espacio, int Num, Grafo grafo){

        int ContadorNiv = 0;                                                     //Este sera el contador de Niveles de profundidad
        int NumVPorNiv = 1;                                                      //Numero de vertices por nivel
        int NumVPorNivSig = 0;                                                   //Numero de vertices del siguiente nivel
        int v;                                                                   //Variable que contendra un vertice

        while(cola.size() != 0){
            v = cola.poll();
            NumVPorNiv--;                                                        //Falta 1 vertice menos por revsar en el nivel dado
            if(ContadorNiv<Num){                                                 //Si el recorrido llega a la profundidad cota, no sigue el recorrido
                for(int w=0; w<grafo.ListaAdy.get(v).size(); w++){
                    if(P[grafo.ListaAdy.get(v).get(w)]==-1){
                        P[grafo.ListaAdy.get(v).get(w)] = v;
                        System.out.println(Espacio+String.valueOf(v)+" - "+String.valueOf(grafo.ListaAdy.get(v).get(w)));
                        cola.offer(grafo.ListaAdy.get(v).get(w));                //Ingresa en la cola cada vertice de la capa actual
                        NumVPorNivSig++;                                         //Se encontro un nuevo vertice en el nivel siguiente
                    }
                }
                if(NumVPorNiv==0){                                               //Si el numero de vertices que faltan por revisar en el nivel es igual a 0, se salta al proximo nivel
                    Espacio = Espacio + "   ";
                    ContadorNiv++;                                               //Luego de que se abren todos los vertices de una capa, se aumenta el numero de profundidad
                    NumVPorNiv = NumVPorNivSig;
                    NumVPorNivSig = 0;
                }
            }
		}
    }
}