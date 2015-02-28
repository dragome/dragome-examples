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
package com.dragome.examples.pectin;

import java.util.List;

import com.dragome.forms.bindings.client.bean.AbstractBeanModelProvider;
import com.dragome.forms.bindings.client.value.ValueHolder;
import com.dragome.forms.bindings.reflect.ReflectionBeanModelProvider;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.templates.TemplateLayout;
import com.dragome.templates.interfaces.Template;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "pectin")
public class PectinDemo1 extends GuiaVisualActivity
{
	public void build()
	{
		loadMainTemplate("PectinDemo2");
		//	PersonService personService= ServiceLocator.getClientSideRemoteObjectsService().createRemoteService(PersonService.class);
		PersonService personService= serviceFactory.createSyncService(PersonService.class);

		List<Person> persons= personService.getPersons();
		Person person= persons.get(0);

		Template template= mainTemplate.getChild("panel").getChild("row");

		VisualPanel panel= new VisualPanelImpl(new TemplateLayout(template));
		addPectinPart(person, panel);

	}

	private void addPectinPart(Person person, VisualPanel panel)
	{
		AbstractBeanModelProvider<Person> personProvider= new ReflectionBeanModelProvider<Person>(Person.class);
		personProvider.setBeanSource(new ValueHolder<Person>(person));
		personProvider.setAutoCommit(true);

		PersonFormModel personFormModel= new PersonFormModel(personProvider);
		new PersonForm(personFormModel, panel);
	}
}
