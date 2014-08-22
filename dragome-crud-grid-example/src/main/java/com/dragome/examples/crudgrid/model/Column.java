package com.dragome.examples.crudgrid.model;

public interface Column
{

    String getName();

    String getStyleName();

    String getOrderBy();
    
    boolean isAutoincrement();

    boolean isLookup();

}
