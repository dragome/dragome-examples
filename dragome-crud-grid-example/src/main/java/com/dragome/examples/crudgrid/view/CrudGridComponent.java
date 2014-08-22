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

import com.dragome.examples.crudgrid.model.CrudGrid;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.VisualPanelImpl;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.interfaces.VisualTextField;
import com.dragome.templates.interfaces.Template;

public class CrudGridComponent extends VisualPanelImpl
{
    public CrudGridComponent(Template template)
    {
	super(template);

	CrudGrid crudGrid= new CrudGrid();
	ComponentBuilder componentBuilder= new ComponentBuilder(this);

	componentBuilder.bindTemplate("loading").as(VisualLabel.class).toProperty(crudGrid::isLoading, crudGrid::setLoading).build();
	componentBuilder.bindTemplate("filter").as(VisualTextField.class).toProperty(crudGrid::getFilter, crudGrid::setFilter).build();
	componentBuilder.bindTemplate("remove-filter").as(VisualPanel.class).to(() -> crudGrid.getFilter().length() == 0).onClick(v -> crudGrid.setFilter("")).build();
	componentBuilder.bindTemplate("add-mode-toggler").as(VisualLabel.class).onClick(v -> crudGrid.toggleAddMode()).styleWith("glyphicon-minus", "glyphicon-plus").accordingTo(() -> crudGrid.isAddMode()).build();

	componentBuilder.bindTemplate("table-header").as(VisualPanel.class).toList(crudGrid.getColumns()).repeat((column, builder) -> {
	    builder.parentBuilder().onClick(() -> crudGrid.setOrderBy(column));
	    builder.parentBuilder().styleWith(column.getStyleName()).when(() -> crudGrid.isAddMode());
	    builder.bindTemplate("order-icon").as(VisualLabel.class).styleWith("glyphicon-sort-by-alphabet", "glyphicon-sort-by-alphabet-alt").accordingTo(() -> crudGrid.getOrder().equals("asc")).showWhen(() -> crudGrid.getOrderBy().equals(column.getName())).build();
	});

	ComponentBuilder childBuilder= componentBuilder.bindTemplate("add-section").as(VisualPanel.class).showWhen(crudGrid::isAddMode).childBuilder();
	childBuilder.bindTemplate("save-button").as(VisualButton.class).onClick(() -> crudGrid.addObject()).build();
	childBuilder.bindTemplate("remove-button").as(VisualButton.class).onClick(() -> crudGrid.toggleAddMode()).build();

	childBuilder.bindTemplate("columns").as(VisualPanel.class).toList(crudGrid.getColumns()).repeat((column, builder) -> {
	    builder.parentBuilder().switchUsing(() -> !column.isLookup());
	    builder.bindTemplate("input").as(VisualTextField.class).toProperty(crudGrid.getItem().getObject(), column.getName()).switchDefault().disableWhen(() -> column.isAutoincrement());
	    builder.bindTemplate("select").to(new VisualComboBoxImpl<>(crudGrid.getLookupData())).toProperty(crudGrid.getItem().getObject(), column.getName()).switchWhen(() -> false).showWhen(() -> column.isLookup());
	});

	componentBuilder.bindTemplate("objects").as(VisualPanel.class).toListProperty(crudGrid::getObjects).filter(crudGrid::getFilterTester).repeat((item, itemBuilder) -> {
	    ComponentBuilder toolbarChildrenBuilder= itemBuilder.bindTemplate("toolbar").as(VisualPanel.class).switchUsing(() -> !item.isEditMode()).childBuilder();

	    ComponentBuilder viewModeBuilder= toolbarChildrenBuilder.bindTemplate("view-mode").as(VisualPanel.class).switchDefault().childBuilder();
	    viewModeBuilder.bindTemplate("edit").as(VisualLabel.class).onClick(() -> crudGrid.toggleEditMode(item)).build();
	    viewModeBuilder.bindTemplate("trash").as(VisualLabel.class).onClick(() -> crudGrid.deleteObject(item)).build();

	    ComponentBuilder editModeBuilder= toolbarChildrenBuilder.bindTemplate("edit-mode").as(VisualPanel.class).switchWhen(() -> false).childBuilder();
	    editModeBuilder.bindTemplate("save").as(VisualLabel.class).onClick(() -> crudGrid.updateObject(item).toggleEditMode(item)).build();
	    editModeBuilder.bindTemplate("remove").as(VisualLabel.class).onClick(() -> crudGrid.toggleEditMode(item)).build();

	    itemBuilder.bindTemplate("columns").as(VisualPanel.class).switchUsing(() -> item.isEditMode()).toList(crudGrid.getColumns()).repeat((column, columnBuilder) -> {
		ComponentBuilder editModePanelBuilder= columnBuilder.bindTemplate("edit-mode").as(VisualPanel.class).switchDefault().switchWhen(() -> column.isLookup()).onClick(() -> crudGrid.toggleEditMode(item)).childBuilder();
		editModePanelBuilder.bindTemplate("not-lookup").as(VisualLabel.class).switchDefault().toProperty(item.getObject(), column.getName()).build();
		editModePanelBuilder.bindTemplate("lookup").as(VisualLabel.class).switchWhen(() -> false).toProperty(item.getObject(), column.getName()).build();

		ComponentBuilder viewModePanelBuilder= columnBuilder.bindTemplate("view-mode").as(VisualPanel.class).switchWhen(() -> true).switchUsing(() -> !column.isLookup()).childBuilder();
		viewModePanelBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(item.getObject(), column.getName()).switchDefault().disableWhen(() -> column.isAutoincrement()).build();
		viewModePanelBuilder.bindTemplate("select").to(new VisualComboBoxImpl<>(crudGrid.getLookupData())).toProperty(item.getObject(), column.getName()).switchWhen(() -> false).disableWhen(() -> column.isAutoincrement()).build();
	    });
	});
    }
}
