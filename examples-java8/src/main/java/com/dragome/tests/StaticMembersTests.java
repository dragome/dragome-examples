package com.dragome.tests;

import junit.framework.TestCase;

public class StaticMembersTests extends TestCase
{
	public static class ParentClass
	{
		protected static String parentClassStaticField1= "sf1a"; 
		protected static String parentClassStaticField2= "sf2a"; 
		protected static String parentClassStaticField3= "sf3a"; 
		
		protected static String staticMethodA()
		{
			return "staticMethodA1";
		}
	}
	
	public static class ChildClass extends ParentClass
	{
		protected static String parentClassStaticField1= "sf1b"; 
		protected static String parentClassStaticField2= "sf2b"; 
	}

	public static class GrandChildClass extends ChildClass
	{
		protected static String parentClassStaticField1= "sf1c"; 
	}

	
	public void testStaticFieldInThirdLevel()
	{
		assertEquals("sf1c", GrandChildClass.parentClassStaticField1);
	}
	
	public void testStaticFieldInSecondLevel()
	{
		assertEquals("sf2b", GrandChildClass.parentClassStaticField2);
	}
	
	public void testStaticFieldInFirstLevel()
	{
		assertEquals("sf3a", GrandChildClass.parentClassStaticField3);
	}
	
	public void testStaticMethodInFirstLevel()
	{
		assertEquals("staticMethodA1", GrandChildClass.staticMethodA());
	}
	
	
}
