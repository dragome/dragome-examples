package org.jbox2d.gwt.showcase.client.framework;

import java.util.EventListener;

public class StartExampleEvent
{
	private BaseExample example;

	public StartExampleEvent(BaseExample example)
	{
		super();
		this.example= example;
	}

	public static interface StartExampleEventListener extends EventListener
	{
		void onStartExample(BaseExample example);
	}

	protected void dispatch(StartExampleEventListener handler)
	{
		handler.onStartExample(example);
	}
}
