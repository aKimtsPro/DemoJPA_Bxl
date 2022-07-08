package bstorm.akimts.exo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false, length = 5)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private Set<Market> markets = new LinkedHashSet<>();

    public Product(String id, String name, String brand, float price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = BigDecimal.valueOf(price);
    }
}