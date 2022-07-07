package bstorm.akimts.jpa;

import bstorm.akimts.jpa.daos.SectionDAO;
import bstorm.akimts.jpa.entities.Course;
import bstorm.akimts.jpa.entities.Section;
import bstorm.akimts.jpa.entities.Student;
import bstorm.akimts.jpa.exceptions.EntityAlreadyExistsException;
import jakarta.persistence.*;

import java.util.List;

public class ExoDao {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        EntityManager manager = emf.createEntityManager();

        Course c = new Course("EG1010", "nom",0, 0 , null);
        Section s = manager.find(Section.class, 1010);

        manager.getTransaction().begin();
//        c.setSections( List.of( s ) ); // j'ajoute les sections aux cours // ne fonctionne pas
//        s.setCourses( List.of( c ) ); // j'ajoute les cours aux sections // fonctionne

        s.getCourses().add(c); // ???
        manager.getTransaction().commit();


        s = manager.find(Section.class, 1010);
        System.out.println( s );

        emf.close();

    }

}
