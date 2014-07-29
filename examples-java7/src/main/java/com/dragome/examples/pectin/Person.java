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
package com.dragome.examples.pectin;

import java.util.ArrayList;
import java.util.List;

public class Person
{
	private String givenName;
	private String surname;
	private String nickName;
	private Gender gender;
	private Integer age;
	private Address address= new Address();

	private List<Wine> favoriteWines= new ArrayList<Wine>();
	private boolean wineLover= false;
	private List<String> favoriteCheeses= new ArrayList<String>();
	private boolean cheeseLover= false;

	public Person()
	{
	}

	public Person(String givenName, List<Wine> favoriteWines, boolean wineLover)
	{
		String[] split= givenName.split("\\s");
		this.givenName= split[0];
		this.surname= split[1];
		//	this.favoriteWines= favoriteWines;
		this.wineLover= wineLover;
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

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName= nickName;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age= age;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender= gender;
	}

	public List<Wine> getFavoriteWines()
	{
		return new ArrayList<Wine>(favoriteWines);
	}

	public void setFavoriteWines(List<Wine> favoriteWines)
	{
		this.favoriteWines= new ArrayList<Wine>(favoriteWines);
	}

	public boolean isWineLover()
	{
		return wineLover;
	}

	public void setWineLover(boolean wineLover)
	{
		this.wineLover= wineLover;
	}

	public List<String> getFavoriteCheeses()
	{
		return new ArrayList<String>(favoriteCheeses);
	}

	public void setFavoriteCheeses(List<String> favoriteCheeses)
	{
		this.favoriteCheeses= new ArrayList<String>(favoriteCheeses);
	}

	public boolean isCheeseLover()
	{
		return cheeseLover;
	}

	public void setCheeseLover(boolean cheeseLover)
	{
		this.cheeseLover= cheeseLover;
	}

	public Address getAddress()
	{
		return address;
	}
}
