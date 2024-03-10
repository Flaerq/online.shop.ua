package ua.flaer.onlineshop.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.enums.UserType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
