package ua.flaer.onlineshop.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.dao.interfaces.UserDao;
import ua.flaer.onlineshop.model.User;

@Component
public class UserDaoImpl implements UserDao {


    private JdbcTemplate template;

    public UserDaoImpl(JdbcTemplate template){
        this.template = template;
    }


    @Override
    public void save(User user) {
        String sql = """
            INSERT INTO users(user_type, first_name, last_name, email, phone_number, password, address)
            VALUES 
            (?, ?, ?, ?, ?, ?, ?);
                """;

        template.update(sql, user.getUserType(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getAddress());
    }
}
