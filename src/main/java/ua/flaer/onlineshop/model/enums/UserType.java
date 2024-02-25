package ua.flaer.onlineshop.model.enums;


import lombok.Getter;

@Getter
public enum UserType {

    USER("User"),
    ADMIN("Admin");

    private String typeDisplayName;

    UserType(String typeDisplayName){
        this.typeDisplayName = typeDisplayName;
    }




}
