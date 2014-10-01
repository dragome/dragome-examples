package com.dragome.examples.tutorial;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualTextField;

@PageAlias(alias= "simple-binding")
public class SimpleBinding extends DragomeVisualActivity
{
	protected String text;
	
	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("textfield")
			.as(VisualTextField.class)
			.toProperty(this::getText, this::setText)
			.build();

		componentBuilder.bindTemplate("label")
    		.as(VisualLabel.class)
    		.toProperty(this::getText)
    		.build();
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text= text;
	}
}
