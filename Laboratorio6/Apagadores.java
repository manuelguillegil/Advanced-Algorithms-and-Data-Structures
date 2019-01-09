//Estudiantes:
//Pietro Iaia 15-10718
//Manuel Guillermo Gil 14-10397

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class Apagadores{
    static int[] Casa;

    /*Este metodo es utilizado por el metodo cargarGrafo para Cargar las aristas del Grafo
    */
    private static void cargarAristas(String linea, Grafo grafo)
			throws IllegalArgumentException
	{
		String[] vertices = linea.split(" ");                          //Separa la linea por espacios, para tener en vertices: vertice incial en vertices[0] y vertice final en vertices[1]
		int vertice1 = Integer.parseInt(vertices[0]);                                  
		int vertice2 =  Integer.parseInt(vertices[1]);	
		grafo.AgregarArista(vertice1, vertice2);
    }
    


    /*Este metodo es utilizado por el metodo cargarGrafo para Cargar las acciones del problema
    */
    private static void cargarAccion(String linea, Grafo grafo)
			throws IllegalArgumentException
	{
		String[] vertices = linea.split(" ");                          //Separa la linea por espacios, para tener en vertices: vertice incial en vertices[0] y vertice final en vertices[1]
		int vertice1 = Integer.parseInt(vertices[0]);                                  
		int vertice2 =  Integer.parseInt(vertices[1]);	
		grafo.AgregarAcciones(vertice1, vertice2);
	}



    /*Este metodo es utilizado por el main para Cargar el Grafo con los Vertices y sus Arcos
    */
	static Grafo cargarGrafo(String nombreArchivo, Grafo grafo)
			throws IOException
	{
		int n;                                                                   // Corresponde a la cantidad de vértices en el grafo
        int m;                                                                   // Corresponde a la cantidad de aristas en el grafo
        int k;                                                                   // Corresponde al numero de Acciones
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
        String linea = Lector.readLine(); 						                 // Lee la primera línea que corresponde a la cantidad de vertices, aristas y acciones del grafo en el archivo txt
        String[] Partes = linea.split(" ");
		n = Integer.parseInt(Partes[0]);   
        m = Integer.parseInt(Partes[1]);
        k = Integer.parseInt(Partes[2]);

        Casa = new int[n];                                                       //Crea el arreglo que dira cuales habitaciones estan encendidas (Denotadas por un 1) y donde esta Don Opaco (Denotado por un 2)
        


		for(int i = 0; i < n; i++){
			grafo.AgregarVertice();                   			                 //Agrega los vertices al grafo 
		}

		linea = Lector.readLine();			                                     //Pasamos de linea para comenzar a guardar las aristas
		
		for(int i=1; i<=m; i++){
            cargarAristas(linea, (Grafo)grafo);                                  //Aquí añadimos las aristas que nos indica el archivo txt
			linea=Lector.readLine();
        }

        do{
			cargarAccion(linea, (Grafo)grafo);                                   //Aquí añadimos las acciones posibles del problema
			linea=Lector.readLine();
		}while(linea != null);
        
		return grafo;
    }



    public static void main(String[] args)
    throws IOException, IllegalArgumentException
    {
        Grafo grafo = new Grafo();                                                     //Crea el objeto Grafo
        grafo = cargarGrafo(args[0], (Grafo)grafo);                                    //Cargra el Grafo con la informacion leida del archivo .txt
        Casa[0] = 2;                                                                   //Don Opaco esta en la habitacion 1 (pasillo)

        ArrayList<ArrayList<int[]>> Soluciones = new ArrayList<ArrayList<int[]>>();    //ArrayList con todas las soluciones del problema
        ArrayList<ArrayList<int[]>> AccionesVal = new ArrayList<ArrayList<int[]>>();   //ArrayList con todas las Acciones Validas desde cada situacion
        ArrayList<int[]> C = new ArrayList<int[]>();                                   //ArrayList que servira para Guardar el Recorrido actual de el grafo implicito del problema
        C.add(Aplicar(Casa));                                                          //Se agrega el inicio del Recorrido
        Soluciones = BT(Soluciones, AccionesVal, C, Casa, (Grafo)grafo);

        if(Soluciones.size()==0){
            System.out.println("El problema no puede ser resuelto.");
        }
        else{

            /*Ahora se encontrara el Recorrido mas corto con el cual llegamos a una solucion*/
            int min = 99999;
            ArrayList<int[]> MinSol = null;
            for(int i=0; i<Soluciones.size(); i++){
                if(Soluciones.get(i).size()<min){
                    min = Soluciones.get(i).size();
                    MinSol = Soluciones.get(i);
                }
            }

            System.out.println("\nEl problema puede ser resuelto en "+String.valueOf(MinSol.size()-1)+" pasos:");
            /*Comparamos dos situaciones consecutivas para ver que sucede en la casa*/
            for(int i=1; i<MinSol.size();i++){
                for(int j=0; j<MinSol.get(i).length; j++){                                 //Este loop es para recorrer los espacion en el arreglo
                    /*Si se encuentra un cambio en la casa, pasamos a revisar cual fue*/
                    if(MinSol.get(i-1)[j]!=MinSol.get(i)[j]){
                        /*Si Don Opaco Encendio una luz*/
                        if(MinSol.get(i)[j]==1 && MinSol.get(i-1)[j]!=2){
                            System.out.println("Enciende la luz en la habitacion "+String.valueOf(j+1)+".");
                        }
                        /*Si Don Opaco Apago una luz*/
                        else if(MinSol.get(i)[j]==0){
                            System.out.println("Apaga la luz en la habitacion "+String.valueOf(j+1)+".");
                        }
                        /*Si Don Opaco se movio de habitacion*/
                        else if(MinSol.get(i)[j]==2){
                            System.out.println("Muevete a la habitacion "+String.valueOf(j+1)+".");
                        }

                    }

                }
            }
        }
    }



    /*Este sera el metodo donde el Backtracking tomara Lugar. C guardara el recorrido actual por el grafo implicito del problema
    * Si se llega a un Nodo error (nodo donde no es solucion y no tiene Acciones Validas) entonces es eliminado del Recorrido y se
    * regresa en la recursion al nodo anterior en el recorrido y este luego de agotar sus Acciones Validas tambien sera eliminado del
    * recorrido, el programa termina cuando el Recorrido no contenga ningun vertice(Es decir, ya se recorrio por todo camino existente en el grafo implicito). 
    * Si nos encontramos con un recorrido el cual llega a la solucion entonces es guardado en Soluciones y sigue el programa.
    */
    public static ArrayList<ArrayList<int[]>> BT(ArrayList<ArrayList<int[]>> Soluciones, ArrayList<ArrayList<int[]>> AccionesVal, ArrayList<int[]> C, int[] Casa, Grafo grafo){

        /*Revisa si es Solucion, si lo es se agrega al conjunto de soluciones*/
        if(EsSolucion(Casa)){
            Soluciones.add(AgregarCamino(C));
        }

        /*Adquiere las acciones validas para esta situacion en el problema*/
        AccionesVal.add(AccionesValidas(C, Casa, (Grafo)grafo));                                          

        /*Hace este loop para probar cada Accion Valida de la situacion*/
        while(AccionesVal.get(AccionesVal.size()-1).size()!=0){
            /*Aplica el cambio a la casa (Se encendio/apago una luz o Don Opaco se movio de habitacion)*/
            Casa = AccionesVal.get(AccionesVal.size()-1).get(0);
            /*Agrega este cambio en la ArrayList que contiene el Recorrido actual*/
            C.add(Aplicar(Casa));
            /*Remueve la Accion valida para que no repita esta Accion una vez que vuelva por el Backtracking*/
            AccionesVal.get(AccionesVal.size()-1).remove(0);
                                                                                        
            Soluciones = BT(Soluciones, AccionesVal, C, Casa, (Grafo)grafo);
                                                                   
        }
        /*Como el conjunto de acciones validas en la situacion es igual a cero, entonces eliminamos este conjunto*/
        AccionesVal.remove(AccionesVal.size()-1);                              
        /*Si se acabaron las acciones validas desde un nodo, se elimina este del recorrido*/
        C.remove(C.size()-1);                                                 
        return Soluciones;                                                       //Devuelve las soluciones obtenidas hasta este punto
    }



    /*Metodo utilizado por BT para verificar si una situacion es Solucion del problema
    */
    public static Boolean EsSolucion(int[] Casa){
        for(int i=0; i<Casa.length; i++){
            if(Casa[i]==1){
                return false;
            }
        }

        if(Casa[Casa.length-1]==2){
            return true;
        }
        else{
            return false;
        }
    }



    /*Este metodo calculara las Acciones validas desde la habitacion en la que se encuentra Don Opaco 
    * y lo hace de manera que no agregue acciones que provoquen ciclos en el reccorido
    */
    public static ArrayList AccionesValidas(ArrayList<int[]> C, int[] Casa, Grafo grafo){
        Boolean HayIguales;
        ArrayList<int[]> AccionesVal = new ArrayList<int[]>();
        int EstoyEn = -1;

        /*Revisa donde estoy en la casa*/
        for(int i=0; i<Casa.length; i++){
            if(Casa[i]==2){
                EstoyEn = i;
            }
        }
        
        /*Por cada habitacion a la que puedo ir, revisa que se puede hacer*/
        for(int i=0; i<grafo.ListaAdy.get(EstoyEn).size(); i++){
            HayIguales = false;
            
            /*Si la habitacion esta encendida, ir a esa habitacion*/
            if(Casa[grafo.ListaAdy.get(EstoyEn).get(i)]==1){
                Casa[grafo.ListaAdy.get(EstoyEn).get(i)]=2;
                Casa[EstoyEn]=1;

                /*Ahora revisaremos si ya existe una situacion similar en el Recorrido, si ya existe no 
                * lo agrega como Accion valida ya que backtracking no puede tener ciclos*/ 
                for(int k=0; k<C.size(); k++){
                    if(CompararArreglos(C.get(k), Casa)){
                        HayIguales = true;
                    }
                }
                if(!HayIguales){
                    AccionesVal.add(Aplicar(Casa));
                }
                Casa[grafo.ListaAdy.get(EstoyEn).get(i)]=1;
                Casa[EstoyEn]=2;
            }

        }

        /*Por cada habitacion a la que puede apagar/encender Luces, revisa que se puede hacer*/
        for(int i=0; i<grafo.Acciones.get(EstoyEn).size(); i++){
            HayIguales = false;

            /*Si en la habitacion que estoy puedo apagar la luz de otra habitacion, entonces la apago*/
            if(Casa[grafo.Acciones.get(EstoyEn).get(i)]==1){
                Casa[grafo.Acciones.get(EstoyEn).get(i)]=0;

                /*Ahora revisaremos si ya existe una situacion similar en el Recorrido, si existe no 
                * lo agrega como Accion valida ya que backtracking no puede tener ciclos*/ 
                for(int k=0; k<C.size(); k++){
                    if(CompararArreglos(C.get(k), Casa)){
                        HayIguales = true;
                    }
                }
                if(!HayIguales){
                    AccionesVal.add(Aplicar(Casa));
                }
                Casa[grafo.Acciones.get(EstoyEn).get(i)]=1;
            }

            /*Si en la habitacion que estoy puedo encender la luz de otra habitacion, entonces la enciendo*/
            else if(Casa[grafo.Acciones.get(EstoyEn).get(i)]==0){
                Casa[grafo.Acciones.get(EstoyEn).get(i)]=1;

                /*Ahora revisaremos si ya existe una situacion similar en el Recorrido, si existe no 
                * lo agrega como Accion valida ya que backtracking no puede tener ciclos*/
                for(int k=0; k<C.size(); k++){
                    if(CompararArreglos(C.get(k), Casa)){
                        HayIguales = true;
                    }
                }
                if(!HayIguales){
                    AccionesVal.add(Aplicar(Casa));
                }
                Casa[grafo.Acciones.get(EstoyEn).get(i)]=0;
            }
        }
        return AccionesVal;                                                      //Retorna el conjunto de Acciones Validas para esta situacion
    }



    /*Este es un metodo usado por el metodo AccionesValidas para verificar si dos arreglos son iguales, si son diferentes devuelve false
    */
    public static Boolean CompararArreglos(int[] C, int[] Casa){
        for(int i=0; i<C.length; i++){
            //System.out.println(C[i]);
            //System.out.println(Casa[i]);

            if(C[i]!=Casa[i]){
                return false;
            }
        }
        return true;
    }



    /*Como el ArrayList guarda un identificador al elemento, debemos crear una nueva instancia de casa para que cuando modifiquemos el arreglo Casa
    * este no cambie dentro del ArrayList.
    */
    public static int[] Aplicar(int[]Casa){
        int[] Casa2 = new int[Casa.length];
        for(int i=0; i<Casa.length; i++){
            Casa2[i] = Casa[i];
        }
        return Casa2;
    }



    /*Como el ArrayList guarda un identificador al elemento, debemos crear una nueva instancia de C para que cuando modifiquemos el ArrayList C
    * este no cambie dentro del ArrayList Soluciones.
    */
    public static ArrayList<int[]> AgregarCamino(ArrayList<int[]> C){
        ArrayList<int[]> C2= new ArrayList<int[]>();
        for(int i=0; i<C.size(); i++){
            C2.add(C.get(i));
        }
        return C2;
    }
}