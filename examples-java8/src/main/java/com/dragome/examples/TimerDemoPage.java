package com.dragome.examples;

import com.dragome.annotations.PageAlias;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualBounds;
import com.dragome.guia.components.interfaces.VisualButton;
import com.dragome.guia.components.interfaces.VisualComponent;
import com.dragome.guia.components.interfaces.VisualImage;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.html.dom.Timer;

@PageAlias(alias= "timer-demo")
public class TimerDemoPage extends GuiaVisualActivity
{
	int dx= 1;
	int dy= 1;
	int speed= 0;
	Timer timer= new Timer();

	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("moveButton").as(VisualButton.class).onClick(() -> setSpeed(speed + 3)).build();
		componentBuilder.bindTemplate("pauseButton").as(VisualButton.class).onClick(() -> setSpeed(speed - 3)).build();
		componentBuilder.bindTemplate("speed").as(VisualLabel.class).to(this::getSpeed).build();
		VisualImage image= componentBuilder.bindTemplate("ball-image").as(VisualImage.class).build();
		moveComponent(image);
	}

	public void moveComponent(VisualComponent component)
	{
		VisualBounds bounds= component.getStyle().getBounds();
		bounds.setX(bounds.getX() + (dx*= bounds.getX() > 400 - speed || bounds.getX() < -3 + speed ? -1 : 1) * speed);
		bounds.setY(bounds.getY() + (dy*= bounds.getY() > 300 - speed || bounds.getY() < -3 + speed ? -1 : 1) * speed);
		timer.setTimeout(() -> moveComponent(component), 10);
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
