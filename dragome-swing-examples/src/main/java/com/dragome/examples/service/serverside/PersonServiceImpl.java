/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.examples.service.serverside;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dragome.examples.model.Person;
import com.dragome.examples.service.PersonService;

public class PersonServiceImpl implements PersonService
{
	protected static EntityManager entityManager= Persistence.createEntityManagerFactory("hsqldb-ds").createEntityManager();

	public List<Person> getPersons()
	{
		List<Person> resultList= findAll();
		if (resultList.isEmpty())
		{
			createPersons();
			resultList= findAll();
		}

		return resultList;
	}

	private List<Person> findAll()
	{
		CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery= criteriaBuilder.createQuery(Person.class);
		Root<Person> from= criteriaQuery.from(Person.class);
		//	criteriaQuery.orderBy(criteriaBuilder.asc(from.get("nickname")));
		CriteriaQuery<Person> select= criteriaQuery.select(from);
		TypedQuery<Person> typedQuery= entityManager.createQuery(select);
		List<Person> resultList= typedQuery.getResultList();
		return resultList;
	}

	private void createPersons()
	{
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(new Person("Diego Maradona", "Pelusa"));
		entityManager.persist(new Person("Ariel Ortega", "Burrito"));
		entityManager.persist(new Person("Ricardo Bochini", "Bocha"));

		transaction.commit();
	}

	public void savePersons(List<Person> persons)
	{
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();

		entityManager.createQuery("DELETE FROM Person").executeUpdate();
		entityManager.flush();
		transaction.commit();

		entityManager.clear();

		transaction.begin();

		for (Person person : persons)
        {
			person.setId(0);
	        entityManager.persist(person);
        }

		transaction.commit();
	}
}
