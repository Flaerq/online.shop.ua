package ua.flaer.onlineshop.exceptions;

public class NoProductFoundException extends RuntimeException{

    private String message;

    public NoProductFoundException(Long productId){
        message = "No product is found with id="+productId;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
