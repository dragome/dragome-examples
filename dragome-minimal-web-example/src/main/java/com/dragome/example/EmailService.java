package com.dragome.example;

public class EmailService implements MessageService
{
	public boolean sendMessage(String msg, String receipient)
	{
		//some fancy code to send email
		System.out.println("Email Message sent to " + receipient + " with message=" + msg);
		return true;
	}

}