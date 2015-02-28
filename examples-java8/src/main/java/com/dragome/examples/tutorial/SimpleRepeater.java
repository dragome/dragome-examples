package com.dragome.examples.tutorial;

import java.util.Arrays;
import java.util.List;

import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "simple-repeater")
public class SimpleRepeater extends GuiaVisualActivity
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
