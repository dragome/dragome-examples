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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RunMthdRef implements InvocationHandler
{
	public interface InterfaceA
	{
		int intMethod();
	}

	public int[] add(int a, int b)
	{
		return new int[] { a + b };
	}

	public int sub(int a, int b)
	{
		return a - b;
	}

	public int mul(int a, int b)
	{
		return a * b;
	}

	public int div(int a, int b)
	{
		return a / b;
	}

	public static void main(String[] args)
	{
		try
		{
//			testMethodInvokeByReflection();
			testProxyInvoker();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void testProxyInvoker()
	{
		InterfaceA a= (InterfaceA) Proxy.newProxyInstance(RunMthdRef.class.getClassLoader(), new Class<?>[] { InterfaceA.class }, new RunMthdRef());

		int intMethod= a.intMethod();
		int b= intMethod+ 4;
		System.out.println(b);
	}
	private static void testMethodInvokeByReflection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Integer[] input= { new Integer(2), new Integer(6) };
		Class cl= Class.forName("RunMthdRef");
		Class[] par= new Class[2];
		par[0]= Integer.TYPE;
		par[1]= Integer.TYPE;
		Method mthd= cl.getMethod("add", par);
		int[] output= (int[]) mthd.invoke(new RunMthdRef(), input);
		System.out.println(output);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		int result= 10;
		return result;
	}
}
