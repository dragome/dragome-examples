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
package com.dragome.tests;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.dragome.commons.compiler.annotations.CompilerType;
import com.dragome.commons.compiler.annotations.DragomeCompilerSettings;
import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.services.ServiceLocator;
import com.dragome.services.interfaces.SerializationService;

public class DragomeCompilerTests extends TestCase
{
	public class S0
	{   
		public int m1()     
		{      
			return 0;  
		}
	}   
     
	public class S1 extends S0
	{   
		public int m1() 
		{ 
			return 1;  
		}     
     
		public class S2    
		{
			public int m1()  
			{
				return S1.super.m1();
			}
		} 
	}

	public class Exception1 extends Exception
	{

	}
	public class Exception2 extends RuntimeException
	{

	}

	private boolean useLastChar;
	private char lastChar;
	private int index;
	private char c;

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testIncrementingIntegerByOneReturnsNextInteger()
	{
		int a= 10;
		a++;
		Assert.assertEquals(11, a);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testIncrementingCharByOneReturnsNextChar()
	{
		char a= 10;
		a++;
		Assert.assertEquals(11, a);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testIncrementingByteByOneShouldReturnNextByte()
	{
		byte a= 10;
		a++;
		Assert.assertEquals(11, a);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testCompareTwoChars()
	{
		char a= 'A';
		char b= 'B';

		boolean result= a == b;
 
		assertEquals(false, result);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testCastingIntegerToCharShouldReturnSameChar()
	{
		int i= 10;
		c= (char) i;

		assertEquals((char) 10, c);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testPuttingValueToVariableShouldEvalToSameValue()
	{
		int i= 10;
		ScriptHelper.put("variable1", i, this);
		int value= ScriptHelper.evalInt("variable1*3", this);

		assertEquals((char) 30, value);
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testSettingVariableToNull()
	{
		boolean result= false;
		Object i= null; 

		if (i == null)
			result= true;

		assertEquals(true, result); 
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testTryCatch() throws Exception1
	{
		try
		{
			throw new Exception2();
		}
		catch (Exception2 e)
		{
			assertEquals(Exception2.class, e.getClass());
		}
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testTryNotCatch()
	{
		boolean catched= false;
		try
		{
			try
			{
				throw new Exception1();
			}
			catch (Exception2 e)
			{
				catched= true;
			}

			assertEquals(false, catched);
		}
		catch (Exception1 e)
		{
			assertEquals(Exception1.class.getName(), e.getClass().getName());
		}
	}

	@DragomeCompilerSettings(CompilerType.Strict)
	public void testStaticSuperCall() throws Exception1
	{
		S1.S2 s2= new S1().new S2();
		int m1= s2.m1();

		assertEquals(0, m1);
	}

	public void testSerializationOfPrimitiveBooleanShouldDeserializeToPrimitiveBooleanProperty()  
	{
		SerializationService serializationService= ServiceLocator.getInstance().getSerializationService();
		BooleanTest booleanTest= new BooleanTestImpl();
		booleanTest.setProperty(true);
		String serialize= serializationService.serialize(booleanTest);

		BooleanTest deserialize= (BooleanTest) serializationService.deserialize(serialize);

		ScriptHelper.put("deserializedValue", deserialize, this);
		ScriptHelper.put("booleanTest", booleanTest, this);
		boolean result= ScriptHelper.evalBoolean("deserializedValue.$$$property == booleanTest.$$$property", this);
		
		assertEquals(true, result);
	}

	public static void main(String[] args) 
	{
		TestRunner testRunner= new TestRunner() 
		{
			public synchronized void startTest(Test test)
			{ 
			    super.startTest(test);
			} 
			
			public void testFailed(int status, Test test, Throwable t)
			{
				System.out.println("FAIL!!");
			    super.testFailed(status, test, t);
			}
		};
		testRunner.doRun(new TestSuite(DragomeCompilerTests.class));
	} 
}
