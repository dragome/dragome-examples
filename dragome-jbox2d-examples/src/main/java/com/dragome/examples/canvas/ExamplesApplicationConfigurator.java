package com.dragome.examples.canvas;

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

import java.io.File;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.commons.compiler.CompilerMode;
import com.dragome.web.config.DomHandlerApplicationConfigurator;
import com.dragome.web.helpers.serverside.DefaultClasspathFilter;

@DragomeConfiguratorImplementor
public class ExamplesApplicationConfigurator extends DomHandlerApplicationConfigurator
{
	public ExamplesApplicationConfigurator()
	{
		System.setProperty("dragome-compile-mode", CompilerMode.Production.toString());

		setClasspathFilter(new DefaultClasspathFilter()
		{
			public boolean accept(File pathname)
			{
				boolean accept= super.accept(pathname);

				String string= pathname.toString();

				accept&= !string.contains("java/util/concurrent");
				accept&= !string.contains("java/util/stream");
				accept&= !string.contains("java/util/function");
				accept&= !string.contains("java/sql");
//				accept&= !string.contains("org/w3c/dom/html");
				//				accept&= !string.contains("java/lang/reflect");
				accept&= !string.contains("java/org/junit");
				accept&= !string.contains("junit");

				return accept;
			}

		});
	}
	
	public boolean isCheckingCast()
	{
		return false;
	}
}
