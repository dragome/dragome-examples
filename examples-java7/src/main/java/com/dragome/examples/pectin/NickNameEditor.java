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

import java.util.Arrays;

import com.dragome.guia.components.VisualComboBoxImpl;
import com.dragome.guia.components.VisualTextFieldImpl;
import com.dragome.model.interfaces.HasValue;

public class NickNameEditor extends VisualComboBoxImpl<String>
{
    public NickNameEditor(String aName)
    {
	super(aName, Arrays.asList("Bazza", "Shazza", "Davo", "Damo", "Wayno"));
    }

    protected HasValue<String> createOtherEditor()
    {
	return new VisualTextFieldImpl<String>();
    }
}
