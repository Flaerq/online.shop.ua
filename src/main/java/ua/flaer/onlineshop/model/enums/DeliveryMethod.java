package ua.flaer.onlineshop.model.enums;


import lombok.Getter;

@Getter
public enum DeliveryMethod {
    STANDARD("Standard"),
    EXPRESS("Express"),
    PICKUP("Pickup");

    private final String displayName;

    DeliveryMethod(String displayName) {
        this.displayName = displayName;
    }
}
