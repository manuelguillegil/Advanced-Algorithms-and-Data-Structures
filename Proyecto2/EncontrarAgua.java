import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class EncontrarAgua{

    /*Este metodo es utilizado para Cargar los vertices y aristas en el Grafo Base del problema, lo hace leyendo el archivo .txt que contiene los detalles del Grafo
    */
    static GrafoNoDirigido CargarGrafo(String nombreArchivo, GrafoNoDirigido grafo)
			throws IOException
	{
		int n;                                                                   // Corresponde a la cantidad de vértices en el grafo
        int m;                                                                   // Corresponde a la cantidad de aristas en el grafo

		try{
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
        String linea = Lector.readLine(); 						                 
        n = Integer.parseInt(linea); 
        linea = Lector.readLine();
        m = Integer.parseInt(linea);
        
        String[] Partes;

        /*Carga los Vertices en el Grafo Base*/
		for(int i = 0; i < n; i++){
            linea = Lector.readLine();
            Partes = linea.split(" ");
            Vertice v = new Vertice();
            v.CrearVertice(Partes[0], Integer.parseInt(Partes[1]), Double.valueOf(Partes[2]));
            grafo.AgregarVertice(v);
        }
        
		/*Carga las aristas en el Grafo Base*/
		for(int i=0; i<m; i++){
            linea = Lector.readLine();
            Partes = linea.split(" ");
            Arista a = new Arista();
            a.CrearArista(i, Integer.parseInt(Partes[2]), Double.valueOf(Partes[3]), Partes[0], Partes[1]);
            grafo.AgregarArista(a);
        }

        }catch(IOException e){
            System.out.println("El archivo con el Grafo base del problema no existe o no es formato .txt");
            System.exit(1);
        }catch(ArrayIndexOutOfBoundsException e2){
            System.out.println("Ocurrio un problema leyendo el Archivo con el Grafo base del problema");
            System.exit(1);
        }catch(NumberFormatException e3){
            System.out.println("Ocurrio un problema leyendo el Archivo con el Grafo base del problema");
            System.exit(1);
        }
        return grafo;
    }

    /*En este metodo se recibe el nombre del archivo para poder abrirlo, el Grafo base de el Campus y la Lista de Grafos de los diferentes Casos
    * Dentro de este primero se encarga de clonear el Grafo Base en el Grafo Alterno (El grafo caso). Luego de esto abre el archivo .txt que contiene los
    * Casos del problema y modifica el Grafo Alterno con lo escrito en el archivo .txt y lo agrega a la Lista de Grafos Casos. Luego revisa si aun existen 
    * Casos por tomar en cuenta, si los hay, repite el proceso hasta no tener mas Casos por tomar en cuenta.
    */
    public static void CargarCasos(String nombreArchivo, GrafoNoDirigido Grafo, ArrayList<GrafoNoDirigido> GrafosCasos)
            throws IOException
    {

        try{
        BufferedReader Lector = new BufferedReader(
                new FileReader(nombreArchivo));
        int n;
        int m;
        Boolean AunHayCasos = true;                                                                //Esta Variable sera utilizada para evaluar si aun existen casos o no

        String linea =  Lector.readLine();;
        while(AunHayCasos){
            /*Se crea el Grafo de el Caso*/
            GrafoNoDirigido GrafoAlt = new GrafoNoDirigido();
            GrafoAlt.CrearGrafoNoDirigido(linea);

            /*Se crea un nuevo grafo a partir del anterior, no podemos solo copiar y pegar el mismo grafo ya que tendrian los mismos apuntadores de Vertices y Aristas*/
            for(int i=0; i<Grafo.Vertices.size(); i++){
                Vertice v = new Vertice();                                                                                               //Se crea el nuevo vertice
                v.CrearVertice(Grafo.Vertices.get(i).GetId(), Grafo.Vertices.get(i).GetCapacidad(), Grafo.Vertices.get(i).GetPeso());    //Se llama al constructor con los mismos datos del vertice del Grafo Base
                GrafoAlt.AgregarVertice(v);                                                                                              //Se agrega el Vertice al GrafoAlt
            }
            for(int i=0; i<Grafo.Aristas.size(); i++){
                Arista a = new Arista();                                                                                             //Se crea la nueva arista
                a.CrearArista(Grafo.Aristas.get(i).GetId(), Grafo.Aristas.get(i).GetCapacidad(), Grafo.Aristas.get(i).GetPeso(), Grafo.Aristas.get(i).GetExtremo1(), Grafo.Aristas.get(i).GetExtremo2());
                GrafoAlt.AgregarArista(a);                                                                                           //Se agrega la Arista al GrafoAlt
            }
        
            /*Ahora leeremos el archivo de Casos para hacer los cambios necesarios al GrafoAlt*/
            linea = Lector.readLine();
            n = Integer.parseInt(linea);                                                                                //Numero de Vertices del Caso
            linea = Lector.readLine();
            m = Integer.parseInt(linea);                                                                                //Numero de Aristas del Caso

            /*Primero cambiaremos los pesos de los Vertices identificados en el archivo .txt*/
            String[] Partes;
            for(int i=0; i<n; i++){
                linea = Lector.readLine();
                Partes = linea.split(" ");                                                                                  
                for(int j=0; j<GrafoAlt.Vertices.size(); j++){
                    if(GrafoAlt.Vertices.get(j).GetId().equals(Partes[0])){ 
                        if(Partes.length==2){                                                                                 //Se da cuenta si hay un cambio en el peso
                            GrafoAlt.Vertices.get(j).SetPeso(Grafo.Vertices.get(j).GetPeso() + Double.valueOf(Partes[1]));
                        }
                        GrafoAlt.Vertices.get(j).SetAgua();                                                                   //Este edificio tendra agua
                    }
                }
            }

            /*Ahora Eliminaremos las aristas del GrafoAlt identificadas en el archivo .txt*/
            for(int i=0; i<m; i++){
                linea = Lector.readLine();
                for(int k=0; k<GrafoAlt.Aristas.size(); k++){
                    if(GrafoAlt.Aristas.get(k).GetId() == Integer.parseInt(linea)){      //Si el identificador de la arista es igual al identificador leido del archivo .txt, pasa a eliminarlo
                        GrafoAlt.Aristas.remove(k);
                        k--;                                                             //Debemos hacer esto ya que al eliminar la arista la posiciones en la Lista cambia pero el contador sigue.
                    }                                                                    //No hacer esto nos generaria problemas cuando dos Aristas a eliminar son consecutivas en la Lista
                }
            }

            GrafosCasos.add(GrafoAlt);                                                   //Agrega el Grafo recien creado en la Lista de grafos casos

            linea = Lector.readLine();                                                   //Salta la linea de por medio de los Casos
            linea = Lector.readLine();                                                   //Si aun hay casos, esta linea contendra el identificador del proximo Caso
            if(linea == null){                                                           //Si la linea esta vacia, es porque ya no hay mas Casos y se sale del ciclo
                AunHayCasos = false;
            }
        }

        }catch(IOException e){
            System.out.println("El archivo con los Casos del problema no existe o no es formato .txt");
            System.exit(1);
        }catch(ArrayIndexOutOfBoundsException e2){
            System.out.println("Ocurrio un problema leyendo el Archivo con los casos del problema");
            System.exit(1);
        }catch(NumberFormatException e3){
            System.out.println("Ocurrio un problema leyendo el Archivo con los casos del problema");
            System.exit(1);
        }
    }

    /*Este metodo lo usaremos en el Main para encontrar el indice correspondiente a la posicion del Vertice en la Lista de Vertices del Grafo
    */
    public static int EncontrarVertice(String Nombre, GrafoNoDirigido Grafo){
        for(int i=0; i<Grafo.Vertices.size(); i++){
            if(Grafo.Vertices.get(i).GetId().equals(Nombre)){
                return i;
            }
        }
        return -1;                                                            //Este return nunca se ejecuta pero Java no nos permite tener solo un return en un caso If.
    }


    /*Este metodo recibe dos String, uno del vertice extremo inicial de la arista y otro del vertice extremo final de la arista y el Grafo.
    * Su funcion es encontrar el indice de la arista con estos Vertices como extremos en la Lista de Aristas del grafo.
    */
    public static int EncontrarArista(String Vertice1, String Vertice2, GrafoNoDirigido Grafo){
        /* Si existen Aristas con los mismos vertices extremos, Buscamos la Arista con menor Distancia y con Capacidad de gente mayor a 0 para asi diferenciar
        * A estas aristas*/
        int AristaEncontrada = -1;
        for(int i=0; i<Grafo.Aristas.size(); i++){
            if(Grafo.Aristas.get(i).GetExtremo1().equals(Vertice1) && Grafo.Aristas.get(i).GetExtremo2().equals(Vertice2) && Grafo.Aristas.get(i).GetCapacidad()>0){
                if(AristaEncontrada != -1){
                    if(Grafo.Aristas.get(i).GetPeso()<Grafo.Aristas.get(AristaEncontrada).GetPeso()){
                        AristaEncontrada = i;
                    }
                }
                else{
                    AristaEncontrada = i;
                }
            }
            else if(Grafo.Aristas.get(i).GetExtremo1().equals(Vertice2) && Grafo.Aristas.get(i).GetExtremo2().equals(Vertice1) && Grafo.Aristas.get(i).GetCapacidad()>0){
                if(AristaEncontrada != -1){
                    if(Grafo.Aristas.get(i).GetPeso()<Grafo.Aristas.get(AristaEncontrada).GetPeso()){
                        AristaEncontrada = i;
                    }
                }
                else{
                    AristaEncontrada = i;
                }
            }
        }
        return AristaEncontrada;                                                        
    }


    /*Este Metodo nos devuelve el camino desde un vertice a el Vertice Inicial String
    */
    public static String EncontrarCamino(int Inicio, int[] Predecesor, GrafoNoDirigido Grafo, Double[] Costo, int NumPersonas){
        int Pred = Predecesor[Inicio];
        String Camino = Grafo.Vertices.get(Inicio).GetId();

        while(true){
            if(Pred!=Predecesor[Pred]){                                                              //Si el predecesor del vertice no es el mismo
                Camino = Grafo.Vertices.get(Pred).GetId()+ " - "+Camino;
                Pred = Predecesor[Pred];
            }
            else{                                                                                    //Si el predecesor del vertice es el mismo, entonces este vertice es
                break;                                                                               //El vertice inicial del camino
            }
        }
        Camino = Grafo.Vertices.get(Pred).GetId()+ " - "+Camino;                                     //Agrega el Vertice inicial a el String con el camino.
        Camino = "        "+"Ruta: "+Camino+" ("+String.valueOf(Costo[Inicio])+")";
        return Camino;
    }


    /*Este metodo recibe el arreglo de Costo, el arreglo de Predecesor, el Vertice Final del camino y el Grafo y lo primero que hace es
    * Buscar la minima capacidad de las aristas en el camino (contando el vertice que contiene agua) y luego de esto reduce la capacidad de estos
    * por la capacidad minima, para asi modificar el grafo. Retorna la Capacidad Minima.
    */ 
    public static int CapacidadMinimaCamino(Double[] Costo, int[] Predecesor, int MinElem, GrafoNoDirigido Grafo){
        int Pred = MinElem;
        int CapMin = 999999999;
        int CapArista;
        int Index;

        /*En este ciclo, encobtraremos la capacidad Minima del camino (tomando en cuenta la capacidad del baño)*/
        while(true){
            if(Pred!=Predecesor[Pred]){                                                              //Si el predecesor del vertice no es el mismo
                String Vertice1 = Grafo.Vertices.get(Pred).GetId();                                  //Nombre de el Vertice extremo 1
                String Vertice2 = Grafo.Vertices.get(Predecesor[Pred]).GetId();                      //Nombre de el Vertice extremo 2
                Index = EncontrarArista(Vertice1, Vertice2, Grafo);                                  //Index de la arista con los vertices extremos dados
                CapArista = Grafo.Aristas.get(Index).GetCapacidad();                                 //Capacidad de la arista
                if(CapArista<CapMin){                                                                //Si la capacidad de la arista es menor que la capacidad Minima
                    CapMin = CapArista;
                }
                Pred = Predecesor[Pred];
            }
            else{                                                                                    //Si el predecesor del vertice es el mismo, entonces este vertice es
                if(CapMin>Grafo.Vertices.get(MinElem).GetCapacidad()){                               //El vertice inicial del camino
                    CapMin = Grafo.Vertices.get(MinElem).GetCapacidad();
                }
                break;                                                                               
            }
        }

        Pred = MinElem;
        /*Ahora pasamos a reducir la capacidad en las aristas*/
        while(true){
            if(Pred!=Predecesor[Pred]){                                                              //Si el predecesor del vertice no es el mismo
                String Vertice1 = Grafo.Vertices.get(Pred).GetId();                                  //Nombre de el Vertice extremo 1
                String Vertice2 = Grafo.Vertices.get(Predecesor[Pred]).GetId();                      //Nombre de el Vertice extremo 2
                Index = EncontrarArista(Vertice1, Vertice2, Grafo);                                  //Index de la arista con los vertices extremos dados
                Grafo.Aristas.get(Index).ReduceCapacidad(CapMin);                                    //Reducimos la Capacidad de la arista
                Pred = Predecesor[Pred];
            }
            else{                                                                                    //Si el predecesor del vertice es el mismo, entonces este vertice es
                Grafo.Vertices.get(MinElem).ReduceCapacidad(CapMin);                                 //El vertice inicial del camino
                break;                                                                              
            }
        }

        return CapMin;
    }



    public static void main(String[] args)
    throws IOException, IllegalArgumentException
    {

        /*Crea el Grafo Base del problema donde estan todos los edificios y caminos de el campus*/
        GrafoNoDirigido Grafo = new GrafoNoDirigido();
        Grafo.CrearGrafoNoDirigido("Caso Base");
        CargarGrafo(args[0], (GrafoNoDirigido)Grafo);

        /*Crea la lista de Grafos donde cada Grafo es un caso del problema*/
        ArrayList<GrafoNoDirigido> GrafosCasos = new ArrayList<GrafoNoDirigido>();
        /*Carga todos los Grafos de cada caso en la Lista de Grafo Casos*/
        CargarCasos(args[1], (GrafoNoDirigido)Grafo, GrafosCasos);

        /*Arreglos y Variables necesarios para Bellman-Ford*/
        Double[] Costo;
        int[] Predecesor;
        int VerticeInicial;
        int NumPersonas = Integer.parseInt(args[3]);
        int Extremo1;
        int Extremo2; 
        Boolean CicloNegativo;

        if(NumPersonas<=0){
            System.out.println("\nPara resolver el problema necesitamos un numero de personas mayor que 0 ");
            System.exit(1);
        }

        /*Repetimos todo el programa para cada Caso propuesto en el archivo .txt*/
        for(int l=0; l<GrafosCasos.size(); l++){
            Costo = new Double[GrafosCasos.get(l).Vertices.size()];
            Predecesor = new int[GrafosCasos.get(l).Vertices.size()];
            VerticeInicial = EncontrarVertice(args[2], GrafosCasos.get(l));
            CicloNegativo = false;
            System.out.println("\n"+GrafosCasos.get(l).GetNombre());
            NumPersonas = Integer.parseInt(args[3]);

            while(NumPersonas>0){

            /*Inicializa todas las variables y Arreglos para la corrida del algoritmo Bellman-Ford*/
            for(int i=0; i<GrafosCasos.get(l).Vertices.size(); i++){
                Costo[i] = 99999999.9;
                Predecesor[i] = -1;
            }
            Costo[VerticeInicial] = 0.0;
            Predecesor[VerticeInicial] = VerticeInicial;
            int i= 0;
            Boolean Cambio = true;

            /*Aqui se inicia el Algoritmo de Bellman-Ford, en cada if toma en cuenta la capacidad de las aristas*/
            while(i<GrafosCasos.get(l).Vertices.size() && Cambio){
                Cambio = false;
                for(int j=0; j<GrafosCasos.get(l).Aristas.size(); j++){
                    Extremo1 = EncontrarVertice(GrafosCasos.get(l).Aristas.get(j).GetExtremo1(), GrafosCasos.get(l));            //Encontramos el indice correspondiente con el Vertice extremo 1
                    Extremo2 = EncontrarVertice(GrafosCasos.get(l).Aristas.get(j).GetExtremo2(), GrafosCasos.get(l));            //Encontramos el indice correspondiente con el Vertice extremo 2

                    /*Si el Extremo1 es un Vertice con agua, toma en cuenta los pisos que debe subir para llegar al baño*/
                    if(GrafosCasos.get(l).Vertices.get(Extremo1).GetAgua()){
                        if(Costo[Extremo2] > Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso() && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo2] = Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso();
                            Predecesor[Extremo2] = Extremo1; 
                            Cambio = true;                                                                       //Si ocurrio un cambio
                        }
                        /*Si el costo para llegar a Extremo 1 es mayor que el Costo de llegar a Extremo 2 + Distancia desde Extremo 1 a Extremo 2 + Distancia de subir los pisos hasta el baño*/
                        else if(Costo[Extremo1] > Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso() + GrafosCasos.get(l).Vertices.get(Extremo1).GetPeso()*25 && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo1] = Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso() + GrafosCasos.get(l).Vertices.get(Extremo1).GetPeso()*25;
                            Predecesor[Extremo1] = Extremo2;
                            Cambio = true;                                                                       //Si ocurrio un cambio
                        }
                    }
                    /*Si el Extremo2 es un Vertice con agua, toma en cuenta los pisos que debe subir para llegar al baño*/
                    else if(GrafosCasos.get(l).Vertices.get(Extremo2).GetAgua()){
                        /*Si el costo para llegar a Extremo 2 es mayor que el Costo de llegar a Extremo 1 + Distancia desde Extremo 1 a Extremo 2 + Distancia de subir los pisos hasta el baño*/
                        if(Costo[Extremo2] > Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso() + GrafosCasos.get(l).Vertices.get(Extremo2).GetPeso()*25 && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo2] = Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso() + GrafosCasos.get(l).Vertices.get(Extremo2).GetPeso()*25;
                            Predecesor[Extremo2] = Extremo1;
                            Cambio = true;                                                                       //Si ocurrio un cambio
                        }
                        else if(Costo[Extremo1] > Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso() && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo1] = Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso();
                            Predecesor[Extremo1] = Extremo2;
                            Cambio = true;                                                                       //Si ocurrio un cambio
                        }
                    }
                    /*Si Ninguno de los vertices tiene agua, no toma en cuenta los pisos a subir*/
                    else{
                        if(Costo[Extremo2] > Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso() && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo2] = Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso();
                            Predecesor[Extremo2] = Extremo1;
                            Cambio = true;                                                                       //Si ocurrio un cambio
                        }
                        else if(Costo[Extremo1] > Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso() && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                            Costo[Extremo1] = Costo[Extremo2] + GrafosCasos.get(l).Aristas.get(j).GetPeso();
                            Predecesor[Extremo1] = Extremo2;                                                     //Si ocurrio un cambio
                            Cambio = true;
                        }
                    }
                }
                i++;
            }
            /*Este ciclo se utiliza para revisar si hay un ciclo de peso Negativo, si lo hay. Entonces el Caso no tiene Solucion*/
            for(int j=0; j<GrafosCasos.get(l).Aristas.size(); j++){
                Extremo1 = EncontrarVertice(GrafosCasos.get(l).Aristas.get(j).GetExtremo1(), GrafosCasos.get(l));
                Extremo2 = EncontrarVertice(GrafosCasos.get(l).Aristas.get(j).GetExtremo1(), GrafosCasos.get(l));

                if(Costo[Extremo2] > Costo[Extremo1] + GrafosCasos.get(l).Aristas.get(j).GetPeso() && GrafosCasos.get(l).Aristas.get(j).GetCapacidad()>0){
                    System.out.println("Error: Hay un ciclo de peso negativo");
                    CicloNegativo = true;
                }
            }

            /*Si el Caso no contiene un ciclo negativo, Se procede a buscar los caminos hasta los baños*/
            if(!CicloNegativo){
                /*Esta variable sera a condicion del ciclo, si no se encuentra camino a un baño, la variable dara false.*/
                Boolean NoEncontroCamino = true;
                while(NoEncontroCamino){
                    Double Min = 99999999999999999999.9;
                    int MinElem = -1;
                    /*Se busca el camino mas corto en el grafo, luego se vera si el vertice extremo final tiene agua*/
                    for(int j=0; j<Costo.length; j++){
                        if(Costo[j]<Min){
                            Min = Costo[j];
                            MinElem = j;
                        }
                    }
                    /*Aqui es donde se vera si el vertice extremo final tiene agua o no, si no tiene le aumentamos la distancia para que cuando busquemos el camino mas corto
                    * denuevo, no se tome en cuenta este camino a un edificio sin agua. Pero si tiene agua se procede imprimir el camino, reducir la capacidad de las aristas
                    * y del vertice con agua.
                    */
                    if(GrafosCasos.get(l).Vertices.get(MinElem).GetAgua() == false){
                        Costo[MinElem] = 99999999.9;
                    }
                    else{
                        int CapacidadMin = CapacidadMinimaCamino(Costo, Predecesor, MinElem, GrafosCasos.get(l));
                        if(NumPersonas - CapacidadMin>=0){
                            System.out.println("    "+String.valueOf(CapacidadMin)+" Personas a "+GrafosCasos.get(l).Vertices.get(MinElem).GetId());      
                        }
                        else{
                            System.out.println("    "+String.valueOf(NumPersonas)+" Personas a "+GrafosCasos.get(l).Vertices.get(MinElem).GetId());
                        }
                        System.out.println(EncontrarCamino(MinElem, Predecesor, (GrafoNoDirigido)GrafosCasos.get(l), Costo, NumPersonas));
                        NumPersonas = NumPersonas - CapacidadMin;
                        NoEncontroCamino = false;
                    }

                    /*Aqui se revisara si aun quedan personas sin asignar baño en el problema, si aun hay y la busqueda de baños termino, se procede a decir que quedo
                    * un grupo de personas sin baño. Pero si aun hay personas sin baños y la busqueda de baños no ha terminado, no hace nada.*/
                    Boolean SinAsignar = true;
                    for(int j=0; j<Costo.length; j++){
                        if(Costo[j]!=99999999.9){
                            SinAsignar = false;
                        }
                    }
                    if(SinAsignar){
                        System.out.println("    "+String.valueOf(NumPersonas)+" Personas sin asignar");
                        NoEncontroCamino = false;
                        NumPersonas = 0;
                    }
                }
            }
        }
    }
    }
}