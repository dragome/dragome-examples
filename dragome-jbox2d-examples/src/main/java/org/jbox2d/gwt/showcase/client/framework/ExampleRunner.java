package org.jbox2d.gwt.showcase.client.framework;

import java.util.Date;
import java.util.logging.Logger;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;

import com.dragome.html.dom.Timer;

public class ExampleRunner implements StartExampleEvent.StartExampleEventListener, ExampleMouseEvent.ExampleMouseEventListener, ExampleKeyEvent.ExampleKeyEventListener
{
	private static final Logger logger= Logger.getLogger(ExampleRunner.class.getName());

	BaseExample runningExample;
	ShowcaseSettings settings= new ShowcaseSettings();
	Timer fpsTimer;
	Timer renderTimer;
	DebugDraw camera;

	long fpsStartTime;
	long fpsCounter;

	public ExampleRunner(DebugDraw debugDraw)
	{
		camera= debugDraw;
	}

	public void onStartExample(BaseExample example)
	{
		if (runningExample != null)
		{
			fpsTimer.clearInterval();
			renderTimer.clearInterval();
			fpsTimer= null;
			renderTimer= null;
		}
		runningExample= example;
		runningExample.init(camera);
		createAndLaunchFpsTimer();
		createAndLaunchRenderTimer();
	}

	private void stepSimulation()
	{
		runningExample.update(settings);
	}

	private void render()
	{
		camera.clear();
		runningExample.render(settings);
	}

	private void logFps()
	{
		long fpsEndTime= new Date().getTime();
		double fps= (double) fpsCounter / (double) (fpsEndTime - fpsStartTime) * 1000d;
		logger.info("fps: " + fps);
		fpsCounter= 0;
		fpsStartTime= new Date().getTime();
	}

	private void createAndLaunchRenderTimer()
	{
		renderTimer= new Timer().setInterval(new Runnable()
		{
			public void run()
			{
				stepSimulation();
				stepSimulation();
				stepSimulation();
				render();
				fpsCounter++;
			}
		}, 30);
	}

	private void createAndLaunchFpsTimer()
	{
		fpsCounter= 0;
		fpsStartTime= new Date().getTime();

		fpsTimer= new Timer().setInterval(new Runnable()
		{
			public void run()
			{
				logFps();
			}
		}, 1000);
	}

	public void onKeyPress(char theChar)
	{
		if (runningExample != null)
		{
			runningExample.queueKeyPressed(theChar, (int) theChar);
		}
	}

	public void onKeyDown(char theChar)
	{
		if (runningExample != null)
		{
			settings.keys[theChar]= true;
		}
	}

	public void onKeyUp(char theChar)
	{
		if (runningExample != null)
		{
			settings.keys[theChar]= false;
		}
	}

	@Override
	public void onMouseDown(int x, int y, boolean shift)
	{
		if (runningExample != null)
		{
			if (shift)
			{
				Vec2 vec2= createWorldVec(x, y);
				runningExample.queueShiftMouseDown(vec2);
			}
			else
			{
				Vec2 vec2= createWorldVec(x, y);
				runningExample.queueMouseDown(vec2);
			}
		}
	}

	@Override
	public void onMouseUp(int x, int y, boolean shift)
	{
		if (runningExample != null)
		{
			Vec2 vec2= createWorldVec(x, y);
			runningExample.queueMouseUp(vec2);
		}
	}

	@Override
	public void onMouseMove(int x, int y, boolean shift)
	{
		if (runningExample != null)
		{
			Vec2 vec2= createWorldVec(x, y);
			runningExample.queueMouseMove(vec2);
		}
	}

	private Vec2 createWorldVec(int x, int y)
	{
		Vec2 pos= new Vec2();
		pos.x= x;
		pos.y= y;
		camera.getScreenToWorldToOut(pos, pos);
		return pos;
	}
}
