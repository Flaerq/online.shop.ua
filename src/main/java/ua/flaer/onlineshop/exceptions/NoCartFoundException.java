package ua.flaer.onlineshop.exceptions;

public class NoCartFoundException extends RuntimeException{

    private String message;

    public NoCartFoundException(Long cartId) {
        message = "No cart found with id="+ cartId;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
