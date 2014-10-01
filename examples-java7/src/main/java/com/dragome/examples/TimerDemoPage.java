package com.dragome.examples;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.client.value.ValueSource;
import com.dragome.html.dom.Timer;
import com.dragome.model.interfaces.VisualBounds;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.interfaces.VisualImage;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.listeners.ClickListener;

@PageAlias(alias= "timer-demo")
public class TimerDemoPage extends DragomeVisualActivity
{
	int dx= 1;
	int dy= 1;
	int speed= 0;
	Timer timer= new Timer();

	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("moveButton").as(VisualButton.class).onClick(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				setSpeed(speed + 3);
			}
		}).build();
		componentBuilder.bindTemplate("pauseButton").as(VisualButton.class).onClick(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				setSpeed(speed - 3);
			}
		}).build();
		componentBuilder.bindTemplate("speed").as(VisualLabel.class).to(new ValueSource<Integer>()
		{
			public Integer getValue()
			{
				return getSpeed();
			}
		}).build();
		final VisualImage image= componentBuilder.bindTemplate("ball-image").as(VisualImage.class).build();
		timer.setInterval(new Runnable()
		{
			public void run()
			{
				moveComponent(image);
			}
		}, 10);
	}
	public void moveComponent(VisualComponent component)
	{
		VisualBounds bounds= component.getStyle().getBounds();
		bounds.setX(bounds.getX() + (dx*= bounds.getX() > 400 - speed || bounds.getX() < -3 + speed ? -1 : 1) * speed);
		bounds.setY(bounds.getY() + (dy*= bounds.getY() > 300 - speed || bounds.getY() < -3 + speed ? -1 : 1) * speed);
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed= speed;
	}
}
