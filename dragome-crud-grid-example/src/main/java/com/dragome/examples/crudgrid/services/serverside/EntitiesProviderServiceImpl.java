package com.dragome.examples.crudgrid.services.serverside;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dragome.examples.crudgrid.model.Column;
import com.dragome.examples.crudgrid.model.ColumnImpl;
import com.dragome.examples.crudgrid.model.Identifiable;
import com.dragome.examples.crudgrid.model.People;
import com.dragome.examples.crudgrid.model.Place;
import com.dragome.examples.crudgrid.services.EntitiesProviderService;

public class EntitiesProviderServiceImpl implements EntitiesProviderService
{
    protected static EntityManager entityManager= Persistence.createEntityManagerFactory("hsqldb-ds").createEntityManager();

    public <T extends Identifiable> List<T> getAll(Class<T> entityType)
    {
	List<Place> resultList= findAll(Place.class);
	if (resultList.isEmpty())
	    createPeople();

	return findAll(entityType);
    }

    public List<Column> getColumnsFor(Class<?> entityType)
    {
	if (entityType.equals(People.class))
	    return Arrays.asList(new ColumnImpl("id", "col-md-1", true), new ColumnImpl("name", "col-md-2"), new ColumnImpl("place", "col-md-1", true, Place.class));
	else
	    return Arrays.asList(new ColumnImpl("id", "col-md-1", true), new ColumnImpl("name", "col-md-2"));
    }

    private void createPeople()
    {
	EntityTransaction transaction= entityManager.getTransaction();
	transaction.begin();

	entityManager.persist(new People("Diego Maradona", new Place("Villa Fiorito, Buenos Aires, Argentina")));
	entityManager.persist(new People("Ariel Ortega", new Place("Ledesma, Jujuy, Argentina")));
	entityManager.persist(new People("Ricardo Bochini", new Place("Zárate, Buenos Aires, Argentina")));

	transaction.commit();
    }

    private <T> List<T> findAll(Class<T> type)
    {
	CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
	CriteriaQuery<T> criteriaQuery= (CriteriaQuery<T>) criteriaBuilder.createQuery(type);
	Root<T> from= (Root<T>) criteriaQuery.from(type);
	criteriaQuery.orderBy(criteriaBuilder.asc(from.get("id")));
	CriteriaQuery<T> select= criteriaQuery.select(from);
	TypedQuery<T> typedQuery= entityManager.createQuery(select);
	List<T> resultList= typedQuery.getResultList();
	return resultList;
    }

    public <T extends Identifiable> void saveAll(List<T> entities)
    {
	EntityTransaction transaction= entityManager.getTransaction();
	transaction.begin();

	entityManager.createQuery("DELETE FROM People").executeUpdate();
	entityManager.flush();
	transaction.commit();

	entityManager.clear();

	transaction.begin();

	for (T entity : entities)
	{
	    entity.setId(null);
	    entityManager.persist(entity);
	}

	transaction.commit();
    }
 
    public void delete(Identifiable object)
    {
	EntityTransaction transaction= entityManager.getTransaction();
	transaction.begin();
	Object merge= entityManager.merge(object);
	entityManager.remove(merge);
	entityManager.flush();
	transaction.commit();
    }

    public Identifiable add(Identifiable object)
    {
	EntityTransaction transaction= entityManager.getTransaction();
	transaction.begin();
	object.setId(null);
	entityManager.merge(object);
	entityManager.flush();
	transaction.commit();
	return object;
    }

    public void update(Identifiable object)
    {
	EntityTransaction transaction= entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(entityManager.merge(object));
	entityManager.flush();
	transaction.commit();
    }

}
