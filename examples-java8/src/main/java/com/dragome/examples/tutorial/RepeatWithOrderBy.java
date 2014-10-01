package com.dragome.examples.tutorial;

import java.util.Arrays;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.builders.ObservableList;
import com.dragome.forms.bindings.builders.Order;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualPanel;

@PageAlias(alias= "repeat-with-order-by")
public class RepeatWithOrderBy extends DragomeVisualActivity
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
