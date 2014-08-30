package com.dragome.examples.crudgrid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Place implements Identifiable
{
    @Id
    @GeneratedValue
    Integer id;
    String name;

    public Place()
    {
    }

    public Place(String name)
    {
	this.name= name;
    }

    public Integer getId()
    {
	return id;
    }
    public void setId(Integer id)
    {
	this.id= id;
    }
    public String getName()
    {
	return name;
    }
    public void setName(String name)
    {
	this.name= name;
    }

    public String toString()
    {
	return name + " ";
    }
    
    public int hashCode()
    {
	final int prime= 31;
	int result= 1;
	result= prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Place other= (Place) obj;
	if (name == null)
	{
	    if (other.name != null)
		return false;
	}
	else if (!name.equals(other.name))
	    return false;
	return true;
    }
}
