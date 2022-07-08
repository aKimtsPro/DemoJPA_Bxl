package bstorm.akimts.exo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "number", nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "market")
    private Set<Aisle> aisles = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "market_product",
            joinColumns = @JoinColumn(name = "market_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new LinkedHashSet<>();

    @OneToOne(mappedBy = "market")
    private Director director;

    public Market(String name, String street, String city, Integer number) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.number = number;
    }
}