package ua.flaer.onlineshop.model.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW("New"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    RETURNED("Returned");

    private String statusDisplayName;

    OrderStatus(String statusDisplayName){
        this.statusDisplayName = statusDisplayName;
    }


}
