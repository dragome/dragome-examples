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

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.service.PersonService;
import com.dragome.model.VisualButtonImpl;
import com.dragome.model.VisualLabelImpl;
import com.dragome.model.VisualPanelImpl;
import com.dragome.model.VisualTextFieldImpl;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.listeners.ClickListener;
import com.dragome.render.ItemProcessorImpl;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;

@PageAlias(alias= "persons2")
public class PersonListPage extends DragomeVisualActivity
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
