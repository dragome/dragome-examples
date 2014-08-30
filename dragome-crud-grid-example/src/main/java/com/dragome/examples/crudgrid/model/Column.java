package com.dragome.examples.crudgrid.model;

import com.dragome.forms.bindings.builders.Order;


public interface Column
{
    String getName();

    String getStyleName();

    Order getOrder();
    
    boolean isAutoincrement();

    boolean isLookup();

    void setOrder(Order order);

    Class<?> getLookupEntityType();

    public abstract void setLookupEntityType(Class<?> lookupEntityType);

    public abstract void setLookup(boolean lookup);

    public abstract void setStyleName(String styleName);

    public abstract void setName(String name);

    public abstract void setAutoIncrement(boolean autoIncrement);

    public abstract boolean isAutoIncrement();
}
