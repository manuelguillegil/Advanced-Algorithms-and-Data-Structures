import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**Estudiantes:
 * Pietro Iaia 15-10718
 * Manuel Guillermo Gil 14-10397
 */


 // ARREGLAR LAS LLAMADAS A EL GRAFO
 //
 //
 //
 
class GrafoNoDirigido{

    /*Atributos de Clase GrafoNoDirigido
    * Grafo: Sera el ArrayList de Vertices que contendra cada Vertice del Grafo
    * Nombre: Nombre del Grafo, nos servira para ver cual caso se representa en el grafo
    */
    ArrayList<Vertice> Vertices;
    ArrayList<Arista> Aristas;
    String Nombre;

    /*Constructor de la clase GrafoNoDirigido
    */
    public void CrearGrafoNoDirigido(String Nombre){
        Vertices = new ArrayList<Vertice>();
        Aristas = new ArrayList<Arista>();
        this.Nombre = Nombre;
    }

    /*Restorna el identificador de el grafo
    */
    public String GetNombre(){
        return this.Nombre;
    }

    /*Agrega una nueva arista al grafo 
    */
    public void AgregarArista(Arista a){
        this.Aristas.add(a);
    }

    /*Elimina la arista en el grafo que esté identificada con id. 
    * 
    */
    public void EliminarArista(int id){

        for(int i=0; i<this.Aristas.size(); i++){
            if(this.Aristas.get(i).GetId() == id){
                this.Aristas.remove(i);
            }
        }

    }


    /*Descrito en la interfaz Grafo.java*/
    public void AgregarVertice(Vertice V){
        this.Vertices.add(V);
    }
}