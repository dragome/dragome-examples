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

import java.util.List;

import com.dragome.forms.bindings.client.form.validation.ListValidator;
import com.dragome.forms.bindings.client.form.validation.message.ErrorMessage;
import com.dragome.forms.bindings.client.form.validation.message.InfoMessage;
import com.dragome.forms.bindings.client.form.validation.message.WarningMessage;
import com.dragome.model.interfaces.IndexedValidationResultCollector;

public class WineListValidator implements ListValidator<Wine>
{
    public void validate(List<? extends Wine> values, IndexedValidationResultCollector results)
    {
	if (values.size() < 1)
	{
	    results.add(new ErrorMessage("Please select at least one wine"));
	}
	if (values.size() == 1)
	{
	    results.add(new InfoMessage("Only one favorite?"));
	}
	if (values.size() > 2)
	{
	    results.add(new WarningMessage("Having so many favorites could get expensive."));
	}
    }
}
