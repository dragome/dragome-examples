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
package com.dragome.tests.bugs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestTryBug extends TestCase
{
	int a= 0;

	public Object m1(List<Integer> ints, Class<?> class1) throws Exception
	{
		String methodName= "";
		boolean result= false;
		Integer[] parameters= ints.toArray(new Integer[0]);
		List<Integer> asList= new ArrayList<Integer>();

		Method[] methods= class1.getDeclaredMethods();
		for (Method foundMethod : methods)
		{
			if (foundMethod.getName().equals(methodName))
			{
				foundMethod.setAccessible(true);

				if (parameters.length > 0 && parameters[0].getClass().equals(class1))
					return foundMethod.invoke(asList.remove(0), asList.toArray());
				else
					return foundMethod.invoke(null, asList.toArray());
			}
		}

		return null;
	}

	public void testWhileTryBug() throws Exception
	{
		Object a= m1(Arrays.asList(1, 2, 10, 4), getClass());
		//		Assert.assertEquals(true, a);
	}

	//	@DragomeCompilerSettings(CompilerType.Standard)
	public void testTryInsideWhile()
	{
		int a= 0;
		while (a < 10)
		{
			try
			{
				a++;
			}
			catch (Exception e)
			{
				System.out.println("Error!");
			}
		}

		Assert.assertEquals(10, a);
	}

	public void testTryWhileBug3()
	{ 
		List<Integer> numbers= Arrays.asList(1, 2, 3);

		try
		{
			Integer number= numbers.isEmpty() ? null : numbers.get(numbers.size() - 1);
			while (number != null)
			{
				number= numbers.isEmpty() ? null : numbers.get(numbers.size() - 1);
			}
		}
		finally
		{
			numbers= null;
		}
	}
	
	private void processTato(boolean firing, String events)
    {
		try 
		{             
			firing= true;

			Character event= events.isEmpty() ? null : events.charAt(events.length() - 1);
			while (event != null) 
			{
				event= events.isEmpty() ? null : events.charAt(events.length() - 1);
			}
		}
		finally
		{
			firing= false;
		}
    }
}
