package ua.flaer.onlineshop.exceptions;

public class NoCartItemFoundInCartException extends RuntimeException{

    private String message;

    public NoCartItemFoundInCartException(Long itemId){
        message = "No cart item found in cart with id="+itemId;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
