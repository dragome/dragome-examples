package org.jbox2d.gwt.showcase.client.framework;

import java.util.EventListener;

public class ExampleMouseEvent
{
	public static final int MOUSE_EVENT_TYPE_DOWN= 0;
	public static final int MOUSE_EVENT_TYPE_UP= 1;
	public static final int MOUSE_EVENT_TYPE_MOVE= 2;
	private int mouseEventType;
	private int x;
	private int y;
	private boolean shift;

	public ExampleMouseEvent(int mouseEventType, int x, int y, boolean shift)
	{
		super();
		this.mouseEventType= mouseEventType;
		this.x= x;
		this.y= y;
		this.shift= shift;
	}

	public static interface ExampleMouseEventListener extends EventListener
	{
		void onMouseDown(int x, int y, boolean shift);
		void onMouseUp(int x, int y, boolean shift);
		void onMouseMove(int x, int y, boolean shift);
	}

	public void dispatch(ExampleMouseEventListener handler)
	{
		if (mouseEventType == MOUSE_EVENT_TYPE_DOWN)
		{
			handler.onMouseDown(x, y, shift);
		}
		else if (mouseEventType == MOUSE_EVENT_TYPE_UP)
		{
			handler.onMouseUp(x, y, shift);
		}
		else if (mouseEventType == MOUSE_EVENT_TYPE_MOVE)
		{
			handler.onMouseMove(x, y, shift);
		}
	}
}
