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

import java.util.Arrays;
import java.util.List;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ModelBinder;
import com.dragome.forms.bindings.client.list.ArrayListModel;
import com.dragome.model.VisualButtonImpl;
import com.dragome.model.VisualListBoxImpl;
import com.dragome.model.VisualTextFieldImpl;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.interfaces.VisualListBox;
import com.dragome.model.interfaces.VisualTextField;
import com.dragome.model.listeners.ClickListener;

@PageAlias(alias= "binding-demo")
public class BindingDemo extends DragomeVisualActivity
{
	public void build()
	{
		PersonService personService= serviceFactory.createSyncService(PersonService.class);

		List<Person> persons= personService.getPersons();
		Person person= persons.get(0);

		ModelBinder<Person> binder= new ModelBinder<Person>(person);

		VisualTextFieldImpl<String> textField= new VisualTextFieldImpl<String>("name");
		binder.bindMemberToValueHolder("givenName", textField, String.class);

		VisualListBox<Wine> wineListBox= new VisualListBoxImpl<Wine>("wines", Arrays.asList(Wine.CAB_SAV, Wine.MERLOT, Wine.SHIRAZ));
		wineListBox.setMultipleItems(true);
		wineListBox.setListModel(new ArrayListModel<Wine>(Wine.MERLOT, Wine.SHIRAZ));
		binder.bindListMemberToHasListModel("favoriteWines", wineListBox, Wine.class);

		VisualTextField<Integer> ageTextField= new VisualTextFieldImpl<Integer>("age");
		binder.bindFormattedMemberToValueHolder("age", ageTextField, Integer.class, new AgeFormat());

		VisualButtonImpl button= new VisualButtonImpl("button1", onClick(person));

		mainPanel.addChild(button);
		mainPanel.addChild(textField);
		mainPanel.addChild(ageTextField);
		mainPanel.addChild(wineListBox);
	}

	private ClickListener onClick(final Person person)
	{
		return new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				System.out.println(person.getGivenName());
				System.out.println(person.getAge());
				System.out.println(person.getFavoriteWines());
			}
		};
	}
}
