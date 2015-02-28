package com.dragome.examples.tutorial;

import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualTextField;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "simple-binding")
public class SimpleBinding extends GuiaVisualActivity
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
