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
package com.dragome;

import com.dragome.callbackevictor.CallbackEvictorConfigurator;
import com.dragome.commons.DragomeConfigurator;
import com.dragome.commons.DragomeConfiguratorImplementor;

@DragomeConfiguratorImplementor
public class ExamplesApplicationConfigurator extends CallbackEvictorConfigurator implements DragomeConfigurator
{
	public ExamplesApplicationConfigurator()
	{
		enabled= false;
	}
}
