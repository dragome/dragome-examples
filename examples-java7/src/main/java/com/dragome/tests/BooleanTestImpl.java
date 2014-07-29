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
package com.dragome.tests;

public class BooleanTestImpl implements BooleanTest
{
	protected boolean property;

	public BooleanTestImpl()
	{
	}

	public boolean isProperty()
	{
		return property;
	}

	public void setProperty(boolean property)
	{
		this.property= property;
	}
}
