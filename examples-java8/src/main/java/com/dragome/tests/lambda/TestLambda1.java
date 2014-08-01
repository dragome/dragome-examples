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

import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import com.dragome.commons.compiler.annotations.CompilerType;
import com.dragome.commons.compiler.annotations.DragomeCompilerSettings;

import junit.framework.Assert;
import junit.framework.TestCase;

@DragomeCompilerSettings(CompilerType.Standard)
public class TestLambda1 extends TestCase
{
	private int instanceVar= 0;

	public void testLambda_using_instance_variables()
	{
		Runnable lambda= () -> {
			instanceVar= 42;
		};
		lambda.run();

		Assert.assertEquals(instanceVar, 42);
	}

	public void testLambda_using_local_variables()
	{
		int[] localVar= new int[1];
		Runnable lambda= () -> {
			localVar[0]= 43;
		};
		lambda.run();

		Assert.assertEquals(localVar[0], 43);
	}

	public void testCreatingBinaryOperatorWithLambda()
	{
		BinaryOperator<Integer> sum= (v1, v2) -> v1 + v2;
		Integer result= sum.apply(1, 2);

		Assert.assertEquals(new Integer(3), result);
	}

	public void testLambaFromLambda()
	{
		FileFilter[] filters= new FileFilter[] { f -> f.exists(), f -> f.canRead(), f -> f.getName().startsWith("q") };

		Supplier<Runnable> c= () -> () -> {
			instanceVar= 80;
		};
		Runnable runnable= c.get();
		runnable.run();

		Assert.assertEquals(80, instanceVar);
	}

	public void testNoParametersMethodReferenceAsLambda()
	{
		String[] list= new String[] { "One", "Two", "Three", "Four", "Five", "Six" };

		Comparator<String> upperComparator= Comparator.comparing(String::toUpperCase);

		Arrays.sort(list, upperComparator);

		Assert.assertEquals(list[0], "Five");
		Assert.assertEquals(list[1], "Four");
		Assert.assertEquals(list[2], "One");
		Assert.assertEquals(list[3], "Six");
		Assert.assertEquals(list[4], "Three");
		Assert.assertEquals(list[5], "Two");
	}

	public void testUseTwoParametersMethodReferenceAsComparator()
	{
		String[] list= new String[] { "One", "Two", "Three", "Four", "Five", "Six" };
		Arrays.sort(list, (a, b)->a.substring(1).compareTo(b.substring(1)));

		Assert.assertEquals(list[0], "Three");
		Assert.assertEquals(list[1], "Five");
		Assert.assertEquals(list[2], "Six");
		Assert.assertEquals(list[3], "One");
		Assert.assertEquals(list[4], "Four");
		Assert.assertEquals(list[5], "Two");
	}
	public static int compareFrom2(String a, String b)
	{
		return a.substring(1).compareTo(b.substring(1));
	}
}
