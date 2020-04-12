import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.nio.*;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 * --------- PROYECTO 1 LABORATORIO DE ALGORITMOS Y ESTRUCTURAS III ----------------- 
 */

public class Cliente {

	public static void main(String[] args){
        try {	

			int n;                                                          // Corresponde a la cantidad de vértices en el grafo
			int m;                                                          // Corresponde a la cantidad de aristas en el grafo
			String v;                                                       // Corresponde al tipo de datos que serán los vértice
			String e;                                                       // Corresponde al tipo de datos que serán los lados
			String o;                                                       // Correponde de orientación que va a ser el grafo
			
			BufferedReader Lector = new BufferedReader(
					new FileReader(args[0]));
			
			String linea = Lector.readLine();                               // Lee la primera línea que corresponde al tipo de datos que vamos almacenar en los vértices
			v =  linea.trim();   
			linea = Lector.readLine();                                      // Lee la segunda línea que corresponde al tipo de datos que vamos almacenar en los lados
			e =  linea.trim();   
			linea = Lector.readLine();                                      // Lee la tercera línea que corresponde a la orientación de los lados
			o =  linea.trim();  
			linea = Lector.readLine();                                      // Lee la cuarta línea que corresponde al número de vértices
			n =  Integer.parseInt(linea);  
			linea = Lector.readLine();                                      // Lee la quinta línea que corresponde al número de lados
			m =  Integer.parseInt(linea);


            // Ahora dependiendo del tipo de orietación, creamos un grafo Dirigido o un grafo no Dirigido
			if(o.equals("D")) {

                GrafoDirigido grafo = null;
                Scanner reader = new Scanner(System.in);
                
                /*Creamos 9 if statements ya que necesitamos tomar en cuenta las distintas combinaciones para crear el grafo
                * por los tipos genericos. 
                * Creamos, construimos y cargamos el grafo en cada if ya que necesitamos tener un Grafo diferente (por los tipos de Datos de Vertice
                * y Arco) para cada posibilidad y tenemos que crear sus debidos Transformadores para hacer el cambio de String a estos Tipos genericos
                */
                if (v.equals("B") && e.equals("B")) {
                    grafo = new GrafoDirigido<Boolean,Boolean>();                                          //Inicializa Grafo Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArco = new TransformarBoolean();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);           

                } else if (v.equals("B") && e.equals("D")) {
                    grafo = new GrafoDirigido<Boolean,Double>();                                           //Inicializa Grafo Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArco = new TransformarDouble();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("B") && e.equals("S")) {
                    grafo = new GrafoDirigido<Boolean,String>();                                           //Inicializa Grafo Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArco = new TransformarString();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);
                    
                } else if (v.equals("D") && e.equals("B")) {
                    grafo = new GrafoDirigido<Double, Boolean>();                                          //Inicializa Grafo Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArco = new TransformarBoolean();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("D") && e.equals("D")) {
                    grafo = new GrafoDirigido<Double,Double>();                                            //Inicializa Grafo Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArco = new TransformarDouble();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("D") && e.equals("S")) {
                    grafo = new GrafoDirigido<Double, String>();                                           //Inicializa Grafo Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArco = new TransformarString();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("S") && e.equals("B")) {
                    grafo = new GrafoDirigido<String, Boolean>();                                          //Inicializa Grafo Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArco = new TransformarBoolean();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("S") && e.equals("D")) {
                    grafo = new GrafoDirigido<String,Double>();                                            //Inicializa Grafo Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArco = new TransformarDouble();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);

                } else if (v.equals("S") && e.equals("S")) { 
                    grafo = new GrafoDirigido<String,String>();                                            //Inicializa Grafo Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArco = new TransformarString();                //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoDirigido();                                                            //Llama al constructor de Grafo Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArco);
                }


                // Menu de Grafo Dirigido
                while(true){
                    System.out.println(
                        "Menu de opciones sobre el grafo Dirigido: \n" +
                        "Presione q (seguido de \"Enter\") para saber el numero de Vertices del grafo \n" +
                        "Presione w (seguido de \"Enter\") para saber el numero de Arcos del grafo \n" +
                        "Presione e (seguido de \"Enter\") para agregar un nuevo Vertice al grafo  \n" +
                        "Presione r (seguido de \"Enter\") para agregar un nuevo Arco al grafo  \n" +
                        "Presione t (seguido de \"Enter\") para obtener un Vertice del grafo \n" +
                        "Presione y (seguido de \"Enter\") para obtener un Arco del grafo \n" +
                        "Presione u (seguido de \"Enter\") para saber si un Vertice esta en el grafo \n" +
                        "Presione i (seguido de \"Enter\") para saber si un Arco esta en el grafo \n" +
                        "Presione o (seguido de \"Enter\") para eliminar un Vertice del grafo \n" +
                        "Presione p (seguido de \"Enter\") para eliminar un Arco del grafo \n" +
                        "Presione a (seguido de \"Enter\") para imprimir una lista de los Arcos en el grafo \n" +
                        "Presione s (seguido de \"Enter\") para imprimir la lista de Vertices del grafo \n" +
                        "Presione d (seguido de \"Enter\") para imprimir el grado de un Vertice del grafo \n" +
                        "Presione f (seguido de \"Enter\") para imprimir el grado interno de un Vertice del grafo \n" +
                        "Presione g (seguido de \"Enter\") para imprimir el grado externo de un Vertice del grafo \n" +
                        "Presione h (seguido de \"Enter\") para obtener los Vertices adyacentes a un Vertice dado \n" +
                        "Presione j (seguido de \"Enter\") para obtener la lista de sucesores de un Vertice dado \n" +
                        "Presione k (seguido de \"Enter\") para obtener la lista de predecesores de un Vertice \n" +
                        "Presione l (seguido de \"Enter\") para obtener la lista de Arcos incidentes a un Vertice dado \n" +
                        "Presione z (seguido de \"Enter\") para clonar el grafo a un grafo nuevo \n" +
                        "Presione x (seguido de \"Enter\") para imprimir una representacion del grafo \n" +
                        "Presione n (seguido de \"Enter\") para salir");
                    String c = reader.next();
                    System.out.println(" ");
                    if (c.equals("q")) {
                        System.out.println("El numero de Vertices es: " + grafo.NumeroDeVertices()+"\n");
                    }
    
                    else if (c.equals("w")) {
                        System.out.println("El numero de Arco es: " + grafo.NumeroDeLados()+"\n");
                    }

                    else if (c.equals("e")) {
                        Boolean Funciono = false;
                        String Identificador;
                        String dato;
                        String peso;

                        System.out.println("Escriba el Identificador del nuevo vertice: ");
                        Identificador = reader.next();
                        System.out.println("Escriba el Dato del nuevo Vertice: ");
                        dato = reader.next();
                        System.out.println("Escriba el peso del nuevo Vertice");
                        peso = reader.next();

                        /* Crearemos un vertice dependiendo de la eleccion del tipo de Dato dado en el archivo .txt
                        * por eso debemos crear 3 if statements para cada tipo de Dato 
                        */
                        try{
                            if(v.equals("D")){
                                Vertice<Double> Ve = new Vertice<Double>();
                                Ve.CrearVertice(Identificador, Double.parseDouble(dato), Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            else if(v.equals("S")){
                                Vertice<String> Ve = new Vertice<String>();
                                Ve.CrearVertice(Identificador, dato, Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            else if(v.equals("B")){
                                Vertice<Boolean> Ve = new Vertice<Boolean>();
                                Ve.CrearVertice(Identificador, Boolean.valueOf(dato), Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            if(Funciono){
                                System.out.println("Se agrego el Vertice al grafo.\n");
                            }
                            else{
                                System.out.println("El Vertice ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                        }catch(NumberFormatException err){
                            System.out.println("Algo mal ocurrio Agregando el Vertice, revisa si el Dato es del tipo expresado en el archivo .txt o revisa si el peso es un numero tipo Double\n");
                        }
                    }

                    else if (c.equals("r")) {
                        Boolean Funciono = false;
                        String Identificador;
                        String dato;
                        String peso;
                        String IdV1;
                        String IdV2;

                        System.out.println("Escriba el Identificador de la nueva Arco: ");
                        Identificador = reader.next();
                        System.out.println("Escriba el Dato de la nueva Arco: ");
                        dato = reader.next();
                        System.out.println("Escriba el peso de la nueva Arco: ");
                        peso = reader.next();
                        System.out.println("Escriba el Identificador del Vertice inicial de la Arco: ");
                        IdV1 = reader.next();
                        System.out.println("Escriba el Identificador del Vertice final de la Arco: ");
                        IdV2 = reader.next();

                        /* Crearemos una Arista dependiendo de la eleccion del tipo de Dato dado en el archivo .txt
                        * por eso debemos crear 3 if statements para cada tipo de Dato 
                        */
                        try{
                            if(e.equals("D")){
                                Arco<Double> Ar = new Arco<Double>();
                                Ar.CrearArco(Identificador, Double.parseDouble(dato), Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArco(Ar);
                            }
                            else if(e.equals("S")){
                                Arco<String> Ar = new Arco<String>();
                                Ar.CrearArco(Identificador, dato, Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArco(Ar);
                            }
                            else if(e.equals("B")){
                                Arco<Boolean> Ar = new Arco<Boolean>();
                                Ar.CrearArco(Identificador, Boolean.valueOf(dato), Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArco(Ar);
                            }
                            if(Funciono){
                                System.out.println("Se agrego El Arco al grafo.\n");
                            }
                            else{
                                System.out.println("El Arco ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                        }catch(NoSuchElementException err1){
                            System.out.println("Algo mal ocurrio Agregando el Arco, revisa si los vertices extremos existen en el Grafo\n");
                        }catch(NumberFormatException err2){
                            System.out.println("Algo mal ocurrio Agregando el Arco, revisa si el Dato es del tipo expresado en el archivo .txt o revisa si el peso es un numero tipo Double\n");
                        }
                    }

                    else if (c.equals("t")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = reader.next();
                        try{
                            System.out.println(grafo.ObtenerVertice(Identificador).ToString()+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el Grafo");
                        }   
                    }

                    else if (c.equals("y")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador de la Arco a buscar: ");
                        Identificador = reader.next();
                        try{
                            System.out.println(grafo.ObtenerArco(Identificador).ToString()+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Arco ingresada no existe en el Grafo");
                        }
                        
                    }

                    else if (c.equals("u")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = reader.next();
                        if(grafo.EstaVertice(Identificador)){
                            System.out.println("El Vertice si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("El Vertice no esta en el grafo.\n");
                        }
                    }

                    else if (c.equals("i")){
                        String Identificador1;
                        String Identificador2;
                        System.out.println("Ingrese el Identificador de el Vertice inicial del Arco: ");
                        Identificador1 = reader.next();
                        System.out.println("Ingrese el Identificador de el Vertice final del Arco: ");
                        Identificador2 = reader.next();
                        if(grafo.EstaLado(Identificador1, Identificador2)){
                            System.out.println("La Arco si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("La Arco no esta en el grafo.\n");
                        }
                    }

                    else if (c.equals("o")){
                        Boolean Funciono;
                        String Identificador;
                        System.out.println("Ingrese el Identificador de el Vertice a eliminar: ");
                        Identificador = reader.next();
                        Funciono = grafo.EliminarVertice(Identificador);
                        if(Funciono){
                            System.out.println("El vertice se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("El vertice no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }

                    else if (c.equals("p")){
                        Boolean Funciono;
                        String Identificador;
                        System.out.println("Ingrese el Identificador de la Arco a eliminar: ");
                        Identificador = reader.next();
                        Funciono = grafo.EliminarArco(Identificador);
                        if(Funciono){
                            System.out.println("La Arco se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("La Arco no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }

                    else if (c.equals("a")){
                        System.out.println("Arcos en el grafo: ");
                        if(e.equals("D")){
                            ArrayList<Lado<Double>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(e.equals("S")){
                            ArrayList<Lado<String>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(e.equals("B")){
                            ArrayList<Lado<Boolean>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        System.out.println("\n");
                    }

                    else if (c.equals("s")){
                        System.out.println("Vertices en el grafo: ");
                        if(v.equals("D")){
                            ArrayList<Vertice<Double>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(v.equals("S")){
                            ArrayList<Vertice<String>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(v.equals("B")){
                            ArrayList<Vertice<Boolean>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        System.out.println("\n");
                    }

                    else if (c.equals("d")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado: ");
                        Identificador = reader.next();
                        try{
                            System.out.println("El grado del Vertice es: "+grafo.Grado(Identificador)+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo");
                        }
                    }

                    else if(c.equals("f")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado interior: ");
                        Identificador = reader.next();
                        try{
                            System.out.println("El grado interior del Vertice es: "+grafo.GradoInterior(Identificador)+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo.\n");
                        }

                    }

                    else if(c.equals("g")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado exterior: ");
                        Identificador = reader.next();
                        try{
                            System.out.println("El grado interior del Vertice es: "+grafo.GradoExterior(Identificador)+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo.\n");
                        }
                    }

                    else if (c.equals("h")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus vertices adyacentes: ");
                        Identificador = reader.next();
                        try{
                            if(v.equals("D")){
                                ArrayList<Vertice<Double>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("S")){
                                ArrayList<Vertice<String>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("B")){
                                ArrayList<Vertice<Boolean>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");

                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice ingresado no existe en el Grafo.\n");
                        }
                    }

                    else if(c.equals("j")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Sucesores: ");
                        Identificador = reader.next();
                        try{
                            if(v.equals("D")){
                                ArrayList<Vertice<Double>> Arr = grafo.Sucesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("S")){
                                ArrayList<Vertice<String>> Arr = grafo.Sucesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("B")){
                                ArrayList<Vertice<Boolean>> Arr = grafo.Sucesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo.\n");
                        }
                    }

                    else if(c.equals("k")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Predecesores: ");
                        Identificador = reader.next();
                        try{
                            if(v.equals("D")){
                                ArrayList<Vertice<Double>> Arr = grafo.Predecesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("S")){
                                ArrayList<Vertice<String>> Arr = grafo.Predecesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("B")){
                                ArrayList<Vertice<Boolean>> Arr = grafo.Predecesores(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo.\n");
                        }
                    }

                    else if (c.equals("l")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Arcos Incidentes: ");
                        Identificador = reader.next();
                        try{
                            if(e.equals("D")){
                                ArrayList<Lado<Double>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(e.equals("S")){
                                ArrayList<Lado<String>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(e.equals("B")){
                                ArrayList<Lado<Boolean>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");

                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice ingresado no existe en el Grafo.\n");
                        }
                    }

                    else if (c.equals("z")){
                        if(v.equals("D")){
                            ArrayList<Vertice<Double>> GrafoClone = grafo.Clone();
                        }
                        else if(v.equals("S")){
                            ArrayList<Vertice<String>> GrafoClone = grafo.Clone();
                        }
                        else if(v.equals("B")){
                            ArrayList<Vertice<Boolean>> GrafoClone = grafo.Clone();
                        }
                        System.out.println("Se creo un Grafo cloneado de el Grafo Inicial");
                    }

                    else if (c.equals("x")){
                        System.out.println(grafo.ToString()+"\n");
                    }

                    else if (c.equals("n")){
                        break;
                    }
                }


            /* Ahora viene el codigo para cuando el Grafo es No Dirigido
            */
            }
            else if(o.equals("N")){

                GrafoNoDirigido grafo = null;
                Scanner reader = new Scanner(System.in);

                /*Creamos 9 if statements ya que necesitamos tomar en cuenta las distintas combinaciones para crear el grafo
                * por los tipos genericos. 
                * Creamos, construimos y cargamos el grafo en cada if ya que necesitamos tener un Grafo diferente por cada tipo de Dato de Vertice
                * y Arco y tenemos que crear sus debidos Transformadores para hacer el cambio de String a estos Tipos genericos.
                */
                if (v.equals("B") && e.equals("B")) {
                    grafo = new GrafoNoDirigido<Boolean,Boolean>();                                        //Inicializa Grafo no Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArista = new TransformarBoolean();            //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("B") && e.equals("D")) {
                    grafo = new GrafoNoDirigido<Boolean,Double>();                                         //Inicializa Grafo no Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArista = new TransformarDouble();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("B") && e.equals("S")) {
                    grafo = new GrafoNoDirigido<Boolean,String>();                                         //Inicializa Grafo no Dirigido
                    Transformer<String,Boolean> TransformadorVertice = new TransformarBoolean();           //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArista = new TransformarString();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("D") && e.equals("B")) {
                    grafo = new GrafoNoDirigido<Double, Boolean>();                                        //Inicializa Grafo no Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArista = new TransformarBoolean();            //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("D") && e.equals("D")) {
                    grafo = new GrafoNoDirigido<Double,Double>();                                          //Inicializa Grafo no Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArista = new TransformarDouble();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("D") && e.equals("S")) { 
                    grafo = new GrafoNoDirigido<Double, String>();                                         //Inicializa Grafo no Dirigido
                    Transformer<String,Double> TransformadorVertice = new TransformarDouble();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArista = new TransformarString();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("S") && e.equals("B")) {
                    grafo = new GrafoNoDirigido<String, Boolean>();                                        //Inicializa Grafo no Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Boolean> TransformadorArista = new TransformarBoolean();            //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("S") && e.equals("D")) {
                    grafo = new GrafoNoDirigido<String,Double>();                                          //Inicializa Grafo no Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,Double> TransformadorArista = new TransformarDouble();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                } else if (v.equals("S") && e.equals("S")) {
                    grafo = new GrafoNoDirigido<String,String>();                                          //Inicializa Grafo no Dirigido
                    Transformer<String,String> TransformadorVertice = new TransformarString();             //Transformador para Transformar String a Tipo Generico V
                    Transformer<String,String> TransformadorArista = new TransformarString();              //Transformador para Transformar String a Tipo Generico E
                    grafo.CrearGrafoNoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                    grafo.CargarGrafo(n, m, args[0], TransformadorVertice, TransformadorArista);

                }


                // Menu de Grafo No Dirigido
                while(true){
                    System.out.println(
                        "Menu de opciones sobre el grafo No Dirigido: \n" +
                        "Presione q (seguido de \"Enter\") para saber el numero de Vertices del grafo \n" +
                        "Presione w (seguido de \"Enter\") para saber el numero de Aristas del grafo \n" +
                        "Presione e (seguido de \"Enter\") para agregar un nuevo Vertice al grafo  \n" +
                        "Presione r (seguido de \"Enter\") para agregar una nueva Arista al grafo  \n" +
                        "Presione t (seguido de \"Enter\") para obtener un Vertice del grafo \n" +
                        "Presione y (seguido de \"Enter\") para obtener una Arista del grafo \n" +
                        "Presione u (seguido de \"Enter\") para saber si un Vertice esta en el grafo \n" +
                        "Presione i (seguido de \"Enter\") para saber si una Arista esta en el grafo \n" +
                        "Presione o (seguido de \"Enter\") para eliminar un Vertice del grafo \n" +
                        "Presione p (seguido de \"Enter\") para eliminar una Arista del grafo \n" +
                        "Presione a (seguido de \"Enter\") para imprimir una lista de las Aristas en el grafo \n" +
                        "Presione s (seguido de \"Enter\") para imprimir la lista de Vertices del grafo \n" +
                        "Presione d (seguido de \"Enter\") para imprimir el grado de un Vertice del grafo \n" +
                        "Presione f (seguido de \"Enter\") para obtener los Vertices adyacentes a un Vertice dado \n" +
                        "Presione g (seguido de \"Enter\") para obtener la lista de Aristas incidentes a un Vertice dado \n" +
                        "Presione h (seguido de \"Enter\") para clonar el grafo a un grafo nuevo \n" +
                        "Presione j (seguido de \"Enter\") para imprimir una representacion del grafo \n" +
                        "Presione n (seguido de \"Enter\") para salir");
                    String c = reader.next();
                    System.out.println(" ");
                    if (c.equals("q")) {
                        System.out.println("El numero de Vertices es: " + grafo.NumeroDeVertices()+"\n");
                    }

                    else if (c.equals("w")) {
                        System.out.println("El numero de Aristas es: " + grafo.NumeroDeLados()+"\n");
                    }

                    else if (c.equals("e")) {
                        Boolean Funciono = false;
                        String Identificador;
                        String dato;
                        String peso;

                        System.out.println("Escriba el Identificador del nuevo vertice: ");
                        Identificador = reader.next();
                        System.out.println("Escriba el Dato del nuevo Vertice: ");
                        dato = reader.next();
                        System.out.println("Escriba el peso del nuevo Vertice");
                        peso = reader.next();

                        /* Crearemos un vertice dependiendo de la eleccion del tipo de Dato dado en el archivo .txt
                        * por eso debemos crear 3 if statements para cada tipo de Dato 
                        */
                        try{
                            if(v.equals("D")){
                                Vertice<Double> Ve = new Vertice<Double>();
                                Ve.CrearVertice(Identificador, Double.parseDouble(dato), Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            else if(v.equals("S")){
                                Vertice<String> Ve = new Vertice<String>();
                                Ve.CrearVertice(Identificador, dato, Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            else if(v.equals("B")){
                                Vertice<Boolean> Ve = new Vertice<Boolean>();
                                Ve.CrearVertice(Identificador, Boolean.valueOf(dato), Double.parseDouble(peso));
                                Funciono = grafo.AgregarVertice(Ve);
                            }
                            if(Funciono){
                                System.out.println("Se agrego el Vertice al grafo.\n");
                            }
                            else{
                                System.out.println("El Vertice ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                        }catch(NumberFormatException err){
                            System.out.println("Algo mal ocurrio Agregando el Vertice, revisa si el Dato es del tipo expresado en el archivo .txt o revisa si el peso es un numero tipo Double\n");
                        }
                    }

                    else if (c.equals("r")) {
                        Boolean Funciono = false;
                        String Identificador;
                        String dato;
                        String peso;
                        String IdV1;
                        String IdV2;

                        System.out.println("Escriba el Identificador de la nueva Arista: ");
                        Identificador = reader.next();
                        System.out.println("Escriba el Dato de la nueva Arista: ");
                        dato = reader.next();
                        System.out.println("Escriba el peso de la nueva Arista: ");
                        peso = reader.next();
                        System.out.println("Escriba el Identificador del vertice 1 de la Arista: ");
                        IdV1 = reader.next();
                        System.out.println("Escriba el Identificador del vertice 2 de la Arista: ");
                        IdV2 = reader.next();

                        /* Crearemos una Arista dependiendo de la eleccion del tipo de Dato dado en el archivo .txt
                        * por eso debemos crear 3 if statements para cada tipo de Dato 
                        */
                        try{
                            if(e.equals("D")){
                                Arista<Double> Ar = new Arista<Double>();
                                Ar.CrearArista(Identificador, Double.parseDouble(dato), Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArista(Ar);
                            }
                            else if(e.equals("S")){
                                Arista<String> Ar = new Arista<String>();
                                Ar.CrearArista(Identificador, dato, Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArista(Ar);
                            }
                            else if(e.equals("B")){
                                Arista<Boolean> Ar = new Arista<Boolean>();
                                Ar.CrearArista(Identificador, Boolean.valueOf(dato), Double.parseDouble(peso), grafo.ObtenerVertice(IdV1), grafo.ObtenerVertice(IdV2));
                                Funciono = grafo.AgregarArista(Ar);
                            }
                            if(Funciono){
                                System.out.println("Se agrego La Arista al grafo.\n");
                            }
                            else{
                                System.out.println("La Arista ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                        }catch(NoSuchElementException err1){
                            System.out.println("Algo mal ocurrio Agregando la Arista, revisa si los vertices extremos existen en el Grafo\n");
                        }catch(NumberFormatException err2){
                            System.out.println("Algo mal ocurrio Agregando la Arista, revisa si el Dato es del tipo expresado en el archivo .txt o revisa si el peso es un numero tipo Double\n");
                        }
                    }

                    else if (c.equals("t")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = reader.next();
                        try{
                            System.out.println(grafo.ObtenerVertice(Identificador).ToString()+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el Grafo");
                        }   
                    }

                    else if (c.equals("y")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador de la Arista a buscar: ");
                        Identificador = reader.next();
                        try{
                            System.out.println(grafo.ObtenerArista(Identificador).ToString()+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("La Arista ingresada no existe en el Grafo");
                        }
                        
                    }

                    else if (c.equals("u")) {
                        String Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = reader.next();
                        if(grafo.EstaVertice(Identificador)){
                            System.out.println("El Vertice si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("El Vertice no esta en el grafo.\n");
                        }
                    }

                    else if (c.equals("i")){
                        String Identificador1;
                        String Identificador2;
                        System.out.println("Ingrese el Identificador de el Vertice 1 de la Arista: ");
                        Identificador1 = reader.next();
                        System.out.println("Ingrese el Identificador de el Vertice 2 de la Arista: ");
                        Identificador2 = reader.next();
                        if(grafo.EstaLado(Identificador1, Identificador2)){
                            System.out.println("La Arista si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("La Arista no esta en el grafo.\n");
                        }
                    }

                    else if (c.equals("o")){
                        Boolean Funciono;
                        String Identificador;
                        System.out.println("Ingrese el Identificador de el Vertice a eliminar: ");
                        Identificador = reader.next();
                        Funciono = grafo.EliminarVertice(Identificador);
                        if(Funciono){
                            System.out.println("El vertice se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("El vertice no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }

                    else if (c.equals("p")){
                        Boolean Funciono;
                        String Identificador;
                        System.out.println("Ingrese el Identificador de la Arista a eliminar: ");
                        Identificador = reader.next();
                        Funciono = grafo.EliminarArista(Identificador);
                        if(Funciono){
                            System.out.println("La Arista se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("La Arista no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }

                    else if (c.equals("a")){
                        System.out.println("Aristas en el grafo: ");
                        if(e.equals("D")){
                            ArrayList<Lado<Double>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(e.equals("S")){
                            ArrayList<Lado<String>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(e.equals("B")){
                            ArrayList<Lado<Boolean>> Arr = grafo.Lados();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        System.out.println("\n");
                    }

                    else if (c.equals("s")){
                        System.out.println("Vertices en el grafo: ");
                        if(v.equals("D")){
                            ArrayList<Vertice<Double>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(v.equals("S")){
                            ArrayList<Vertice<String>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        else if(v.equals("B")){
                            ArrayList<Vertice<Boolean>> Arr = grafo.Vertices();
                            for(int i=0; i<Arr.size(); i++){
                                System.out.println(Arr.get(i).ToString());
                            }
                        }
                        System.out.println("\n");
                    }

                    else if (c.equals("d")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado: ");
                        Identificador = reader.next();
                        try{
                            System.out.println("El grado del Vertice es: "+grafo.Grado(Identificador)+"\n");
                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice Ingresado no existe en el grafo");
                        }
                    }

                    else if (c.equals("f")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus vertices adyacentes: ");
                        Identificador = reader.next();
                        try{
                            if(v.equals("D")){
                                ArrayList<Vertice<Double>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("S")){
                                ArrayList<Vertice<String>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(v.equals("B")){
                                ArrayList<Vertice<Boolean>> Arr = grafo.Adyacentes(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");

                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice ingresado no existe en el Grafo.\n");
                        }
                    }

                    else if (c.equals("g")){
                        String Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Aristas Incidentes: ");
                        Identificador = reader.next();
                        try{
                            if(e.equals("D")){
                                ArrayList<Lado<Double>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(e.equals("S")){
                                ArrayList<Lado<String>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            else if(e.equals("B")){
                                ArrayList<Lado<Boolean>> Arr = grafo.Incidente(Identificador);
                                for(int i=0; i<Arr.size(); i++){
                                    System.out.println(Arr.get(i).ToString());
                                }
                            }
                            System.out.println("\n");

                        }catch(NoSuchElementException err){
                            System.out.println("El Vertice ingresado no existe en el Grafo");
                        }
                        
                    }

                    else if (c.equals("h")){
                        if(v.equals("D")){
                            ArrayList<Vertice<Double>> GrafoClone = grafo.Clone();
                        }
                        else if(v.equals("S")){
                            ArrayList<Vertice<String>> GrafoClone = grafo.Clone();
                        }
                        else if(v.equals("B")){
                            ArrayList<Vertice<Boolean>> GrafoClone = grafo.Clone();
                        }
                        System.out.println("Se creo un Grafo cloneado de el Grafo Inicial");
                    }

                    else if (c.equals("j")){
                        System.out.println(grafo.ToString()+"\n");
                    }

                    else if (c.equals("n")){
                        break;
                    }
                }
            }
        } catch(IOException err) {
			System.out.println("Excepción: Hay problemas para cargar grafo.txt");
        }
    }
}