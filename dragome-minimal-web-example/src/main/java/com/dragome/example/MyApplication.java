package com.dragome.example;

import com.google.inject.Inject;

public class MyApplication
{
	@Inject
	private MessageService service;

	//  constructor based injector
	public MyApplication(MessageService svc)
	{
		this.service= svc;
	}

	//setter method injector

	public boolean sendMessage(String msg, String rec)
	{
		//some business logic here
		return service.sendMessage(msg, rec);
	}

	public void setService(MessageService svc)
	{
		this.service= svc;
	}
}