package com.dragome.examples.service;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.websocket.WebSocket;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.view.DefaultVisualActivity;
import com.dragome.web.annotations.PageAlias;
import com.dragome.web.enhancers.jsdelegate.JsCast;

@PageAlias(alias= "ws-test")
public class WordgameClient extends DefaultVisualActivity
{
	public interface MessageEvent extends Event
	{
		public String getData();
	}

	public void build()
	{
		ScriptHelper.put("fer", "ws://localhost:8080/examples/game", this);
		WebSocket webSocket= ScriptHelper.evalCasting("new WebSocket(fer)", WebSocket.class, this);
		webSocket.setOnopen(new EventListener()
		{
			public void handleEvent(Event evt)
			{
				System.out.println("open");
			}
		});
		webSocket.setOnmessage(new EventListener()
		{
			public void handleEvent(Event evt)
			{
				MessageEvent messageEvent= JsCast.castTo(evt, MessageEvent.class);
				System.out.println("message: "+ messageEvent.getData());
			}
		});
		webSocket.setOnclose(new EventListener()
		{
			public void handleEvent(Event evt)
			{
				System.out.println("close");
			}
		});
	}
}