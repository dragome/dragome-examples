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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreams1
{
	public static void main(String[] args)
	{
//		List<Integer> numbers= Arrays.asList(9, 10, 3, 4, 7, 3, -5);
//		numbers.stream().map(i -> i * i).distinct().forEach(System.out::println);

		Stream.of("1", "2", "3", "4", "5").limit(3).map(TestStreams1::parserInt).filter(i -> i % 2 != 0).forEach(i -> System.out.println(i));

		System.out.println("sdg");
		//		m5();
		//		m2();
	}



	private static int parserInt(String a)
    {
	    return Integer.parseInt(a);
    }
	
	

	private static void m2()
	{
		Collection<String> myList= Arrays.asList("Hello", "Java", "World", "8", "Streams", "Fer");
		Predicate<String> isKnown= myList::contains;

		long countLongStrings= myList.stream().filter(element -> element.length() > 4).count();
		System.out.println(countLongStrings);
	}

	private static void m1()
	{
		List<String> myList= Arrays.asList("element1", "element2", "element3");
		myList.forEach(element -> System.out.println(element));
	}

	private static void m3()
	{
		Collection<String> myList= Arrays.asList("Hello", "Java", "World", "ocho", "Streams", "Fer");

		List<String> collect= myList.stream().map(s -> s.toUpperCase()).limit(4).filter(matches()).skip(2).collect(Collectors.toList());
	}

	private static Predicate<? super String> matches()
	{
		return s -> {
			System.out.println(s);
			return s.length() > 3;
		};
	}

	private static void m4()
	{
		Persona[] personas= new Persona[] { new Persona("Diego"), new Persona("Armando"), new Persona("Maradona") };
		Comparator<? super Persona> comparator= Persona::firstNameComparator;
		List<Persona> collect= Stream.of(personas).sorted(comparator).collect(Collectors.toList());
	}

	private static void m5()
	{
		Persona[] personas= new Persona[] { new Persona("Diego"), null, new Persona("Armando"), new Persona("Maradona") };
		Comparator<? super Persona> comparator= Persona::firstNameComparator;
		List<String> collect= Stream.of(personas).filter(p -> p != null).map(p -> p.getFirstName()).collect(Collectors.toList());
	}

	private void m6()
	{
		List<String> names= Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		names.stream().filter(((Predicate<String>) n -> n.startsWith("J")).and(n -> n.length() == 4)).forEach((n) -> System.out.print("Empieza con J y tiene 4 caracteres: " + n));
	}

	public static class Persona
	{
		private String firstName;

		public Persona(String firstName)
		{
			super();
			this.firstName= firstName;
		}

		public int firstNameComparator(Persona persona)
		{
			return getFirstName().compareTo(persona.getFirstName());
		}

		private String getFirstName()
		{
			return firstName;
		}
	}

}
