package ua.flaer.onlineshop.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.entities.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn(columnDefinition = "category_id",referencedColumnName = "id")
    private Category category;

}
