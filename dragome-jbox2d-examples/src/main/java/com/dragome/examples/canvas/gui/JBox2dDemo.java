package com.dragome.examples.canvas.gui;

import org.jbox2d.gwt.showcase.client.example.BlobTest4;
import org.jbox2d.gwt.showcase.client.example.ExampleList;
import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.rendering.ExampleView;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.SimpleRenderer;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.interfaces.VisualComboBox;
import com.dragome.model.interfaces.VisualLabel;

@PageAlias(alias= "demo")
public class JBox2dDemo extends DragomeVisualActivity
{
	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		ExampleView exampleView= new ExampleView(mainTemplate.getChild("canvas").getContent());

		VisualComboBox<BaseExample> exampleList= new VisualComboBoxImpl<>();
		exampleList.setRenderer(new SimpleRenderer<BaseExample>()
		{
			public String render(BaseExample object)
			{
				return object.getTestName();
			}
		});
		exampleList.setAcceptableValues(ExampleList.tests);

		
		VisualLabel<String> label= componentBuilder.bindTemplate("instructions").as(VisualLabel.class).build();
		
		componentBuilder.bindTemplate("example-select").to(exampleList).onValueChange(() -> {
			try	
			{
				BaseExample newInstance= exampleList.getValue();
				exampleView.onStartExample(newInstance);
				label.setValue(newInstance.getInstructions().toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}).build();
		

		BlobTest4 example= new BlobTest4();
		exampleView.onStartExample(example);
		label.setValue(example.getInstructions().toString());
	}
}