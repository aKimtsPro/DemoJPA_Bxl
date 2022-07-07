package bstorm.akimts.jpa.daos;

import bstorm.akimts.jpa.entities.Section;
import bstorm.akimts.jpa.exceptions.EntityAlreadyExistsException;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class SectionDAO {

    private final EntityManager manager;

    public SectionDAO(EntityManager manager) {
        this.manager = manager;
    }

    public Optional<Section> getById(int id ){
        return Optional.ofNullable( manager.find(Section.class, id) );
    }

    public List<Section> getAll(){
        TypedQuery<Section> query = manager.createQuery("SELECT s.name FROM Section s", Section.class);
        return query.getResultList();
    }

    public void insert( Section toInsert ){

//        if( toInsert == null )
//            throw new IllegalArgumentException();
//
//        if( existsById(toInsert.getId()) )
//            throw new EntityAlreadyExistsException(toInsert.getId(), Section.class);
//
//        manager.getTransaction().begin();
//        manager.persist(toInsert);
//        manager.getTransaction().commit();

        try{
            beginTransact();
            manager.persist(toInsert);
            commit();
        }
        catch (RollbackException ex){
            throw new EntityAlreadyExistsException(toInsert.getId(), Section.class);
        }

    }

    public void deleteById(int id){

        beginTransact();
        getById(id).ifPresent( manager::remove );
        commit();

    }

    public void delete( Section toDelete ){
        deleteById(toDelete.getId());
    }


    public void update( Section section ){

        if( section == null )
            throw new IllegalArgumentException("section cannot be null");

        if( !existsById(section.getId()) )
            throw new EntityNotFoundException("Entity not found");

        beginTransact();
        manager.merge(section);
        commit();
    }

    public boolean existsById(int id){
        TypedQuery<Integer> query = manager.createQuery("SELECT COUNT(s) FROM Section s WHERE s.id = " + id, Integer.class);
        return query.getSingleResult() > 0;
    }


    private void beginTransact(){
        manager.getTransaction().begin();
    }

    private void commit(){
        manager.getTransaction().commit();
    }

}
