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
package com.dragome.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.model.Person;
import com.dragome.examples.service.PersonService;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.builders.ObservableList;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.interfaces.VisualTextField;

@PageAlias(alias= "crud2")
public class PersonCrudUsingBuilders extends DragomeVisualActivity
{
	List<Person> persons= new ObservableList<Person>(new ArrayList<Person>());

	public void build()
	{
		PersonService personService= serviceFactory.createSyncService(PersonService.class);
		persons.addAll(personService.getPersons());
		ComponentBuilder<PersonCrudUsingBuilders> componentBuilder= new ComponentBuilder<PersonCrudUsingBuilders>(mainPanel, this);
		componentBuilder.bindTemplate("save-button").as(VisualButton.class).onClick(() -> personService.savePersons(persons)).build();
		componentBuilder.bindTemplate("add-button").as(VisualButton.class).onClick(() -> persons.add(new Person())).build();

		componentBuilder.bindTemplate("row").as(VisualPanel.class).toList(persons).repeat((person, b) -> {
			b.bindTemplate("givenName").as(VisualTextField.class).toProperty(Person::getGivenName, Person::setGivenName).build();
			b.bindTemplate("surname").as(VisualTextField.class).toProperty(Person::getSurname, Person::setSurname).build();
			b.bindTemplate("complete-name").as(VisualLabel.class).to(() -> person.getGivenName() + " " + person.getSurname()).build();
			b.bindTemplate("nickname").to(new VisualComboBoxImpl<String>("nickname", Arrays.asList("Pelusa", "Burrito", "Bocha", "Bruja"))).toProperty(Person::getNickname, Person::setNickname).build();
			b.bindTemplate("delete-button").as(VisualButton.class).onClick(() -> persons.remove(person)).build();
		});
	}
}
