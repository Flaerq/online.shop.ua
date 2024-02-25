package ua.flaer.onlineshop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.enums.UserType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String address;

    private UserType userType;
}
