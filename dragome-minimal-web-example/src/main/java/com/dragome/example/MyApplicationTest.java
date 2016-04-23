package com.dragome.example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import junit.framework.Assert;

public class MyApplicationTest
{

	private Injector injector;

	public void setUp() throws Exception
	{
		injector= Guice.createInjector(new AbstractModule()
		{

			@Override
			protected void configure()
			{
				bind(MessageService.class).to(MockMessageService.class);
			}
		});
	}

	public void tearDown() throws Exception
	{
		injector= null;
	}

	public void test()
	{
		MyApplication appTest= injector.getInstance(MyApplication.class);
		Assert.assertEquals(true, appTest.sendMessage("Hi Pankaj", "pankaj@abc.com"));
		;
	}

}