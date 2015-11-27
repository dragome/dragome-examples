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
import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.commons.ExecutionHandler;
import com.dragome.commons.compiler.annotations.CompilerType;
import com.dragome.examples.TimerDemoPage;
import com.dragome.methodlogger.MethodLoggerConfigurator;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class ExamplesApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	private CallbackEvictorConfigurator callbackEvictorConfigurator;
	private MethodLoggerConfigurator methodLoggerConfigurator;

	public ExamplesApplicationConfigurator()
	{
		callbackEvictorConfigurator= new CallbackEvictorConfigurator(); 
		callbackEvictorConfigurator.setEnabled(false); 

		methodLoggerConfigurator= new MethodLoggerConfigurator(TimerDemoPage.class.getName());
		methodLoggerConfigurator.setEnabled(true);

		init(callbackEvictorConfigurator, methodLoggerConfigurator);
	}

	public ExecutionHandler getExecutionHandler()
	{
		return callbackEvictorConfigurator.getExecutionHandler();
	}

	public CompilerType getDefaultCompilerType()
	{
		return CompilerType.Standard;
	}
}
