//Estudiantes:
//Pietro Iaia 15-10718
//Manuel Guillermo Gil 14-10397

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class DeepWeb{

    /*Este metodo es utilizado por el metodo cargarGrafo para Cargar los Arcos del Grafo
    */
    private static void cargarArco(String linea, Grafo grafo)
			throws IllegalArgumentException
	{
		String[] vertices = linea.split(" ");                          //Separa la linea por espacios, para tener en vertices: vertice incial en vertices[0] y vertice final en vertices[1]
		int vertice1 = Integer.parseInt(vertices[0]);                                  
		int vertice2 =  Integer.parseInt(vertices[1]);	
		grafo.AgregarArco(vertice1, vertice2);
	}


    /*Este metodo es utilizado por el main para Cargar el Grafo con los Vertices y sus Arcos
    */
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
			cargarArco(linea, (Grafo)grafo);                 // Aquí añadimos las aristas que nos indica el archivo txt
			linea=Lector.readLine();
		}while(linea != null);
		
		return grafo;
    }



    public static void main(String[] args)
        throws IOException, IllegalArgumentException
    {
    Grafo grafo = new Grafo();                                         //Crea el objeto Grafo
    grafo = cargarGrafo(args[0], (Grafo)grafo);
    int V0 = Integer.valueOf(args[1]);                                 //Obtiene el vertice inicial del recorrido

    /*Si el usuario quiere hacer el recorrido por medio de dfs, se ejecuta este codigo
    */
    if(args[2].equals("dfs")){
        
        DFSB DFS = new DFSB();                                                   //Crea el objeto DFS

        /*Aqui ira la parte del codigo que se ejecuta si el Usuario ingresa --trun
        */
        if(args[3].equals("--trunc")){
            int Num = Integer.parseInt(args[4]);                                 //Toma el numero con el cual se va a truncar

            int[] Conex;
            Conex = DFS.Inicio(V0, "pred", Num, (Grafo)grafo);                   //Busca los predecesores para luego buscar los que no tengan y agregarlos a la lista DeepWeb

            ArrayList<Integer> DeepW = new ArrayList<Integer>();                 //
            for(int j=0; j<Conex.length; j++){                                   //Encuentra que nodos son partes de la DeepWeb
                if(Conex[j]==-1){                                                //
                    DeepW.add(j);                                                //
                } 
            }

            if(DeepW.size()==0){                                                 //Si el conjunto de vertices en la DeepWeb es vacio, imprime que todas las paginas son del internet visible
                System.out.println("\nTodas las paginas son parte de la internet visible.");
            }
            else{
                System.out.println(DeepW);                                       //Si el conjunto de vertices en la DeepWeb no es vacio, Imprime los Vertices pertenecientes a la DeepWeb
            }

            for(int i=0; i<args.length; i++){
                /*Si el Usuario ingresa --arb, se ejecuta este codigo
                */
                if(args[i].equals("--arb")){
                    System.out.println("\nArbol:");
                    DFS.InicioPrint(V0, "arb", Num, (Grafo)grafo);
                }

                /*Si el Usuario ingresa --pred, se ejecuta este codigo
                */
                else if(args[i].equals("--pred")){
                    System.out.println("\nPredecesores:");
                    int[] Pred;
                    Pred = DFS.Inicio(V0, "pred", Num, (Grafo)grafo);

                    for(int j=0; j<Pred.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Pred[j]));
                    }

                }

                /*Si el Usuario ingresa --ord, se ejecuta este codigo
                */
                else if(args[i].equals("--ord")){
                    System.out.println("\nOrdinales:");
                    int[] Ord;
                    Ord = DFS.Inicio(V0, "ord", Num, (Grafo)grafo);

                    for(int j=0; j<Ord.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Ord[j]));
                    }

                }
            }
        }

        /*Aqui ira la parte del codigo que se ejecuta si el Usuario no ingresa --trun y Num sera 999999999 ya que no hay cota de profundidad en el recorrido
        */
        else{

            int[] Conex;
            Conex = DFS.Inicio(V0, "pred", 999999999, (Grafo)grafo);             //Busca los predecesores para luego buscar los que no tengan y agregarlos a la lista DeepWeb

            ArrayList<Integer> DeepW = new ArrayList<Integer>();                 //
            for(int j=0; j<Conex.length; j++){                                   //Encuentra que nodos son partes de la DeepWeb
                if(Conex[j]==-1){                                                //
                    DeepW.add(j);                                                //
                } 
            }

            if(DeepW.size()==0){                                                 //Si el conjunto de vertices en la DeepWeb es vacio, imprime que todas las paginas son del internet visible
                System.out.println("\nTodas las paginas son parte de la internet visible.");
            }
            else{
                System.out.println(DeepW);                                       //Si el conjunto de vertices en la DeepWeb no es vacio, Imprime los Vertices pertenecientes a la DeepWeb
            }

            for(int i=0; i<args.length; i++){
                /*Si el Usuario ingresa --arb, se ejecuta este codigo
                */
                if(args[i].equals("--arb")){
                    System.out.println("\nArbol:");
                    DFS.InicioPrint(V0, "arb", 999999999, (Grafo)grafo);                         
                }

                /*Si el Usuario ingresa --pred, se ejecuta este codigo
                */
                else if(args[i].equals("--pred")){
                    System.out.println("\nPredecesores:");
                    int[] Pred;
                    Pred = DFS.Inicio(V0, "pred", 999999999, (Grafo)grafo);

                    for(int j=0; j<Pred.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Pred[j]));
                    }
                }

                /*Si el Usuario ingresa --ord, se ejecuta este codigo
                */
                else if(args[i].equals("--ord")){
                    System.out.println("\nOrdinales:");
                    int[] Ord;
                    Ord = DFS.Inicio(V0, "ord", 999999999, (Grafo)grafo);

                    for(int j=0; j<Ord.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Ord[j]));
                    }
                }
            }
        }
    }

    /*Si el usuario quiere hacer el recorrido por medio de bfs, se ejecuta este codigo
    */
    else if(args[2].equals("bfs")){
        BFSB BFS = new BFSB();                                                   //Crea el objeto BFS

        /*Aqui ira la parte del codigo que se ejecuta si el Usuario ingresa --trun
        */
        if(args[3].equals("--trunc")){
            int Num = Integer.parseInt(args[4]);                                 //Toma el numero con el cual se va a truncar

            int[] Conex;
            Conex = BFS.Inicio(V0, "pred", Num, (Grafo)grafo);                   //Busca los predecesores para luego buscar los que no tengan y agregarlos a la lista DeepWeb

            ArrayList<Integer> DeepW = new ArrayList<Integer>();                 //
            for(int j=0; j<Conex.length; j++){                                   //Encuentra que nodos son partes de la DeepWeb
                if(Conex[j]==-1){                                                //
                    DeepW.add(j);                                                //
                } 
            }

            if(DeepW.size()==0){                                                 //Si el conjunto de vertices en la DeepWeb es vacio, imprime que todas las paginas son del internet visible
                System.out.println("\nTodas las paginas son parte de la internet visible.");
            }
            else{
                System.out.println(DeepW);                                       //Si el conjunto de vertices en la DeepWeb no es vacio, Imprime los Vertices pertenecientes a la DeepWeb
            }

            for(int i=0; i<args.length; i++){
                /*Si el Usuario ingresa --arb, se ejecuta este codigo
                */
                if(args[i].equals("--arb")){
                    System.out.println("\nArbol:");
                    BFS.InicioPrint(V0,"arb", Num, (Grafo)grafo);
                }

                /*Si el Usuario ingresa --pred, se ejecuta este codigo
                */
                else if(args[i].equals("--pred")){
                    System.out.println("\nPredecesores:");
                    int[] Pred;
                    Pred = BFS.Inicio(V0, "pred", Num, (Grafo)grafo);

                    for(int j=0; j<Pred.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Pred[j]));
                    }

                }

                /*Si el Usuario ingresa --ord, se ejecuta este codigo
                */
                else if(args[i].equals("--ord")){
                    System.out.println("\nOrdinales:");
                    int[] Ord;
                    Ord = BFS.Inicio(V0, "ord", Num, (Grafo)grafo);

                    for(int j=0; j<Ord.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Ord[j]));
                    }

                }
            }
        }
        
        /*Aqui ira la parte del codigo que se ejecuta si el Usuario no ingresa --trun y Num sera 999999999 ya que no hay cota de profundidad en el recorrido
        */
        else{

            int[] Conex;
            Conex = BFS.Inicio(V0, "pred", 999999999, (Grafo)grafo);             //Busca los predecesores para luego buscar los que no tengan y agregarlos a la lista DeepWeb

            ArrayList<Integer> DeepW = new ArrayList<Integer>();                 //
            for(int j=0; j<Conex.length; j++){                                   //Encuentra que nodos son partes de la DeepWeb
                if(Conex[j]==-1){                                                //
                    DeepW.add(j);                                                //
                } 
            }

            if(DeepW.size()==0){                                                 //Si el conjunto de vertices en la DeepWeb es vacio, imprime que todas las paginas son del internet visible
                System.out.println("\nTodas las paginas son parte de la internet visible.");
            }
            else{
                System.out.println(DeepW);                                       //Si el conjunto de vertices en la DeepWeb no es vacio, Imprime los Vertices pertenecientes a la DeepWeb
            }

            for(int i=0; i<args.length; i++){
                /*Si el Usuario ingresa --arb, se ejecuta este codigo
                */
                if(args[i].equals("--arb")){
                    System.out.println("\nArbol:");
                    BFS.InicioPrint(V0, "arb", 999999999, (Grafo)grafo);                         
                }

                /*Si el Usuario ingresa --pred, se ejecuta este codigo
                */
                else if(args[i].equals("--pred")){
                    System.out.println("\nPredecesores:");
                    int[] Pred;
                    Pred = BFS.Inicio(V0, "pred", 999999999, (Grafo)grafo);

                    for(int j=0; j<Pred.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Pred[j]));
                    }
                }

                /*Si el Usuario ingresa --ord, se ejecuta este codigo
                */
                else if(args[i].equals("--ord")){
                    System.out.println("\nOrdinales:");
                    int[] Ord;
                    Ord = BFS.Inicio(V0, "ord", 999999999, (Grafo)grafo);

                    for(int j=0; j<Ord.length; j++){
                        System.out.println(String.valueOf(j)+": "+String.valueOf(Ord[j]));
                    }
                }
            }

        }

    }

    }
}