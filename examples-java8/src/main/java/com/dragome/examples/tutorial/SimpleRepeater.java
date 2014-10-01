package com.dragome.examples.tutorial;

import java.util.Arrays;
import java.util.List;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualPanel;

@PageAlias(alias= "simple-repeater")
public class SimpleRepeater extends DragomeVisualActivity
{
	public void build() 
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		List<String> names= Arrays.asList("Maradona", "Bochini", "Ortega");
		
		componentBuilder.bindTemplate("row")
    		.as(VisualPanel.class)
    		.toList(names)
    		.repeat((name, builder) -> {
    			
    			builder.bindTemplate("name")
    				.as(VisualLabel.class)
    				.to(() -> name)
    				.build();
    			
    		});
	}
}
