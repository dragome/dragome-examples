package com.dragome.examples.animation;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.interfaces.VisualBounds;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.interfaces.VisualImage;
import com.dragome.model.interfaces.VisualLabel;

@PageAlias(alias= "timer-demo")
public class TimerDemoPage extends DragomeVisualActivity
{
    int dx= 1;
    int dy= 1;
    int speed= 0;
    Timer timer= new Timer();

    public void build()
    {
	ComponentBuilder<?> componentBuilder= new ComponentBuilder<>(mainPanel, this);

	componentBuilder.bindTemplate("moveButton").as(VisualButton.class).onClick(() -> setSpeed(speed + 3)).build();
	componentBuilder.bindTemplate("pauseButton").as(VisualButton.class).onClick(() -> setSpeed(speed - 3)).build();
	componentBuilder.bindTemplate("speed").as(VisualLabel.class).to(this::getSpeed).build();
	VisualImage image= componentBuilder.bindTemplate("ball-image").as(VisualImage.class).build();
	timer.setInterval(() -> moveComponent(image), 10);
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
