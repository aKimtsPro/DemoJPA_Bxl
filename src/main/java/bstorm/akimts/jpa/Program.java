package bstorm.akimts.jpa;

import bstorm.akimts.jpa.entities.Section;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        EntityManager manager = emf.createEntityManager();

        // GET ONE
        Section s = manager.find(Section.class, 1010);
        System.out.println( s );

        // GET ALL
        TypedQuery<Section> query = manager.createQuery( "SELECT s FROM Section s", Section.class );
        List<Section> list = query.getResultList();

        list.forEach( System.out::println );

        // INSERT
        Section toInsert = new Section( 3, "to delete", null );

        manager.getTransaction().begin();
        manager.persist( toInsert );
        manager.getTransaction().commit();

        // DELETE
        manager.getTransaction().begin();
        manager.remove( toInsert );
        manager.getTransaction().commit();

        // UPDATE
        manager.getTransaction().begin();
        s.setDelegateId(25);
        manager.getTransaction().commit();


        manager.getTransaction().begin();
        Section s1 = manager.find(Section.class, 1);
        manager.detach(s1);
        s1.setId(9);
        manager.persist(s1);
        s1.setDelegateId(5);
        Section s2 = manager.merge(s1);

        manager.flush(); // ecrit les modifs
        manager.clear();
        manager.getTransaction().commit(); // applique les modifs


        emf.close();

    }

}
