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

public class Address
{
    private String addressOne= "1 Orchard Drv";
    private String addressTwo;
    private String suburb= "Fruitville";
    private String postCode= "1234";

    public String getAddressOne()
    {
	return addressOne;
    }

    public void setAddressOne(String addressOne)
    {
	this.addressOne= addressOne;
    }

    public String getAddressTwo()
    {
	return addressTwo;
    }

    public void setAddressTwo(String addressTwo)
    {
	this.addressTwo= addressTwo;
    }

    public String getSuburb()
    {
	return suburb;
    }

    public void setSuburb(String suburb)
    {
	this.suburb= suburb;
    }

    public String getPostCode()
    {
	return postCode;
    }

    public void setPostCode(String postCode)
    {
	this.postCode= postCode;
    }
}
