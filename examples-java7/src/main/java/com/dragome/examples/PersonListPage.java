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

import java.util.List;

import com.dragome.examples.service.PersonService;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualLabelImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.VisualTextFieldImpl;
import com.dragome.guia.components.interfaces.VisualComponent;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.events.listeners.interfaces.ClickListener;
import com.dragome.render.ItemProcessorImpl;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "persons2")
public class PersonListPage extends GuiaVisualActivity
{
    public void build()
    {
	loadMainTemplate("person-list2");
	final Template template= mainTemplate.getChild("panel");

	VisualComponent button= new VisualButtonImpl("button", "Load persons");
	button.addClickListener(new ClickListener()
	{
	    public void clickPerformed(VisualComponent aVisualComponent)
	    {
		showPersons(template);
	    }
	});

	new VisualPanelImpl(template).addChild(button);
    }

    private void showPersons(final Template template)
    {
	PersonService personService= serviceFactory.createSyncService(PersonService.class);
	//	PersonService personService= SyncServices.createSynchronousService(PersonService.class);

	List<Person> persons= personService.getPersons();

	new TemplateRepeater().repeatItems(persons, new ItemProcessorImpl<Person>(template, "row")
	{
	    public void fillTemplates(Person person, List<Template> aRowTemplate)
	    {
		VisualPanel rowPanel= new VisualPanelImpl(aRowTemplate.get(0));
		
		rowPanel.addChild(new VisualLabelImpl<String>("name", person.getGivenName()));
		rowPanel.addChild(new VisualLabelImpl<String>("last-name", person.getSurname()));
		rowPanel.addChild(new VisualTextFieldImpl<String>("nickname", person.getNickname()));
	    }
	});
    }
}
