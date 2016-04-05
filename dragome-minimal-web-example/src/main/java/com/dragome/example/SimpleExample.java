package com.dragome.example;

import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.dragome.services.WebServiceLocator;
import com.dragome.view.DefaultVisualActivity;
import com.dragome.web.annotations.PageAlias;
import com.dragome.web.enhancers.jsdelegate.JsCast;

@PageAlias(alias= "hello-world")
public class SimpleExample extends DefaultVisualActivity
{
	public void build()
	{
		Document document= WebServiceLocator.getInstance().getDomHandler().getDocument();

		Element button= document.getElementById("button");
		final Element messageElement= document.getElementById("message");

		EventTarget eventTarget= JsCast.castTo(button, EventTarget.class);

		eventTarget.addEventListener("click", new EventListener()
		{
			public void handleEvent(Event event)
			{
				messageElement.setTextContent("hello world - " + new Date().toString());
			}
		}, false);
	}
}
