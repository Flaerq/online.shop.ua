package ua.flaer.onlineshop.dao.impls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.flaer.onlineshop.model.User;
import ua.flaer.onlineshop.model.enums.UserType;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTests {

    @Mock
    private JdbcTemplate template;

    @InjectMocks
    private UserDaoImpl userDaoImpl;

    @Test
    public void isUserSaves(){
        String sql = """
            INSERT INTO users(user_type, first_name, last_name, email, phone_number, password, address)
            VALUES 
            (?, ?, ?, ?, ?, ?, ?);
                """;

        User user = User.builder()
                .id(10l)
                .firstName("John")
                .lastName("Ferd")
                .email("test@gmail.com")
                .password("qwerty")
                .address("New York")
                .phoneNumber("+3999999999")
                .userType(UserType.USER)
                .build();

        userDaoImpl.save(user);

        verify(template).update(eq(sql),
                eq(UserType.USER),
                eq("John"),
                eq("Ferd"),
                eq("test@gmail.com"),
                eq("+3999999999"),
                eq("qwerty"),
                eq("New York"));
    }
}
