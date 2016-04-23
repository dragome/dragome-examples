package com.dragome.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EventHandler;
import org.w3c.dom.XMLHttpRequest;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.view.DefaultVisualActivity;
import com.dragome.web.annotations.PageAlias;
import com.dragome.web.enhancers.jsdelegate.JsCast;
import com.dragome.web.html.dom.Window;

@PageAlias(alias= "hello-world")
public class SimpleExample extends DefaultVisualActivity
{
	public void build()
	{

		ClientApplication.main(null);

		//		Document document= ScriptHelper.evalCasting("document", Document.class, this);

		new Window();
		Document document= Window.getDocument();

		Element button= document.getElementById("button");
		final Element messageElement= document.getElementById("message");

		final XMLHttpRequest httpRequest= ScriptHelper.evalCasting("new XMLHttpRequest()", XMLHttpRequest.class, null);
		httpRequest.setOnreadystatechange(new EventHandler()
		{
			public void handleEvent(Event event)
			{
				System.out.println(httpRequest.getResponseText());
			}
		});

		httpRequest.open("get", "/example/dragome-resources/css/dragome.css");
		httpRequest.send();

		EventTarget eventTarget= JsCast.castTo(button, EventTarget.class);
		eventTarget.addEventListener("click", new EventListener()
		{
			public void handleEvent(Event event)
			{
				messageElement.setTextContent("hello world - " + event.getType() + ":" + System.currentTimeMillis());
			}
		}, false);
	}
}
