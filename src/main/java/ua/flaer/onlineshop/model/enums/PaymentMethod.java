package ua.flaer.onlineshop.model.enums;


import lombok.Getter;

@Getter
public enum PaymentMethod {

    CASH("Cash"),
    CREDIT_CARD("Credit Card");

    private String methodDisplayName;

    PaymentMethod(String methodDisplayName){
        this.methodDisplayName = methodDisplayName;
    }
}
