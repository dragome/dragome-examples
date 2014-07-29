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
package com.dragome.tests.lambda; // TODO Auto-generated constructor stub

import java.util.function.Supplier;

import com.dragome.tests.lambda.TypedReflectionTest.Getter;
import com.dragome.tests.lambda.TypedReflectionTest.Setter;

public class HibernateRefactorEnabledMapper<T>
{
	private Supplier<T> supplier;

	public HibernateRefactorEnabledMapper(Supplier<T> supplier)
	{
		this.supplier= supplier;
	}

	public <V> void setIdAccessors(Getter<T, V> getter, Setter<T, V> setter)
	{
		T obj= supplier.get();
		setter.set(obj, (V) (Integer) 1);
		V v= getter.get(obj);
	}

	public <V> void addPropertyAccessors(Getter<T, V> getter, Setter<T, V> setter)
	{

	}
}
