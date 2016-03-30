package com.dragome.examples.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.dragome.guia.GuiaVisualActivity;
import com.dragome.services.WebServiceLocator;
import com.dragome.web.annotations.PageAlias;
import com.dragome.web.enhancers.jsdelegate.JsCast;

@PageAlias(alias= "test-dom1")
public class TestDom1 extends GuiaVisualActivity
{
	private int delta= 0;

	public void build()
	{
		Document document= WebServiceLocator.getInstance().getDomHandler().getDocument();

		final Element div1= document.getElementById("div1");
		div1.setAttribute("style", "position: relative;");
		String attribute= div1.getAttribute("class");

		EventTarget eventTarget= JsCast.castTo(div1, EventTarget.class);

		eventTarget.addEventListener("click", new EventListener()
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
		}, false);
	}
}
