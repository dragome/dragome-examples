package com.dragome.examples;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.model.Person;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualTextField;

@PageAlias(alias= "test1")
public class Test1Page extends DragomeVisualActivity
{
    public void build()
    {
	templateHandlingStrategy.loadMainTemplate("test1");

	Person person= new Person();
	ComponentBuilder<Person> componentBuilder= new ComponentBuilder<Person>(mainPanel, person);

	componentBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(Person::getGivenName, Person::setGivenName).build();
	componentBuilder.bindTemplate("text").as(VisualLabel.class).toProperty(Person::getGivenName, Person::setGivenName).build();

    }
}