package com.dragome.examples.tutorial;

import java.util.Arrays;

import com.dragome.annotations.PageAlias;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.builders.ObservableList;
import com.dragome.forms.bindings.builders.Order;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualButton;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualPanel;

@PageAlias(alias= "repeat-with-order-by")
public class RepeatWithOrderBy extends GuiaVisualActivity
{
	protected Order order= Order.ASC;

	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("input")
    		.as(VisualButton.class)
    		.onClick(() -> setOrder(getOrder().swap()))
    		.build();

		ObservableList<String> names= new ObservableList<String>(Arrays.asList("Maradona", "Bochini", "Ortega", "Veron"));
		
		componentBuilder.bindTemplate("row")
    		.as(VisualPanel.class)
    		.toList(names)
    		.orderBy(s -> s, () -> getOrder())
    		.repeat((name, builder) -> {
    			builder.bindTemplate("name")
        			.as(VisualLabel.class)
        			.to(() -> name)
        			.build();
    		});
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order= order;
	}
}
