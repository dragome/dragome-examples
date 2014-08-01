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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;

@PageAlias(alias="tests")
public class TestPage extends DragomeVisualActivity
{
	public void build()
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
		TestSuite testSuite= new TestSuite();
		testSuite.addTestSuite(StreamTests.class);
		testRunner.doRun(testSuite);
		
//		TestLambda1.main(null);
//		
//		StreamTests.main(null);
//		TestStreams1.main(null);
//		TestDefaultMethods.main(null);
//		DragomeCompilerTests.main(null);
//		try
//        {
//	        Test1.main(null);
//        }
//        catch (Exception e)
//        {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//        }
	}
}
