package org.jbox2d.gwt.showcase.client.rendering;

import java.util.List;

import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.framework.ExampleKeyEvent;
import org.jbox2d.gwt.showcase.client.framework.ExampleMouseEvent;
import org.jbox2d.gwt.showcase.client.framework.ExampleRunner;
import org.jbox2d.gwt.showcase.client.framework.StartExampleEvent;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.KeyboardEvent;
import org.w3c.dom.events.MouseEvent;

import com.dragome.dispatcher.EventDispatcherImpl;
import com.dragome.html.dom.html5canvas.HTMLCanvasElement;
import com.dragome.templates.interfaces.Content;

public class ExampleView implements StartExampleEvent.StartExampleEventListener
{
	CanvasDebugDraw cameraRenderer;
	ExampleRunner exampleRunner;

	public ExampleView(Content<?> content)
	{
		cameraRenderer= new CanvasDebugDraw((Element)content.getValue());
		exampleRunner= new ExampleRunner(cameraRenderer);
		HTMLCanvasElement canvas= cameraRenderer.getCanvas();

		EventDispatcherImpl.setEventListener(canvas, new EventListener()
		{
			public void handleEvent(Event event)
			{
				if (event instanceof MouseEvent)
				{
					MouseEvent mouseEvent= (MouseEvent) event;

					if (event.getType().equals("mousedown"))
						onMouseDown(mouseEvent);
					else if (event.getType().equals("mouseup"))
						onMouseUp(mouseEvent);
					else if (event.getType().equals("mousemove"))
						onMouseMove(mouseEvent);
				}
				else if (event instanceof KeyboardEvent)
				{
					KeyboardEvent keyboardEvent= (KeyboardEvent) event;

					if (event.getType().equals("keypress"))
						onKeyPress(keyboardEvent);
					else if (event.getType().equals("keyup"))
						onKeyUp(keyboardEvent);
					else if (event.getType().equals("keydown"))
						onKeyDown(keyboardEvent);
				}
			}
		}, "mousedown", "mouseup", "mousemove", "keypress", "keyup", "keydown");
	}
	protected void onMouseUp(MouseEvent event)
	{
		event.preventDefault();
		ExampleMouseEvent eme= new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_UP, event.getClientX(), event.getClientY(), event.getShiftKey());
		eme.dispatch(exampleRunner);

	}
	protected void onMouseDown(MouseEvent event)
	{
		event.preventDefault();
		ExampleMouseEvent eme= new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_DOWN, event.getClientX(), event.getClientY(), event.getShiftKey());
		eme.dispatch(exampleRunner);
	}
	protected void onMouseMove(MouseEvent event)
	{
		event.preventDefault();
		ExampleMouseEvent eme= new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_MOVE, event.getClientX(), event.getClientY(), event.getShiftKey());
		eme.dispatch(exampleRunner);
	}

	protected void onKeyPress(KeyboardEvent keyboardEvent)
	{
		keyboardEvent.preventDefault();
		ExampleKeyEvent eme= new ExampleKeyEvent(ExampleKeyEvent.KEY_EVENT_TYPE_PRESS, keyboardEvent.getKeyIdentifier().charAt(0));
		eme.dispatch(exampleRunner);
	}

	protected void onKeyDown(KeyboardEvent keyboardEvent)
	{
		keyboardEvent.preventDefault();
		ExampleKeyEvent eme= new ExampleKeyEvent(ExampleKeyEvent.KEY_EVENT_TYPE_DOWN, keyboardEvent.getKeyIdentifier().charAt(0));
		eme.dispatch(exampleRunner);
	}
	protected void onKeyUp(KeyboardEvent keyboardEvent)
	{
		keyboardEvent.preventDefault();
		ExampleKeyEvent eme= new ExampleKeyEvent(ExampleKeyEvent.KEY_EVENT_TYPE_UP, keyboardEvent.getKeyIdentifier().charAt(0));
		eme.dispatch(exampleRunner);
	}

	public CanvasDebugDraw getCameraRenderer()
	{
		return cameraRenderer;
	}

	public void onStartExample(BaseExample example)
	{
		StringBuffer sb= new StringBuffer();
		List<String> instr= example.getInstructions();
		for (String instruaction : instr)
		{
			sb.append(instruaction).append("<br/>");
		}
		//		instructions.setHTML(sb.toString());

		exampleRunner.onStartExample(example);
	}
}
