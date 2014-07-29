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

import java.util.EventListener;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class Test1
{
	public class ActionEventTest
	{

		private String actionCommand;

		public String getActionCommand()
		{
			return actionCommand;
		}

		public ActionEventTest(int i, int j, String string)
		{
			this.actionCommand= string;
		}

	}

	private int instanceVar= 0;

	public void lambda_using_instance_variables()
	{
		Runnable lambda= () -> {
			instanceVar= 42;
		};
		lambda.run();
	}

	public void lambda_using_instance_variables2()
	{
		Integer localVar= 41;
		Runnable lambda= () -> {
			instanceVar= localVar;
		};
		lambda.run();
	}

	public void lambda_using_local_variables()
	{
		Integer localVar= 43;
		Runnable lambda= () -> {
			Integer localVar2= localVar;
		};
		lambda.run();

	}

	public interface ActionListener extends EventListener
	{
		public void actionPerformed(ActionEventTest e);
	}

	public void lambda_using_local_variables3()
	{
		Integer localVar= 43;
		ActionListener a= (ActionEventTest e) -> {
			System.out.println(e.getActionCommand());
			System.out.println(localVar);
		};

		a.actionPerformed(new ActionEventTest(1, 2, "3"));

	}

	public void lambda_using_local_variables2()
	{
		Integer localVar1= 43;
		Integer localVar2= 44;
		Integer localVar3= 45;
		Runnable lambda= () -> {
			Integer localVar1a= localVar1;
			Integer localVar2a= localVar2;
			Integer localVar3a= localVar3;
		};
		lambda.run();

	}

	static class IntPredicates
	{
		public static boolean isOdd(Integer n)
		{
			return n % 2 != 0;
		}
		public static boolean isEven(Integer n)
		{
			return n % 2 == 0;
		}
		public static boolean isPositive(Integer n)
		{
			return n >= 0;
		}
	}

	public void method_references_to_virtual_methods() throws Exception
	{
		String foo= "foo";
		Callable<String> ref= foo::toUpperCase;
		String call= ref.call();
		System.out.println(call);

		Predicate<Integer> isOdd= IntPredicates::isOdd;
		Predicate<Integer> isEven= IntPredicates::isEven;

		boolean odd= isOdd.test(3);
		boolean even= isEven.test(7);
	}
	public static void main(String[] args) throws Exception
	{
		Test1 lambdaTest= new Test1();
		lambdaTest.method_references_to_virtual_methods();
		lambdaTest.lambda_using_local_variables();
		lambdaTest.lambda_using_instance_variables();
		lambdaTest.lambda_using_local_variables2();
		lambdaTest.lambda_using_instance_variables2();
		lambdaTest.lambda_using_local_variables3();
	}
}
