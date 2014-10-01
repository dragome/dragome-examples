package com.dragome.examples.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.model.EventDispatcherImpl;
import com.dragome.services.ServiceLocator;

@PageAlias(alias= "test-dom1")
public class TestDom1 extends DragomeVisualActivity
{
	private int delta= 0;

	public void build()
	{
		Document document= ServiceLocator.getInstance().getDomHandler().getDocument();

		final Element div1= document.getElementById("div1");
		div1.setAttribute("style", "position: relative;");
		String attribute= div1.getAttribute("class");

		EventDispatcherImpl.setEventListener(div1, new EventListener()
		{
			public void handleEvent(Event event)
			{
				System.out.println(event.getType());
				if (event.getType().equals("click"))
					delta+= 5;
				else if (event.getType().equals("mouseout"))
					delta-= 5;
				
				div1.setAttribute("style", "position: relative; left:" + delta + "px;top:" + delta + "px;");
			}
		}, "click", "mouseout");
	}
}
