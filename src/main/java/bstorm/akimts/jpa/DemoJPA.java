package bstorm.akimts.jpa;

import bstorm.akimts.jpa.entities.Section;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class DemoJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();


        Section s = new Section(0, "delete me");
        s = manager.merge(s);
//        manager.flush();

        try {
            Query q = manager.createNativeQuery("SELECT COUNT( section_id ) FROM section");
            long nbrSections = (long) q.getSingleResult();

            manager.getTransaction().commit();
        }
        catch (Throwable t){
            t.printStackTrace();
            manager.getTransaction().rollback();
        }


        emf.close();

    }
}
