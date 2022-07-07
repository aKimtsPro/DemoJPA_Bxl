package bstorm.akimts.jpa;

import bstorm.akimts.jpa.daos.SectionDAO;
import bstorm.akimts.jpa.entities.Section;
import bstorm.akimts.jpa.exceptions.EntityAlreadyExistsException;
import jakarta.persistence.*;

public class ExoDao {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        EntityManager manager = emf.createEntityManager();

        SectionDAO dao = new SectionDAO( manager );
        Section s = dao.getById(1010).orElse(null);

        manager.detach(s);

//        dao.delete(s);
        dao.insert(null);
//        dao.update( s );


        try{
            dao.insert(s);
        }
        catch (EntityAlreadyExistsException ex) {
            System.out.println( ex.getMessage() );
        }

        dao.deleteById(0);

        emf.close();

    }

}
