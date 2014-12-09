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
package com.dragome.examples.canvas;

import com.dragome.callbackevictor.CallbackEvictorConfigurator;
import com.dragome.commons.ChainedInstrumentationDragomeConfigurator;
import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.commons.ExecutionHandler;
import com.dragome.commons.compiler.annotations.CompilerType;

@DragomeConfiguratorImplementor
public class ExamplesApplicationConfigurator extends ChainedInstrumentationDragomeConfigurator
{
	private CallbackEvictorConfigurator callbackEvictorConfigurator;

	public ExamplesApplicationConfigurator()
	{
		callbackEvictorConfigurator= new CallbackEvictorConfigurator();
		callbackEvictorConfigurator.setEnabled(false);

		init(callbackEvictorConfigurator);
	}

	public ExecutionHandler getExecutionHandler()
	{
		return callbackEvictorConfigurator.isEnabled() ? callbackEvictorConfigurator.getExecutionHandler() : super.getExecutionHandler();
	}

	public CompilerType getDefaultCompilerType()
	{
		return CompilerType.Standard;
	}
	
	public boolean filterClassPath(String classpathEntry)
	{
		boolean include= super.filterClassPath(classpathEntry) ;

		return include;
	}

}
