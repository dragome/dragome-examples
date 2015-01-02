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
package com.dragome.examples.crudgrid;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.config.DomHandlerApplicationConfigurator;
import com.dragome.examples.crudgrid.model.CrudGrid;
import com.dragome.methodlogger.MethodLoggerConfigurator;

@DragomeConfiguratorImplementor
public class CrudGridApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	public CrudGridApplicationConfigurator()
	{
		MethodLoggerConfigurator methodLoggerConfigurator= new MethodLoggerConfigurator(CrudGrid.class.getPackage().getName());
		init(methodLoggerConfigurator);
	}
}
