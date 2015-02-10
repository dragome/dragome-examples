/*******************************************************************************
 * Copyright (c) 2011-2015 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.examples.todo;

import java.io.File;
import java.io.FileFilter;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.commons.compiler.CompilerMode;
import com.dragome.config.DomHandlerApplicationConfigurator;
import com.dragome.methodlogger.MethodLoggerConfigurator;

@DragomeConfiguratorImplementor
public class ScalaTestsApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	public ScalaTestsApplicationConfigurator()
	{
		MethodLoggerConfigurator methodLoggerConfigurator= new MethodLoggerConfigurator(ScalaSimpleBinding.class.getName());
		init(methodLoggerConfigurator);

		setClasspathFilter(new FileFilter()
		{

			public boolean accept(File pathname)
			{
				String string= pathname.toString();
				boolean isServerSideOnly= string.contains("/serverside");
				boolean isDebuggingPackage= string.contains("/debugging");
				if (!CompilerMode.Production.toString().equals(System.getProperty("dragome-compile-mode")))
					isDebuggingPackage= false;

				boolean result= !(isServerSideOnly || isDebuggingPackage);

				if (string.startsWith("scala/"))
				{
					if (!string.substring(6).contains("/"))
						return result;
					else if (string.startsWith("scala/runtime"))
						return true;

					return false;
				}
				else
					return result;
			}
		});
	}

	public boolean filterClassPath(String classpathEntry)
	{
		boolean include= super.filterClassPath(classpathEntry);
		include|= classpathEntry.contains("scala-library-");

		return include;
	}

}
