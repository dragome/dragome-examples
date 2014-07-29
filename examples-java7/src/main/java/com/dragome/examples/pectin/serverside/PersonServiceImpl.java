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
package com.dragome.examples.pectin.serverside;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dragome.examples.pectin.Person;
import com.dragome.examples.pectin.PersonService;
import com.dragome.examples.pectin.Wine;

@SuppressWarnings("unchecked")
public class PersonServiceImpl implements PersonService
{
    public List<Person> getPersons()
    {
	List<Person> persons= new ArrayList<Person>();

	persons.add(new Person("Diego Maradona", Arrays.asList(Wine.MERLOT), true));
	persons.add(new Person("Ariel Ortega", Arrays.asList(Wine.MERLOT, Wine.SHIRAZ), false));
	persons.add(new Person("Ricardo Bochini", Arrays.asList(Wine.CAB_SAV, Wine.SHIRAZ), Boolean.FALSE));

	return persons;
    }
}
