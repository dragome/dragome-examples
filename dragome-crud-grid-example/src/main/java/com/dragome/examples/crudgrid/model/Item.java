package com.dragome.examples.crudgrid.model;

public interface Item
{
	boolean isEditMode();
	void setEditMode(boolean editMode);
	Identifiable getObject();
	void setObject(Identifiable object);
}
