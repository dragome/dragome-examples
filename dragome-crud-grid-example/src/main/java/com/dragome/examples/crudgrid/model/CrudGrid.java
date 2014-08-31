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
package com.dragome.examples.crudgrid.model;

import java.util.ArrayList;
import java.util.List;

import com.dragome.examples.crudgrid.services.EntitiesProviderService;
import com.dragome.forms.bindings.builders.Getter;
import com.dragome.forms.bindings.builders.ObservableList;
import com.dragome.forms.bindings.builders.Order;
import com.dragome.forms.bindings.builders.Tester;
import com.dragome.services.ServiceLocator;

public class CrudGrid
{
	private boolean loading= true;
	private String filter= "";
	private boolean addMode;
	private List<Item> objects;
	private Item item;
	private List<Column> columns;
	private Tester<Item> filterTester= updateFilterTester();
	private Column orderColumn;
	private EntitiesProviderService entitiesProviderService= ServiceLocator.getInstance().getServiceFactory().createSyncService(EntitiesProviderService.class);
	private Class<?> entityType;

	public CrudGrid(Class<?> entityType)
	{
		this.entityType= entityType;
		List<Identifiable> all= entitiesProviderService.getAll((Class) entityType);
		List<Item> result= new ArrayList<Item>();
		for (Identifiable object : all)
			result.add(new ItemImpl(object));

		objects= new ObservableList<Item>(result);
		columns= new ObservableList<Column>(entitiesProviderService.getColumnsFor(entityType));

		item= initItem();
		orderColumn= columns.get(0);

		setLoading(false);
	}

	public void addObject()
	{
		getItems().add(item);
		Identifiable added= entitiesProviderService.add(item.getObject());
		item.getObject().setId(added.getId());
		toggleAddMode();
	}

	public void deleteObject(Item item)
	{
		objects.remove(item);
		entitiesProviderService.delete(item.getObject());
	}

	public List<Column> getColumns()
	{
		return columns;
	}

	public String getFilter()
	{
		return filter;
	}

	public Tester<Item> getFilterTester()
	{
		return filterTester;
	}

	public Item getItem()
	{
		return item;
	}

	public List<Item> getItems()
	{
		return objects;
	}

	public List<Object> getLookupData(Class<?> lookupEntityType)
	{
		if (lookupEntityType == null)
			return new ArrayList<Object>();
		else
			return entitiesProviderService.getAll((Class) lookupEntityType);
	}

	public Column getOrderColumn()
	{
		return orderColumn;
	}

	private ItemImpl initItem()
	{
		Identifiable classInstance= (Identifiable) ServiceLocator.getInstance().getReflectionService().createClassInstance(entityType);

		return new ItemImpl(classInstance);
	}

	public boolean isAddMode()
	{
		return addMode;
	}
	public boolean isLoading()
	{
		return loading;
	}

	public void setAddMode(boolean addMode)
	{
		this.addMode= addMode;
	}

	public void setFilter(String filter)
	{
		this.filter= filter;
		setFilterTester(updateFilterTester());
	}

	public void setFilterTester(Tester<Item> filterTester)
	{
		this.filterTester= filterTester;
		setItems(getItems());
	}

	public void setItem(Item item)
	{
		this.item= item;
	}

	public void setLoading(boolean loading)
	{
		this.loading= loading;
	}

	public void setItems(List<Item> objects)
	{
		this.objects= objects;
	}

	public void setOrderColumn(Column column)
	{
		if (this.orderColumn == column)
			this.orderColumn.setOrder(this.orderColumn.getOrder() == Order.ASC ? Order.DESC : Order.ASC);
		else
			this.orderColumn= column;

		setItems(getItems());
	}

	public void toggleAddMode()
	{
		setAddMode(!isAddMode());
		setItem(initItem());
	}

	public void toggleEditMode(Item item)
	{
		item.setEditMode(!item.isEditMode());
	}

	private Tester<Item> updateFilterTester()
	{
		return new Tester<Item>()
		{
			public boolean test(Item t)
			{
				String serialize= ServiceLocator.getInstance().getSerializationService().serialize(t);
				return serialize.contains(filter);
			}
		};
	}
	public CrudGrid updateObject(Item item)
	{
		entitiesProviderService.update(item.getObject());
		return this;
	}

	public Getter<Item, Comparable<?>> getColumnValueGetter()
	{
		return new Getter<Item, Comparable<?>>()
		{
			public Comparable<?> get(Item obj)
			{
				return (Comparable) ServiceLocator.getInstance().getReflectionService().getPropertyValue(obj.getObject(), orderColumn.getName());
			}
		};
	}

}
