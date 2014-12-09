package org.jbox2d.gwt.showcase.client.framework;

import java.util.EventListener;

public class ExampleKeyEvent
{
	public static final int KEY_EVENT_TYPE_DOWN= 0;
	public static final int KEY_EVENT_TYPE_UP= 1;
	public static final int KEY_EVENT_TYPE_PRESS= 2;
	private int mouseEventType;
	private char charCode;

	public ExampleKeyEvent(int mouseEventType, char charCode)
	{
		super();
		this.mouseEventType= mouseEventType;
		this.charCode= charCode;
	}

	public static interface ExampleKeyEventListener extends EventListener
	{
		void onKeyPress(char theChar);
		void onKeyDown(char theChar);
		void onKeyUp(char theChar);
	}

	public void dispatch(ExampleKeyEventListener handler)
	{
		if (mouseEventType == KEY_EVENT_TYPE_DOWN)
		{
			handler.onKeyDown(charCode);
		}
		else if (mouseEventType == KEY_EVENT_TYPE_UP)
		{
			handler.onKeyUp(charCode);
		}
		else if (mouseEventType == KEY_EVENT_TYPE_PRESS)
		{
			handler.onKeyPress(charCode);
		}
	}
}
