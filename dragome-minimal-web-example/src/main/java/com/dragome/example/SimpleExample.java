package com.dragome.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.typedarray.ArrayBuffer;

import com.dragome.commons.javascript.ScriptHelper;
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

		ArrayBuffer arrayBuffer= ScriptHelper.evalCasting("new ArrayBuffer(13);", ArrayBuffer.class, null);
		int byteLength= arrayBuffer.getByteLength();

		eventTarget.addEventListener("click", new EventListener()
		{
			public void handleEvent(Event event)
			{
				messageElement.setTextContent("hello world - " + event.getType() + ":" + System.currentTimeMillis());
			}
		}, false);
	}
}
