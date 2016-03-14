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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ReflectionAPITests extends TestCase
{
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Annotation1
	{
		String value1() default "1";
		String value2() default "1";
	}

	public class SuperClass implements ReflectionInterface1, ReflectionInterface2
	{
	}

	@Annotation1(value1= "ReflectionInterface1")    
	public interface ReflectionInterface1
	{
	}

	public interface ReflectionInterface2
	{
	}

	@Annotation1(value1= "ReflectionClass")
	public class ReflectionClass extends SuperClass
	{
		@Annotation1(value1= "methodWithNoArguments")
		public void methodWithNoArguments()
		{
		}

		public String methodWithIntegerArgument(@Annotation1(value1= "methodWithIntegerArgument_i") Integer i)
		{
			return i + "";
		}

		public void overridenMethod(int i)
		{
		}

		public void overridenMethod(@Annotation1(value1= "value1=overridenMethod_s", value2= "value2=overridenMethod_s") String s, @Annotation1(value1= "overridenMethod_l") Long l)
		{
		}

		public List<String> methodWithGenericReturn()
		{
			return null;
		}
	}

	public void testSearchingForMethodWithNoArgumentsReturnsMethodWithSameName() throws Exception
	{
		Method method= ReflectionClass.class.getMethod("methodWithNoArguments", null);
		assertEquals("methodWithNoArguments", method.getName());
	}

	public void testSearchingForMethodWithIntegerArgumentReturnsRightOne() throws Exception
	{
		Method method= ReflectionClass.class.getMethod("methodWithIntegerArgument", Integer.class);
		assertEquals("methodWithIntegerArgument", method.getName());
		assertEquals(Integer.class, method.getParameterTypes()[0]);
	}

	public void testGetReturnTypeReturnsRightOne() throws Exception
	{
		Method method= ReflectionClass.class.getMethod("methodWithIntegerArgument", Integer.class);
		assertEquals(String.class, method.getReturnType());
	}

	public void testGetGenericReturnTypeOfStringListReturnsStringAsFirstType() throws Exception
	{
		Method method= new ReflectionClass().getClass().getMethod("methodWithGenericReturn");
		Type genericReturnType= method.getGenericReturnType();
		Type[] actualTypeArguments= ((ParameterizedType) genericReturnType).getActualTypeArguments();
		assertEquals(String.class, actualTypeArguments[0]);
	}

	public void testFoundOverridenMethodsMatchTheirArguments() throws Exception
	{
		Method overridenMethodStringLong= ReflectionClass.class.getMethod("overridenMethod", String.class, Long.class);
		Method overridenMethodInt= ReflectionClass.class.getMethod("overridenMethod", int.class);

		assertEquals(int.class, overridenMethodInt.getParameterTypes()[0]);
		assertEquals(String.class, overridenMethodStringLong.getParameterTypes()[0]);
		assertEquals(Long.class, overridenMethodStringLong.getParameterTypes()[1]);
	}

	public void testInvokeMethodWithIntegerArgumentReturnsIntegerAsString() throws Exception
	{
		Method method= ReflectionClass.class.getMethod("methodWithIntegerArgument", Integer.class);
		String result= (String) method.invoke(new ReflectionClass(), 7);

		assertEquals("7", result);
	}

	public void testGetMethodsReturnsAllMethods() throws Exception
	{
		Method[] methods= ReflectionClass.class.getMethods();

		Map<String, Method> methodsMap= new HashMap<String, Method>();
		for (Method method : methods)
			methodsMap.put(method.getName(), method);

		assertTrue(4 <= methods.length);
		assertNotNull(methodsMap.get("methodWithNoArguments"));
		assertNotNull(methodsMap.get("methodWithIntegerArgument"));
		assertNotNull(methodsMap.get("overridenMethod"));
	}

	public void testIsInterfaceOverClassIsFalse() throws Exception
	{
		assertFalse(ReflectionClass.class.isInterface());
	}

	public void testIsInterfaceOverInterfaceIstrue() throws Exception
	{
		assertTrue(ReflectionInterface1.class.isInterface());
	}

	public void testGetSuperClassReturnsRightOne() throws Exception
	{
		assertEquals(SuperClass.class, ReflectionClass.class.getSuperclass());
	}

	public void testGetInterfacesOfNonImplementorClassReturnsNothing() throws Exception
	{
		Class<?>[] interfaces= ReflectionClass.class.getInterfaces();
		assertEquals(0, interfaces.length);
	}

	public void testGetInterfacesOfImplementorClassReturnsRightInterface() throws Exception
	{
		Class<?>[] interfaces= ReflectionClass.class.getSuperclass().getInterfaces();
		assertEquals(2, interfaces.length);
		assertEquals(ReflectionInterface1.class, interfaces[0]);
		assertEquals(ReflectionInterface2.class, interfaces[1]);
	}

	public void testIsPrimiteOfIntReturnsTrue() throws Exception
	{
		assertTrue(int.class.isPrimitive());
	}

	public void testIsPrimiteOfIntegerReturnsFalse() throws Exception
	{
		assertFalse(Integer.class.isPrimitive());
	}

	public void testGetPackageOfStringReturnJavaLang() throws Exception
	{
		assertEquals("java.lang", String.class.getPackage().getName());
	}

	public void testForNameOfJavaLangIntegerReturnsInteger() throws Exception
	{
		assertEquals(Integer.class, Class.forName("java.lang.Integer"));
	}

	public void testNewInstanceOfJavaLangStringReturnsStringInstance() throws Exception
	{
		String newInstance= String.class.newInstance();
		assertEquals(String.class, newInstance.getClass());
	}

	public void testGetSimpleNameReturnsNameWithoutPackage() throws Exception
	{
		assertEquals("String", String.class.getSimpleName());
	}

	public void testNumberIsNotAssignableFromString() throws Exception
	{
		assertFalse(Number.class.isAssignableFrom(String.class));
	}

	public void testNumberIsAssignableFromInteger() throws Exception
	{
		assertTrue(Number.class.isAssignableFrom(Integer.class));
	}

	public void testGettingAnnotationFromInterface() throws Exception
	{
		Annotation1 annotation1= ReflectionInterface1.class.getAnnotation(Annotation1.class);
		assertEquals("ReflectionInterface1", annotation1.value1());
	}

	public void testGettingAnnotationFromMethod() throws Exception
	{
		Class<ReflectionClass> class1= ReflectionClass.class;
		Method method= class1.getMethod("methodWithNoArguments", null);
		Annotation1 annotation1= method.getAnnotation(Annotation1.class);
		assertEquals("methodWithNoArguments", annotation1.value1());
	}

	public void testGettingAnnotationFromParameter() throws Exception
	{
		Class<ReflectionClass> class1= ReflectionClass.class;
		Method method= class1.getMethod("methodWithIntegerArgument", Integer.class);
		Annotation1 annotation1= method.getParameters()[0].getAnnotation(Annotation1.class);
		assertEquals("methodWithIntegerArgument_i", annotation1.value1());
	}
	
	public void testGettingAnnotationFromTwoParameters() throws Exception
	{
		Class<ReflectionClass> class1= ReflectionClass.class;
		Method method= class1.getMethod("overridenMethod", String.class, Long.class);
		Annotation1 annotation1= method.getParameters()[0].getAnnotation(Annotation1.class);
		Annotation1 annotation2= method.getParameters()[1].getAnnotation(Annotation1.class);
		assertEquals("value1=overridenMethod_s", annotation1.value1());
		assertEquals("value2=overridenMethod_s", annotation1.value2());
		assertEquals("overridenMethod_l", annotation2.value1());
	}

}
