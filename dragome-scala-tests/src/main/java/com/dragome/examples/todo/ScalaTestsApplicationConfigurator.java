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
import java.net.URL;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.commons.compiler.ClasspathFileFilter;
import com.dragome.commons.compiler.CompilerMode;
import com.dragome.methodlogger.MethodLoggerConfigurator;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class ScalaTestsApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	public ScalaTestsApplicationConfigurator()
	{
		MethodLoggerConfigurator methodLoggerConfigurator= new MethodLoggerConfigurator("com.dragome.examples.todo.ScalaSimpleBinding");
		init(methodLoggerConfigurator);

		setClasspathFilter(new ClasspathFileFilter()
		{
			public boolean accept(File pathname, File folder)
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
//		include|= classpathEntry.contains("my-stuff-0.1.0-SNAPSHOT-standalone");

		return include;
	}

	public boolean isRemoveUnusedCode()
	{
		return true;
	}

	public URL getAdditionalCodeKeepConfigFile()
	{
		URL resource= getClass().getResource("/proguard-extra.conf");
		return resource;
	}
}
