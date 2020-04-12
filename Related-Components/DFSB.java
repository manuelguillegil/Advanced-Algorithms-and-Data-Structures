//Estudiantes:
//Pietro Iaia 15-10718
//Manuel Guillermo Gil 14-10397

import java.util.*;

class DFSB{

    int[] P;                                                                     //Areglo de Predecesores
    String[] C;                                                                  //Arreglo de Colores
    int[] D;                                                                     //Arreglo de Ordinales

    /*Este codigo es Utilizado cuando el Usuario ingrese como --ord o --pred
    * int V0: El vertice inicial del recorrido
    * String arg: Indica que funcion debe ejecutarse
    * int Num: Cota de profundidad de recorrido
    * Grafo grafo: contiene la informacion del grafo
    */
    public int[] Inicio(int V0, String arg, int Num, Grafo grafo){
        if(arg.equals("pred")){

            int Time = 0;
            P = new int[grafo.ListaAdy.size()];                                  //
			C = new String[grafo.ListaAdy.size()];                               // Inicializa cada arreglo                             
            D = new int[grafo.ListaAdy.size()];                                  //
            
            for(int i=0; i<grafo.ListaAdy.size(); i++){
				P[i] = -1;
				D[i] = -1;
				C[i] = "White";
            }
            
            P[V0]=V0;                                                            //El predecesor de el vertice inicial es el mismo
            DFS(Num, 0, V0, Time, grafo);
            return P;                                                            //Retorna el arreglo de Predecesores
        }

        else{

            int Time = 0;
            P = new int[grafo.ListaAdy.size()];                                  //
			C = new String[grafo.ListaAdy.size()];                               // Inicializa cada arreglo                             
            D = new int[grafo.ListaAdy.size()];                                  //
            
            for(int i=0; i<grafo.ListaAdy.size(); i++){
				P[i] = -1;
				D[i] = -1;
				C[i] = "White";
            }
            
            P[V0]=V0;                                                            //El predecesor de el vertice inicial es el mismo
            DFS(Num, 0, V0, Time, grafo);
            return D;                                                            //Retorna el arreglo de Ordinales

        }
    }


    /*Este codigo es Utilizado cuando el Usuario ingrese como --arb y quiera imprimir el arbol
    * int V0: El vertice inicial del recorrido
    * String arg: Indica que funcion debe ejecutarse
    * int Num: Cota de profundidad de recorrido
    * Grafo grafo: contiene la informacion del grafo
    */
    public void InicioPrint(int V0, String arg, int Num, Grafo grafo){
                             
		C = new String[grafo.ListaAdy.size()];                                   // Inicializa el Arreglo de Colores

        for(int i=0; i<grafo.ListaAdy.size(); i++){
            C[i] = "White";
        }

        String Espacio = "";                                                     //String que contiene la identacion necesaria para cada nivel
        System.out.println(String.valueOf(0)+" - "+String.valueOf(0)+" (Raiz)");
        DFSPrint(Espacio, Num, 0, V0, grafo);
    }

    /*Este metodo es DFS para cuando no necesitemos imprimir el recorrido
    */
    private int DFS(int Num, int ContadorNiv, int v, int Time, Grafo grafo){

        if(ContadorNiv<Num){                                                     //Si el recorrido llega a la profundidad cota, no sigue el recorrido
            D[v] = Time;                                                         //Este sera el arreglo donde tendremos el tiempo en el cual fue descubierto un vertice
            Time = Time + 1;
		    C[v] = "Gray";
		    for(int i=0; i<grafo.ListaAdy.get(v).size(); i++){
			    if (C[grafo.ListaAdy.get(v).get(i)].equals("White")){
                    P[grafo.ListaAdy.get(v).get(i)] = v;
                    ContadorNiv++;
                    Time = DFS(Num, ContadorNiv, grafo.ListaAdy.get(v).get(i), Time, grafo);
                    ContadorNiv--;
			    }  
		    }
        }
        C[v] = "Black";
        return Time;
    }

    /*Este metodo es DFS para cuando necesitemos imprimir el recorrido
    */
    private void DFSPrint(String Espacio, int Num, int ContadorNiv, int v, Grafo grafo){

        if(ContadorNiv<Num){                                                     //Si el recorrido llega a la profundidad cota, no sigue el recorrido
            Espacio = Espacio+" ";
		    C[v] = "Gray";
		    for(int i=0; i<grafo.ListaAdy.get(v).size(); i++){
			    if (C[grafo.ListaAdy.get(v).get(i)].equals("White")){
                    System.out.println(Espacio+String.valueOf(v)+" - "+String.valueOf(grafo.ListaAdy.get(v).get(i)));
                    ContadorNiv++;
                    DFSPrint(Espacio, Num, ContadorNiv, grafo.ListaAdy.get(v).get(i), grafo);
                    ContadorNiv--;
			    }  
		    }
        }
        C[v] = "Black";
    }


}