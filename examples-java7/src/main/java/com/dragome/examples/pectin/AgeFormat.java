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

import com.dragome.forms.bindings.client.format.FormatException;
import com.dragome.forms.bindings.client.format.IntegerFormat;

public class AgeFormat extends IntegerFormat
{
    protected void validateInteger(int integer) throws FormatException
    {
	if (integer < 0)
	{
	    throw new FormatException("Age can't be negative");
	}
    }
}
