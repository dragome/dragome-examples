package com.dragome.examples.todo.view;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.todo.model.Todo;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualTextField;

@PageAlias(alias= "test1")
public class Test1Page extends DragomeVisualActivity
{
	public void build()
	{
		templateHandlingStrategy.loadMainTemplate("test1");

		Todo todo= new Todo("hello world", true);
		ComponentBuilder<Todo> componentBuilder= new ComponentBuilder<Todo>(mainPanel, todo);

		componentBuilder.bindTemplate("input").as(VisualTextField.class).toProperty(Todo::getTitle, Todo::setTitle).build();
		componentBuilder.bindTemplate("text").as(VisualLabel.class).toProperty(Todo::getTitle, Todo::setTitle).build();

	}
}
