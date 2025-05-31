package pe.idat.pri.par.productapi.models;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;


    @Column(name = "brand", length = 100, nullable = true)  
    private String brand;

    @Column(name = "code", length = 50, nullable = true)   
    private String code;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "sku", length = 50, unique = true, nullable = false)
    private String sku;

    @Column(name = "price", nullable = false)
    private Double price;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Category> categories;
}
