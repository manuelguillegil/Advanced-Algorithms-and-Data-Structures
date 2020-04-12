/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class Evaluador{

    /**
     * Metodo main para cuando se quiera utilizar el Evaluador sin utilizar el USBDataFlow. Lo que hace es leer cada una de las lineas con una expresion Aritmetica
     * e imprime su resultado
     * 
     * Parametros entrada:
     * @param args = args[0] contiene el nombre del archivo .txt
     * @throws IOException
     */
    public static void main(String[] args)
            throws IOException
    {
        try{
            BufferedReader Lector = new BufferedReader(
                new FileReader(args[0]));
            String linea;
            while(true){
                linea = Lector.readLine();
                if(linea == null){
                    break;
                }
                System.out.println(evaluar(linea.toUpperCase()));
            }
        }catch(IOException e1){
            System.out.println("ERROR: Se ingreso el nombre de un archivo .txt inexistente");
        }
    }
    
    /**
     * Este metodo en donde se crea la pila, se llama a la funcion que la rellena con la expresion Aritmetica y luego de que regresa de ahi
     * Procede a rellenar el arbol de la expresion para luego llamar al evaluador (recorridoArbol) y evaluar la expresion Aritmetica
     * 
     * Parametros entrada:
     * @param cadena = String que contendra la Expresion aritmetica
     * Parametros salida:
     * @return = Valor numerico de la evaluar la expresion
     */
    public static int evaluar(String cadena){                               
        ArrayList<String> pila = new ArrayList<String>();                                    //Creamos la pila que contendra las operaciones aritemticas
        recurrencias(cadena, pila);                                                          //Con esto la rellenamos

        /*Ahora cargaremos el arbol evaluador con los elementos de la pila*/
        ArrayList<NodoEvaluador> arbolEvaluador = new ArrayList<NodoEvaluador>();
        for(int i=pila.size()-1; i>-1; i--){
            /*Si el elemento de la pila es un numero, loa agrega al arbol*/
            if(pila.get(i).matches("-?\\d+(\\.\\d+)?")){
                NodoEvaluador V = new NodoEvaluador();
                V.setId(pila.get(i));
                arbolEvaluador.add(V);
            }
            /*Si el elemento de la pila no es un numero, entonces antes de agregarlo al arbol encuentra sus hijos y a estos se le agrega como su padre
            * Encontrar los hijos se efectua de la siguiente manera: recorremos los nodos del grafo y si encontramos un nodo previamente agregado al arbol, 
            * sin padre, entonces este sera uno de los dos hijos y se actualiza el padre (previamente null). Una vez que encontremos dos hijos finaliza este proceso 
            * y se agrega el elemento de la pila al arbol*/
            else{
                NodoEvaluador V = new NodoEvaluador();                                         //Crea el nodo del arbol
                V.setId(pila.get(i));                                                          //Identificador del nodo
                int nodoTomados = 0;                                                           //Variable para identificar cuantos hijos del nodo ya se han encontrado
                if(V.getId().equals("SUM")){                                                   //SUM solo debe tener 1 hijo
                    for(int j=arbolEvaluador.size()-1; j>-1; j--){                           
                        if(arbolEvaluador.get(j).getPadre() == null && nodoTomados<1){         //Si un nodo no tiene padre y aun falta por encontrar el hijo, este sera el hijo
                            arbolEvaluador.get(j).setPadre(V);                            
                            if(V.getHijo1() == null){                                          //Si aun no se ha encontrado el hijo #1, lo agrega como hijo #1
                                V.setHijo1(arbolEvaluador.get(j));
                                nodoTomados++;
                            }
                        }
                    }
                }
                else{
                    for(int j=arbolEvaluador.size()-1; j>-1; j--){                               
                        if(arbolEvaluador.get(j).getPadre() == null && nodoTomados<2){         //Si un nodo no tiene padre y aun falta por encontrar hijos, este sera el hijo
                            arbolEvaluador.get(j).setPadre(V);                            
                            if(V.getHijo1() == null){                                          //Si aun no se ha encontrado el hijo #1, lo agrega como hijo #1
                                V.setHijo1(arbolEvaluador.get(j));
                                nodoTomados++;
                            }
                            else if(V.getHijo2() == null){                                     //Si aun no se ha encontrado el hijo #2, lo agrega como hijo #2
                                V.setHijo2(arbolEvaluador.get(j));
                                nodoTomados++;
                            }
                        }
                    }

                }
                arbolEvaluador.add(V);                                                        //Agrega el nodo al arbol
            }
        }

        return recorridoArbol(arbolEvaluador.get(arbolEvaluador.size()-1));                   //Ahora recorre el arbol para evaluarlo
                                   
    }

    /**
     * En este metodo recorremos el Arbol que contiene la expresion y regresa el resultado de esta.
     * Recorremos el abrol de forma PostOrden para asi obtener el resultado de los hijos y evaluar la operacion del padre usando estos resultados.
     * Si el padre es un numero, se regresa el mismo para que el padre de este pueda usarlo en una operacion.
     *
     * Parmetros entrada:
     * @param V = Nodo del arbol, usado para recorrer el arbol por sus hijos
     * Parametros salida:
     * @return = Resultado de la evaluacion de el arbol con la expresion
     */
    static int recorridoArbol(NodoEvaluador V){
        int resultadoHijo1=0;                                         //Inicializamos el valor resultado del Hijo 1
        int resultadoHijo2=0;                                         //Inicializamos el valor resultado del Hijo 2
        if(V.getHijo1() != null){                                     //Si el hijo 1 no es null. Buscamos su Resultado 
            resultadoHijo1 = recorridoArbol(V.getHijo1());
        } 
        if(V.getHijo2() != null){                                     //Si el hijo 2 no es null. Buscamos su Resultado 
            resultadoHijo2 = recorridoArbol(V.getHijo2()); 
        }

        if(V.getId().matches("-?\\d+(\\.\\d+)?")){                    //Si el nodo es un numero, se regresa el mismo
            return Integer.parseInt(V.getId());
        }
        else{                  
            if(V.getId().equals("+")){                                //Si el nodo es una suma, sumamos el resultado de sus dos hijos
                return resultadoHijo1+resultadoHijo2;
            }
            else if(V.getId().equals("-")){                           //Si el nodo es una resta, restamos el resultado de sus dos hijos
                return resultadoHijo1-resultadoHijo2;
            }
            else if(V.getId().equals("*")){                           //Si el nodo es una multiplicacion, multiplicamos el resultado de sus dos hijos
                return resultadoHijo1*resultadoHijo2;
            }
            else if(V.getId().equals("MAX")){                         //Si el nodo es una comparacion MAX, comparamos el resultado de sus dos hijos y retornamos el mayor
                if(resultadoHijo1>=resultadoHijo2){
                    return resultadoHijo1;
                }
                else{
                    return resultadoHijo2;
                }
            }
            else if(V.getId().equals("MIN")){                         //Si el nodo es una comparacion MIN, comparamos el resultado de sus dos hijos y retornamos el menor
                if(resultadoHijo1<=resultadoHijo2){
                    return resultadoHijo1;
                }
                else{
                    return resultadoHijo2;
                }
            }
            else{                                                     //Si el nodo es una Sumatoria, retornamos la sumatoria desde 0 hasta el resultado de su hijo 1.
                if(resultadoHijo1>0){
                    return SumPositivo(resultadoHijo1);                           //SUM solo tiene un hijo. 
                }else{
                    return SumNegativo(resultadoHijo1);                           //SUM solo tiene un hijo. 
                }
            }
        }
    }

    /**
     *
     * En este metodo Realizamos la sumatoria desde el 0 hasta el numero i. Este metodo es utilizado por la operacion SUM en la expresion aritmetica
     *
     * Parametros entrada:
     * @param i = Cota de la sumatoria
     * Parametros salida:
     * @return = resultado de la sumatoria
     */
    public static int SumPositivo(int i){ 
        if(i>0){
            return i+SumPositivo(i-1);
        }
        else{
            return 0;
        }
    }

    /**
     * En este metodo Realizamos la sumatoria desde el el numero i (negativo) hasta el 0. Este metodo es utilizado por la operacion SUM en la expresion aritmetica
     * 
     * Parametros entrada:
     * @param i = Cota de la sumatoria
     * @return = resultado de la sumatoria
     */
    public static int SumNegativo(int i){ 
        if(i<0){
            return i+SumNegativo(i+1);
        }
        else{
            return 0;
        }
    }

    /**
     * Aqui es donde se rellena la Pila de la expresion Aritmetica y donde la mayoria de la ejecucion toma lugar. Manipulamos el String que contiene la expresion
     * Para sacar de el los operandos y las operaciones y agregarlas a la pila para luego agregarlas al arbol.
     * La razon de porque existen varios ciclo es que queremos agregar las operaciones con un orden a la pila. Es decir, primero queremos que se ingresen las
     * sumas, luego las restas, luego las multiplicaciones y luego los MAX, MIN, y SUM para luego crear el arbol de una manera mas facil. La sumas, restas y multiplicaciones
     * que estan dentro de un MAX, MIN, SUM, se agregaran luego de estos en la pila, es por ello que se toma en cuenta si los operadores estan dentro de parentesis
     * o no.
     *
     * Parametros entrada:
     * @param cadena = String que contiene la expresion aritmetica
     * @param pila = pila que rellenaremos con los operandos y operadores de la Expresion Aritmetica
     */
    static void recurrencias(String cadena, ArrayList<String> pila){
        try{

        int i=0;                                                                    //Contador del ciclo
        Boolean done = true;                                                        //Booleano que usaremos cuando hayamos encontrado la operacion buscada
        int parentesisAbiertos = 0;                                                 //Variable que contara la cantidad de parentesis abiertos dependiendo en que caracter estemos en la expresion        
        while(i<cadena.length() && done){                                           //Recorremos la cadena con la expresion
            if(cadena.charAt(i) == '('){                                            //Si encontramos un caracter '(' entonces aumentamos parentesisAbiertos
                parentesisAbiertos++;
            }
            if(cadena.charAt(i) == ')'){                                            //Si encontramos un caracter ')' entonces decrementamos parentesisCerrados
                parentesisAbiertos--;
            }
            if(cadena.charAt(i) == '+' && parentesisAbiertos==0){                   //Si el caracter es un operador + y no esta dentro de ningun parentesis
                pila.add(String.valueOf(cadena.charAt(i)));                         //Se agrega a la pila
                recurrencias(cadena.substring(0,i), pila);                          //Se busca su operando izquierdo
                recurrencias(cadena.substring(i+1, cadena.length()), pila);         //Se busca su operando derecho
                done = false;                                                       //Encontramos el operando/operador buscado
            }
            i++;
        }

        /*Tratamos al operador - ya que al usarlo como unario nos cambia el valor del numero y por ello necesitamos extra precaucion. Por ende tendra su propio ciclo */
        i=0;                                                                        //Reinicio el contador del ciclo
        parentesisAbiertos = 0;                                                     //Reinicio el contador de parentesisAbiertos
        while(i<cadena.length() && done){
            if(cadena.charAt(i) == '('){                                            //Si encontramos un caracter '(' entonces aumentamos parentesisAbiertos
                parentesisAbiertos++;
            }
            if(cadena.charAt(i) == ')'){                                            //Si encontramos un caracter ')' entonces decrementamos parentesisCerrados
                parentesisAbiertos--;
            }
            if(cadena.charAt(i) == '-' && i!=0 && parentesisAbiertos==0){      //Si el caracter es un operador '-' y no esta dentro de ningun parentesis 
                if(String.valueOf(cadena.charAt(i-1)).matches("-?\\d+(\\.\\d+)?")){
                    pila.add(String.valueOf(cadena.charAt(i)));                     //Se agrega a la pila
                    recurrencias(cadena.substring(0,i), pila);                      //Se busca su operando izquierdo
                    recurrencias(cadena.substring(i+1, cadena.length()), pila);     //Se busca su operando derecho
                    done = false;                                                   //Encontramos el operando/operador buscado
                }
            }
            i++;                                                                  
        }

        i=0;                                                                        //Reiniciamos el contador de ciclo
        parentesisAbiertos = 0;                                                     //Reiniciamos el contador de parentesisAbiertos
        while(i<cadena.length() && done){
            if(cadena.charAt(i) == '('){                                            //Si encontramos un caracter '(' entonces aumentamos parentesisAbiertos
                parentesisAbiertos++;
            }
            if(cadena.charAt(i) == ')'){                                            //Si encontramos un caracter ')' entonces decrementamos parentesisCerrados
                parentesisAbiertos--;
            }
            if(cadena.charAt(i) == '*' && parentesisAbiertos==0){                   //Si el caracter es un operador '*' y no esta dentro de ningun parentesis
                pila.add(String.valueOf(cadena.charAt(i)));                         //Se agrega a la pila
                recurrencias(cadena.substring(0,i), pila);                          //Se busca su operando izquierdo
                recurrencias(cadena.substring(i+1, cadena.length()), pila);         //Se busca su operando derecho
                done = false;                                                       //Encontramos el operando/operador buscado
            }
            i++;
        }

        i=0;                                                                        //Reiniciamos el contador de ciclo
        while(i<cadena.length() && done){
            if(cadena.charAt(i) == 'M'){                                    
                if(cadena.charAt(i+1) == 'A'){                                      //Si el operador encontrado es MAX
                    int parentesisAt = -1;                                          //Variable que denotara donde termina el parentesis de este MAX
                    parentesisAbiertos = 0;
                    int comaAt = 0;                                                 //Variable que denotara donde se encuentra la coma que divide los dos operandos
                    for(int j=i; j<cadena.length(); j++){                           //En este ciclo buscamos la locacion de los dos elementos anteriores
                        if(cadena.charAt(j) == '('){
                            parentesisAbiertos++;
                        }
                        if(cadena.charAt(j) == ')'){
                            parentesisAbiertos--;
                            if(parentesisAbiertos==0){
                                parentesisAt = j;
                                break;
                            }
                        }
                        if(cadena.charAt(j) == ',' && parentesisAbiertos ==1){
                            comaAt = j;
                        }
                    }
                    pila.add(cadena.substring(i, i+3));                             //Agregamos la operacion a la pila
                    recurrencias(cadena.substring(i+4, comaAt), pila);              //Se busca su Operando izquierdo
                    recurrencias(cadena.substring(comaAt+1, parentesisAt), pila);   //Se busca su Operando Derecho
                    done = false;                                                   //Encontramos el operando/operador buscado
                }
                else if(cadena.charAt(i+1) == 'I'){                                 //Si el operador encontrado es MIN
                    int parentesisAt = -1;                                          //Variable que denotara donde termina el parentesis de este MIN
                    parentesisAbiertos = 0;                                        
                    int comaAt = 0;                                                 //Variable que denotara donde se encuentra la coma que divide los dos operandos
                    for(int j=i; j<cadena.length(); j++){                           //En este ciclo buscamos la locacion de los dos elementos anteriores
                        if(cadena.charAt(j) == '('){
                            parentesisAbiertos++;
                        }
                        if(cadena.charAt(j) == ')'){
                            parentesisAbiertos--;
                            if(parentesisAbiertos==0){
                                parentesisAt = j;
                                break;
                            }
                        }
                        if(cadena.charAt(j) == ',' && parentesisAbiertos ==1){
                            comaAt = j;
                        }
                    }
                    pila.add(cadena.substring(i, i+3));                             //Agregamos la operacion a la pila
                    recurrencias(cadena.substring(i+4, comaAt), pila);              //Se busca su Operando izquierdo
                    recurrencias(cadena.substring(comaAt+1, parentesisAt), pila);   //Se busca su Operando Derecho
                    done = false;                                                   //Encontramos el operando/operador buscado
                }
            }
            else if(cadena.charAt(i) == 'S'){                                       //Si el operador encontrado es SUM
                int parentesisAt = -1;                                              //Variable que denotara donde termina el parentesis de este SUM
                parentesisAbiertos = 0;
                for(int j=i; j<cadena.length(); j++){                               //En este ciclo encontraremos la locacion de este parentesis
                    if(cadena.charAt(j) == '('){
                        parentesisAbiertos++;
                    }
                    if(cadena.charAt(j) == ')'){
                        parentesisAbiertos--;
                        if(parentesisAbiertos==0){
                            parentesisAt = j;
                            break;
                        }
                    }
                }
                pila.add(cadena.substring(i, i+3));                                 //Agregamos la operacion a la pila
                recurrencias(cadena.substring(i+4, parentesisAt), pila);            //Se busca su unico operando
                done =false;                                                        //Encontramos el operando/operador buscado
            }
            i++;
        }

        if(cadena.matches("-?\\d+(\\.\\d+)?") && !cadena.equals("")){               //Si lo que econtramos es un numero (negativos tambien), lo agregamos a la pila 
            pila.add(cadena);
        }

    }catch(StringIndexOutOfBoundsException e1){
        System.out.println("ERROR: Revisar que las expresiones hayan sido escritas correctamente.");
        System.exit(1);
    }

    }
}