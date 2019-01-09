class TransformarDouble implements Transformer<String, Double>{

    public Double Transformar(String input){
        return Double.parseDouble(input);
    }
    
}