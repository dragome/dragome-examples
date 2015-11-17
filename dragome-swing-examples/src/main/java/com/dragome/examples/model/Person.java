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
package com.dragome.examples.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person
{
	@Id
	@GeneratedValue
	private int id;

	public int getId()
	{
		return id;
	}
 
	public void setId(int id)
	{
		this.id= id;
	}

	private String givenName= "";
	private String surname= "";
	private String nickname= "";

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname= nickname;
	}

	public Person()
	{
	}

	public Person(String givenName, String nickname)
	{
		String[] split= givenName.split("\\s");
		this.givenName= split[0];
		this.surname= split[1];
		this.nickname= nickname;
		this.id= 0;
	}

	public String getGivenName()
	{
		return givenName;
	}

	public void setGivenName(String givenName)
	{
		this.givenName= givenName;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname= surname;
	}
}
