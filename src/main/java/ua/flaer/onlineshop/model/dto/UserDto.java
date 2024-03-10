package ua.flaer.onlineshop.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.enums.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String address;

    private UserType userType;

}
