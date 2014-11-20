package com.dragome.tests;

import junit.framework.Test;
import junit.textui.TestRunner;

public class TestSuiteRunner
{
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

		testRunner.doRun(DefaultTestSuite.suite());
	}
}
