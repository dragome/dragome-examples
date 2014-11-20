package com.dragome.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.dragome.tests.lambda.LambdaTest;
import com.dragome.tests.lambda.TestDefaultMethods;
import com.dragome.tests.lambda.TestLambda1;

public class DefaultTestSuite extends TestSuite
{
	static public Test suite()
	{
		TestSuite suite= new TestSuite();
		suite.addTestSuite(ReflectionAPITests.class);
		suite.addTestSuite(LambdaTest.class);
		suite.addTestSuite(TestDefaultMethods.class);
		suite.addTestSuite(TestLambda1.class);
		return suite;
	}
}
