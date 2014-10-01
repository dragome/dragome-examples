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
package com.dragome.examples.crudgrid.view;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.crudgrid.model.People;
import com.dragome.examples.crudgrid.model.Place;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.templates.interfaces.Template;

@PageAlias(alias= "crudgrid")
public class GridCrudExamplePage extends DragomeVisualActivity
{
	public void build()
	{
		Template gridTemplate= templateHandlingStrategy.loadTemplate("grid");

		ComponentBuilder builder= new ComponentBuilder(mainPanel);

		builder.bindTemplate("people").to(new CrudGridComponent(gridTemplate, People.class)).build();
		builder.bindTemplate("place").to(new CrudGridComponent(gridTemplate, Place.class)).build();
	}
}
