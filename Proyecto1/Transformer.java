public interface Transformer<I,O>{

    /*Creamos esta interfaz para ayudarnos a hacer el cambio de String a Tipo Generico 
    *a la hora de Agregar Vertices y agregar Lados
    */
    public O Transformar(I input);          
    
}