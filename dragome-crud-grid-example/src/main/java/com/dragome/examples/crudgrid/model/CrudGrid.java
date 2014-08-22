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

import com.dragome.forms.bindings.builders.Tester;

public class CrudGrid
{
    private boolean loading;
    private String filter;
    private boolean addMode;
    private Item object;
    private List<Item> objects;
    private Tester<Item> filterTester;

    public Tester<Item> getFilterTester()
    {
        return filterTester;
    }

    public void setFilterTester(Tester<Item> filterTester)
    {
        this.filterTester= filterTester;
    }

    public List<Item> getObjects()
    {
        return objects;
    }

    public void setObjects(List<Item> objects)
    {
        this.objects= objects;
    }

    public boolean isAddMode()
    {
	return addMode;
    }

    public void setAddMode(boolean addMode)
    {
	this.addMode= addMode;
    }

    public String getFilter()
    {
	return filter;
    }

    public void setFilter(String filter)
    {
	this.filter= filter;
    }

    public boolean isLoading()
    {
	return loading;
    }

    public void setLoading(boolean loading)
    {
	this.loading= loading;
    }

    public void toggleAddMode()
    {
	setAddMode(!isAddMode());
    }

    public void toggleEditMode(Item item)
    {
	item.setEditMode(!item.isEditMode());
    }

    public void setOrderBy(Column column)
    {
    }

    public String getOrderBy()
    {
	// TODO Auto-generated method stub
	return null;
    }

    public String getOrder()
    {
	// TODO Auto-generated method stub
	return null;
    }

    public void addObject()
    {
    }

    public Item getItem()
    {
	return object;
    }

    public List<Object> getLookupData()
    {
	return null;
	// TODO Auto-generated method stub
	
    }
    
    
    public void deleteObject(Item item)
    {
    }

    public CrudGrid updateObject(Item item)
    {
	return this;
	// TODO Auto-generated method stub
	
    }
    public List<Column> getColumns()
    {
	return null;
    }

}
