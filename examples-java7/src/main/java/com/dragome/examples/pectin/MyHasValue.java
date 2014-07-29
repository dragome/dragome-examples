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

import com.dragome.model.interfaces.GwtEvent;
import com.dragome.model.interfaces.HandlerRegistration;
import com.dragome.model.interfaces.HasValue;
import com.dragome.model.interfaces.ValueChangeEvent;
import com.dragome.model.interfaces.ValueChangeHandler;

public class MyHasValue implements HasValue<String>
{

    private List<ValueChangeHandler> handlers= new ArrayList<ValueChangeHandler>();
    private String value;

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler)
    {
	handlers.add(handler);
	return new HandlerRegistration()
	{
	    public void removeHandler()
	    {
	    }
	};
    }

    public void fireEvent(GwtEvent<?> event)
    {
	// TODO Auto-generated method stub
	System.out.println("sdgsdg");
    }

    @Override
    public String getValue()
    {
	return value;
    }

    @Override
    public void setValue(String value)
    {
	setValue(value, true);
	handlers.get(0).onValueChange(new ValueChangeEvent(value)
	{
	});
    }

    @Override
    public void setValue(String value, boolean fireEvents)
    {
	this.value= value;
    }
}
