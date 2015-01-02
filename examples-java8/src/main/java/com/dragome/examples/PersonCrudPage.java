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
import com.dragome.examples.model.Person;
import com.dragome.examples.service.PersonService;
import com.dragome.forms.bindings.builders.ModelBinder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualComboBoxImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.VisualTextFieldImpl;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;

@PageAlias(alias= "crud1")
public class PersonCrudPage extends GuiaVisualActivity
{
	PersonService personService= serviceFactory.createSyncService(PersonService.class);
	List<Person> persons= new ArrayList<Person>();

	public void build()
	{
		mainPanel.addChild(new VisualButtonImpl("save-button", v -> personService.savePersons(persons)));
		mainPanel.addChild(new VisualButtonImpl("add-button", v -> showPersons(Arrays.asList(new Person()))));
		showPersons(personService.getPersons());
	}

	private void showPersons(final List<Person> somePersons)
	{
		persons.addAll(somePersons);
		new TemplateRepeater<Person>(somePersons, mainTemplate, "row", this::fillTemplate, false).repeatItems();
	}

	public void fillTemplate(final Person person, Template itemTemplate)
	{
		final VisualPanel rowPanel= new VisualPanelImpl(itemTemplate);
		mainPanel.addChild(rowPanel);

		rowPanel.addChild(new VisualButtonImpl("delete-button", v -> {
			persons.remove(person);
			rowPanel.getParent().removeChild(rowPanel); 
		}));

		ModelBinder<Person> modelBinder= new ModelBinder<Person>(person, rowPanel);
		modelBinder.bindToPanel(new VisualTextFieldImpl<String>("givenName"));
		modelBinder.bindToPanel(new VisualTextFieldImpl<String>("surname"));
		modelBinder.bindToPanel(new VisualComboBoxImpl<String>("nickname", Arrays.asList("Pelusa", "Burrito", "Bocha", "Bruja")));
	}
}
