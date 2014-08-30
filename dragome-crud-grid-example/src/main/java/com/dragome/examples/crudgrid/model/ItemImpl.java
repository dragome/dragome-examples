package com.dragome.examples.crudgrid.model;

public class ItemImpl implements Item
{
    private boolean editMode=false;
    private Identifiable object;

    public void setObject(Identifiable object)
    {
        this.object= object;
    }

    public ItemImpl()
    {
    }
    
    public ItemImpl(Identifiable object)
    {
	this.object= object;
    }

    public boolean isEditMode()
    {
	return editMode;
    }

    public void setEditMode(boolean editMode)
    {
	this.editMode= editMode;
    }

    public Identifiable getObject()
    {
	return object;
    }
}
