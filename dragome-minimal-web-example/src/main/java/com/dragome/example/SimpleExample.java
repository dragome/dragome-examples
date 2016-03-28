package com.dragome.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.helpers.DragomeEntityManager;
import com.dragome.services.WebServiceLocator;
import com.dragome.view.DefaultVisualActivity;
import com.dragome.web.annotations.PageAlias;
import com.dragome.web.enhancers.jsdelegate.JsDelegateFactory;

@PageAlias(alias= "hello-world")
public class SimpleExample extends DefaultVisualActivity
{
	public void build()
	{
		Document document= WebServiceLocator.getInstance().getDomHandler().getDocument();

		Element button= document.getElementById("button");
		final Element messageElement= document.getElementById("message");

		EventTarget eventTarget= JsDelegateFactory.createFromNode(button, EventTarget.class);
		addEventListener(eventTarget, "click", new EventListener()
		{
			public void handleEvent(Event event)
			{
				messageElement.setTextContent("hello world - " + event.getType() + ":" + System.currentTimeMillis());
			}
		}, false);
	}

	private void addEventListener(EventTarget eventTarget, String type, EventListener eventListener, boolean b)
	{
		ScriptHelper.putMethodReference("handleEventMethod", EventListener.class, this).handleEvent(null);
		ScriptHelper.put("eventListener", eventListener, this);
		Object listener= ScriptHelper.eval("(function(){handleEventMethod.apply(eventListener, arguments)})", this);
		ScriptHelper.put("listener", listener, this);

		ScriptHelper.put("javaRefId", DragomeEntityManager.add(eventListener), this);
		ScriptHelper.eval("eventListener.javaRefId= javaRefId", this);

		ScriptHelper.put("eventTarget", eventTarget, this);
		ScriptHelper.put("type", type, this);
		ScriptHelper.eval("eventTarget.node.addEventListener(type, listener)", this);
	}
}
