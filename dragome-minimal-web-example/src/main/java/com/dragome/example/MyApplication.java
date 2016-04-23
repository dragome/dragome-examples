package com.dragome.example;

import com.google.inject.Inject;

public class MyApplication
{

	@Inject(optional=false)
	private MessageService service;

	//  constructor based injector
//	@Inject
//	public MyApplication(MessageService svc)
//	{
//		this.service= svc;
//	}

	//setter method injector
	public void setService(MessageService svc)
	{
		this.service= svc;
	}

	public boolean sendMessage(String msg, String rec)
	{
		//some business logic here
		return service.sendMessage(msg, rec);
	}
}