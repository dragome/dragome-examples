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

public class TypedReflectionTest
{
	interface Setter<O, T>
	{
		void set(O obj, T param);
	}

	interface Getter<O, T>
	{
		T get(O obj);
	}

	public int getField1()
	{
		return field1;
	}

	public void setField1(int field1)
	{
		this.field1= field1;
	}

	protected int field1;

	public static void main(String[] args)
	{
		HibernateRefactorEnabledMapper<Person> mapper= new HibernateRefactorEnabledMapper<Person>(Person::new);
		mapper.setIdAccessors(Person::getId, Person::setId);
		mapper.addPropertyAccessors(Person::getName, Person::setName);
		mapper.addPropertyAccessors(Person::getAge, Person::setAge);
		
		
		Getter<TypedReflectionTest, Integer> getter= TypedReflectionTest::getField1;
		Setter<TypedReflectionTest, Integer> setter= TypedReflectionTest::setField1;

		TypedReflectionTest obj= new TypedReflectionTest();

		setter.set(obj, 11);
		Integer value= getter.get(obj);
	}
}
