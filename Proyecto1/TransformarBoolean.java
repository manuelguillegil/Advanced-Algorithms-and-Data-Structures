class TransformarBoolean implements Transformer<String, Boolean>{

    public Boolean Transformar(String input){
        return Boolean.valueOf(input);
    }
    
}