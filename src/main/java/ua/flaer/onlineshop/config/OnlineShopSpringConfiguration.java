package ua.flaer.onlineshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.flaer.onlineshop.model.entities.Category;
import ua.flaer.onlineshop.model.entities.User;

import javax.sql.DataSource;

@Configuration
public class OnlineShopSpringConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public BeanPropertyRowMapper<User> userBeanPropertyRowMapper(){
        return new BeanPropertyRowMapper<>(User.class);
    }

    @Bean
    public BeanPropertyRowMapper<Category> categoryBeanPropertyRowMapper(){
        return new BeanPropertyRowMapper<>(Category.class);
    }
}
