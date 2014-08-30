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
import com.dragome.forms.bindings.builders.Order;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.VisualPanelImpl;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.interfaces.VisualTextField;
import com.dragome.services.ServiceLocator;
import com.dragome.templates.TemplateLayout;
import com.dragome.templates.interfaces.Template;

public class CrudGridComponent extends VisualPanelImpl
{
    private Class<?> entityType;

    public CrudGridComponent(Template template, Class<?> entityType)
    {
	this.entityType= entityType;
	Template clonedTemplate= ServiceLocator.getInstance().getTemplateHandler().clone(template);
	initLayout(new TemplateLayout(clonedTemplate));
    }
    
    public void setParent(VisualPanel parent)
    {
        super.setParent(parent);

        CrudGrid crudGrid= new CrudGrid(entityType);
	ComponentBuilder componentBuilder= new ComponentBuilder(this);

	componentBuilder.bindTemplate("loading").as(VisualLabel.class).showWhen(crudGrid::isLoading).build();
	componentBuilder.bindTemplate("filter").as(VisualTextField.class).toProperty(crudGrid::getFilter, crudGrid::setFilter).build();
	componentBuilder.bindTemplate("remove-filter").as(VisualLabel.class).disableWhen(() -> crudGrid.getFilter().length() == 0).onClick(v -> crudGrid.setFilter("")).build();
	componentBuilder.bindTemplate("add-mode-toggler").as(VisualLabel.class).onClick(v -> crudGrid.toggleAddMode()).styleWith("glyphicon-minus", "glyphicon-plus").accordingTo(() -> crudGrid.isAddMode()).build();

	componentBuilder.bindTemplate("table-header").as(VisualPanel.class).toList(crudGrid.getColumns()).repeat((column, builder) -> {
	    builder.onClick(() -> crudGrid.setOrderColumn(column)).build();
	    builder.styleWith(column.getStyleName()).when(() -> true);
	    builder.bindTemplate("column-name").as(VisualLabel.class).to(() -> column.getName()).build();
	    builder.bindTemplate("order-icon").as(VisualLabel.class).styleWith("glyphicon-sort-by-alphabet", "glyphicon-sort-by-alphabet-alt").accordingTo(() -> crudGrid.getOrderColumn().getOrder().equals(Order.ASC)).showWhen(() -> crudGrid.getOrderColumn() == column).build();
	});

	componentBuilder.bindTemplate("add-section").as(VisualPanel.class).showWhen(crudGrid::isAddMode).buildChildren(childrenBuilder -> {

	    childrenBuilder.bindTemplate("save-button").as(VisualButton.class).onClick(() -> crudGrid.addObject()).build();
	    childrenBuilder.bindTemplate("remove-button").as(VisualButton.class).onClick(() -> crudGrid.toggleAddMode()).build();

	    childrenBuilder.bindTemplate("columns").as(VisualPanel.class).toList(crudGrid.getColumns()).repeat((column, builder) -> {
		builder.switchUsing(() -> !column.isLookup()).buildChildren(columnBuilder -> {
		    columnBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(() -> crudGrid.getItem().getObject(), column.getName()).switchDefault().disableWhen(() -> column.isAutoincrement()).build();
		    columnBuilder.bindTemplate("select").to(new VisualComboBoxImpl<>(crudGrid.getLookupData(column.getLookupEntityType()))).toProperty(() -> crudGrid.getItem().getObject(), column.getName()).switchWhen(() -> false).showWhen(() -> column.isLookup()).build();
		});
	    });
	});

	componentBuilder.bindTemplate("objects").as(VisualPanel.class).toListProperty(crudGrid::getItems).orderBy(crudGrid.getColumnValueGetter(), () -> crudGrid.getOrderColumn().getOrder()).filter(crudGrid::getFilterTester).repeat((item, itemBuilder) -> {

	    itemBuilder.bindTemplate("toolbar").as(VisualPanel.class).switchUsing(() -> !item.isEditMode()).buildChildren(toolbarChildrenBuilder -> {
		toolbarChildrenBuilder.bindTemplate("view-mode").as(VisualPanel.class).switchDefault().buildChildren(childrenBuilder -> {
		    childrenBuilder.bindTemplate("edit").as(VisualLabel.class).onClick(() -> crudGrid.toggleEditMode(item)).build();
		    childrenBuilder.bindTemplate("trash").as(VisualLabel.class).onClick(() -> crudGrid.deleteObject(item)).build();
		});

		toolbarChildrenBuilder.bindTemplate("edit-mode").as(VisualPanel.class).switchWhen(() -> false).buildChildren(childrenBuilder -> {
		    childrenBuilder.bindTemplate("save").as(VisualLabel.class).onClick(() -> crudGrid.updateObject(item).toggleEditMode(item)).build();
		    childrenBuilder.bindTemplate("remove").as(VisualLabel.class).onClick(() -> crudGrid.toggleEditMode(item)).build();
		});
	    });

	    itemBuilder.bindTemplate("columns").as(VisualPanel.class).toList(crudGrid.getColumns()).repeat((column, columnBuilder) -> {

		columnBuilder.switchUsing(() -> item.isEditMode()).buildChildren(columnChildrenBuilder -> {

		    columnChildrenBuilder.bindTemplate("view-mode").as(VisualPanel.class).switchDefault().onClick(() -> crudGrid.toggleEditMode(item)).switchUsing(() -> column.isLookup()).buildChildren(editModePanelBuilder -> {
			editModePanelBuilder.bindTemplate("not-lookup").as(VisualLabel.class).switchDefault().toProperty(item.getObject(), column.getName()).build();
			editModePanelBuilder.bindTemplate("lookup").as(VisualLabel.class).switchWhen(() -> false).toProperty(item.getObject(), column.getName()).build();
		    });

		    columnChildrenBuilder.bindTemplate("edit-mode").as(VisualPanel.class).switchWhen(() -> true).switchUsing(() -> !column.isLookup()).buildChildren(viewModePanelBuilder -> {
			viewModePanelBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(item.getObject(), column.getName()).switchDefault().disableWhen(() -> column.isAutoincrement()).build();
			viewModePanelBuilder.bindTemplate("select").to(new VisualComboBoxImpl<>(crudGrid.getLookupData(column.getLookupEntityType()))).toProperty(item.getObject(), column.getName()).switchWhen(() -> false).disableWhen(() -> column.isAutoincrement()).build();
		    });
		});
	    });
	});
    }
}
