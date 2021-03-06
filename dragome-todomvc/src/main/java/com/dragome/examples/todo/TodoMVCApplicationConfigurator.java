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
package com.dragome.examples.todo;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.examples.todo.model.Todo;
import com.dragome.methodlogger.MethodLoggerConfigurator;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class TodoMVCApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	public TodoMVCApplicationConfigurator()
	{
		MethodLoggerConfigurator methodLoggerConfigurator= new MethodLoggerConfigurator(Todo.class.getPackage().getName());
		init(methodLoggerConfigurator);
	}

//	public boolean filterClassPath(String classpathEntry)
//	{
//		boolean include= false || classpathEntry.contains("todomvc");
//		return include;
//	}
}
