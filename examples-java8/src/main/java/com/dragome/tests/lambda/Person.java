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
package com.dragome.tests.lambda;

public class Person
{
	public Person()
	{
	}

	private int id;
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name= name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age= age;
	}

	private int age;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id= id;
	}

}
