package bstorm.akimts.exo;

import bstorm.akimts.exo.entity.Aisle;
import bstorm.akimts.exo.entity.Director;
import bstorm.akimts.exo.entity.Market;
import bstorm.akimts.exo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("exo-jpa");
        EntityManager manager = factory.createEntityManager();

        Market m = new Market("primini", "rue du pognon", "argenville", 420);
        Director d = new Director(m, "Pol", "Hueur", BigDecimal.valueOf(999999.99));
        Product p = new Product("AAAAA", "pomme", "pompidou", 1.F);
        Aisle a = new Aisle(0, m, "légumes");

        manager.getTransaction().begin();

        manager.persist(p);
        m.getProducts().add(p);
        manager.persist(m);
        manager.persist(d);
        manager.merge(a);

        manager.getTransaction().commit();
        factory.close();
    }

}
