package com.dragome.examples.tutorial;

import java.util.Arrays;

import com.dragome.annotations.PageAlias;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.builders.ObservableList;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.components.interfaces.VisualTextField;

@PageAlias(alias= "repeat-with-filter")
public class RepeatWithFilter extends GuiaVisualActivity
{
	String filterText= "";

	public void build()
	{
		ObservableList<String> names= new ObservableList<String>(Arrays.asList("Maradona", "Bochini", "Ortega"));

		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("input")
			.as(VisualTextField.class)
			.toProperty(this::getFilterText, this::setFilterText)
			.build();
		
		componentBuilder.bindTemplate("row")
			.as(VisualPanel.class)
			.toList(names)
			.filter(() -> value -> value.startsWith(getFilterText()))
			.repeat((name, builder) -> {
				
				builder.bindTemplate("name")
    				.as(VisualLabel.class)
    				.to(() -> name)
    				.build();
			
		});
	}
	
	public String getFilterText()
	{
		return filterText;
	}

	public void setFilterText(String filterText)
	{
		this.filterText= filterText;
	}
}
