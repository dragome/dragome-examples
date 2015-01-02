package com.dragome.examples;

import com.dragome.annotations.PageAlias;
import com.dragome.examples.model.Person;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualTextField;

@PageAlias(alias= "test1")
public class Test1Page extends GuiaVisualActivity
{
	public void build()
	{
		templateHandlingStrategy.loadMainTemplate("test1");

		Person person= new Person();
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(person::getGivenName, person::setGivenName).build();
		componentBuilder.bindTemplate("text").as(VisualLabel.class).toProperty(person::getGivenName, person::setGivenName).build();

	}
}
