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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTests
{
	protected int b= 1;

	public static void main(String[] args)
	{
		StreamTests streamTests= new StreamTests();
		streamTests.m3();
	}

	private void m1()
	{
		//	IntStream.iterate(0, i -> i + 2).limit(100).forEach(System.out::println);
		//
		List<String> languages= Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Jsp", "Jasper");
		//	String a= "";
		//
		Predicate<String> startsWithJ= n -> n.startsWith("J");
		Predicate<String> fourLetterLong= n -> n.length() > 3;
		Stream<String> stream= languages.stream();
		stream= getStream(languages);
		stream.filter(startsWithJ.and(fourLetterLong)).map(s -> s.toLowerCase() + s.toUpperCase()).forEach((n) -> System.out.println("Name, which starts with 'J' and four letter long is : " + n));
	}

	private void m2()
	{
		Predicate<String> startsWithJ= n -> n.startsWith("J");
		Predicate<String> fourLetterLong= n -> n.length() > 3;
		Predicate<String> combined= startsWithJ.and(fourLetterLong);
		boolean test= combined.test("Java");
		System.out.println(test);
	}

	private <T> Stream<T> getStream(List<T> languages)
	{
		return new StreamImpl<T, T>(languages);
	}

	private void m2(int a1, Integer b, boolean c)
	{
		Supplier<Collection<?>> a= ArrayList::new;

		Supplier[] collectionFactories= { ArrayList::new, HashSet::new, HashMap::new };
		Collection object= (Collection) collectionFactories[2].get();
	}

	private void m3()
	{
		IntStream.range(0, 100).parallel().filter(i -> i % 2 == 0).forEach(System.out::println);
	}
}
