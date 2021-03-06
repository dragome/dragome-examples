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
package com.dragome.tests.lambda;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDefaultMethods extends TestCase
{
	public interface A
	{
		default String foo(int a)
		{
			return "Calling A.foo():" + a;
		}

		default String bar(int a)
		{
			return "Calling A.bar():" + a;
		}
	}

	public interface B
	{
		default String foo(int a)
		{
			return "Calling B.foo():" + a;
		}
	}

	public static class AB implements A, B
	{
		public String foo(int a)
		{
			return B.super.foo(a);
		}
	}

	public void testSuperDefaultMethodIsCalledCorrectly()
	{
		String foo= new AB().foo(1);
		String bar= new AB().bar(2);

		Assert.assertEquals("Calling B.foo():1", foo);
		Assert.assertEquals("Calling A.bar():2", bar);
	}
}
