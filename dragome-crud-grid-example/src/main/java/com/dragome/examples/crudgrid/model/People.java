package com.dragome.examples.crudgrid.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class People implements Identifiable
{
    @Id
    @GeneratedValue
    Integer id;

    String name;

    @ManyToOne(cascade= CascadeType.PERSIST)
    Place place;

    public People()
    {
    }

    public People(String name, Place place)
    {
	super();
	this.name= name;
	this.place= place;
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
    public Place getPlace()
    {
	return place;
    }
    public void setPlace(Place place)
    {
	this.place= place;
    }
}
