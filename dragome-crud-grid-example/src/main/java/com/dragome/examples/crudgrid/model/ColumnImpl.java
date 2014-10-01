package com.dragome.examples.crudgrid.model;

import com.dragome.forms.bindings.builders.Order;

public class ColumnImpl implements Column
{
	private String name;

	public boolean isAutoIncrement()
	{
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement)
	{
		this.autoIncrement= autoIncrement;
	}

	public void setName(String name)
	{
		this.name= name;
	}

	public void setStyleName(String styleName)
	{
		this.styleName= styleName;
	}

	public void setLookup(boolean lookup)
	{
		this.lookup= lookup;
	}

	private String styleName= "column-style";
	private Order order= Order.ASC;
	private boolean autoIncrement;
	private boolean lookup;
	private Class<?> lookupEntityType;

	public void setLookupEntityType(Class<?> lookupEntityType)
	{
		this.lookupEntityType= lookupEntityType;
	}

	public ColumnImpl()
	{
	}

	public ColumnImpl(String name, String styleName)
	{
		this.name= name;
		this.styleName= styleName;
	}

	public ColumnImpl(String name, String styleName, boolean lookup, Class<?> lookupEntityType)
	{
		this(name, styleName);
		this.lookup= lookup;
		this.lookupEntityType= lookupEntityType;
	}

	public ColumnImpl(String name, String styleName, boolean autoIncrement)
	{
		this(name, styleName);
		this.autoIncrement= autoIncrement;
	}

	public String getName()
	{
		return name;
	}

	public String getStyleName()
	{
		return styleName;
	}

	public Order getOrder()
	{
		return order;
	}

	public boolean isAutoincrement()
	{
		return autoIncrement;
	}

	public boolean isLookup()
	{
		return lookup;
	}

	public void setOrder(Order order)
	{
		this.order= order;
	}

	public Class<?> getLookupEntityType()
	{
		return lookupEntityType;
	}
}
